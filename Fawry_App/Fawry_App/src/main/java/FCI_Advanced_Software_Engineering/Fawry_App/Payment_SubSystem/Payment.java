package FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

public class Payment {
	
	private Ipayment paymentStrategy;
	private User myUser;
	
	public Payment(User myUser){
		this.myUser = myUser;
	}
	
	public void setPaymentStrategy(Ipayment paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}
	public Ipayment getPaymentStrategy() {
		
		return paymentStrategy;
	}
	public void pay(double amount){
		paymentStrategy.Pay(amount, myUser);
	}
}
