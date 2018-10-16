import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
		int ansd=0,ansv=0;
		for (int i=1;i<=7;i++){
			int a=cin.nextInt(), b=cin.nextInt();
			if (a+b>ansv) {
				ansv=a+b;
				ansd=i;
			}
		}
		System.out.println(ansd);
    }
}