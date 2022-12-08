package frc.robot;


public final class Constants {
    
    public final static int smartCurrentLimit = 40;
    public final static double openLoopRampRate = 0.2;
    // IDs for Motors on robot
    public final static int frontLeftMotor = 3;
    public final static int backLeftMotor = 4;
    public final static int frontRightMotor = 1;
    public final static int backRightMotor = 2;
    // Indexing Motors
    public final static int frontLeftIndex = 0;
    public final static int backLeftIndex = 1;
    public final static int frontRightIndex = 2;
    public final static int backRightIndex = 3;
    // Operator Joysticks
    public final static int leftJoystickX = 0;
    public final static int leftJoystickY = 1;
    public final static int rightJoystickX = 4;
    public final static int rightJoystickY = 5;
    // Operator Button
    public final static int aButtonID = 1;
    // Driver Computer Ports
    public final static int driverControllerPort = 0;
    public final static int operatorControllerPort = 1;
    // Finger Breaker Motor ID
    public final static int fingerBreakerMotor = 5;
    public final static double[] fingerBreakerPid = {1, 0, 0};
    public final static int fingerBreakerPidSlot = 1;
    // Finger Breaker Reference Points
    public final static int fingerBreakerReferenceA = 0;
    public final static int fingerBreakerReferenceB = 500;
}
