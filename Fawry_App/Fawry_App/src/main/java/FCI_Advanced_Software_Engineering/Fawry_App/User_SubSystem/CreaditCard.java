package FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem;

public class CreaditCard{
	private long creaditId ;
	private int creaditPassword;
	private double creaditBalance = 1000000;
	
	public void setCreaditCardInfo(long creaditId, int creaditPassword){
		this.creaditId = creaditId;
		this.creaditPassword = creaditPassword;
	}
	
	public void ConsumeFunds(double ConsumedBalance) {
		creaditBalance -= ConsumedBalance;
	}
	
	 public double getCreaditBalance() {
		return creaditBalance;
	}
	 
	 public long getCreaditID() {
		return creaditId;
	}
}
