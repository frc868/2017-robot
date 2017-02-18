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

    public static ArrayList<Double> DrivePowersR = new ArrayList<>();
    public static ArrayList<Double> DrivePowersL = new ArrayList<>();
    public static ArrayList<Double> GearPowers = new ArrayList<>();
    public static ArrayList<Double> ShooterColPowers = new ArrayList<>();
    public static ArrayList<Double> ShooterPowers = new ArrayList<>();
    public static ArrayList<Double> TurretPowers = new ArrayList<>();

    public void saveFile(String fileLoc) throws IOException {
        FileWriter output = new FileWriter(fileLoc);
        ArrayList<String> filemod = new ArrayList<>(Arrays.asList(fileLoc.split("")));
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
        for (int count = 0; count < RecordMotorMovement.DrivePowersR.size();) {
            outstr = outstr + (RecordMotorMovement.DrivePowersR.get(count) + ";" + "\t"
                    + RecordMotorMovement.DrivePowersL.get(count) + ";" + "\t"
                    + RecordMotorMovement.GearPowers.get(count) + ";" + "\t"
                    + RecordMotorMovement.ShooterColPowers.get(count) + ";" + "\t"
                    + RecordMotorMovement.ShooterPowers.get(count) + ";" + "\t" + "TurretPowers" + ";" + "\r");
            count++;
        }
        output.write(outstr);

        output.close();
        incheck.close();
    }

    public void RecordMotors() {
        RecordMotorMovement.DrivePowersR.add(DriveSubsystem.getInstance().getRSpeed());
        RecordMotorMovement.DrivePowersL.add(DriveSubsystem.getInstance().getLSpeed());

    }

    public void readFile(String fileLoc) throws IOException {
        InputStream inPower = null;
        FileWriter mkfl = null;
        try {
            inPower = new FileInputStream(fileLoc);
        } catch (FileNotFoundException e1) {
            mkfl = new FileWriter(fileLoc);
        }
        ArrayList<Character> powers = new ArrayList<>();
        ArrayList<String> TDrivePowersR = new ArrayList<>();
        ArrayList<String> TDrivePowersL = new ArrayList<>();
        ArrayList<String> TGearPowers = new ArrayList<>();
        ArrayList<String> TShooterColPowers = new ArrayList<>();
        ArrayList<String> TShooterPowers = new ArrayList<>();
        ArrayList<String> TTurretPowers = new ArrayList<>();
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
            RecordMotorMovement.DrivePowersR.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TDrivePowersL) {
            RecordMotorMovement.DrivePowersL.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TGearPowers) {
            RecordMotorMovement.GearPowers.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TShooterColPowers) {
            RecordMotorMovement.ShooterColPowers.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TShooterPowers) {
            RecordMotorMovement.ShooterPowers.add(StringToDouble.stringToDouble(string));
        }
        for (String string : TTurretPowers) {
            RecordMotorMovement.TurretPowers.add(StringToDouble.stringToDouble(string));
        }

        if (mkfl != null) {
            mkfl.close();
        }
        if (inPower != null) {
            inPower.close();
        }
    }

    public static RecordMotorMovement getInstance() {
        if (RecordMotorMovement.instance == null) {
            RecordMotorMovement.instance = new RecordMotorMovement();
        }
        return RecordMotorMovement.instance;
    }
}
