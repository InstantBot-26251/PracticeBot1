package org.firstinspires.ftc.teamcode.vyan;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "VyanTeleOp")
public class VyanTeleOpMode extends OpMode {
    ChassisVyan driveTrain;
    LiftVyan liftKit;

    double y;
    double x;
    double rx;

    @Override
    public void init() {
        driveTrain = new ChassisVyan(hardwareMap);
        liftKit = new LiftVyan(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        y = applyResponseCurve(gamepad1.left_stick_y);
        x = -applyResponseCurve(gamepad1.left_stick_x);
        rx = -applyResponseCurve(gamepad1.right_stick_x);

        if (gamepad2.dpad_up)
            liftKit.toPoint(-1000);
        if (gamepad2.dpad_down)
            liftKit.toPoint(0);

        liftKit.setPower();

        driveTrain.drive(x, y, rx);

        telemetry.addData("Initialization", "Complete");
        telemetry.addData("sliderX", x);
        telemetry.addData("sliderY", y);
        telemetry.addData("sliderRX", rx);
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick y", gamepad1.right_stick_y);
        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("right stick x", gamepad1.right_stick_x);
        telemetry.addData("Right Gamepad Left stick y", gamepad2.left_stick_y);
        telemetry.addData("LiftEncoderValue", liftKit.getEncoderValue());
        telemetry.addData("Vyan", "THE Great");

        telemetry.update();


    }

    public double applyResponseCurve(double input) {
        double exponent = 2;
        return Math.signum(input) * Math.pow(Math.abs(input), exponent);
    }
}
