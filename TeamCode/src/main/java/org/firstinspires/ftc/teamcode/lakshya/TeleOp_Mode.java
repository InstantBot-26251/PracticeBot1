package org.firstinspires.ftc.teamcode.lakshya;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "lakshyaCode")
public class TeleOp_Mode extends OpMode {
    LiftLakshya theBetterLift;
    ChassisLakshya theBetterChassis;
    double x;
    double y;
    double rx;

    @Override
    public void init() {
        theBetterChassis = new ChassisLakshya(hardwareMap);
        theBetterLift = new LiftLakshya(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry() );
    }

    @Override
    public void loop() {
        y = applyResponseCurve(gamepad1.left_stick_y);
        x = -applyResponseCurve(gamepad1.left_stick_x);
        rx = -applyResponseCurve(gamepad1.right_stick_x);

        telemetry.addData("Lift Encoder", theBetterLift.getEncoderValue());
        telemetry.addData("Lift Setpoint", theBetterLift.getSetPoint());
        telemetry.addData("Left stick y", gamepad1.left_stick_y);
        telemetry.addData("Left stick x", gamepad1.left_stick_x);
        telemetry.addData("Right stick x", gamepad1.right_stick_x);
        telemetry.addData("Adjusted x", x);
        telemetry.addData("Adjusted y", y);
        telemetry.addData("Adjusted rx", rx);

        if (gamepad1.y) {
            theBetterChassis.resetYaw();
        }

        if (gamepad2.dpad_up) {
            theBetterLift.toPoint(-200);
        }
        if (gamepad2.dpad_down) {
            theBetterLift.toPoint(0);
        }
        theBetterLift.setPower();

        theBetterChassis.drive(x, y, rx);

        telemetry.update();
    }

    public double applyResponseCurve(double input) {
        double exponent = 2;
        return Math.signum(input) * Math.pow(Math.abs(input), exponent);
    }

}
// git fetch
//git pull