
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.can.CANMessageNotFoundException;

/**
 * Defines all of the hardware port numbers for each hardware component, and then constructs each component for use by other
 * subsystems. This makes it easy for one to change the IDs
 *
 * @author Teju Nareddy
 *
 */
public class RobotMap
{
	// Hardware IDs are declared before
	
	// Drive Jaguar IDs
	private static final int DRIVE_LEFT_JAG1_ID = 2;
	private static final int DRIVE_LEFT_JAG2_ID = 4;
	private static final int DRIVE_RIGHT_JAG1_ID = 7;
	private static final int DRIVE_RIGHT_JAG2_ID = 10;
	
	// Subsystem Jaguar IDs
	private static final int FORKS_JAG_ID = 11;
	private static final int ELEVATOR_JAG_ID = 13;
	
	// Digital IDs
	private static final int DRIVE_QUAD_ENCODER_LEFT_IDA = 1;
	private static final int DRIVE_QUAD_ENCODER_LEFT_IDB = 2;
	private static final int DRIVE_QUAD_ENCODER_RIGHT_IDA = 3;
	private static final int DRIVE_QUAD_ENCODER_RIGHT_IDB = 4;
	private static final int FORKS_HIGH_LIMITSWITCH_ID = 7;
	private static final int FORKS_LOW_LIMITSWITCH_ID = 6;
	private static final int ELEVATOR_HIGH_LIMSWITCH_ID = 9;
	private static final int ELEVATOR_LOW_LIMSWITCH_ID = 8;
	
	// Analog IDs
	private static final int ANALOG_SWITCH_ID = 0;
	private static final int ANALOG_GYRO_ID = 1;
	
	// -----------------------------------------//
	// -----------------------------------------//
	
	// Jag Scaling Values. If the Jags are too fast or slow, changing this value will change the Jag's speed in every single file.
	// Value between 0 and 1. For example, the drive jags are reduced to 1/2 their power.
	private static final double DRIVE_LEFT_JAG1_SCALE = 0.5;
	private static final double DRIVE_LEFT_JAG2_SCALE = 0.5;
	private static final double DRIVE_RIGHT_JAG1_SCALE = -0.5;
	private static final double DRIVE_RIGHT_JAG2_SCALE = -0.5;
	private static final double FORKS_JAG_SCALE = 1;
	private static final double ELEVATOR_JAG_SCALE = 1;
	
	// Limit Switches IsReversed variables -- If the wiring of the limit switch is backward, set this to true for the robot to
	// correctly determine if the limit switch is opened or closed
	private static final boolean FORKS_HIGH_LIMITSWITCH_REVERSED = true;
	private static final boolean FORKS_LOW_LIMITSWITCH_REVERSED = true;
	private static final boolean ELEVATOR_HIGH_LIMSWITCH_REVERSED = true;
	private static final boolean ELEVATOR_LOW_LIMSWITCH_REVERSED = true;
	
	// -----------------------------------------//
	// -----------------------------------------//
	
	// Declare Drive Subsystem Drive Wheels
	public static ScaledCANJaguar driveLeftJag1;
	public static ScaledCANJaguar driveLeftJag2;
	public static ScaledCANJaguar driveRightJag1;
	public static ScaledCANJaguar driveRightJag2;
	
	// Declare Forklift Subsystem Jaguars
	public static ScaledCANJaguar forksJag;
	public static ScaledCANJaguar elevatorJag;
	
	// Declare Forklift Subsystem Limit Switches
	public static DigitalLimitSwitch forksHighLimitSwitch;
	public static DigitalLimitSwitch elevatorHighLimitSwitch;
	public static DigitalLimitSwitch forksLowLimitSwitch;
	public static DigitalLimitSwitch elevatorLowLimitSwitch;
	
	// Declare Built-In Accelerometer
	public static BuiltInAccelerometer accelerometer;
	
	// Declare Gyro Sensor
	public static Gyro gyro;
	
	// Declare Quad Encoders
	public static Encoder driveQuadEncoderLeft;
	public static Encoder driveQuadEncoderRight;
	
