package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftVyan {

    DcMotorEx Lift;
    public static int LIFT_TOP = -1250;
    public static int LIFT_BOTTOM = -1;

    public LiftVyan(HardwareMap hardwareMap) {

        Lift = hardwareMap.get(DcMotorEx.class, "lift");
        Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void drive(double input) {
        if (input < 0) {
            if (Lift.getCurrentPosition() >= LIFT_TOP){
                Lift.setPower(0);
        } else {
                Lift.setPower(input);
            }
        } else if  (input >= 0) {
            if (Lift.getCurrentPosition() >= LIFT_BOTTOM) {
                Lift.setPower(0);
            }
        }
    }
    public double getEncoderValue(){
        return Lift.getCurrentPosition();

    }
}