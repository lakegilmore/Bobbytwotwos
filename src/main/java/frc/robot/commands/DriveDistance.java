// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {

  private double m_distance;
  private double m_LeftSpeed;
  private double m_RightSpeed;
  private DriveTrain m_drivetrain;

  public DriveDistance(double distance, double leftSpeed, double rightSpeed, DriveTrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_distance = distance;
    m_LeftSpeed = leftSpeed;
  m_RightSpeed = rightSpeed;
      m_drivetrain = subsystem;
    addRequirements(m_drivetrain);
   
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  //  m_drivetrain.my_resetLeftEncoder();
    m_drivetrain.my_resetRightEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    m_drivetrain.my_DriveTank(m_LeftSpeed, m_RightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.my_DriveTank(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   
    return (Math.abs(m_drivetrain.my_getRight_Distance_Inches())) >= m_distance;
  }
}
