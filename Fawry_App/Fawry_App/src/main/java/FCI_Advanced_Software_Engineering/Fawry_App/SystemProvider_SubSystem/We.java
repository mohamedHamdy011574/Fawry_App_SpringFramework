package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;


public class We extends RechargeServiceProviders{

	public We(){
		super("We","We123");
		PhoneNumber = "015";
	}
		
	
	public String getPhoneNumberPrifex() {
		return PhoneNumber;
	}

	@Override
	public void resetPhoneNumber() {
		PhoneNumber = "015";
	}
}
