package FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem;

import java.util.HashMap;

import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.AccountInfo;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.Transaction;


/**
 *
 * @author Hamdy
 */
public class Admin extends AccountInfo implements Observer {
	
	
	private HashMap<Transaction,Double> RefundList = new HashMap<Transaction,Double>();
	public boolean login = false;
	
    private Admin() {
    	super("Eng.mohamed samir","0000","Admin@admin.com");
    }
    
    private static Admin myAdmin = null;
    
     public static Admin getInstance() {
    	if(myAdmin == null) {
    		myAdmin = new Admin();
    	}
    	return myAdmin;
    	
    }
     
	public  String showAllRefundTransactions() {
		if(RefundList.size() > 0) {
			String myList ="";
			for (HashMap.Entry<Transaction,Double> entry : RefundList.entrySet()) {
				myList += entry.getKey().transactionInfo() + "\n";
			}
			
			return myList;
		}
		return "There is no refund ransaction in teh list";
	}
	
    
	// take a string choise : rejected or accepted
	public boolean manageRefundTransaction(int id, String choise) {
		Transaction targetTransaction = null;
				
		for (HashMap.Entry<Transaction, Double> entry : RefundList.entrySet()) {
			Transaction transaction = entry.getKey();
			if(transaction.getTransactionId() == id) {
				targetTransaction = transaction;
				break;
			}
		}
		
		if(targetTransaction == null) {
			return false;
		}
		else {
			targetTransaction.setStatus(choise);
			RefundList.remove(targetTransaction);
			return true;
		}
	}

	//method to update the observer, used by subject
	public void update(Transaction endedTransac) {
			RefundList.put(endedTransac,endedTransac.getTransactionAmount());
			
	}
	
	
        
}
