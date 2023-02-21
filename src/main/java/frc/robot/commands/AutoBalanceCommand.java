// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoBalanceCommand extends PIDCommand {
  /** Creates a new AutoBalanceCommand. */

  private DrivetrainSubsystem drivetrain;

  public AutoBalanceCommand(DrivetrainSubsystem drivetrain, double speed) {
    super(
        // The controller that the command will use
        new PIDController(1, 0, 0.0),
        // This should return the measurement
        () -> drivetrain.getPitch(),
        // This should return the setpoint (can also be a constant)
        () -> drivetrain.getZeroAngle(),
        // This uses the output
        output -> {
          // Use the output here
          SmartDashboard.putNumber("output", output);
          if(Math.abs(output)>0.3) {
            output = (output/Math.abs(output)) * 0.3;
          }
          drivetrain.arcadeDrive(-output, 0);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(drivetrain);
    this.getController().setTolerance(1);
    this.drivetrain = drivetrain;
  }

  // Returns true when the command should end.

  @Override
  public void execute() {
    SmartDashboard.putNumber("balance error", this.getController().getPositionError());
    SmartDashboard.putNumber("balance set point", this.getController().getSetpoint());
    SmartDashboard.putNumber("balance  pitch", drivetrain.getPitch());
    super.execute();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
