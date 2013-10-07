/**
 * 12527 - DIFFERENT DIGITS
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3972
 *
 * The inhabitants of Nlogonia are very superstitious. One of their beliefs is that 
 * street house numbers that have a repeated digit bring bad luck for the residents. 
 * Therefore, they would never live in a house which has a street number like 838 or 1004.
 * 
 * The Queen of Nlogonia ordered a new seaside avenue to be built, and wants to assign to 
 * the new houses only numbers without repeated digits, to avoid discomfort among her subjects. 
 * You have been appointed by Her Majesty to write a program that, given two integers N and M, 
 * determines the maximum number of houses that can be assigned street numbers between N and M, 
 * inclusive, that do not have repeated digits.
 */
package differentdigits;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static final int MAX = 5001;
	public static int[] dig = new int[MAX];

	public static void main(String[] args) {
		try {
			dig[0] = 0;
			for (int i = 1; i < MAX; i++)
				dig[i] = dig[i - 1] + different(i);

			//InputStream input = new DataInputStream(new FileInputStream(
			//"src/differentdigits/digits.in"));
			InputStream input = System.in;

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			StringTokenizer tokenizer = new StringTokenizer(" ");

			String params;

			while ((params = reader.readLine()) != null) {
				tokenizer = new StringTokenizer(params);

				int n = Integer.parseInt(tokenizer.nextToken());
				int m = Integer.parseInt(tokenizer.nextToken());

				System.out.println(dig[m] - dig[n - 1]);
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int different(int arg) {
		int[] dig = new int[12];

		for (int i = 0; i < 12; i++) {
			dig[i] = 0;
		}

		while (arg > 0) {
			dig[arg % 10]++;
			if (dig[arg % 10] > 1)
				return 0;
			arg /= 10;
		}

		return 1;
	}

}
