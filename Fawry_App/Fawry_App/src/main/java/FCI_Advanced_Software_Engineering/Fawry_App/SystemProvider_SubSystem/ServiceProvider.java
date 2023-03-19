package FCI_Advanced_Software_Engineering.Fawry_App.SystemProvider_SubSystem;

public abstract class ServiceProvider {
	

	
	protected String ServiceProviderName;
	protected String BankMail;
	
	public ServiceProvider(String ServiceProviderName, String BankMail){
		this.ServiceProviderName = ServiceProviderName;
		this.BankMail = BankMail;
	}
	

	
	public String getServiceProviderName() {
		return ServiceProviderName;
	}

}
