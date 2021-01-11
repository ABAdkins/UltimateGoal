package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.drive.TurnCommand;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * This is a simple routine to test turning capabilities.
 *
 * NOTE: this has been refactored to use FTCLib's command-based
 */
@Config
@Disabled
@Autonomous(group = "drive")
public class TurnTest extends CommandOpMode {

    public static double ANGLE = 180; // deg

    private Drivetrain drive;
    private TurnCommand turnCommand;

    @Override
    public void initialize() {
        drive = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        turnCommand = new TurnCommand(drive, 180);
        schedule(new WaitUntilCommand(this::isStarted).andThen(turnCommand.whenFinished(() -> {
            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("finalX", poseEstimate.getX());
            telemetry.addData("finalY", poseEstimate.getY());
            telemetry.addData("finalHeading", poseEstimate.getHeading());
            telemetry.update();
        })));
        /*
        waitForStart();

        if (isStopRequested()) return;

        drive.turnBlock(Math.toRadians(ANGLE));
        */

    }

}
