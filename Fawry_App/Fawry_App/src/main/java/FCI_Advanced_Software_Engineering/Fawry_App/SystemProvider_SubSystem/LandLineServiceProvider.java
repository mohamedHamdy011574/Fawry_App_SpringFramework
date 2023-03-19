package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;

import FCI_Advanced_Software_Engineering.Fawry_App.DataBase_SubSystem.SystemDataBase;

public class LandLineServiceProvider extends ServiceProvider{
	
	protected String landLineNumber = "";
	
	public void setLandlineNumber(String landLineNumber) {
		this.landLineNumber = landLineNumber;
	}
	
	public void resetLandLineNumber() {
		landLineNumber = "";
	}
	
	
	public LandLineServiceProvider() {
		super("LandLine", "landLineBank123");
		// TODO Auto-generated constructor stub
	}
	
	private final double QuarterRecipt = 300.0;
	private final double MonthlyRecipt = 150.0;
		
	public double getQuarterRecipt() {
		return QuarterRecipt;
	}
	public double getMonthlyRecipt() {
		return MonthlyRecipt;
	}
	

	public boolean CompletePayment(double amount) {
			
		boolean rechargeResponse = true;
		
		if(Service_ProviderController.ValidLandLineNumber(landLineNumber)) {
			SystemDataBase.CurrentUser.pay(amount);
		}
		else
			rechargeResponse = false;
		
		resetLandLineNumber();
		return rechargeResponse;
	}
	
	
}
