package FCI_Advanced_Software_Engineering.Fawry_App.API_Controllers;



import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Admin_Model_Controller {
	
	private AdminBsl myAdminBsl = new AdminBsl();

	
	@PostMapping(value="/Admin/Login")
	public String adminLogin(@RequestHeader String email , @RequestHeader String password) {
		return myAdminBsl.adminLogin(email,password);
	}
	
	@GetMapping(value="/Admin/HomePage")
	public String adminHomePage() {
		return myAdminBsl.adminHomepage();
	}
	
	@PostMapping(value="/AddDiscount/OverAllDiscount")
	public String addOverAllDiscount(@RequestParam double amountAdded ) {
		
		return myAdminBsl.addOverAllDiscount(amountAdded);
	}
	
	@PostMapping(value="/AddDiscount/ServiceDiscount")
	public String addSpecificDiscount(@RequestParam double amount,@RequestParam String serviceName) {
		return myAdminBsl.addSpecificDiscount(amount,serviceName);
	}
	
	@GetMapping(value="/Admin/showRefundTransactions")
	public String showAllRefundTransactions() {
		return myAdminBsl.showAllRefundTransactions();
	}
	
	@GetMapping(value="/Admin/showAllUserPaymentTransaction")
	public String showAllUserPaymentTransaction(@RequestParam String userEmail) {
		return myAdminBsl.showAllUserPaymentTransaction(userEmail.toLowerCase());
	}
	
	@GetMapping(value="/Admin/showAllUserAddedToWalletTransaction")
	public String showAllUserAddedToWalletTransaction(@RequestParam String userEmail) {
		return myAdminBsl.showAllUserAddedToWalletTransaction(userEmail.toLowerCase());
	}
	
	@GetMapping(value="/Admin/showAllUserRefundTransaction")
	public String showAllUserRefundTransaction(@RequestParam String userEmail) {
		return myAdminBsl.showAllUserRefundTransaction(userEmail.toLowerCase());
	}
	
	@GetMapping(value="/Admin/RefundResponse")
	public String AcceptOrReject(@RequestParam int transactionId, int acceptOrNot) {
		return myAdminBsl.AcceptOrReject(transactionId, acceptOrNot);
	}
}
