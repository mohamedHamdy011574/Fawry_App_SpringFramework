package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.DataBase_SubSystem.SystemDataBase;

public abstract class RechargeServiceProviders extends ServiceProvider {

	protected String PhoneNumber = "";
	
	protected String landLineNumber = "";
	public void setLandlineNumber(String landLineNumber) {
		this.landLineNumber = landLineNumber;
	}
	
	public void resetLandLineNumber() {
		landLineNumber = "";
	}
	
	
	public RechargeServiceProviders(String ServiceProviderName, String BankMail){
		super(ServiceProviderName,BankMail);
	}

	public void CompleteThePhoneNumber(String RestOfThePhoneNumbers) {
		PhoneNumber += RestOfThePhoneNumbers;	
	}
	

	
	public boolean CompletePayment(double amount) {
		boolean rechargeResponse = true;
		
		if(Service_ProviderController.ValidPhoneNumber(PhoneNumber)) {
			SystemDataBase.CurrentUser.pay(amount);
		}
		else
			rechargeResponse = false;
		
		resetPhoneNumber();
		return rechargeResponse;
	}
	
	public boolean rechargeLandlineInternet(double amount) {
		boolean rechargeResponse = true;
		
		if(Service_ProviderController.ValidLandLineNumber(landLineNumber)) {
			SystemDataBase.CurrentUser.pay(amount);
		}
		else
			rechargeResponse = false;
				
		resetLandLineNumber();
		return rechargeResponse;
	}
	
	public abstract String getPhoneNumberPrifex();
	
	public abstract void resetPhoneNumber();
	
}
