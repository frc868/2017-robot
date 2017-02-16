package lib.util;

public class StringToDouble {
	
	public static Double stringToDouble(String string){
		double b = 0;
		char[] Char = string.toCharArray();
		for(int a = 0; a < string.length(); a++){
			if(Char[a] == '0'){
				b = b * 10;
			}else if(Char[a] == '1'){
				b = (b * 10) + 1;
			}else if(Char[a] == '2'){
				b = (b * 10) + 2;
			}else if(Char[a] == '3'){
				b = (b * 10) + 3;
			}else if(Char[a] == '4'){
				b = (b * 10) + 4;
			}else if(Char[a] == '5'){
				b = (b * 10) + 5;
			}else if(Char[a] == '6'){
				b = (b * 10) + 6;
			}else if(Char[a] == '7'){
				b = (b * 10) + 7;
			}else if(Char[a] == '8'){
				b = (b * 10) + 8;
			}else if(Char[a] == '9'){
				b = (b * 10) + 9;
			}else if(Char[a] == '.'){
				int c = string.length() - a;
				int d = (a - 1) + c;
				for(; c >= 1; c--, d--){
					if(Char[d] == '0'){
						b = b + (0 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '1'){
						b = b + (1 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '2'){
						b = b + (2 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '3'){
						b = b + (3 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '4'){
						b = b + (4 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '5'){
						b = b + (5 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '6'){
						b = b + (6 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '7'){
						b = b + (7 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '8'){
						b = b + (8 * Math.pow(10, -c) * 10);
					}else if(Char[d] == '9'){
						b = b + (9 * Math.pow(10, -c) * 10);
					}
					a = string.length() + a + 1;
				}
			}
			
		}
		if(!string.equals("")){
				if(Char[0] == '-'){
					return (Double)(-b);
				}else{
					return (Double)b;
				}
			}else{
				return (Double) 0.0;
			}
	}

}
