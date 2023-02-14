// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;

public class WristSubsystem extends SubsystemBase {
  TalonFX rizzmotor = new WPI_TalonFX(WristConstants.WRIST_MOTOR);

  public WristSubsystem() {
    rizzmotor.configFactoryDefault();
    rizzmotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("wrist encoder", getWristEncoder());
  }

  public void turnWrist(double rotation){
    rizzmotor.set(ControlMode.PercentOutput, rotation);
  }

  public double getWristEncoder() {
    return rizzmotor.getSelectedSensorPosition();
  }
}
