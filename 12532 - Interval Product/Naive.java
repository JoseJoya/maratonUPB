package intervalproduct;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Naive {

	public static int[] x;

	public static void main(String[] args) {
		try {
			InputStream input = new DataInputStream(new FileInputStream(
					"src/intervalproduct/interval.in"));
			//InputStream input = System.in;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			StringBuilder out = new StringBuilder();

			int n, k;
			String params;
			String[] sp;

			while ((params = reader.readLine()) != null && params.length() > 0) {
				sp = params.split(" ");
				n = Integer.parseInt(sp[0]);
				k = Integer.parseInt(sp[1]);
				
				x = new int[n];

				params = reader.readLine();
				sp = params.split(" ");

				for (int i = 0; i < n; i++) {
					x[i] = Integer.parseInt(sp[i]);

					if (x[i] > 1)
						x[i] = 1;
					if (x[i] < -1)
						x[i] = -1;
				}

				while (k-- > 0) {
					params = reader.readLine();
					sp = params.split(" ");

					if (sp[0].equals("C")) {
						int ind = Integer.parseInt(sp[1]);
						int val = Integer.parseInt(sp[2]);
						ind--;

						if (val > 1)
							val = 1;
						if (val < -1)
							val = -1;
						
						x[ind] = val;

					} else {
						int start = Integer.parseInt(sp[1]);
						int endInd = Integer.parseInt(sp[2]);

						start--;
						endInd--;
						
						int res = 1;
						
						for (int i=start; i<=endInd; i++) {
							res *= x[i];
						}

						if (res == 1)
							out.append("+");
						else if (res == -1)
							out.append("-");
						else
							out.append("0");

					}
				}

				out.append("\n");
			}
			
			System.out.print(out);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
