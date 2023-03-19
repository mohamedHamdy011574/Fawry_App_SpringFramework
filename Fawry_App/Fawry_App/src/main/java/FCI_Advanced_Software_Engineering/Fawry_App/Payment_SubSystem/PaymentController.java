package FCI_Advanced_Software_Engineering.Fawry_App.Payment_SubSystem;

public class PaymentController {

	
	private static int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
         
        return sum;
    }
 
    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    private static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }
 
    // Return sum of odd-place digits in number
    private static int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");       
        return sum;
    }
 
    // Return true if the digit d is a prefix for number
    private static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }
 
    // Return the number of digits in d
    private static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }
 
    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    private static long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
	
	public static boolean ValidCreaditcard(long CreaditCardId) {
	       return (getSize(CreaditCardId) >= 13 &&
	               getSize(CreaditCardId) <= 16) &&
	               (prefixMatched(CreaditCardId, 4) ||
	                prefixMatched(CreaditCardId, 5) ||
	                prefixMatched(CreaditCardId, 37) ||
	                prefixMatched(CreaditCardId, 6)) &&
	              ((sumOfDoubleEvenPlace(CreaditCardId) +
	                sumOfOddPlace(CreaditCardId)) % 10 == 0);
		
	}
	 

	
	
}
