package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftAvyukt {
    DcMotorEx lift;
    PIDFController pidf = new PIDFController(kp, ki, kd, kf:1);
    public static double kp = 0;
    public static double ki = 0.0;
    public static double kd = 0.0;

    public LiftAvyukt(HardwareMap hardwareMap) {

        lift = hardwareMap.get(DcMotorEx.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void toPoint (double position) {
        pidf.setSetPoint(position);
        double output = pidf.calculate(getEncoderValue());
        lift.setPower(output);
    }
    public double getEncoderValue() {
        return lift.getCurrentPosition();
    }
}
//Notes
// Derivitive less than 1
//Best to leave "i" as 0
//"d" is how quick it stops when nearing the finish
//"p" is calculating the distance and determining how fast to go