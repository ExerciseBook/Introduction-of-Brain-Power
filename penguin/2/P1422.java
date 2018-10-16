import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
		
		/*
			0-150 0.4463
			151-400 0.4663
			401+ 0.5663
		*/
		int l1,l2,l3;
		l1 = Math.min(150,a);
		a=a-l1;
		l2 = Math.min(250,a);
		a=a-l2;
		l3 = a;
		
		System.out.printf("%.1f\n",l1*0.4463+l2*0.4663+l3*0.5663);
    }
}