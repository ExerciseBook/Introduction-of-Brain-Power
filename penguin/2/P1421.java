import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner cin = new Scanner(System.in);
        int ah = cin.nextInt(), an = cin.nextInt(), bh = cin.nextInt(), bn = cin.nextInt();
		int dn = (bh * 60 + bn) - (ah * 60 + an);
		if (dn==0) {
			dn = 24*60;
		}
		int dh = dn / 60;
		dn = dn % 60;
        System.out.printf("%d %d\n",dh,dn);
    }
}