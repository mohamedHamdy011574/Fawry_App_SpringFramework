package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;


public class Vodafone extends RechargeServiceProviders{

	public Vodafone(){
		super("Vodafone","Vodafone123");
		PhoneNumber = "010";
	}
	@Override
	public String getPhoneNumberPrifex() {
		return PhoneNumber;
	}
	
	@Override
	public void resetPhoneNumber() {
		PhoneNumber = "010";
	}
}
