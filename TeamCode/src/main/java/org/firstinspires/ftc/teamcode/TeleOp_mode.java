package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

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


        IMU imu;
        IMU.Parameters parameters;

    double botHeading;

    // Rotate the movement direction counter to the bot's rotation
    double rotx;
    double roty;



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

        imu = hardwareMap.get(IMU.class,"imu");

        parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);
    }

    @Override
    public void loop() {
        botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        rotx = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        roty = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
        y = gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_y;
        denominator =  Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontLeftPower = (roty + rotx + rx) / denominator;
        frontRightPower = (roty - rotx - rx) / denominator;
        backLeftPower = (roty - rotx + rx) / denominator;
        backRightPower = (roty + rotx - rx)/ denominator;
        fl.setPower(frontLeftPower);
        fr.setPower(frontRightPower);
        bl.setPower(backLeftPower);
        br.setPower(backRightPower);

    }

}
