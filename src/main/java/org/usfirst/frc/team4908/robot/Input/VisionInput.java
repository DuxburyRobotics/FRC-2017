package org.usfirst.frc.team4908.robot.Input;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.opencv.core.Mat;
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
    
    private double cameraBoilerHeightDiffernce = 37.0;
    private double cameraOffset = 6.0;

    private static final double C_X = IMG_WIDTH/2 - 0.5;
    private static final double C_Y = IMG_HEIGHT/2 - 0.5;

    private AxisCamera camera;
    
    private double[] distanceTable = 
    		{
    			0.0,
    			10.0,
    			20.0,
    			30.0,
    			40.0,
    			50.0,
    			60.0,
    			70.0,
    			80.0,
    			90.0,
    			100.0,
    			110.0,
    			120.0,
    			130.0,
    			140.0, 
    		};
    
    private int closerDistance;
    private int fartherDistance;
    private int count = 0;
    private File distanceFile;
    
    private double xAngle = Math.tan(Math.toRadians(Ha/2.0));
    
    public VisionInput(DriverInput di)
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

                    //System.out.println(r.width + "\t\t" + r.height + "\t\t" + r.area() + "\t\t" + centerY);
                }
            
                try {
					//Thread.sleep(20);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }            
        });
                
        /*
        distanceFile = new File("Distances.txt");
        
        try
        {
        	Scanner readFile = new Scanner(distanceFile);
        
        	while(readFile.hasNext())
        	{
        		distanceTable[count] = readFile.nextDouble();
        		count++;
        		System.out.println(count + "\t" +distanceTable[count]);
        	}
        }
        catch(FileNotFoundException e)
        {
        	System.out.println("rip no file");
        }
        */
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
        return Math.toDegrees(Math.atan((centerX-C_X) / Hf));
    }

    public double getTargetDistanceInches()
    {	
    	double inchesPerPixel = 16.0/getWidth();
    	
    	double diagonalDistance = ((inchesPerPixel*(IMG_WIDTH/2.0))/xAngle);
        	
    	return Math.sqrt(Math.pow(diagonalDistance, 2) - Math.pow(cameraBoilerHeightDiffernce, 2)) - cameraOffset;
    	
        //return Math.sqrt(Math.abs(Math.pow(((inchesPerPixel*centerX)/xAngle), 2) - Math.pow(37.0, 2))) - 6.0;
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
    
    
    public void start()
    {

    }
    
	public void stop()
    {
    	runner.destroy();
    }
}
