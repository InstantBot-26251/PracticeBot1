package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "lakshyaCode")
public class TeleOp_Mode extends OpMode {
    ChassisLakshya theBetterChassis;
    double x;
    double y;
    double rx;

    @Override
    public void init() {
        theBetterChassis = new ChassisLakshya(hardwareMap);
    }

    @Override
    public void loop() {
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x * 1.1;
        rx = gamepad1.right_stick_x;

        telemetry.addData("Left stick y", gamepad1.left_stick_y);
        telemetry.addData("Left stick x", gamepad1.left_stick_x);
        telemetry.addData("Right stick x", gamepad1.right_stick_x);
        telemetry.addData("Right stick x", gamepad1.right_stick_x);

        theBetterChassis.drive(x, y, rx);

        telemetry.update();
    }
}
