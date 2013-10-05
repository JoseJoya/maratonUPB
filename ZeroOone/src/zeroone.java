import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class zeroone {
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String num;
		try {
			num = in.readLine();
			while (!num.equals("")) {
				try {
					String[] linea = num.split(" ");
					int a, b, c;
					a = Integer.parseInt(linea[0]);
					b = Integer.parseInt(linea[1]);
					c = Integer.parseInt(linea[2]);
					if ((a == b) && (b == c)) {
						System.out.println("*");
					}
					if (a == b && b != c) {
						System.out.println("C");
					}
					if (a == c && c != b) {
						System.out.println("B");
					}
					if (c == b && b != a) {
						System.out.println("A");
					}
					num = in.readLine();
				} catch (Exception e) {
					System.exit(0);
				}
			}
		} catch (IOException e1) {
		}
	}
}
