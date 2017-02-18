package lib.util;

//add here text here

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

public class RecordMotorMovement {

    private static RecordMotorMovement instance;

    public static ArrayList<Double> DrivePowersR = new ArrayList<Double>();
    public static ArrayList<Double> DrivePowersL = new ArrayList<Double>();
    public static ArrayList<Double> GearPowers = new ArrayList<Double>();
    public static ArrayList<Double> ShooterColPowers = new ArrayList<Double>();
    public static ArrayList<Double> ShooterPowers = new ArrayList<Double>();
    public static ArrayList<Double> TurretPowers = new ArrayList<Double>();

    public void saveFile(String fileLoc) throws IOException {
        FileWriter output = new FileWriter(fileLoc);
        ArrayList<String> filemod = new ArrayList<String>(Arrays.asList(fileLoc.split("")));
        FileReader incheck = new FileReader(fileLoc);
        int check = incheck.read();
        boolean mknewfl = false;
        Integer counting = 1;
        while (mknewfl) {
            if (check != -1) {
                filemod.add(filemod.size() - 4, "(");
                filemod.add(filemod.size() - 4, counting.toString());
                filemod.add(filemod.size() - 4, ")");
                for (String temp : filemod) {
                    fileLoc = fileLoc + temp;
                }
            } else {
                mknewfl = true;
            }
            output = new FileWriter(fileLoc);
            incheck = new FileReader(fileLoc);
            check = incheck.read();
            counting++;
        }
        String outstr = "";
        for (int count = 0; count < DrivePowersR.size();) {
            outstr = outstr + (DrivePowersR.get(count) + ";" + "\t" + DrivePowersL.get(count) + ";" + "\t"
                    + GearPowers.get(count) + ";" + "\t" + ShooterColPowers.get(count) + ";" + "\t"
                    + ShooterPowers.get(count) + ";" + "\t" + "TurretPowers" + ";" + "\r");
            count++;
        }
        output.write(outstr);

        output.close();
        incheck.close();
    }

    public void RecordMotors() {
        DrivePowersR.add(DriveSubsystem.getInstance().getRSpeed());
        DrivePowersL.add(DriveSubsystem.getInstance().getLSpeed());

    }

    public void readFile(String fileLoc) throws IOException {
        InputStream inPower = null;
        FileWriter mkfl = null;
        try {
            inPower = new FileInputStream(fileLoc);
        } catch (FileNotFoundException e1) {
            mkfl = new FileWriter(fileLoc);
        }
        ArrayList<Character> powers = new ArrayList<Character>();
        ArrayList<String> TDrivePowersR = new ArrayList<String>();
        ArrayList<String> TDrivePowersL = new ArrayList<String>();
        ArrayList<String> TGearPowers = new ArrayList<String>();
        ArrayList<String> TShooterColPowers = new ArrayList<String>();
        ArrayList<String> TShooterPowers = new ArrayList<String>();
        ArrayList<String> TTurretPowers = new ArrayList<String>();
        int powersint;
        while ((powersint = inPower.read()) != -1) {
            powers.add((char) powersint);
        }
        String temp = "";
        for (Character a : powers) {
            if (a == '\n' || a == '\r' || a == '\t' || a == '\b' || a == '\\' || a == '\f' || a == '\"' || a == '\'') {
                a = 0;
            }
            if (a != 0) {
                temp += a;
            }
        }
        String[] powersStrng = temp.split(";");
        int count = 0;
        String temp2 = "";
        while (count <= (powersStrng.length)) {
            temp2 = powersStrng[count];
            TDrivePowersR.add(temp2);
            count++;
            temp2 = powersStrng[count];
            TDrivePowersL.add(temp2);
            count++;
            temp2 = powersStrng[count];
            TGearPowers.add(temp2);
            count++;
            temp2 = powersStrng[count];
            TShooterColPowers.add(temp2);
            count++;
            temp2 = powersStrng[count];
            TShooterPowers.add(temp2);
            count++;
            temp2 = powersStrng[count];
            TTurretPowers.add(temp2);
            count++;
        }

        for (String string : TDrivePowersR) {
            DrivePowersR.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TDrivePowersL) {
            DrivePowersL.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TGearPowers) {
            GearPowers.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TShooterColPowers) {
            ShooterColPowers.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TShooterPowers) {
            ShooterPowers.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TTurretPowers) {
            TurretPowers.add(StringToDouble.stringToDouble(string));
        }

        if (mkfl != null) mkfl.close();
        if (inPower != null) inPower.close();
    }

    public static RecordMotorMovement getInstance() {
        if (instance == null) instance = new RecordMotorMovement();
        return instance;
    }
}
