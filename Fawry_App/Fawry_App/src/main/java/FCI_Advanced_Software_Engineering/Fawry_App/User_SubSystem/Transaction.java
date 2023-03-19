package FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem;

public class Transaction {
	
	private int transactionId;
	private double transactionAmount;
	private String transactionStatus;
	
	public Transaction(int transacationId, double transactionAmount, String transactionStatus) {
		this.transactionId = transacationId;
		this.transactionAmount = transactionAmount;
		this.transactionStatus = transactionStatus;
	}
	
	public double getTransactionAmount(){
		return transactionAmount;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public void setStatus(String status) {
		this.transactionStatus = status;
	}
	public String getStatus() {
		return transactionStatus;
	}
	public String transactionInfo() {
		return "Transaction Id: "+ transactionId 
							+ ", Transaction Amount: "+ transactionAmount+", Status: "+ transactionStatus+" ";
	}
}
