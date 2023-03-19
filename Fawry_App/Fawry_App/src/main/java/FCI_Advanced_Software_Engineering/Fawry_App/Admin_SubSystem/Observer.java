package FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.Transaction;

public interface Observer {
	//method to update the observer, used by subject
	public void update(Transaction endedTransaction);
	
}
