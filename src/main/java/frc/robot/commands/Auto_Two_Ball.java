// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto_Two_Ball extends SequentialCommandGroup {
  /** Creates a new Auto_Two_Ball. */
  public Auto_Two_Ball(Shooter m_shooter, Tower m_tower, DriveTrain m_driveTrain, Intake m_intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
                parallel(new ShooterRun(1, m_shooter).withTimeout(5),
                        sequence(new WaitCommand(2),
                                new TowerRun(1, m_tower).withTimeout(3)
                                )     
                        ),
                new ParallelDeadlineGroup(new DriveDistance(90, -.75, -.75, m_driveTrain), 
                                          new InakeRun(1, m_intake),
                                          new TowerRun(.7, m_tower)
                                          ),
                new TowerRun(-.5, m_tower).withTimeout(.5),
                parallel(new DriveDistance(70, .5, .5, m_driveTrain),
                          parallel(new ShooterRun(1, m_shooter).withTimeout(5),
                                  sequence(new WaitCommand(2),
                                            new TowerRun(1, m_tower)
                                          )
                                  ) 
                      )       
               


    );
  }
}
