import java.io.*;
import java.util.*;


public class Main {
	public static int NeedOneMore(int a){
		if (a>0) {
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
		int n=cin.nextInt();
		int a_amount=cin.nextInt();
		int a_price=cin.nextInt();
		int b_amount=cin.nextInt();
		int b_price=cin.nextInt();
		int c_amount=cin.nextInt();
		int c_price=cin.nextInt();
		
		int a_p = (n/a_amount+NeedOneMore(n%a_amount))*a_price;
		int b_p = (n/b_amount+NeedOneMore(n%b_amount))*b_price;
		int c_p = (n/c_amount+NeedOneMore(n%c_amount))*c_price;
		
		int p=a_p;
		if (b_p<p) {p=b_p;};
		if (c_p<p) {p=c_p;};
		
		System.out.println(p);

    }
}

