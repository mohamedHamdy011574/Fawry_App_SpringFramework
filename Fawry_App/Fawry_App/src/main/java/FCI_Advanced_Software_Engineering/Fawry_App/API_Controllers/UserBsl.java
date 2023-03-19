package FCI_Advanced_Software_Engineering.Fawry_App.API_Controllers;



import FCI_Advanced_Software_Engineering.Fawry_App.Service_SubSystem.*;


import FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem.*;


import FCI_Advanced_Software_Engineering.Fawry_App.Admin_SubSystem.Admin;
import FCI_Advanced_Software_Engineering.Fawry_App.DataBase_SubSystem.SystemDataBase;
import FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem.CreaditCardPayment;
import FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem.PaymentController;
import FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem.WalletPayment;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.Transaction;
import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

public class UserBsl {
	
	private Admin SystemAdmin ;
	
	public UserBsl() {
		SystemAdmin = Admin.getInstance();
		CreateSystemServices();
		CreateRechargeServiceProviders();
		CreateDonationsServiceProvider();
		
	}
	
	public String SignUp(User user){
		if(user == null || (user != null && (user.getEmail() == null || user.getPassword() == null || user.getUserName() == null))) {
			return "SignUp Failed";
		}
		if(getUser(user.getEmail().toLowerCase()) != null) {
			return "Email is already sign up";
		}
		if(authentication.ValidSignUpInfo(user.getUserName(),user.getEmail().toLowerCase(), user.getPassword())) {
			User newUser = new User(user.getUserName(),user.getPassword(),user.getEmail().toLowerCase());
			newUser.register(SystemAdmin);
			SystemDataBase.users.add(newUser);
			return "SignUp done Successfully";
		}
		return "SignUp Failed";
	}
	
	public String Login(User user){
	
		if( SystemDataBase.CurrentUser != null && SystemDataBase.CurrentUser.getEmail().equals(user.getEmail().toLowerCase()))
			return "You already loged in try to login with other user";
		if(authentication.ValidUserAccount(user.getEmail().toLowerCase(), user.getPassword())) {
			
			SystemDataBase.CurrentUser = getUser(user.getEmail().toLowerCase());
			
			return "Login done Successfully, current User is: " + SystemDataBase.CurrentUser.getEmail() ;
		}
		return "Login Failed";
	}
	
	public String HomePage(UserSelections selections) {
		
		if(SystemDataBase.CurrentUser != null) {
			if(selections == null || selections.ServiceNumber == null || (selections.ServiceNumber != null && selections.ServiceNumber.isEmpty())) {
				String myList = "";
				for(int i=0;i<SystemDataBase.SystemServices.size();i++) {
					myList += (i+1) + ". " + SystemDataBase.SystemServices.get(i).getServiceName() + "\n";
				}
				return myList;
			}
			
			return showServiceProviders(selections);
		}
		return "You must Login first to access the user services";
		
	}
	
	

//------------------------------------------------------------------------------------------
	private String showServiceProviders(UserSelections selections) {
		
		switch (selections.ServiceNumber) {
		case "1":
			return rechargeService(selections);
		case "2":
			return rechargeService(selections);
		case "3":
			return LandLineService(selections);
		case "4":
			return DonationService(selections);
		case "5":
			return goToWallet(selections.amountAddedToWallet);
		case "6":
			return Search(selections.ServiceToSearch);
		case "7":
			return AskForRefund(selections.transactionToRefund);
		case "8":
			return showAllTransactions();
		default:	
			return "Enter a valid service number";
		}
		
	}
	
	
	private String rechargeService(UserSelections selections) {
		
		if(selections.ServiceProviderNumber == null || (selections.ServiceProviderNumber != null && selections.ServiceProviderNumber.isEmpty())|| (Integer.parseInt(selections.ServiceProviderNumber) > 4 || Integer.parseInt(selections.ServiceProviderNumber) < 1 ) )
		{
			String myList = "";
			for(int i=0;i<SystemDataBase.RechargeServiceProviders.size();i++) {
				myList += (i+1) + ". " + SystemDataBase.RechargeServiceProviders.get(i).getServiceProviderName() + "\n";
			}
			return myList;
		}
		if(selections.ServiceNumber.equals("1"))
			return RechargeMobile(selections);
		else
			return InternetRecharge(selections);
	}
	


