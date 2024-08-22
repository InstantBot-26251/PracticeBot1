package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class TeleOpMode extends OpMode {

    double y;
    double x;
    double rx;
    ChassisIshaan drivetrain;

    @Override
    public void init() {

        drivetrain = new ChassisIshaan(hardwareMap);


    }
//LIBRARIES?!?!?!?!?!?!?!?!?! for servos
    @Override
    public void loop() {

        drivetrain.drive(x, y, rx);

        telemetry.addData("Ishaan", "is AWESOME!");
        telemetry.addData("LS-Y", gamepad1.left_stick_y);
        telemetry.addData("LS-X", gamepad1.left_stick_x);
        telemetry.addData("RS-X", gamepad1.right_stick_x);

        y = Math.pow(gamepad1.left_stick_y, 2);
        x = Math.pow(-gamepad1.left_stick_x, 2);
        rx = Math.pow(-gamepad1.right_stick_x, 2);


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

