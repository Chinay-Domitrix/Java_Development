package Classwork.Computer_Science_in_the_21st_Century.src.parameteredMethods;

import java.util.Scanner;

import static java.lang.System.out;

class Physics {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.println("What do you want to do: Free Fall Height after Time, Projectile Motion, or Particle Velocity Calculation? (all lowercase, and \"hat\" for Free Fall, \"pm\" for Projectile Motion, and \"pvc\" for Particle Velocity Calculation)");
		String c1 = in.nextLine();
		switch (c1) {
			case "hat":
				out.println("Please enter the time and original height, in that exact order.");
				double a = dropper(in.nextDouble(), in.nextDouble());
				out.println("The answer is " + a + " meters");
				break;
			case "pm":
				out.println("Please enter the initial velocity and original height, in that exact order.");
				double b = projectile(in.nextDouble(), in.nextDouble());
				out.println("The projectile's velocity is " + b + " meters per second");
				break;
			case "pvc":
				out.println("Please enter your end and starting locations and end and beginning times, in that exact order.");
				double c = pv(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
				out.println("The particle's velocity is " + c + " meters per second");
				break;
		}
		in.close();
	}

	private static double dropper(double t, double b) {
		return ((-4.9 * Math.pow(t, 2)) + b);
	}

	private static double projectile(double i, double t) {
		return (i - (9.8 * t));
	}

	private static double pv(double e, double s, double b, double a) {
		return ((e - s) / (b - a));
	}
}
