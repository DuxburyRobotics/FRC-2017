package org.usfirst.frc.team4908.robot;

import com.ctre.CANTalon;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;
import edu.wpi.first.wpilibj.vision.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionRunner.Listener;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot 
{	
	
	private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 360;
	
	private VisionThread mynewTHread;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private double centerY = 0.0;
	
	private final Object imgLock = new Object();
	
	RobotDrive drive;
	
	SpeedController frontLeft;
	SpeedController rearLeft;
	SpeedController frontRight;
	SpeedController rearRight;
	
	Joystick joystick;
	
	UsbCamera camera;
	
	VideoSource visionSource;

	//GripPipeline pipeline;
	
	double turn;
	
	public void robotInit() 
	{
		frontLeft = new CANTalon(0);
		rearLeft = new CANTalon(1);
		frontRight = new CANTalon(2);
		rearRight = new CANTalon(3);
		
		drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		
		joystick = new Joystick(0);
		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	
		
		mynewTHread = new VisionThread((VideoSource) camera, new GripPipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) 
	        {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock)
	            {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	    });
		mynewTHread.start();
	}

	@Override
	public void autonomousInit() 
	{
		
	}

	@Override
	public void autonomousPeriodic() 
	{
		
	}

	@Override
	public void teleopPeriodic() 
	{
		turn = centerX - (IMG_WIDTH / 2);
		drive.arcadeDrive(0.0, (-turn/320.0)*0.5);
		
		
		System.out.println(turn);
	}
	
	@Override
	public void testPeriodic() 
	{
		
	}
}