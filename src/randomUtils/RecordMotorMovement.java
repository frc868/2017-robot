package randomUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecordMotorMovement {
	
	public static ArrayList<Double> DrivePowersR = new ArrayList<Double>();
	public static ArrayList<Double> DrivePowersL = new ArrayList<Double>();
	public static ArrayList<Double> GearPowers = new ArrayList<Double>();
	public static ArrayList<Double> ShooterColPowers = new ArrayList<Double>();
	public static ArrayList<Double> ShooterPowers = new ArrayList<Double>();
	public static ArrayList<Double> TurretPowers = new ArrayList<Double>();
	
	public void saveFile(double[] powers, String fileLoc){
		
	}
	
	public void readFile(String fileLoc) throws IOException{
		InputStream inPower = null;
		try{
			inPower = new FileInputStream(fileLoc);
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
		ArrayList<Character> powers = new ArrayList<Character>();
		ArrayList<String> TDrivePowersR = new ArrayList<String>();
		ArrayList<String> TDrivePowersL = new ArrayList<String>();
		ArrayList<String> TGearPowers = new ArrayList<String>();
		ArrayList<String> TShooterColPowers = new ArrayList<String>();
		ArrayList<String> TShooterPowers = new ArrayList<String>();
		ArrayList<String> TTurretPowers = new ArrayList<String>();
		int powersint;
		while((powersint = inPower.read()) != -1){
			powers.add((char) powersint);
		}
		String temp = "";
		for(Character a:powers){
			if(a == '\n'||a=='\r'||a=='\t'||a=='\b'||a=='\\'||a=='\f'||a=='\"'||a=='\''){
				a = 0;
			}
			if(a != 0){
				temp += a;
			}
		}
		String[] powersStrng = temp.split(";");
		int count = 0;
		String temp2 = "";
		while(count <= (powersStrng.length)){
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
		
		for(String string:TDrivePowersR){
			DrivePowersR.add(StringToDouble.stringToDouble(string));
		}
		for(String string:TDrivePowersL){
			DrivePowersL.add(StringToDouble.stringToDouble(string));
		}
		for(String string:TGearPowers){
			GearPowers.add(StringToDouble.stringToDouble(string));
		}
		for(String string:TShooterColPowers){
			ShooterColPowers.add(StringToDouble.stringToDouble(string));
		}
		for(String string:TShooterPowers){
			ShooterPowers.add(StringToDouble.stringToDouble(string));
		}
		for(String string:TTurretPowers){
			TurretPowers.add(StringToDouble.stringToDouble(string));
		}
	}
}
