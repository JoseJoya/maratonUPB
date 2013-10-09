/**
 * 12531 - Hours and Minutes
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3976
 * 
 * Heidi has a discrete analog clock in the shape of a circle, as the one in the figure. 
 * Two hands rotate around the center of the circle, indicating hours and minutes. 
 * The clock has 60 marks placed around its perimeter, with the distance between 
 * consecutive marks being constant.
 * The minute hand moves from its current mark to the next exactly once every minute. 
 * The hour hand moves from its current mark to the next exactly once every 12 minutes, 
 * so it advances ve marks each hour.
 * We consider that both hands move discretely and instantly, which means they are 
 * always positioned exactly over one of the marks and never in between marks.
 * At midnight both hands reach simultaneously the top mark, which indicates zero hours 
 * and zero minutes. After exactly 12 hours or 720 minutes, both hands reach the same position 
 * again, and this process is repeated over and over again. Note that when the minute hand moves, 
 * the hour hand may not move; however, when the hour hand moves, the minute hand also moves.
 * Heidi likes geometry, and she likes to measure the minimum angle between the two hands of 
 * the clock at dierent times of the day. She has been writing some measures down, but after 
 * several years and a long list, she noticed that some angles were repeated while some others 
 * never appeared. 
 * For instance, Heidi's list indicates that both at three o'clock and at nine o'clock the 
 * minimum angle between the two hands is 90 degrees, while an angle of 65 degrees does not 
 * appear in the list. Heidi decided to check, for any integer number A between 0 and 180, 
 * if there exists at least one time of the day such that the minimum angle between the two 
 * hands of the clock is exactly A degrees. 
 * Help her with a program that answers this question.
 */
package hoursandminutes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static boolean[] angles = new boolean[360];

	public static void main(String[] args) throws IOException {
		BufferedReader reader;
		Arrays.fill(angles, false);
		
		int h = 0;
		int m = 0;

		while (h < 12) {
			int angleH = 30 * h;
			int angleM = 6 * m;
			
			int a = Math.abs(angleH - angleM);
			
			if (a > 180) {
				a = a - 180;
			}
			angles[a] = true;
			
			m++;
			
			if (m % 60 == 0) {
				m = 0;
				h++;
			}
		}

		//InputStream input = new DataInputStream(new FileInputStream(
		//		"src/hoursandminutes/hours.in"));
		InputStream input = System.in;

		reader = new BufferedReader(new InputStreamReader(input));

		String params;

		while ((params = reader.readLine()) != null) {
			int a = Integer.parseInt(params);
			
			if (angles[a]) {
				System.out.println("Y");
			} else {
				System.out.println("N");
			}
		}

		reader.close();
	}
}