	// Declare Auto switch
	public static AnalogSwitch autoSwitch = new AnalogSwitch(ANALOG_SWITCH_ID);
	
	/**
	 * This method is called to instantiate each hardware component
	 */
	public static void init()
	{
		// Instantiate all of the jaguars with the scaling factors and IDs. Surround with try/catch in case the jaguars are not
		// plugged in. This way, the whole robot will not crash, only the individual jaguar will be disabled.
		try
		{
			driveLeftJag1 = new ScaledCANJaguar(DRIVE_LEFT_JAG1_ID, DRIVE_LEFT_JAG1_SCALE);
		}
		catch (CANMessageNotFoundException e)
		{
			System.out.println("Left Drive Jag 1 was not created! Missing CAN ID #" + DRIVE_LEFT_JAG1_ID);
		}
		
		try
		{
			driveLeftJag2 = new ScaledCANJaguar(DRIVE_LEFT_JAG2_ID, DRIVE_LEFT_JAG2_SCALE);
		}
		catch (CANMessageNotFoundException e)
		{
			System.out.println("Left Drive Jag 2 was not created! Missing CAN ID #" + DRIVE_LEFT_JAG2_ID);
		}
		
		try
		{
			driveRightJag1 = new ScaledCANJaguar(DRIVE_RIGHT_JAG1_ID, DRIVE_RIGHT_JAG1_SCALE);
		}
		catch (CANMessageNotFoundException e)
		{
			System.out.println("Right Drive Jag 1 was not created! Missing CAN ID #" + DRIVE_RIGHT_JAG1_ID);
		}
		
		try
		{
			driveRightJag2 = new ScaledCANJaguar(DRIVE_RIGHT_JAG2_ID, DRIVE_RIGHT_JAG2_SCALE);
		}
		catch (CANMessageNotFoundException e)
		{
			System.out.println("Right Drive Jag 2 was not created! Missing CAN ID #" + DRIVE_RIGHT_JAG2_ID);
		}
		
		try
		{
			forksJag = new ScaledCANJaguar(FORKS_JAG_ID, FORKS_JAG_SCALE);
		}
		catch (CANMessageNotFoundException e)
		{
			System.out.println("Forks Jag was not created! Missing CAN ID #" + FORKS_JAG_ID);
		}
		
		try
		{
			elevatorJag = new ScaledCANJaguar(ELEVATOR_JAG_ID, ELEVATOR_JAG_SCALE);
		}
		catch (CANMessageNotFoundException e)
		{
			System.out.println("Elevator Jag was not created! Missing CAN ID #" + ELEVATOR_JAG_ID);
		}
		
		// Instantiate limit switches with the IsReversed variable
		forksHighLimitSwitch = new DigitalLimitSwitch(FORKS_HIGH_LIMITSWITCH_ID, FORKS_HIGH_LIMITSWITCH_REVERSED);
		elevatorHighLimitSwitch = new DigitalLimitSwitch(ELEVATOR_HIGH_LIMSWITCH_ID, ELEVATOR_HIGH_LIMSWITCH_REVERSED);
		forksLowLimitSwitch = new DigitalLimitSwitch(FORKS_LOW_LIMITSWITCH_ID, FORKS_LOW_LIMITSWITCH_REVERSED);
		elevatorLowLimitSwitch = new DigitalLimitSwitch(ELEVATOR_LOW_LIMSWITCH_ID, ELEVATOR_LOW_LIMSWITCH_REVERSED);
		
		// Instantiate Built-In Accelerometer
		accelerometer = new BuiltInAccelerometer();
		
		// Instantiate Gyro
		gyro = new Gyro(ANALOG_GYRO_ID);
		
		// Instantiate quad encoders
		driveQuadEncoderLeft = new Encoder(DRIVE_QUAD_ENCODER_LEFT_IDA, DRIVE_QUAD_ENCODER_LEFT_IDB, false);
		driveQuadEncoderRight = new Encoder(DRIVE_QUAD_ENCODER_RIGHT_IDA, DRIVE_QUAD_ENCODER_RIGHT_IDB, true);
	}
}
