package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;

public class Service_ProviderController {
	public static boolean ValidPhoneNumber(String phone) {
		if(phone.length() != 11)
			return false;
		
		for(int i=0;i<phone.length();i++) {
			if(phone.charAt(i) < 48 || phone.charAt(i) > 57)
				return false;
		}
		return true;
	}
	
	public static boolean ValidLandLineNumber(String landLineNumber) {
		if(!(landLineNumber.startsWith("+20")))
			return false;
		
		for(int i=3;i<landLineNumber.length();i++) {
			if((landLineNumber.charAt(i) < 48 || landLineNumber.charAt(i) > 57))
				return false;
		}
		return true;
	}

}
