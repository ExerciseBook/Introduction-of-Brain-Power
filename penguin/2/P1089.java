import java.io.*;
import java.util.*;

public class Main {
	
	static int remain=0, store=0;
	
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
		for (int i=1;i<=12;i++){
			int a = cin.nextInt();
			remain+=300;
			if (remain<a) {
				System.out.println(-i);
				return;
			}
			else
			if (remain-a>=100) {
				int b = (remain-a) / 100 * 100;
				remain -= b;
				store += b;
			};
			remain -= a;
		}
		System.out.println(remain+store*6/5);
    }
}