	// no switch between recipt
	private String LandLineService(UserSelections selections) {
		
		if(selections.ServiceProviderNumber == null || (selections.ServiceProviderNumber != null && selections.ServiceProviderNumber.isEmpty()) || (Integer.parseInt(selections.ServiceProviderNumber) > 2 || Integer.parseInt(selections.ServiceProviderNumber) < 1 ))
		{
			return "1. Quarter Recipt (" + SystemDataBase.myLandlineProvider.getQuarterRecipt()+ "). \n"
					+ "2. Monthly Recipt (" + SystemDataBase.myLandlineProvider.getMonthlyRecipt() + "). \n";
		}
		return LandLinePay(selections);
	}
			
	private String DonationService(UserSelections selections) {
		
		if(selections.ServiceProviderNumber == null || ((selections.ServiceProviderNumber != null && selections.ServiceProviderNumber.isEmpty()))|| (Integer.parseInt(selections.ServiceProviderNumber) > 3 || Integer.parseInt(selections.ServiceProviderNumber) < 1 ) )
		{
			String myList = "";
			for(int i=0;i<SystemDataBase.donationsServiceProviders.size();i++) {
				myList += (i+1) + ". " + SystemDataBase.donationsServiceProviders.get(i).getServiceProviderName() + "\n";
			}
			return myList;
		}
		return DonationPay(selections);
		
		
	}
	
	private String goToWallet(double amount) {
		if(amount <= 0)
			return "wallet Balance: "+ SystemDataBase.CurrentUser.getWallet().getBalance() 
									+ ", Enter amountAdedToWallet > 0 to be added in the wallet ";
		
		SystemDataBase.CurrentUser.getCreaditCard().setCreaditCardInfo(5196081888500645l,000);
		SystemDataBase.CurrentUser.setPaymentStrategt(new CreaditCardPayment());
		SystemDataBase.CurrentUser.getWallet().addFunds(amount);
		SystemDataBase.CurrentUser.AddToWalletTransactions(true);
		SystemDataBase.CurrentUser.pay(amount);
		
		return "added Balance: " + amount +
					", Wallet Balance: "+ SystemDataBase.CurrentUser.getWallet().getBalance();
	}
	
	public String Search( String ServiceToSearch) {
		if(ServiceToSearch == null || ( ServiceToSearch != null && ServiceToSearch.isEmpty())) {
			return "Enter your search in ServiceToSearch ";
		}
		else {
			
			String servicesInSearch = "";
			for (service myService : SystemDataBase.SystemServices) {

				if(myService.getServiceName().toLowerCase().startsWith(ServiceToSearch.toLowerCase())) {
					servicesInSearch += myService.getServiceName() + "\n";
				}
			   }
			if(servicesInSearch.isEmpty()) {
				return "No service match that search";
			}
			else {
				return servicesInSearch;
			}
		}
	}

	public String AskForRefund(int transactionID) {
		if(transactionID == -1 ) {
			return "Enter your id in transactionToRefund to use it";
		}
		for (Transaction oneTransaction : SystemDataBase.CurrentUser.AllUserTransaction()) {
			if(oneTransaction.getTransactionId() == transactionID) {
				if(oneTransaction.getStatus().equals("Waiting for refund"))
					return "Transaction is already  in refund list ";
				SystemDataBase.CurrentUser.askForRefund(transactionID);
				return "transaction with id "+ transactionID+" is ' NOW 'in refund list";
			}
		}
		return "transaction with id "+ transactionID+" is ' NOT ' in the transaction list";
	}
	
	public String showAllTransactions() {
		String allTransactions = "";
		for (Transaction oneTransaction : SystemDataBase.CurrentUser.AllUserTransaction()) {
			
			allTransactions += oneTransaction.transactionInfo() + "\n"; 
		}
		
		if(allTransactions.isEmpty()) {
			return "No Transactions in the list";
		}
		else {
			return allTransactions;
		}
	}
	
	private User getUser(String mail) {
		for (User user : SystemDataBase.users) {
			if(user.getEmail().equals(mail))
				return user;
		}
		return null;
	}
	
	private RechargeServiceProviders getServiceProvider(String targetServiceProvider) {
		return SystemDataBase.RechargeServiceProviders.get(Integer.parseInt(targetServiceProvider)-1);
	}
	
