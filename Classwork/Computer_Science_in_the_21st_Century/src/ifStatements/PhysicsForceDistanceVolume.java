package ifStatements;

import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

class PhysicsForceDistanceVolume {
	public static void main(String[] args) {
		management();
	}

	private static void management() {
		Scanner in = new Scanner(System.in);
		String a = "Does your question involve force, distance, or volume?", c, d;
		out.println(a);
		double dbl = 0;
		switch (in.nextLine()) {
			case "force":
			case "Force":
				c = "Do you want to convert from pounds to Newtons or from Newtons to pounds? (PTN and NTP, respectively) ";
				out.println(c);
				d = in.nextLine();
				if (d.equals("PTN")) {
					out.println("Please enter your force in pounds. ");
					dbl = PTN(in.nextDouble());
				} else if (d.equals("NTP")) {
					out.println("Please enter your force in Newtons. ");
					dbl = NTP(in.nextDouble());
				} else
					err.println("Error");
				break;
			case "distance":
			case "Distance":
				c = "Do you want to convert from feet to meters or from meters to feet? (FTM and MTF, respectively) ";
				out.println(c);
				d = in.nextLine();
				if (d.equals("FTM")) {
					out.println("Please enter your distance in feet. ");
					dbl = FTM(in.nextDouble());
				} else if (d.equals("MTF")) {
					out.println("Please enter your distance in meters. ");
					dbl = MTF(in.nextDouble());
				} else
					err.println("Error");
				break;
			case "volume":
			case "Volume":
				c = "Do you want to convert from liters to gallons or from gallons to liters? (LTG and GTL, respectively) ";
				out.println(c);
				d = in.nextLine();
				if (d.equals("GTL")) {
					out.println("Please enter your volume in gallons. ");
					dbl = GTL(in.nextDouble());
				} else if (d.equals("LTG")) {
					out.println("Please enter your volume in liters. ");
					dbl = LTG(in.nextDouble());
				} else
					err.println("Error");
				break;
			default:
				err.println("Error");
				break;
		}
		if (dbl != 0)
			out.println(dbl);
		in.close();
	}

	private static double NTP(double a) {
		return a / 4.45;
	}

	private static double PTN(double a) {
		return a * 4.45;
	}

	private static double FTM(double a) {
		return a / 3.28;
	}

	private static double MTF(double a) {
		return a * 3.28;
	}

	private static double GTL(double a) {
		return a / 3.79;
	}

	private static double LTG(double a) {
		return a * 3.79;
	}
}
