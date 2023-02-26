// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.utils.SetPointSupplier;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetOtherLevelsCommand extends SequentialCommandGroup {
  /** I HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDSI HATE NAMING COMMANDS */
  public SetOtherLevelsCommand(ElevatorSubsystem elevator, ArmSubsystem arm, double setpoint) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetArmExtensionCommand(0, arm),
      new SetElevatorPositionCommand(elevator, setpoint, 0.00006, 0.0, 0.0),
      new LockElevatorCommand(new SetPointSupplier(), elevator)
    );

    addRequirements(elevator, arm);
  }
}
