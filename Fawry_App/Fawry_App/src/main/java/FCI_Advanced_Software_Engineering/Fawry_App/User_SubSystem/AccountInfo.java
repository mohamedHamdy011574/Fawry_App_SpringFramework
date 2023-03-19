package FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem;

public abstract class AccountInfo{
    private String userName;
    private String password;
    private String Email;


  public AccountInfo() {
     	this.userName = "No name";
         this.password = "No password";
         this.Email = "No email";
         
    }
 public AccountInfo(String userName,String password, String Email) {
 	this.userName = userName.toLowerCase();
     this.password = password.toLowerCase();
     this.Email = Email.toLowerCase();
     
 }
 
 public String getUserName() {
         return userName;
     }

 public void setUserName(String userName) {
         this.userName = userName.toLowerCase();
     }

 public String getPassword() {
         return password;
     }

 public void set(String password) {
         this.password = password.toLowerCase();
     }
 
 public String getEmail() {
     return Email;
 }

 public void setEmail(String Email) {
     this.Email = Email.toLowerCase();
 }
 public void viewProfile(){
         System.out.println("");
         System.out.println("AccountInfo{ Email = " + Email + ", userName = " +userName+", password = " + password + '}');
         System.out.println("");
 }
 
 
 
}
