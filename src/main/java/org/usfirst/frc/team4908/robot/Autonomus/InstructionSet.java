package org.usfirst.frc.team4908.robot.Autonomus;

import org.usfirst.frc.team4908.robot.Autonomus.Commands.ICommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class InstructionSet {

    private ArrayList<ICommand> instructionsList;
    private boolean isRunnable;


    /**
     * This construction will use a file to get the instructions
     */
    public InstructionSet(File file) {
        importFile(file);

    }

    /**
     * This constructor will be the default one and will wait until you add a commnad to make runnable
     */

    public InstructionSet() {
        isRunnable = false;

    }

    //region Commands

    /**
     * This will take a text file and turn it into command code for AutoCommand
     */
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


    /**
     * This class will add a command to the instruction array list!
     */
    public void addInstruction(ICommand command) {

        instructionsList.add(command);

        if (!isRunnable)    //we now know that a instruction was added and we can more on in life!
            isRunnable = true;
    }


    /**
     * This class will add a command to the instruction array list!
     */
    public void addInstruction(String str_command) {

        //TODO: Dissect the string into a command!

        //instructionsList.add(new ICommand(0, ""));

        if (!isRunnable)    //we now know that a instruction was added and we can do more on in life!
            isRunnable = true;
    }

    //endregion

    //region Execution
    //So after the instructionList is populated now we need to run each command.

    public void runCommand(int index) {
        instructionsList.get(index).execute();
    }

    public ICommand getCommand(int index) {
        return instructionsList.get(index);
    }


    //endregion






}
