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

public class Naive {

	public static boolean isPerfectSquare(double n) {
		double sqrt = Math.sqrt(n);
		sqrt = sqrt - (sqrt % 1);
		
		if ((sqrt * sqrt) == n) {
			return true;
		}
		
		return false;
	}
	
    public static void main(String[] args) {
        try {
            InputStream input = new DataInputStream(new FileInputStream(
            "src/triangularnumbers/tri.in"));
            //InputStream input = System.in;

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    input));

            String params;

            while (!(params = reader.readLine()).equals("0")) {
                double n = Double.parseDouble(params);

                if (isPerfectSquare(8*n + 1)) {
                	System.out.println("YES");
                } else {
                	System.out.println("NO");
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
