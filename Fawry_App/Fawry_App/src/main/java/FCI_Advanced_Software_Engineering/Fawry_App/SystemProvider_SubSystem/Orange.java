package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;


public class Orange extends RechargeServiceProviders{

	public Orange(){
		super("Orange","Orange123");
		PhoneNumber = "012";
	}
	public String getPhoneNumberPrifex() {
		return PhoneNumber;
	}
	
	@Override
	public void resetPhoneNumber() {
		PhoneNumber = "012";
	}
}