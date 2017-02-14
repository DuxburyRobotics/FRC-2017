package org.usfirst.frc.team4908.robot.Autonomous;

import org.usfirst.frc.team4908.robot.Autonomous.Commands.ICommand;
import org.usfirst.frc.team4908.robot.SubSystems.RobotComponents;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Sequence {

    private ArrayList<ICommand> instructionsList;
    private boolean isRunnable;


    /**
     * This constructor will be the default one and will wait until you add a commnad to make runnable
     */

    public Sequence() {
        isRunnable = false;

        instructionsList = new ArrayList();
    }

    //region Commands

    /**
     * This class will add a command to the instruction array list!
     */
    public void addInstruction(ICommand command) {

        instructionsList.add(command);

        if (!isRunnable)    //we now know that a instruction was added and we can move on in life!
            isRunnable = true;
    }

    public void finished(int index) {
        instructionsList.get(index).finish();
    }

    public ICommand getCommand(int index) {
        return instructionsList.get(index);
    }


    //endregion

}
