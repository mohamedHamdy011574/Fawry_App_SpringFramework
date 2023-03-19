package FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

public class CreaditCardPayment implements Ipayment{

	@Override
	public void Pay(double amount, User myUser) {
		System.out.println("Pay with CreaditCard");
		
		myUser.getCreaditCard().ConsumeFunds(amount);
	}

}
