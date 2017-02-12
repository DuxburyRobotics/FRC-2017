package org.usfirst.frc.team4908.robot.Autonomous;

import org.usfirst.frc.team4908.robot.Autonomous.Commands.*;
import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.SubSystems.*;
import java.util.ArrayList;

/**
 * Created by kyleknobloch
 */
public class AutoCommand {

    private RobotOutput ro;
    private SensorInput si;

    private int instructionSequence;
    private int commandNumber;
    private boolean firstRun;
    private ArrayList<Sequence> autoInstructionList;
    private Sequence sequence;

    private double startTime;
    private double time;


    //TODO: Figure out a way to set sequence from the DuxDash

    //region Constructors

    /**
     * You must ALWAYS pass in the rc or else nothing will move. Make sure it's the one from the Robot class
     * so that you don't anciently have two in the Robot (because that would be weird).
     */
    public AutoCommand(RobotOutput ro, SensorInput si) {
        this.instructionSequence = 0;
        this.commandNumber = 0;
        this.firstRun = true;
        this.ro = ro;
        this.si = si;
        this.autoInstructionList = new ArrayList<Sequence>();

        generateDefaultAuto();
    }

    //endregion

    public void init()
    {
        startTime = System.currentTimeMillis()/1000.0;
        this.firstRun = true;
    }


    /**
     * This function is called periodically from the Robot class when in auto mode. This class will check to see where the
     * InstructionSet is and when to move onto the next command.
     *
     * NOTE: This is designed to change thc command when the first command has decided to call finish() and move to the
     * next command. Until then this will keep calling update() in ICommand.
     */
    public void periodic() {

        if(firstRun) {
            firstRun = false;

            if (sequence == null && instructionSequence >= 0) //if sequence was empty and there is a instructionSequence we want to use
            {
                sequence = autoInstructionList.get(instructionSequence);
                System.out.println("\nAUTOCOMMAND:: We are using the Sequence: " + sequence + " for auto today!!! \n");
                System.out.println("\nAUTOCOMMAND:: We are using the Sequence: " + sequence + " for auto today!!! \n");

            } else {
                sequence = autoInstructionList.get(1); //just get the default one that does nothing since there was nothing set to use
                                                     //This means that Auto did not know what to run and is using default one.
                System.out.println("AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n" +
                        "AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n");

            } //else
        } //if

        if(sequence.getCommand(commandNumber).isFirstRun())
            sequence.getCommand(commandNumber).firstRun(); //set command firstRun to false and run init


        //now run update
        sequence.getCommand(commandNumber).update(System.currentTimeMillis()/1000.0-startTime);

        //check if finished
        if (sequence.getCommand(commandNumber).isFinished()) {
            sequence.getCommand(commandNumber).finish(); //call finish function to end command
            commandNumber++; //move to next command
        }

    }


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

        default0.addInstruction(new ICommand("do_Nothing", ro, si) {
            @Override
            public void firstRun() {
                //do nothing
            }
        });


        /**
         * index: 1
         *
         * Actions:
         * drive "20"
         */
        Sequence drive = new Sequence();
        drive.addInstruction(new AutoDrive("drive", ro, si, 20.0));


        /**
         * Add the Sequences into the araw
         */
        autoInstructionList.add(default0);
        autoInstructionList.add(drive);
    }


    /**
     * This must be called before we init auto.
     */
    public void setInstructionSequence(int instructionSequence) {
        this.instructionSequence = instructionSequence;
        this.sequence = autoInstructionList.get(instructionSequence);

    }

    public void setInstructionSet(Sequence instructionSet) {
        this.sequence = instructionSet;
        this.instructionSequence = 0; //this value is not useful as the sequence was not in the array list in the first place.

    }


    //region depreciated

        /*
    public AutoCommand() {
        this.instructionSequence = 0;
        this.commandNumber = 0;
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
     commandNumber = 0;
     firstRun = false;
     }

     sequence.runCommand(commandNumber);
     incrementTime(sequence.getCommand(commandNumber).getWaitTime());


     } */
    /**
     * Wait the secondsToWait until moving the command up.
     * @param secondsToWait double seconds to waiy
     *
    private void incrementTime(double secondsToWait) {
    if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000)) {
    lastTime = System.nanoTime();
    commandNumber++;
    }

    }*/
    //endregion
}