	private service getService(String targetServiceProvider) {
		return SystemDataBase.SystemServices.get(Integer.parseInt(targetServiceProvider)-1);
	}
	
	private void CreateSystemServices() {

		SystemDataBase.SystemServices.add(new service("Mobile recharge services","Payment Service"));
		SystemDataBase.SystemServices.add(new service("Internet Payment services","Payment Service"));
		SystemDataBase.SystemServices.add(new service("Landline services","Payment Service"));
		SystemDataBase.SystemServices.add(new service("Donations","NonPayment Service"));
		SystemDataBase.SystemServices.add(new service("Wallet Information","NonPayment Service"));
		SystemDataBase.SystemServices.add(new service("Search For Service","NonPayment Service"));
		SystemDataBase.SystemServices.add(new service("Ask For Refund","NonPayment Service"));
		SystemDataBase.SystemServices.add(new service("Show All Transactions","NonPayment Service"));
	}
	
	private void CreateRechargeServiceProviders() {

		SystemDataBase.RechargeServiceProviders.add(new Etislat());
		SystemDataBase.RechargeServiceProviders.add(new We());
		SystemDataBase.RechargeServiceProviders.add(new Vodafone());
		SystemDataBase.RechargeServiceProviders.add(new Orange());
	}
	
	private void CreateDonationsServiceProvider() {

		SystemDataBase.donationsServiceProviders.add(new DonationsServiceProvider("Canser Hspital"));
		SystemDataBase.donationsServiceProviders.add(new DonationsServiceProvider("Schools"));
		SystemDataBase.donationsServiceProviders.add(new DonationsServiceProvider("NGOs"));
	}	
	private String RechargeMobile(UserSelections selections) {
		
		if(selections.CreditCardOrWallet == -1 || selections.PhoneNumber == null || (selections.PhoneNumber != null && selections.PhoneNumber.isEmpty()) || selections.amoubtToPay <= 0) {
			return "Choose the payment method, phone number and 'amountToPay' > 0 to recharge to complete the recharge";
		}
		
		
		double discount = getService(selections.ServiceNumber).getdiscountValue();
		selections.amoubtToPay -= discount;
		
		if(selections.CreditCardOrWallet == 1) {
			SystemDataBase.CurrentUser.getCreaditCard().setCreaditCardInfo(5196081888500645l,000);
			if(PaymentController.ValidCreaditcard(SystemDataBase.CurrentUser.getCreaditCardID())) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new CreaditCardPayment());
			}
		}
		
		else if(selections.CreditCardOrWallet == 2){
			
			if(SystemDataBase.CurrentUser.getWallet().getBalance() >= selections.amoubtToPay) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new WalletPayment());
			}
			else
				return "Wallet Balance not enough";
		}
		
		else {
			return "Enter  1 for credit Payment and 2 for wallet Payment , no other numbers";
		}
		
		getServiceProvider(selections.ServiceProviderNumber).CompleteThePhoneNumber(selections.PhoneNumber);
		if (getServiceProvider(selections.ServiceProviderNumber).CompletePayment(selections.amoubtToPay)) {
			if(selections.amoubtToPay < 0)
				selections.amoubtToPay = 0;
			return selections.amoubtToPay +" LE is payid for: " + getServiceProvider(selections.ServiceProviderNumber).getServiceProviderName();
		}
		return "Enter a valid phone number enter the numebrs after : " 
					+ getServiceProvider(selections.ServiceProviderNumber).getPhoneNumberPrifex();
	}
	
	private String InternetRecharge(UserSelections selections) {

		if(selections.CreditCardOrWallet == -1 || selections.LandLineNumber == null || (selections.LandLineNumber != null && selections.LandLineNumber.isEmpty())) {
			return "Choose the payment method and enter the Landline number to complete the recharge";
		}
		
		selections.amoubtToPay = 200.0;
		
		double discount = getService(selections.ServiceNumber).getdiscountValue();
		selections.amoubtToPay -= discount;
		
		if(selections.CreditCardOrWallet == 1) {
			SystemDataBase.CurrentUser.getCreaditCard().setCreaditCardInfo(5196081888500645l,000);
			if(PaymentController.ValidCreaditcard(SystemDataBase.CurrentUser.getCreaditCardID())) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new CreaditCardPayment());
			}
		}
		else if(selections.CreditCardOrWallet == 2){
			if(SystemDataBase.CurrentUser.getWallet().getBalance() >= selections.amoubtToPay) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new WalletPayment());
			}
			else
				return "Wallet Balance not enough";
		}
		else {
			return "Enter  1 for credit Payment and 2 for wallet Payment , no other numbers";
		}
		
		getServiceProvider(selections.ServiceProviderNumber).setLandlineNumber(selections.LandLineNumber);
		if (getServiceProvider(selections.ServiceProviderNumber).rechargeLandlineInternet(selections.amoubtToPay)) {
			if(selections.amoubtToPay < 0)
				selections.amoubtToPay = 0;
			return selections.amoubtToPay+" LE (from 200) Internet is payid for: " + getServiceProvider(selections.ServiceProviderNumber).getServiceProviderName();
		}
		return "Enter a valid Landline number start with +20 " ;
	
	}
	

	private String LandLinePay(UserSelections selections) {
		
		if(selections.CreditCardOrWallet == -1 || (selections.LandLineNumber2 != null && selections.LandLineNumber2.isEmpty())|| selections.LandLineNumber2 == null) {
			return "Choose the payment method, Landline2 number and 'amountToPay' > 0 to recharge to complete the recharge";
		}
		
		if(selections.ServiceProviderNumber.equals("1")) {
			selections.amoubtToPay = SystemDataBase.myLandlineProvider.getQuarterRecipt();
		}
		else{
			selections.amoubtToPay = SystemDataBase.myLandlineProvider.getMonthlyRecipt();
		}
		
		double discount = getService(selections.ServiceNumber).getdiscountValue();
		selections.amoubtToPay -= discount;
		

		if(selections.CreditCardOrWallet == 1) {
			SystemDataBase.CurrentUser.getCreaditCard().setCreaditCardInfo(5196081888500645l,000);
			if(PaymentController.ValidCreaditcard(SystemDataBase.CurrentUser.getCreaditCardID())) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new CreaditCardPayment());
			}
		}
		else if(selections.CreditCardOrWallet == 2){
			if(SystemDataBase.CurrentUser.getWallet().getBalance() >= selections.amoubtToPay) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new WalletPayment());
			}
			else
				return "Wallet Balance not enough";
		}
		else {
			return "Enter  1 for credit Payment and 2 for wallet Payment , no other numbers";
		}
		SystemDataBase.myLandlineProvider.setLandlineNumber(selections.LandLineNumber2);
		if (SystemDataBase.myLandlineProvider.CompletePayment(selections.amoubtToPay)) {
			if(selections.amoubtToPay < 0)
				selections.amoubtToPay = 0;
			return selections.amoubtToPay +" LE is payid ,discount equal = "+ discount;
		}
		return "Enter a valid LandlIne2 Number start with +20";
	}
	
	private String DonationPay(UserSelections selections) {
		if(selections.CreditCardOrWallet == -1 || selections.amoubtToPay <= 0) {
			return "Choose the payment method and 'amountToPay' > 0 to recharge to complete the recharge";
		}	
		double discount = getService(selections.ServiceNumber).getdiscountValue();
		selections.amoubtToPay -= discount;
		

		if(selections.CreditCardOrWallet == 1) {
			SystemDataBase.CurrentUser.getCreaditCard().setCreaditCardInfo(5196081888500645l,000);
			if(PaymentController.ValidCreaditcard(SystemDataBase.CurrentUser.getCreaditCardID())) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new CreaditCardPayment());
			}
		}
		else if(selections.CreditCardOrWallet == 2){
			if(SystemDataBase.CurrentUser.getWallet().getBalance() >= selections.amoubtToPay) {
				SystemDataBase.CurrentUser.setPaymentStrategt(new WalletPayment());
			}
			else
				return "Wallet Balance not enough";
		}
		else {
			return "Enter  1 for credit Payment and 2 for wallet Payment , no other numbers";
		}
		
		SystemDataBase.CurrentUser.pay(selections.amoubtToPay);
		return (selections.amoubtToPay)+" LE Donate done successfully for " +SystemDataBase.donationsServiceProviders.get(Integer.parseInt(selections.ServiceProviderNumber)-1).getServiceProviderName();
			
	}
}
