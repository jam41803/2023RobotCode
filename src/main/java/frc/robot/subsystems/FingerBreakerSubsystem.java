// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//TODO: Make controller toggle from position 0 to 180

public class FingerBreakerSubsystem extends SubsystemBase {
  private final CANSparkMax armMotor;
  private final RelativeEncoder armEncoder;
  private final SparkMaxPIDController armPIDController;
  private boolean toggleArm;
  /** Creates a new VArmSubsystem. */
  public FingerBreakerSubsystem() {
    armMotor = new CANSparkMax(Constants.fingerBreakerMotor, MotorType.kBrushless);
    armEncoder = armMotor.getEncoder();
    armPIDController = armMotor.getPIDController();
    toggleArm = false;
    armEncoder.setPosition(0); //Sets Position of Encoder
    armPIDController.setP(0);
    armPIDController.setI(0);
    armPIDController.setD(0);
    SmartDashboard.putNumber("PID P", armPIDController.getP());
    SmartDashboard.putNumber("PID I", armPIDController.getI());
    SmartDashboard.putNumber("PID D", armPIDController.getD());
    SmartDashboard.putBoolean("PID Button", false);
    armMotor.setSmartCurrentLimit(40);
    armMotor.setOpenLoopRampRate(0.2);
    armMotor.enableVoltageCompensation(11);
  }

  @Override
  public void periodic() {
    if (SmartDashboard.getBoolean("PID Button", false)) {
      armPIDController.setP(SmartDashboard.getNumber("PID P", armPIDController.getP()));
      armPIDController.setI(SmartDashboard.getNumber("PID I", armPIDController.getI()));
      armPIDController.setD(SmartDashboard.getNumber("PID D", armPIDController.getD()));
    }
    SmartDashboard.putBoolean("toggleArm", toggleArm);
    SmartDashboard.putNumber("FB Encoder Pos", armEncoder.getPosition());
  }

  public void toggleArm() {
    toggleArm = !toggleArm; //Toggle boolean
    if (toggleArm) {
      armPIDController.setReference(Constants.fingerBreakerReferenceA, ControlType.kPosition);
    } else {
      armPIDController.setReference(Constants.fingerBreakerReferenceB, ControlType.kPosition);
    }
    
  }
}
