package FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem;

public class Wallet {
	
	double userBalance = 0.0;
	
	public void addFunds(double addedBalance) {
		userBalance += addedBalance;
	}
	
	public void ConsumeFunds(double ConsumedBalance) {
		userBalance -= ConsumedBalance;
	}
	
	public double getBalance() {
		return userBalance;
	}

}
