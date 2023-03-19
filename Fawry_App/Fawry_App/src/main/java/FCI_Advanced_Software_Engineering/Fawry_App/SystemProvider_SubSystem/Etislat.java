package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;


public class Etislat extends RechargeServiceProviders{

	public Etislat(){
		super("Etislat","Etislat123");
		PhoneNumber = "011";
	}

	public String getPhoneNumberPrifex() {
		return PhoneNumber;
	}
	
	@Override
	public void resetPhoneNumber() {
		PhoneNumber = "011";
	}
}
