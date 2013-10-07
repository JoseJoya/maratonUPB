package intervalproduct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DP {

	public static int[] x;
	public static int[] z;
	public static int[] g;

	public static void main(String[] args) {
		try {
			// InputStream input = new DataInputStream(new FileInputStream(
			// "src/intervalproduct/interval.in"));
			InputStream input = System.in;
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

				x = new int[n + 1];
				z = new int[n + 1];
				g = new int[n + 1];

				z[0] = 0;
				g[0] = 0;

				params = reader.readLine();
				sp = params.split(" ");

				for (int i = 1; i <= n; i++) {
					x[i] = Integer.parseInt(sp[i - 1]);
					z[i] = (x[i] == 0) ? z[i - 1] + 1 : z[i - 1];
					g[i] = (x[i] < 0) ? g[i - 1] + 1 : g[i - 1];

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

						if (val > 1)
							val = 1;
						if (val < -1)
							val = -1;

						if ((val == 0 && x[ind] != 0)
								|| (val < 0 && x[ind] >= 0)
								|| (val > 0 && x[ind] <= 0)) {
							x[ind] = val;

							for (int i = ind; i <= n; i++) {
								z[i] = (x[i] == 0) ? z[i - 1] + 1 : z[i - 1];
								g[i] = (x[i] < 0) ? g[i - 1] + 1 : g[i - 1];
							}
						}

					} else {
						int start = Integer.parseInt(sp[1]);
						int endInd = Integer.parseInt(sp[2]);

						int numZeroes = z[endInd] - z[start - 1];
						int numNegatives = g[endInd] - g[start - 1];

						if (numZeroes > 0) {
							out.append("0");
						} else if (numNegatives % 2 == 1) {
							out.append("-");
						} else {
							out.append("+");
						}
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
