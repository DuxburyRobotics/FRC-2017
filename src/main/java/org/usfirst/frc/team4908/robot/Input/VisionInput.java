package org.usfirst.frc.team4908.robot.Input;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4908.robot.Input.Vision.GripPipeline;
import java.io.File;

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
    			0.0,    // 0ft
    			10.0,   // 1ft
    			20.0,   // 2ft
    			30.0,   // 3ft
    			40.0,   // 4ft
    			50.0,   // 5ft
    			60.0,   // 6ft
    			70.0,   // 7ft
    			80.0,   // 8ft
    			90.0,   // 9ft
    			100.0,  // 10ft
    			110.0,  // 11ft
    			120.0,  // 12ft
    			130.0,  // 13ft
    			140.0,  // 14ft
    		};
    
    private int closerDistance;
    private int fartherDistance;

    private double xAngle = Math.tan(Math.toRadians(Ha/2.0));
    
    public VisionInput(DriverInput di)
    {
    	/*
        new Thread(() ->
        {
            System.out.println("Here1");
            GripPipeline pipeline = new GripPipeline();

            camera = CameraServer.getInstance().addAxisCamera("10.49.8.12");
            camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

            CvSink cvSink = CameraServer.getInstance().getVideo();
            cvSink.setSource(camera);

            Mat source = new Mat();

            while(!Thread.interrupted())
            {
            	System.out.println("2-.5");
            	
                synchronized (imgLock)
                {
                	System.out.println("no idea");
            	
            	if(di.getShooterButton())
            	{
            		System.out.println("Here2");
            		cvSink.grabFrame(source);
            		pipeline.process(source);

            		long frameTime = cvSink.grabFrame(source);

            		if(frameTime == 0L)
            		{
            			String error = cvSink.getError();
            			DriverStation.reportError(error, true);
            		}
            		else
            		{
            			pipeline.process(source);

            			Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            			synchronized (imgLock)
            			{
            				centerX = r.x + (r.width / 2.0);
            				centerY = r.x + (r.height / 2.0);
            				width = r.width;
            				height = r.height;
            				area = r.area();

            				//System.out.println(r.width + "\t\t" + r.height + "\t\t" + r.area() + "\t\t" + centerY);
            			}
            		}
            	}
                }
            
            	try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        }).start();
		*/


        /*
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
					Thread.sleep(20);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }            
        });
        
        runner.start();
    
		*/
    }
    
    public void calc()
    {
    	
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
