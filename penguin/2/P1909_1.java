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
		TInfo a = new TInfo();
		a.amount=cin.nextInt();
		a.price=cin.nextInt();
		TInfo b = new TInfo();
		b.amount=cin.nextInt();
		b.price=cin.nextInt();
		TInfo c = new TInfo();
		c.amount=cin.nextInt();
		c.price=cin.nextInt();
		
		a.p = (n/a.amount+NeedOneMore(n%a.amount))*a.price;
		b.p = (n/b.amount+NeedOneMore(n%b.amount))*b.price;
		c.p = (n/c.amount+NeedOneMore(need%c.amount))*c.price;
		
		int p=a.p;
		if (b.p<p) {p=b.p;};
		if (c.p<p) {p=c.p;};
		
		System.out.println(p);
		
		
		/*
		int price=0;
		int need=n;

		while (need>0) {
			System.out.printf("%d %d\n",price,need);
			
			if (need>=a.amount) {a.p = a.price / a.amount;} else {a.p = a.price / need;};
			if (need>=b.amount) {b.p = b.price / b.amount;} else {b.p = b.price / need;};
			if (need>=c.amount) {c.p = c.price / c.amount;} else {c.p = c.price / need;};
			
			TInfo p=a;
			if (b.p<p.p) {p=b;};
			if (c.p<p.p) {p=c;};
			
			price += p.price;
			need -= p.amount;
		}
		System.out.println(price);
		*/
    }
}

public class TInfo {
    public int amount;
    public int price;
    //public double p;
    public int p;
	public static void main(String args[]) {
	}
}
