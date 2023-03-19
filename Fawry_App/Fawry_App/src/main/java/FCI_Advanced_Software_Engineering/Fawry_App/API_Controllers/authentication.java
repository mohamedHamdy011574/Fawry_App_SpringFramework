package FCI_Advanced_Software_Engineering.Fawry_App.API_Controllers;



import java.util.regex.Pattern;

import FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem.Admin;
import FCI_Advanced_Software_Engineering.Fawry_App.DataBase_SubSystem.SystemDataBase;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;


public class authentication {
	
	
	private static boolean ValidEmail(String Email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
         
		Pattern pt = Pattern.compile(emailRegex);
		if(Email == null)
			return false;
		
		return pt.matcher(Email).matches();
	}
	
		
    // Get the result from Step 2
    
	//Admin login controller 
	public static boolean ValidAdminAccount(String Email, String password,Admin admin){
		if(password.equals(admin.getPassword()) && Email.equals(admin.getEmail()))
		{
			return true;
		}
		return false;
	}
	/// with user log in
	private static User IsUser(String Email) {
		for(int i=0;i<SystemDataBase.users.size();i++) {
			if(Email.equals(SystemDataBase.users.get(i).getEmail())) {
				return SystemDataBase.users.get(i);
			}
		}
		return null;
	}

	//user login controller
	public static boolean ValidUserAccount(String Email, String password){
		if(!ValidEmail(Email)) {
			return false;
		}
		
		User registeredUser = IsUser(Email);
		
		if(registeredUser != null) {
			if(password.equals(registeredUser.getPassword())) {
				return true;
			}
		}
		return false;
	}
	// validation of information of sign up
	public static boolean ValidSignUpInfo(String userName, String Email, String password) {
		User newUser = IsUser(Email);
		
		if(newUser == null && !Email.equals(SystemDataBase.admin.getEmail()) 
						   && userName.length() >0 && ValidEmail(Email) 
						   && password.length() >= 4 ) {
				return true;
		}
		return false;
	}
	

}
