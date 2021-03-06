package org.usfirst.frc.team4908.robot.Autonomous;

import org.usfirst.frc.team4908.robot.Autonomous.Commands.*;
import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

/**
 * Created by kyleknobloch
 */
public class AutoCommand {

    private RobotOutput ro;
    private SensorInput si;
    private VisionInput vi;
    
    private int instructionSequence;
    private int commandIndex;
    private boolean firstRun;
    private boolean isFinished;
    private ArrayList<Sequence> autoInstructionList;
    private Sequence sequence;
    
    private final int default_auto = 1;

    private double startTime;
    private double time;


    //TODO: Figure out a way to set sequence from the DuxDash
    //private String[] autoList = {"Nothing", "Cross BL", "CG", "CG and BL", "CG, shoot and BL", "shot and BL"};

    private ArrayList<String> autoSequ;

    //region Constructors

    /**
     * You must ALWAYS pass in the rc or else nothing will move. Make sure it's the one from the Robot class
     * so that you don't anciently have two in the Robot (because that would be weird).
     */
    public AutoCommand(RobotOutput ro, SensorInput si, VisionInput vi) {
        this.instructionSequence = -1;
        this.commandIndex = 0;
        this.firstRun = true;
        this.ro = ro;
        this.si = si;
        this.vi = vi;
        this.autoInstructionList = new ArrayList<Sequence>();
        sequence = null;
        isFinished = false;
        autoSequ = new ArrayList<String>(); 

        generateDefaultAuto();
    }

    //endregion

    public void init()
    {
        startTime = System.currentTimeMillis()/1000.0;
        this.firstRun = true;
        
        //sequence = autoInstructionList.get(1);
    }


    /**
     * This function is called periodically from the Robot class when in auto mode. This class will check to see where the
     * InstructionSet is and when to move onto the next command.
     *
     * NOTE: This is designed to change thc command when the first command has decided to call finish() and move to the
     * next command. Until then this will keep calling update() in ICommand.
     */
    public void periodic() {
        if(!isFinished) {
            if (firstRun) {      //this is the first time init() is called
                firstRun = false;
                commandIndex = 0;

                /**
                 * This is where the code to get the auto Index to run would be
                 */


                if (sequence == null && instructionSequence >= 0) //if sequence was empty and there is a instructionSequence we want to use
                {
                    sequence = autoInstructionList.get(instructionSequence);
                    System.out.println("\nAUTOCOMMAND:: We are using the Sequence: " + sequence + " for auto today!!! \n");
                    System.out.println("\nAUTOCOMMAND:: We are using the Sequence: " + sequence + " for auto today!!! \n");

                } else {
                    sequence = autoInstructionList.get(default_auto); //just get the default one that does nothing since there was nothing set to use
                    //This means that Auto did not know what to run and is using default one.
                    System.out.println("AUTOCOMMAND:: There was no instructionSet defined. Will use default one that does nothing (index 0)\n" +
                            "AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n");

                } //else
            } //if

            if (sequence.getCommand(commandIndex).isFirstRun())
                sequence.getCommand(commandIndex).firstRun(); //set command firstRun to false and run init


            //now run update
            sequence.getCommand(commandIndex).update(System.currentTimeMillis() / 1000.0 - startTime);

            //check if finished
            if (sequence.getCommand(commandIndex).isFinished()) {
                sequence.getCommand(commandIndex).finish(); //call finish function to end command
                if (commandIndex < sequence.getCommandListSize())
                    commandIndex++; //move to next command
                else //there's no more instructions in the list!
                    isFinished = true;
            }



        } //isFinished
    } //periodic()


