// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//TODO: Make controller toggle from position 0 to 180

public class VArmSubsystem extends SubsystemBase {
  private final CANSparkMax armMotor;
  private final RelativeEncoder armEncoder;
  private final SparkMaxPIDController armPIDController;
  private boolean toggleArm;
  /** Creates a new VArmSubsystem. */
  public VArmSubsystem() {
    armMotor = new CANSparkMax(Constants.verticalArmMotor, MotorType.kBrushless);
    armEncoder = armMotor.getEncoder();
    armPIDController = armMotor.getPIDController();
    toggleArm = false;
    armEncoder.setPosition(0); //Sets Position of Encoder
    armPIDController.setP(0, 0);
    armPIDController.setI(0, 0);
    armPIDController.setD(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void toggleArm() {
    toggleArm = !toggleArm; //Toggle boolean
    if (toggleArm) {
      armPIDController.setReference(0, ControlType.kPosition, 0);
    } else {
      armPIDController.setReference(0, ControlType.kPosition, 0);
    }
    
  }
}
