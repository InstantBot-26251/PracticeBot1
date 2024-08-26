package org.firstinspires.ftc.teamcode.vyan;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PIDFController;

@Config
public class LiftVyan {

    DcMotorEx Lift;
    public static double kp = 1.0;
    public static double ki = 0.0;
    public static double kd = 0.0;
    double output;
    PIDFController pidf = new PIDFController(kp, ki, kd,1);

    public LiftVyan(HardwareMap hardwareMap) {

        Lift = hardwareMap.get(DcMotorEx.class, "lift");
        Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower() {
        output = pidf.calculate(getEncoderValue());
        Lift.setPower(output/2000.0);
    }
    public void toPoint (double position) {
        pidf.setSetPoint(position);
    }
    public double getEncoderValue() {
        return Lift.getCurrentPosition();

    }
}