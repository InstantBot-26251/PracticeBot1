package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "VyanTeleOp")
public class TeleOpMode extends OpMode {
    ChassisVyan driveTrain;

    double y;
    double x;
    double rx;

    @Override
    public void init() {
        driveTrain = new ChassisVyan(hardwareMap);
    }

    @Override
    public void loop() {
        y = gamepad1.left_stick_y;
        x = -gamepad1.left_stick_x * 1.1;
        rx = -gamepad1.right_stick_x;

        driveTrain.drive(x, y, rx);

        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick y", gamepad1.right_stick_y);
        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("right stick x", gamepad1.right_stick_x);
        telemetry.addData("Vyan", "THE Great");

        telemetry.update();


    }
}
