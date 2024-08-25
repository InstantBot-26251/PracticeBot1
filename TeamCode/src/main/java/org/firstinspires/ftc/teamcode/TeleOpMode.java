package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "VyanTeleOp")
public class TeleOpMode extends OpMode {
    ChassisVyan driveTrain;
    LiftVyan liftKit;

    double y;
    double x;
    double rx;

    @Override
    public void init() {
        driveTrain = new ChassisVyan(hardwareMap);
        liftKit = new LiftVyan(hardwareMap);
    }

    @Override
    public void loop() {
        y = applyResponseCurve(gamepad1.left_stick_y);
        x = -applyResponseCurve(gamepad1.left_stick_x);
        rx = -applyResponseCurve(gamepad1.right_stick_x);

        driveTrain.drive(x, y, rx);
        liftKit.drive(-gamepad2.left_stick_y);

        telemetry.addData("Initialization", "Complete");
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick y", gamepad1.right_stick_y);
        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("right stick x", gamepad1.right_stick_x);
        telemetry.addData("Right gamepad Left stick y", gamepad2.left_stick_y);
        telemetry.addData("LiftEncoderValue", liftKit.getEncoderValue());
        telemetry.addData("Vyan", "THE Great");

        telemetry.update();


    }

    public double applyResponseCurve(double input) {
        double exponent = 2;
        return Math.signum(input) * Math.pow(Math.abs(input), exponent);
    }
}
