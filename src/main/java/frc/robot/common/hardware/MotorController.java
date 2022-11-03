package frc.robot.common.hardware;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

public class MotorController extends CANSparkMax {
    private double mP;
    private double mI;
    private double mD;

    private String mName;
    private SparkMaxPIDController mPIDController;

    public MotorController(String name, int deviceID) {
        super(deviceID, MotorType.kBrushless);
        mName = name;

        mPIDController = getPIDController();
        getEncoder();
    }

    public MotorController(String name, int deviceID, double[] PID) {
        this(name, deviceID); // intializes CANSparkMax, Encoder, and PIDController
        setPID(PID);
    }

      public void setPID(double[] PID) {
        mP = PID[0];
        mI = PID[1];
        mD = PID[2];
        activatePID(PID);
    }

    public String getName() {
        return this.mName;
    }

    private void activatePID(double[] PID) {
        // Check first that mPIDController has been instantiated
        if (mPIDController == null) {
          throw new NullPointerException(
              "PID Controller for " + this.mName + " has not been instantiated.");
    }
    
        mPIDController.setP(mP);
        mPIDController.setI(mI);
        mPIDController.setD(mD);
    }
}
