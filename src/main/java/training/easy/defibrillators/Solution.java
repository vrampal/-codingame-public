package training.easy.defibrillators;

import static java.lang.Math.*;

import java.util.*;

class GeoCoord {
	final double longi;
	final double lati;
	
	GeoCoord(String longiStr, String latiStr) {
		longi = parseDouble(longiStr);
		lati  = parseDouble(latiStr);
	}
	
	private static double parseDouble(String str) {
		return Double.valueOf(str.replace(",", "."));
	}
	
	double distance(GeoCoord other) {
		double x = (longi - other.longi) * cos((lati + other.lati) / 2.0);
		double y = (lati - other.lati);
		return sqrt(x * x + y * y) * 6371.0;
	}
}

class Solution {

	public static void main(String args[]) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		GeoCoord userCoord = new GeoCoord(in.next(), in.next());

		int defibCount = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}

		String closestName = null;
		double closestDist = Double.MAX_VALUE;
		for (int defixIdx = 0; defixIdx < defibCount; defixIdx++) {
			String defibLine = in.nextLine();
			String[] defibData = defibLine.split(";");
			
			String defibName = defibData[1];
			GeoCoord defibCoord = new GeoCoord(defibData[4], defibData[5]);

			double dist = userCoord.distance(defibCoord);
			if (closestDist > dist) {
				closestDist = dist;
				closestName = defibName;
			}
		}

		System.out.println(closestName);
	}

}