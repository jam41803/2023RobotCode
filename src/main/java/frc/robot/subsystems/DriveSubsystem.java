// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.common.hardware.MotorController;


public class DriveSubsystem extends SubsystemBase {
  private final Joystick driverJoystick;
  private final DifferentialDrive differentialDrive;
  private final MotorController[] motorControllers;

  public DriveSubsystem(Joystick joystick) {
    driverJoystick = joystick;
    motorControllers = new MotorController[4];

    //Controllers
    motorControllers[Constants.frontLeftIndex] =
  new MotorController("DriveBase Left Front", Constants.frontLeftWheel);
    motorControllers[Constants.backLeftIndex] =
  new MotorController("DriveBase Left Rear", Constants.backLeftWheel);
    motorControllers[Constants.frontRightIndex] =
  new MotorController("DriveBase Right Front", Constants.frontRightWheel);
    motorControllers[Constants.backRightIndex] =
  new MotorController("DriveBase Right Rear", Constants.backRightWheel);
    //Making them mirror each other
    motorControllers[Constants.backLeftIndex].follow(
    motorControllers[Constants.frontLeftIndex]);
    motorControllers[Constants.backRightIndex].follow(
    motorControllers[Constants.frontRightIndex]);
    //Inverting Motors
    motorControllers[Constants.frontRightIndex].setInverted(true);

  differentialDrive = new DifferentialDrive(
    motorControllers[Constants.frontLeftIndex],
    motorControllers[Constants.frontRightIndex]);
  }

  public void arcadeDrive() {
    differentialDrive.arcadeDrive(
        driverJoystick.getRawAxis(Constants.leftJoystickY),
        driverJoystick.getRawAxis(Constants.rightJoystickX));
  }

  public void stop() {
    for (MotorController motorController : motorControllers) {
      motorController.stopMotor();
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
