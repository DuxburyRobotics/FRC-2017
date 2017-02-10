package org.usfirst.frc.team4908.robot.Autonomous;

import org.usfirst.frc.team4908.robot.Autonomous.Commands.AutoDrive;
import org.usfirst.frc.team4908.robot.Autonomous.Commands.AutoShooter;
import org.usfirst.frc.team4908.robot.Autonomous.Commands.ICommand;
import org.usfirst.frc.team4908.robot.SubSystems.RobotComponents;

import java.util.ArrayList;

/**
 * Created by kyleknobloch
 */
public class AutoCommand {

    private int instructionSequence;
    private RobotComponents rc;
    private int commandNumber;
    private boolean firstRun;
    private ArrayList<Sequence> autoInstructionList;
    private Sequence sequence;


    //TODO: Figure out a way to set sequence from the DuxDash

    //region Constructors

    /**
     * You must ALWAYS pass in the rc or else nothing will move. Make sure it's the one from the Robot class
     * so that you don't anciently have two in the Robot (because that would be weird).
     * @param rc the Class that controls the robot.
     */
    public AutoCommand(RobotComponents rc) {
        this.instructionSequence = 0;
        this.commandNumber = 0;
        this.firstRun = true;
        this.rc = rc;
        this.autoInstructionList = new ArrayList<Sequence>();
        generateDefaultAuto();
    }

    //endregion

    public void init() {
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
                sequence = autoInstructionList.get(instructionSequence);

            else {
                sequence = autoInstructionList.get(1); //just get the default one that does nothing since there was nothing set to use
                                                     //This means that Auto did not know what to run and is using default one.
                System.out.println("AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n" +
                        "AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n");

            } //else
        } //if

        if(sequence.getCommand(commandNumber).isFirstRun())
            sequence.getCommand(commandNumber).firstRun(); //set command firstRun to false and run init


        //now run update
        sequence.getCommand(commandNumber).update();

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

        Sequence default0 = new Sequence(rc);

        default0.addInstruction(new ICommand("do_Nothing", rc) {
            @Override
            public void firstRun() {
                //do nothing
            }
        });

        /**
         * index: 1
         *
         * Actions:
         * drive "5"
         */
        Sequence drive = new Sequence(rc);
        drive.addInstruction(new AutoDrive("drive", rc, 5.0));


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
        this.instructionSequence = -1; //this value is not useful as the sequence was not in the array list in the first place.

    }

    public RobotComponents getRc() {
        return rc;
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
