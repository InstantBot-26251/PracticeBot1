package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ChassisIshaan {

    DcMotorEx frontRight;
    DcMotorEx frontLeft;
    DcMotorEx backRight;
    DcMotorEx backLeft;

    IMU imu;


    public ChassisIshaan(HardwareMap hardwareMap) {

        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);


        //"imu" is used in ds config


    }

    public void resetYaw() {
        imu.resetYaw();
    }

    public void drive(double x, double y, double rx) {

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double Ax = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double Ay = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);




        double denominator = Math.max(Math.abs(Ax) + Math.abs(Ay) + Math.abs(rx), 1);
        double frontLeftPower = (Ay + Ax + rx) / denominator;
        double backLeftPower = (Ay - Ax + rx) / denominator;
        double frontRightPower = (Ay - Ax - rx) / denominator;
        double backRightPower = (Ay + Ax - rx) / denominator;


        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);


    }


}