    /**
     * Generate pre-defined InstructionsSets here.
     * This always makes sure that the first one is the default one.
     */
    private void generateDefaultAuto() {

        /**
         * index: 0
         *
         * Actions:
         * None.
         */
        Sequence default0 = new Sequence();
        autoSequ.add("NOTHING (default)");

        default0.addInstruction(new ICommand("do_Nothing", ro, si, vi) {
            @Override
            public void firstRun() {
                //do nothing
            }

            public void init() {}
        });


        /**
         * index: 1 center gear + baseline
         *
         * Actions:
         * low gear
         * drive 8 feet // OL
         * Drop intake
         */
        Sequence centerGearBaseline = new Sequence();
        autoSequ.add("Center Gear with Baseline");

        centerGearBaseline.addInstruction(new AutoOpenDrive(ro, si, vi, 165, -0.6, true)); //TODO: Check distences
        centerGearBaseline.addInstruction(new AutoGearDeposit(ro, si, vi));
        centerGearBaseline.addInstruction(new AutoOpenDrive(ro, si, vi, 1500, 0.0));
        

        /**
         * Index x
         * 
         * Actions:
         * OpenDrive 150, .75
         * OpenDrive 1000, 0
         */
        Sequence openBaseLine = new Sequence();
        autoSequ.add("Open Baseline");
        
        openBaseLine.addInstruction(new AutoOpenDrive(ro, si, vi, 250.0, 1.0));
        openBaseLine.addInstruction(new AutoOpenDrive(ro, si, vi, 700.0, 0.0));
        
        
        /**
         * Autonomous 5: 10 kpa and baseline

         SETUP: bumpers against wall with intake forward, back of bumper aligned with alliance station near boiler

         Low gear

         Drop intake

         Drive forward 2ft

         Rotate 90 degrees left (BLUE) or right (RED)

         Vision track, shoot

         Rotate to angle 0

         Drive forward 10 ft

         Autonomous 6/7: Gear at left/right positions

         SETUP: bumpers against wall with intake forward
         */
        
        Sequence tenKpaBaseline = new Sequence();
        autoSequ.add("Ten kpa Baseline");
        tenKpaBaseline.addInstruction(new AutoIntake(ro, si, vi));
        tenKpaBaseline.addInstruction(new AutoRemoveBalls(ro, si, vi));
        tenKpaBaseline.addInstruction(new AutoOpenDrive(ro, si, vi, 25, 0.5));
        tenKpaBaseline.addInstruction(new AutoRotate(ro, si, vi, 90.0));
        tenKpaBaseline.addInstruction(new AutoShooter(ro, si, vi));
        

        Sequence sideGear = new Sequence();
        
        sideGear.addInstruction(new AutoOpenDrive(ro, si, vi, 125, -0.75, true));
        //if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red)
        	centerGearBaseline.addInstruction(new AutoOpenRotate(ro, si, vi, 62, 0.5));
        //else if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue)
        	//centerGearBaseline.addInstruction(new AutoOpenRotate(ro, si, vi, 62, -0.5));
        sideGear.addInstruction(new AutoOpenDrive(ro, si, vi, 100, -0.5, true));
        sideGear.addInstruction(new AutoGearDeposit(ro, si, vi));
        
        /**
         * Add the Sequences into the array
         */
        autoInstructionList.add(default0);
        autoInstructionList.add(centerGearBaseline);
        autoInstructionList.add(openBaseLine);
        autoInstructionList.add(tenKpaBaseline);
        autoInstructionList.add(sideGear);


    }


    /**
     * This must be called before we init auto.
     */
    public void setInstructionSequence(int instructionSequence) {
        this.instructionSequence = instructionSequence;
        //this.sequence = autoInstructionList.get(instructionSequence);

    }

    public void setInstructionSet(Sequence instructionSet) {
        this.sequence = instructionSet;
        this.instructionSequence = 0; //this value is not useful as the sequence was not in the array list in the first place.

    }

    public ArrayList<String> getAutoSequ() {
        return autoSequ;
    }

    //region depreciated

        /*
    public AutoCommand() {
        this.instructionSequence = 0;
        this.commandIndex = 0;
        this.firstRun = true;
        this.autoInstructionList = new ArrayList<InstructionSet>();

    }
    */

    /*
    /**
     * This will take a text file and turn it into command code for AutoCommand

    public void importFile(File file) {
        Scanner scanner = null;


        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.out.print("When trying to create a InstructionSet,\nThis file did not work. Here's what I can gather. \nFile Path: " + file.getPath() +
                    "\nAbsolute Path: " + file.getAbsolutePath() +"\nException e: \n" + e);
        }


        //if this is not runnable we set it here.
        if (scanner == null)    //This means that this is not a valid option for a
            isRunnable = false;

        else {                  //Okay, so the scanner has _something_ so we will work with it.
            isRunnable = true;

            while (scanner.hasNextLine()) {     //for each line the while loop runs
                String working = scanner.nextLine(); //gets the next line
                ICommand cmd = null;
                //cmd = new ICommand(0, "");

                //TODO: Dissect the String to a code!


                addInstruction(cmd);
            }

        }
    }
     */


    /**
     * This function is called periodically from the Robot class when in auto mode. This class will check to see where the
     * InstructionSet is and when to move onto the next command.
     *
     * NOTE: This is designed for a time-based system and NOT a sensor based system
     *
     public void periodic() {
     if (firstRun) {
     lastTime = System.nanoTime();
     sequence = autoInstructionList.get(instructionSequence);
     commandIndex = 0;
     firstRun = false;
     }

     sequence.runCommand(commandIndex);
     incrementTime(sequence.getCommand(commandIndex).getWaitTime());


     } */
    /**
     * Wait the secondsToWait until moving the command up.
     * @param secondsToWait double seconds to waiy
     *
    private void incrementTime(double secondsToWait) {
    if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000)) {
    lastTime = System.nanoTime();
    commandIndex++;
    }

    }*/
    //endregion
}
