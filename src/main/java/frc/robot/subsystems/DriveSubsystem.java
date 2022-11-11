// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private final Joystick driverJoystick;
  private final DifferentialDrive differentialDrive;
  private final CANSparkMax[] motors;
  private double[] velocity;


  public DriveSubsystem(Joystick joystick) {
    driverJoystick = joystick;
    motors = new CANSparkMax[4];
    //Controllers
    motors[Constants.frontLeftIndex] =
  new CANSparkMax(Constants.frontLeftMotor, MotorType.kBrushless);
    motors[Constants.backLeftIndex] =
  new CANSparkMax(Constants.backLeftMotor, MotorType.kBrushless);
    motors[Constants.frontRightIndex] =
  new CANSparkMax(Constants.frontRightMotor, MotorType.kBrushless);
    motors[Constants.backRightIndex] =
  new CANSparkMax(Constants.backRightMotor, MotorType.kBrushless);

    //Making them mirror each other
    motors[Constants.backLeftIndex].follow(
    motors[Constants.frontLeftIndex]);
    motors[Constants.backRightIndex].follow(
    motors[Constants.frontRightIndex]);

    //set limits on motors
    for (CANSparkMax canSparkMax : motors) {
      canSparkMax.setOpenLoopRampRate(Constants.openLoopRampRate);
      canSparkMax.setSmartCurrentLimit(Constants.smartCurrentLimit);
    }

    //Inverting Motors
    motors[Constants.frontRightIndex].setInverted(true);



  differentialDrive = new DifferentialDrive( //Differential Drive
    motors[Constants.frontLeftIndex],
    motors[Constants.frontRightIndex]);
  }

  public void arcadeDrive() { // Arcade Drive
    differentialDrive.arcadeDrive(
        driverJoystick.getRawAxis(Constants.leftJoystickY) * -1, //Y axis was inverted
        driverJoystick.getRawAxis(Constants.rightJoystickX));
  }

  @Override
  public void periodic() {
    int i = 0;
    for(CANSparkMax canSparkMax : motors) {
       velocity[i++] = canSparkMax.getEncoder().getVelocity();
    }
  }

  public void initSmartBoard() {
    SmartDashboard.putNumberArray("Motor RPM", velocity);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
