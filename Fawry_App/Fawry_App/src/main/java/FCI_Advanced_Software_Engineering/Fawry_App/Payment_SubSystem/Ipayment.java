package FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

public interface Ipayment {
	void Pay(double amount, User myUser);
}
