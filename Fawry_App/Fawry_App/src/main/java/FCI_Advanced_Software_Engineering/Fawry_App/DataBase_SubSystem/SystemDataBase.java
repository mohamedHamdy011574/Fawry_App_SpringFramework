package FCI_Advanced_Software_Engineering.Fawry_App.DataBase_SubSystem;

import java.util.ArrayList;




import FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem.Admin;
import FCI_Advanced_Software_Engineering.Fawry_App.Service_SubSystem.service;
import FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem.*;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

public class SystemDataBase {
	
	public static Admin admin = Admin.getInstance();
	public static User CurrentUser = null;
	public static LandLineServiceProvider myLandlineProvider = new LandLineServiceProvider();
	
	
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<service> SystemServices = new ArrayList<service>();
	public static ArrayList<service> AdminServices = new ArrayList<service>();
	public static ArrayList<RechargeServiceProviders> RechargeServiceProviders = new ArrayList<RechargeServiceProviders>();
	public static ArrayList<DonationsServiceProvider> donationsServiceProviders  = new ArrayList<DonationsServiceProvider>();
	
	
	

}
