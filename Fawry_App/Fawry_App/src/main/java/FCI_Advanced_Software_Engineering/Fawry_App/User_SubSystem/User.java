package FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem;

import java.util.ArrayList;
import java.util.Random;

import FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem.Observer;
import FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem.*;



public class User extends AccountInfo implements Subject{
	
	private ArrayList<Transaction> EndedServices = new ArrayList<Transaction>();
	private boolean addToWalletTransactions = false;
	
	private Wallet userWallet;
	private CreaditCard userCreaditCard;
	
	private Payment Py;
	
	private Observer admin;
	
	private int TransactionIsFound(int transId) {
		for(int i=0;i<EndedServices.size();i++) {
			if(EndedServices.get(i).getTransactionId() == transId) {
				return i;
			}
		}
		return -1;
	}
	
	public User() {
		userWallet = new Wallet();
		userCreaditCard = new CreaditCard();
		Py = new Payment(this);
	}
	
	public User(String userName,String password, String Email) {
		super(userName,password, Email);
		userWallet = new Wallet();
		userCreaditCard = new CreaditCard();
		Py = new Payment(this);
	}
	
	public Wallet getWallet() {
		return userWallet;
	}
	
	public CreaditCard getCreaditCard() {
		return userCreaditCard;
	}
	
	public long getCreaditCardID() {
		return userCreaditCard.getCreaditID();
	}
	
	public void setPaymentStrategt(Ipayment strategy) {
		Py.setPaymentStrategy(strategy);
	}
	
	public void pay(double amount) {
		
		if(amount < 0)
			amount = 0;
		
		Py.pay(amount);
		
		long random = System.currentTimeMillis();
		Random rng = new Random(random);
		int id = (rng.nextInt()%90000)+10000;
		
		if(id < 0)
			id *= -1;
		
		Transaction newTransaction =  new Transaction(id, amount,"Payment Transaction");
		
		
		if(addToWalletTransactions == true) {
			newTransaction.setStatus("Add to wallet transaction");
			addToWalletTransactions = false;
		}
		
		EndedServices.add(newTransaction);
		
		
		
	}
	
	@Override
	public boolean askForRefund(int id) {
		int transacIndex = TransactionIsFound(id);
		
		if(transacIndex != -1) {
			Transaction targetTransaction = EndedServices.get(transacIndex);
			targetTransaction.setStatus("Waiting for refund");
			this.admin.update(targetTransaction);
			return true;
		}
		else {
			return false;
			
		}
	}
	
	@Override
	public void register(Observer admin) {
		this.admin = admin;
	}
	
	public ArrayList<Transaction> AllUserTransaction() {
			return EndedServices;
	}
	
	public void AddToWalletTransactions(boolean addToWalletTransactions) {
		this.addToWalletTransactions = addToWalletTransactions;
	}
	
	public int getUserTransactionsListSize(){
		return EndedServices.size();
	}
}