package FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem.Observer;

public interface Subject {
	//methods to register
	public void register(Observer obj);
	
	// publid void unregister(Observers obj)
	
	//methods to notify Observers with update
	public boolean askForRefund(int id);
}
