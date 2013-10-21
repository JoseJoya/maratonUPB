/**
 * 12528 – ENVIRONMENT PROTECTION
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3973
 * 
 * Arsenic & Cyanide Mining (ACM) is a corporation that has recently decided to start 
 * developing its mines in the lands near your hometown. As a member of the citizen's 
 * regulatory committee for ACM's operations, your task is to control how much the 
 * corporation can mine from those lands, so that you get to keep the jobs and other 
 * benefits without sacrificing the environment and the health of the local residents...
 */
package environmentprotection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Naive {

	public static double W, D, A, K;
	public static double[] p1 = new double[10];
	public static double[] q1 = new double[10];
	public static double[] p2 = new double[10];
	public static double[] q2 = new double[10];

	public static double eval(double x, double line) {
		double num1, den1, num2, den2;
		num1 = num2 = den1 = den2 = 0.0;
		for (int i = (int) K; i >= 0; i--) {
			num1 += Math.pow(x, i) * p1[i];
			den1 += Math.pow(x, i) * q1[i];
			num2 += Math.pow(x, i) * p2[i];
			den2 += Math.pow(x, i) * q2[i];
		}

		double integral1, integral2;
		integral1 = num1 / den1;
		integral2 = num2 / den2;

		if (line > integral1) {
			return 0;
		} else if (line < integral2) {
			return integral1 - integral2;
		} else
			return integral1 - line;
	}
	
	public static double simps(double a, double b, double line) {
		return ((b - a) / 6.0)
				* (eval(a, line) + 
					(4 * eval((a + b) / 2.0, line)) + 
					eval(b, line));
	}

	public static void main(String[] args) {
		try {
			//InputStream input = new DataInputStream(new FileInputStream(
				//	"src/environmentprotection/env.in"));
			InputStream input = System.in;

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			StringTokenizer tokenizer = new StringTokenizer(" ");

			String params;

			while ((params = reader.readLine()) != null) {
				tokenizer = new StringTokenizer(params);

				W = Integer.parseInt(tokenizer.nextToken());
				D = Integer.parseInt(tokenizer.nextToken());
				A = Integer.parseInt(tokenizer.nextToken());
				K = Integer.parseInt(tokenizer.nextToken());

				params = reader.readLine();
				tokenizer = new StringTokenizer(params);

				for (int i = 0; i <= K; i++) {
					p1[i] = Double.parseDouble(tokenizer.nextToken());
				}

				params = reader.readLine();
				tokenizer = new StringTokenizer(params);

				for (int i = 0; i <= K; i++) {
					q1[i] = Double.parseDouble(tokenizer.nextToken());
				}

				params = reader.readLine();
				tokenizer = new StringTokenizer(params);

				for (int i = 0; i <= K; i++) {
					p2[i] = Double.parseDouble(tokenizer.nextToken());
				}

				params = reader.readLine();
				tokenizer = new StringTokenizer(params);

				for (int i = 0; i <= K; i++) {
					q2[i] = Double.parseDouble(tokenizer.nextToken());
				}

				double eps = 1e-4;
				double x;
				double low = -D;
				double high = 0;

				for (int i = 0; i < 22; i++) {
					double totalArea = 0.0;
					x = (low + high) / 2.0;

					for (double a = 0; a + eps - 1e-5 < W; a += eps) {
						double b = a + eps;
						totalArea += simps(a, b, x);
					}

					if (totalArea < A) {
						high = x;
					} else {
						low = x;
					}
				}

				DecimalFormat numberFormat = new DecimalFormat("#.00000");
				System.out.println(numberFormat.format(-low));
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
