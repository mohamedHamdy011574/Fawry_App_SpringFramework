package FCI_Advanced_Software_Engineering.Fawry_App.Service_SubSystem;

public  class service {
	
	private String serviceName;
	private String serviceType;
	private double discountValue;
	

	public service(String serviceName, String serviceType){
		this.serviceName = serviceName;
		this.discountValue = 0.0;
		this.serviceType = serviceType;
	}
	public String getServiceName(){
		return serviceName;
	}
	public void setdiscountValue(double value) {
		discountValue = value;
	}
	public double getdiscountValue() {
		return discountValue;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public void removeDiscount() {
		discountValue = 0.0;
	}
}
