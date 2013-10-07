package intervalproduct;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static int N = 1000000;
	public static int M = 100000;
	public static int[] x = new int[M + 10];
	public static int[] segTree = new int[4 * M + 100];

	public static void buildTree(int at, int lo, int hi) {
		if (lo == hi) {
			segTree[at] = x[lo];
			return;
		}

		int mid = (lo + hi) / 2;

		buildTree(2 * at, lo, mid);
		buildTree(2 * at + 1, mid + 1, hi);

		segTree[at] = segTree[2 * at] * segTree[2 * at + 1];
	}

	public static void update(int at, int index, int value, int lo, int hi) {
		if (index < lo || index > hi)
			return;

		if (lo == hi) {
			segTree[at] = value;
			return;
		}

		int mid = (lo + hi) / 2;

		update(2 * at, index, value, lo, mid);
		update(2 * at + 1, index, value, mid + 1, hi);

		segTree[at] = segTree[2 * at] * segTree[2 * at + 1];
	}

	public static int query(int at, int lo, int hi, int start, int endInd) {
		if (start > hi || endInd < lo)
			return 1;

		if (lo >= start && hi <= endInd)
			return segTree[at];

		int mid = (lo + hi) / 2;

		return query(2 * at, lo, mid, start, endInd)
				* query(2 * at + 1, mid + 1, hi, start, endInd);
	}

	void print() {
		System.out.println("\n... printing start... \n");
		for (int i = 0; i < 4 * M; i++) {
			System.out.println(" " + segTree[i]);
		}
		System.out.println("\n... printing end... \n");
	}

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

				params = reader.readLine();
				sp = params.split(" ");

				for (int i = 0; i < n; i++) {
					x[i] = Integer.parseInt(sp[i]);

					if (x[i] > 1)
						x[i] = 1;
					if (x[i] < -1)
						x[i] = -1;
				}

				buildTree(1, 0, n - 1);
				// print();

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

						update(1, ind, val, 0, n - 1);
						// print();

						x[ind] = val;

					} else {
						int start = Integer.parseInt(sp[1]);
						int endInd = Integer.parseInt(sp[2]);

						start--;
						endInd--;

						int res = query(1, 0, n - 1, start, endInd);

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
