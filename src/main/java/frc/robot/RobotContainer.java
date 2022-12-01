// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VArmSubsystem;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private DriveCommand driveCommand;
  private DriveSubsystem driveSubsystem;
  private VArmSubsystem vArmSubsystem;
  private Joystick joystick = new Joystick(Constants.driverControllerPort);
  private JoystickButton breakFingerButton = new JoystickButton(joystick, Constants.aButtonID);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureButtonBindings();
    initSubsystem();
  }




  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

   private void initSubsystem() {
      driveSubsystem = new DriveSubsystem(joystick);
      driveCommand = new DriveCommand(driveSubsystem);
      driveSubsystem.setDefaultCommand(driveCommand);

   }

  private void configureButtonBindings() {
    breakFingerButton.whenPressed(new InstantCommand(vArmSubsystem::toggleArm, vArmSubsystem));
  }

  public Command getAutonomousCommand() {
    return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
