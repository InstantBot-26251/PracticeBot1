package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="guilliesTeleOp")
public class TeleOp_mode extends OpMode{
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    double y;
    double x;
    double rx;
    double denominator;
    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;


    @Override
    public void init() {
        fl = hardwareMap.get(DcMotorEx.class, "frontLeft");
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr = hardwareMap.get(DcMotorEx.class, "frontRight");
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl = hardwareMap.get(DcMotorEx.class, "backLeft");
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br = hardwareMap.get(DcMotorEx.class, "backRight");
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        y = gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_y;
        denominator =  Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontLeftPower = (y + x + rx) / denominator;
        frontRightPower = (y - x - rx) / denominator;
        backLeftPower = (y - x + rx) / denominator;
        backRightPower = (y + x - rx)/ denominator;
        fl.setPower(frontLeftPower);
        fr.setPower(frontRightPower);
        bl.setPower(backLeftPower);
        br.setPower(backRightPower);
    }
}
