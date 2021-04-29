import java.util.*;

public class test {
    public static void main(String[] args) {
        int a = 0xffffff;
        int b = 0xffff00;
        // Scanner s = new Scanner(System.in);
        // String xx =  s.nextLine(); 
        // b = Integer.parseInt(xx , 16);
        a = ~(a ^ b) ^ 0xff000000;
        System.out.println(Integer.toHexString(a));
        // s.close();
    }
}
