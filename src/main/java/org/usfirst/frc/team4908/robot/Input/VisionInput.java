package org.usfirst.frc.team4908.robot.Input;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4908.robot.Input.Vision.GripPipeline;

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
     * Df =
     * Ha =
     * Va =
     */

    private static final double Ha = 59.702;
    private static final double Va = 33.58289;
    private static final double Hf = 557.6000191;
    private static final double Vf = 596.5106429;

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

    public VisionInput()
    {
        cam = CameraServer.getInstance().startAutomaticCapture();
        cam.setResolution(640, 360);

        runner = new VisionThread(cam, new GripPipeline(), visionPipeline ->
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

    public double getTargetDistance()
    {
        return ((16.0/getWidth())*(centerX))/(Math.tan(Ha/2.0));
    }
}
