package org.usfirst.frc.team4908.robot.SubSystems;

/**
 * These are the default functions that _all_ SubSystems must contain
 */

public interface ISubSystem
{

    void calculate();

    void disable();

    RobotOutput getRo();
}
