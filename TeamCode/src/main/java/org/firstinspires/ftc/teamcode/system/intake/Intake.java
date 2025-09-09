package org.firstinspires.ftc.teamcode.system.intake;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.system.shooter.Shooter;
import org.firstinspires.ftc.teamcode.utility.RobotConstants;

import java.util.Arrays;
import java.util.List;

public class Intake {

//    public enum IntakeMode {
//
//
//    }

    // System OpMode
    private LinearOpMode opMode;

    // Intake Setting(s)


    // Define Hardware for subsystem
    private CRServo intakeLeft, intakeRight;
    private List<CRServo> listMotorIntake;

    // Constructor
    public Intake(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {

        // Telemetry - Initialize - Start
        opMode.telemetry.addData(">", "------------------------------------");
        opMode.telemetry.addData(">", "System: Intake");
        opMode.telemetry.addData(">", "------------------------------------");
        opMode.telemetry.update();

        // Define and Initialize Motor(s)
        intakeLeft = opMode.hardwareMap.get(CRServo.class, RobotConstants.HardwareConfiguration.kLabelIntakeServoLeft);
        intakeRight = opMode.hardwareMap.get(CRServo.class, RobotConstants.HardwareConfiguration.kLabelIntakeServoRight);

//        listMotorIntake = Arrays.asList(intakeRight); //, intakeLeft);

        // Set common configuration for drive motor(s)
//        for (CRServo itemMotor : listMotorIntake) {
//
//            // clone motor configuration
//            MotorConfigurationType motorConfigurationType = itemMotor.getMotorType().clone();
//
//            // Set motor configuration properties
//            motorConfigurationType.setAchieveableMaxRPMFraction(RobotConstants.Intake.Configuration.kMotorAchievableMaxRpmFraction);
//
//            // Write out motor configuration to motor
//            itemMotor.setMotorType(motorConfigurationType);
//        }

        // Set Zero Power mode for drivetrain
//        setMotorZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        // Set Non-common motor configuration(s)
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeRight.setDirection(DcMotorSimple.Direction.FORWARD);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        // Telemetry - Initialize - End
        opMode.telemetry.addData(">", "------------------------------------");
        opMode.telemetry.addData(">", "System: Intake (Initialized)");
        opMode.telemetry.addData(">", "------------------------------------");
        opMode.telemetry.update();

    }

    // ----------------------------------------------
    // Action Methods
    // ----------------------------------------------

    public void activateIntake(String hardwareLabel, double setpoint) {

        switch (hardwareLabel) {
            case RobotConstants.HardwareConfiguration.kLabelIntakeServoLeft:
                intakeLeft.setPower(setpoint);
                break;

            case RobotConstants.HardwareConfiguration.kLabelIntakeServoRight:
                intakeRight.setPower(setpoint);
                break;
        }
    }

    public void deactivateIntake(String hardwareLabel) {
        double setpoint = 0;

        activateIntake(hardwareLabel, setpoint);
    }


    // ----------------------------------------------
    // Action Methods - Road Runner
    // ----------------------------------------------

    // Road Runner - Action - Activate Intake
    public class ActionActivateIntake implements Action {

        private String hardwareLabel;
        private double setpoint;

        // Action class constructor
        public ActionActivateIntake(String hardwareLabel, double setpoint) {
            this.hardwareLabel = hardwareLabel;
            this.setpoint = setpoint;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            activateIntake(hardwareLabel, setpoint);
            return false;
        }
    }

    public Action actionActivateIntake(String hardwareLabel, double setpoint) {

        return new ActionActivateIntake(hardwareLabel, setpoint);
    }


    // ----------------------------------------------
    // Get Methods
    // ----------------------------------------------

    public double getIntakePower(String hardwareLabel) {
        double outputPower;

        switch (hardwareLabel) {
            case RobotConstants.HardwareConfiguration.kLabelIntakeServoLeft:
                outputPower = intakeLeft.getPower();
                break;

            case RobotConstants.HardwareConfiguration.kLabelIntakeServoRight:
                outputPower = intakeRight.getPower();
                break;

            default:
                outputPower = 0;
        }

        return outputPower;
    }


    // ----------------------------------------------
    // Set Methods
    // ----------------------------------------------

    private void setMotorPower(double powerRight) { //, double powerLeft) {

        // Send calculated power to wheels
//        intakeLeft.setPower(powerLeft);
        intakeRight.setPower(powerRight);
    }




}
