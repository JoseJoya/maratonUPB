/**
 * Triangular Numbers
 * http://190.156.230.208/exercises/3/exercise_problems/25
 * 
 * Triangular Numbers are positive integer numbers such that they represent an 
 * amount of "dots" with which you can form a compact equilateral triangle of dots.
 * The first five triangular numbers are: 1, 3, 6, 10, 15
 * 
 * For this problem, you must create a program that determines if a given 
 * number n is triangular or not
 */
package triangularnumbers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) {
        try {
             InputStream input = new DataInputStream(new FileInputStream(
             "src/triangularnumbers/tri.in"));
            //InputStream input = System.in;

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    input));

            String params;

            while (!(params = reader.readLine()).equals("0")) {
                BigInteger n = new BigInteger(params);

                long end = 6000000001l;
                long start = 0;
                long mid;

                while (start <= end) {
                    mid = (start + end) / 2;
                    BigInteger midBig = new BigInteger(String.valueOf(mid));
                    BigInteger midNumber = (midBig.multiply(midBig).add(midBig))
                            .divide(TWO);

                    if (midNumber.equals(n)) {
                        System.out.println("YES");
                        break;
                    } else if (midNumber.compareTo(n) < 0) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }

                if (start > end)
                    System.out.println("NO");

            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
