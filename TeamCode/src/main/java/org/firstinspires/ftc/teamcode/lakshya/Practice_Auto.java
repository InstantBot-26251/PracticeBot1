package org.firstinspires.ftc.teamcode.lakshya;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "lakshyaAuto")
public class Practice_Auto extends OpMode {
    // Define our subsystems
    ChassisLakshya chassis;
    LiftLakshya lift;
    // This is what I'll use to define the robot randomization for our sake here
    double randomization = 0;

    // This will work as our wait command (this method was copied straight from LinearOpMode, since it doesn't exist in OpMode)
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e){ // This part just makes sure to not crash the robot if we stop the opmode
            Thread.currentThread().interrupt();
        }
    }

    // Set up our hardware and init dashboard telemetry
    @Override
    public void init() {
        chassis = new ChassisLakshya(hardwareMap);
        lift = new LiftLakshya(hardwareMap);
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);
    }

    // In init loop, we want to change what our randomization is based on the opencv pipeline
    @Override
    public void init_loop() {
// You would need to actually write a vision pipeline/subsystem
// randomization = vision.getRandomization();
    }

    @Override
    public void start() {
// Once you actually start, you don't need to use vision anymore, so we would close our vision pipeline to save resources
// We do this because vision processing is very CPU intensive
// vision.close();

// Because this is an auto, we don't want anything in loop(). Instead, we want things to go in a linear fashion in start()
// This drives our robot diagonally, as if our joysticks were pointing that way
        chassis.drive(0.5, 0.5, 0);
// Here we wait however long we want to drive for, in milliseconds
        sleep(5000);
// What we did here is we basically just drive diagonally for 5 seconds.
// Keep in mind, this is still field centric.

// From here, you can add whatever you want in your auto sequence. One thing to keep in mind, is that with this setup,
// only one subsystem can run at a time. This is because sleep() actually sleeps our entire Java thread, meaning
// nothing else runs during that period.

// Example of continuing the auto:

// drive forward at full speed for 2 seconds
        chassis.drive(0, 1, 0);
        sleep(2000);

// choose how long to turn for based on randomization
        long turnMilliseconds = 0;
        if (randomization == 0) turnMilliseconds = 250;
        else if (randomization == 1) turnMilliseconds = 500;
        else if (randomization == 2) turnMilliseconds = 750;

// turn right at 25% power for turnMilliseconds
        chassis.drive(0, 0, 0.25);
        sleep(turnMilliseconds);

        lift.toPoint(1000);
// here we want our lift to constantly set power until our error (absolute value of setpoint - encoder) is smaller
// than some tolerance value (I picked 75 randomly, you'll need to figure this out yourself)
        while (Math.abs(lift.getSetPoint() - lift.getEncoderValue()) > 75) {
            lift.setPower();
        }
        lift.stop(); // I added this method to Lift myself; it's just lift.setPower(0), nothing much
    }

// Keep in mind that your autonomous will automatically stop at the 30 second mark if you go over that time limit

    // Because we want everything to go in a sequence, we don't need anything in loop()
    @Override
    public void loop() {

    }
}