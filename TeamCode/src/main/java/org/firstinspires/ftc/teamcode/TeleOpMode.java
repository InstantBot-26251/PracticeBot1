package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
//
public class TeleOpMode extends OpMode {

    double y;
    double x;
    double rx;

    ChassisIshaan drivetrain;
    LiftIshaan LiftKit;

    @Override
    public void init() {

        drivetrain = new ChassisIshaan(hardwareMap);
        LiftKit = new LiftIshaan(hardwareMap);


    }

    //LIBRARIES?!?!?!?!?!?!?!?!?! for servos
    @Override
    public void loop() {
        if (gamepad1.options) {
            drivetrain.resetYaw();
        }

        y = applyResponseCurve(gamepad1.left_stick_y);
        x = -applyResponseCurve(gamepad1.left_stick_x);
        rx = -applyResponseCurve(gamepad1.right_stick_x);

        drivetrain.drive(x,y,rx);
        LiftKit.drive(-gamepad2.left_stick_y);

        telemetry.addData("Ishaan", "IS COOL MORE THAN LAKSH");
        telemetry.addData("LS-Y", gamepad1.left_stick_y);
        telemetry.addData("LS-X", gamepad1.left_stick_x);
        telemetry.addData("RS-X", gamepad1.right_stick_x);
        telemetry.addData("RGPD-LS-Y", gamepad2.left_stick_y);
        telemetry.addData("LiftEncoderVal", LiftKit.GetEncoderValue());


    }
    public double applyResponseCurve(double input) {
        double exponent = 2;
        return Math.signum(input) * Math.pow(Math.abs(input), exponent);
    }
}
//KiCad
//Laptop and TV and Game manual, Java, Inventr teach and learn, 3d pronter wire from cieling
// STMS Scedule, Teen Adv Brd., New RAV4, etc. etc., etc. NEW ROUTER
//Setup new laptop
//Redo wheels.
//New shoes
//Roblox
//WIN 12?!?!?!?!?!?!?
// Invengtor Lessons

