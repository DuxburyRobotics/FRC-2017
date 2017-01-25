Classes:

- ISubSystem
  - This is a interface class with calculate() and disable()
  - Climb
    - Controls the climbing motors
  - Drive
    - Controls the driving system and motors
  - Intake
    - Controls the intake motors and system
  - Shooter
  - Controls the shooter and motors. 
- RobotOutput
  - Takes evreything in ISubSystem and outputs it directly to the motors
- TeleOpCommand
  - This is the function that will be cald evrey 20ms in TeleOp
