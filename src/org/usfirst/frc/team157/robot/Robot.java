
package org.usfirst.frc.team157.robot;

import org.usfirst.frc.team157.robot.commands.AutonomousCommand;
import org.usfirst.frc.team157.robot.subsystems.Drive;
import org.usfirst.frc.team157.robot.subsystems.ForkliftElevator;
import org.usfirst.frc.team157.robot.subsystems.ForkliftForks;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This is the first file that runs when the program starts. It is responsible for instantiating a few of the other base classes,
 * such as OI, RobotMap, and the subsystems.
 *
 * @author Teju Nareddy
 * 
 */
public class Robot extends IterativeRobot
{
	// The following code declares public variables that can be accessed in any other class.
	
	// Camera on robot to display on the driver station
	public CameraServer camera;
	
	// Auto command
	public static Command autonomousCommand;
	
	// OI
	public static OI oi;
	
	// Subsystems
	public static Drive drive;
	public static ForkliftElevator elevator;
	public static ForkliftForks forks;
	
	// -----------------------------------------//
	// -----------------------------------------//
	
	/**
	 * This method is called when autonomous mode is enabled on the driver station
	 */
	@Override
	public void autonomousInit()
	{
		// Starts the autonomous command
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
	}
	
	/**
	 * This method is called periodically during autonomous (Approximately every 20 ms)
	 */
	@Override
	public void autonomousPeriodic()
	{
		// No idea what this does, but it is necessary for the robot to run
		Scheduler.getInstance().run();
	}
	
	/**
	 * This method is called when the robot is disabled
	 */
	@Override
	public void disabledInit()
	{
		// Do Nothing
	}
	
	/**
	 * This method is run when the robot is first started up and should be used for any initialization code. Called when the robot
	 * is turned on!
	 */
	@Override
	public void robotInit()
	{
		// Instantiate all hardware components in RobotMap
		RobotMap.init();
		
		// Surround with a try/catch so that if a network error occurs, robot does not crash
		try
		{
			// Sets up the camera for display on the driver station
			camera = CameraServer.getInstance();
			camera.setQuality(50);
			camera.startAutomaticCapture("cam0");
		}
		catch (Exception E)
		{
			// Debug print
			System.out.println("Problem with camera!");
		}
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Instantiate subsystems and other files that are declared at the top of the file
		
		drive = new Drive();
		elevator = new ForkliftElevator();
		forks = new ForkliftForks();
		
		// Instantiate the command used for the autonomous period.
		autonomousCommand = new AutonomousCommand();
		
		// OI must be at the end of the list
		oi = new OI();
	}
	
	/**
	 * This method is called at the beginning of teleop
	 */
	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when teleop starts running. If you want the autonomous to continue
		// until interrupted by another command, remove this line or comment it out.
		if (autonomousCommand != null)
		{
			autonomousCommand.cancel();
		}
	}
	
	/**
	 * This method is called periodically during operator control (teleop)
	 */
	@Override
	public void teleopPeriodic()
	{
		// Keep this here!
		Scheduler.getInstance().run();
	}
	
	/**
	 * This method called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		// Updates SmartDashboard to have test info
		LiveWindow.run();
	}
}
