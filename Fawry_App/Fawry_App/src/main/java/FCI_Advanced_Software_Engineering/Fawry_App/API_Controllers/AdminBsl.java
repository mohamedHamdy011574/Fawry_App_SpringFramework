package FCI_Advanced_Software_Engineering.Fawry_App.API_Controllers;

import FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem.Admin;

import FCI_Advanced_Software_Engineering.Fawry_App.DataBase_SubSystem.SystemDataBase;
import FCI_Advanced_Software_Engineering.Fawry_App.Service_SubSystem.service;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.Transaction;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

public class AdminBsl {
	
	private Admin SystemAdmin;
	
	public AdminBsl() {
		SystemAdmin = Admin.getInstance();
		createAdminServices();
	}
	public String adminLogin(String email, String password){
		if(SystemAdmin.login == true)
			return "Admin is already loged in ";
		
		if(SystemAdmin.getEmail().toLowerCase().equals(email.toLowerCase())) {
			if(SystemAdmin.getPassword().equals(password)) {
				SystemAdmin.login = true;
				return "Admin login Successfully , welcome back: "
						+  SystemAdmin.getUserName();
			}
		}
		
		return email +" "+password + " "+SystemAdmin.getEmail();
	}
	
	public String adminHomepage(){
		if(SystemAdmin.login != true)
			return "You must login first as an admin to use admin services";
		String myList = "";
		for(int i=0;i<SystemDataBase.AdminServices.size();i++) {
			myList += (i+1) + ". " + SystemDataBase.AdminServices.get(i).getServiceName() + "\n";
		}
		return myList;		
	}
	
	public String addOverAllDiscount(double discount) {
		if(SystemAdmin.login != true)
			return "You must login first as an admin to use admin services";
		if(discount > 0) {
			for (service oneService : SystemDataBase.SystemServices) {
				if(oneService.getServiceType().equals("Payment Service"))
					oneService.setdiscountValue(discount);
			}
			return "Discount is applied on all payment services on the user system";
		}
		return "Discount must be greater than  0";
	}
	
	public String addSpecificDiscount(double discount, String  serviceName) {
		if(SystemAdmin.login != true)
			return "You must login first as an admin to use admin services";
		if(discount > 0) {
			for (service oneService : SystemDataBase.SystemServices) {
				if(oneService.getServiceName().toLowerCase().equals(serviceName.toLowerCase())) {
					if(!oneService.getServiceType().equals("NonPayment Service")) {
						oneService.setdiscountValue(discount);
						return "Discount is applied on service with name is : " + serviceName;
					}
					else
						return "This service is a non payment service";
				}
			}
			return "No service with name: " + serviceName;
			
		}
		return "Disount must be greater than  0" + discount;
	}
	
	public String showAllRefundTransactions() {
		if(SystemAdmin.login != true)
			return "You must login first as an admin to use admin services";
		else
			return SystemAdmin.showAllRefundTransactions();
	}
	
	public String showAllUserPaymentTransaction(String userEmail) {	
		return goAndGit(userEmail, "Payment Transaction");
	}
	
	public String showAllUserAddedToWalletTransaction(String userEmail) {
		return goAndGit(userEmail, "Add to wallet transaction");
	}
	
	public String showAllUserRefundTransaction(String userEmail) {
		return goAndGit(userEmail, "Waiting for refund");
	}
	
	public String AcceptOrReject(int transactionId, int acceptOrReject) {
		if((acceptOrReject == 1 || acceptOrReject == 0) && transactionId > 0) {
			
			boolean flag = false;
			
			String choise = "Rjected refund";
			if(acceptOrReject == 1) {
				choise = "Accepted refund";
				flag = SystemAdmin.manageRefundTransaction(transactionId, choise);
			}
			else
				flag = SystemAdmin.manageRefundTransaction(transactionId, choise);
			
			if(flag == true)
				return "Refund Transaction now is : " + choise;
			else
				return "Refund Transaction is not in the list";
		}
		return "Enter a valid ID and Choise";
	}
//--------------------------------------------------------------------------------
	private void createAdminServices() {
		SystemDataBase.AdminServices.add(new service("Add Discount","NonPayment Service"));
		SystemDataBase.AdminServices.add(new service("Show All Refund Transactions","NonPayment Service"));
		SystemDataBase.AdminServices.add(new service("User payment transactions","NonPayment Service"));
		SystemDataBase.AdminServices.add(new service("User Add to wallet transactions","NonPayment Service"));
		SystemDataBase.AdminServices.add(new service("User Refund transactions","NonPayment Service"));
	}
	
	private User getUser(String mail) {
		for (User user : SystemDataBase.users) {
			if(user.getEmail().equals(mail.toLowerCase()))
				return user;
		}
		return null;
	}
	
	public String goAndGit(String userEmail, String transactionType) {
		if(getUser(userEmail) != null ) {
			
			String myList = "";
			for (Transaction transaction : getUser(userEmail.toLowerCase()).AllUserTransaction()) {
				if(transaction.getStatus().equals(transactionType)) {
					myList += transaction.transactionInfo() +"\n";
				}
			}
			if(myList.isEmpty()) {
				return "No transactions with this type for this user";
			}
			else
				return myList;
		}
		return "No user with email: " + userEmail;
	}
}
