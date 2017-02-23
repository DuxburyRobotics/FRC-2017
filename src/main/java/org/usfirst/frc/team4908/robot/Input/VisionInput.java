package org.usfirst.frc.team4908.robot.Input;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4908.robot.Input.Vision.GripPipeline;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bill
 *         $
 */
public class VisionInput
{
    /** CAMERA FoV's
     * HD 3000
     * Df = 68.5
     * Ha = 59.702
     * Va = 33.58289
     * Hf = 557.6000191
     * Vf = 596.5106429
     * 
     * Axis M1011
     * Df = 53.92533
     * Ha = 47
     * Va = 26.4375
     * Hf = 735.9496
     */

    private static final double Ha = 47;
    private static final double Va = 26.4375;
    private static final double Hf = 735.9496;
    //private static final double Vf = 596.5106429;

    private static final int IMG_WIDTH = 640;
    private static final int IMG_HEIGHT = 360;

    private final Object imgLock = new Object();

    private VisionThread runner;
    private UsbCamera cam;
    
    private double centerX;
    private double centerY;
    private double width;
    private double height;
    private double area;

    private static final double C_X = IMG_WIDTH/2 - 0.5;
    private static final double C_Y = IMG_HEIGHT/2 - 0.5;

    private AxisCamera camera;
    
    private double[] distanceTable;
    private int closerDistance;
    private int fartherDistance;
    private int count = 0;
    private File distanceFile;
    
    public VisionInput()
    {    	
    	camera = CameraServer.getInstance().addAxisCamera("10.49.8.12");
    	camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    	
        runner = new VisionThread(camera, new GripPipeline(), visionPipeline ->
        {
            if (!visionPipeline.filterContoursOutput().isEmpty())
            {
                Rect r = Imgproc.boundingRect(visionPipeline.filterContoursOutput().get(0));
                synchronized (imgLock)
                {
                    centerX = r.x + (r.width / 2);
                    centerY = r.x + (r.height / 2);
                    width = r.width;
                    height = r.height;
                    area = r.area();
                }
            }
        });

        runner.start();
	
        distanceTable = new double[15];
        distanceFile = new File("DistanceTables.txt");
        
        try
        {
        	Scanner readFile = new Scanner(distanceFile);
        
        	while(readFile.hasNext())
        	{
        		distanceTable[count] = readFile.nextDouble();
        		count++;
        	}
        }
        catch(FileNotFoundException e)
        {
        	System.out.println("rip no file");
        }
    }

    public double getCenterX()
    {
        return centerX;
    }

    public double getCenterY()
    {
        return centerY;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public double getArea()
    {
        return area;
    }

    public double getTargetRotation()
    {
        return Math.atan((centerX-C_X) / Hf);
    }

    public double getTargetDistanceInches()
    {	
        return Math.sqrt(Math.pow((((16.0/getWidth())*(centerX))/(Math.tan(Ha/2.0))), 2) - Math.pow(64.0, 2)) - 25.0;
    }
    
    public double getTargetSpeed(double distance)
    {
    	closerDistance = (int)distance;
    	fartherDistance = closerDistance + 1;
    	
    	try
    	{
    		return (distanceTable[closerDistance] * (fartherDistance-distance)
    				+ (distanceTable[fartherDistance] * (distance-closerDistance)));
    	}
    	catch( ArrayIndexOutOfBoundsException e)
    	{
    		return 75.0;
    	}	
    }
}
