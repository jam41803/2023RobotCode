// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private final Joystick driverJoystick;
  private final DifferentialDrive differentialDrive;
  private final CANSparkMax[] sparkMax;

  public DriveSubsystem(Joystick joystick) {
    driverJoystick = joystick;
    sparkMax = new CANSparkMax[4];

    //Controllers
    sparkMax[Constants.frontLeftIndex] =
  new CANSparkMax(Constants.frontLeftWheel, MotorType.kBrushless);
    sparkMax[Constants.backLeftIndex] =
  new CANSparkMax(Constants.backLeftWheel, MotorType.kBrushless);
    sparkMax[Constants.frontRightIndex] =
  new CANSparkMax(Constants.frontRightWheel, MotorType.kBrushless);
    sparkMax[Constants.backRightIndex] =
  new CANSparkMax(Constants.backRightWheel, MotorType.kBrushless);
    //Making them mirror each other
    sparkMax[Constants.backLeftIndex].follow(
    sparkMax[Constants.frontLeftIndex]);
    sparkMax[Constants.backRightIndex].follow(
    sparkMax[Constants.frontRightIndex]);
    //Inverting Motors
    sparkMax[Constants.frontRightIndex].setInverted(true);

  differentialDrive = new DifferentialDrive(
    sparkMax[Constants.frontLeftIndex],
    sparkMax[Constants.frontRightIndex]);
  }

  public void arcadeDrive() {
    differentialDrive.arcadeDrive(
        driverJoystick.getRawAxis(Constants.leftJoystickY),
        driverJoystick.getRawAxis(Constants.rightJoystickX));
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
