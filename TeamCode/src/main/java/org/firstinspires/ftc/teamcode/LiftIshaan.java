package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.sql.Driver;

public class LiftIshaan {
    //Publicly available class
    DcMotorEx Lift;
    //Introduce the REV MOTOR Lift
    public static int LIFT_TOP = -5000;
    public static int LIFT_BOTTOM = -46;
    //Limits for lift extrusion

    public LiftIshaan(HardwareMap hardwareMap) {

        Lift = hardwareMap.get(DcMotorEx.class, "lift"); //<-- ds config name
        Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void drive(double input) {

        if (input < 0) {
            if (GetEncoderValue() <= LIFT_TOP) {

                Lift.setPower(0);

            } else {
                Lift.setPower(input);

            }


        } else if (input >= 0) {

            if (GetEncoderValue() >= LIFT_BOTTOM) {

                Lift.setPower(0);


            } else {

                Lift.setPower(input);
            }




        }


    }
    public double GetEncoderValue(){
        return Lift.getCurrentPosition();
    }














}
