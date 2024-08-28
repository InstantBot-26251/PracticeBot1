package org.firstinspires.ftc.teamcode.lakshya;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PIDFController;

@Config
public class LiftLakshya {
    DcMotorEx lift;

    public static double kp = 1.0;
    public static double ki = 0.0;
    public static double kd = 0.0;
    double output;

    PIDFController theBetterPidf = new PIDFController(kp, ki, kd, 1);


    public LiftLakshya(HardwareMap hardwareMap) {
        lift = hardwareMap.get(DcMotorEx.class, "slide");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower() {
        output = theBetterPidf.calculate(getEncoderValue());
        lift.setPower(output / 2000.0);
    }

    public void toPoint(double position) {
        theBetterPidf.setSetPoint(position);
    }

    public double getSetPoint() {
        return theBetterPidf.getSetPoint();
    }

    public double getEncoderValue() {
        return lift.getCurrentPosition();
    }

    public void stop() {
        lift.setPower(0);
    }
}

