package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class liftclass {
    DcMotorEx Lift;
    public liftclass(HardwareMap hardwareMap){
    Lift= hardwareMap.get(DcMotorEx.class, "lift");
    }
}
