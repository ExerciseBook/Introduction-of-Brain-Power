import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        int[] a = new int[20];
        a[0]=1;a[1]=1;
        for (int i=2;i<=19;i++) a[i]=a[i-1]+a[i-2];
        for (int i=0;i<=19;i++) System.out.print(a[i]+" ");
        System.out.print("\n");
    }
}