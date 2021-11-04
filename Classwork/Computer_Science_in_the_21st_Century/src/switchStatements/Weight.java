package switchStatements;

import java.util.Scanner;

import static java.lang.System.*;

class Weight {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.print("Please enter your weight: ");
		double weight = in.nextDouble();
		in.nextLine();
		out.print("Please enter what planet you want to weigh yourself on: ");
		String planet = in.nextLine();
		double modifiedWeight = weight;
		switch (planet) {
			case "Earth":
			case "earth":
				break;
			case "Jupiter":
			case "jupiter":
				modifiedWeight *= 2.65;
				break;
			case "Mars":
			case "mars":
				modifiedWeight *= 0.39;
				break;
			case "Mercury":
			case "mercury":
				modifiedWeight *= 0.38;
				break;
			case "Neptune":
			case "neptune":
				modifiedWeight *= 1.23;
				break;
			case "Pluto":
			case "pluto":
				modifiedWeight *= 0.05;
				break;
			case "Saturn":
			case "saturn":
				modifiedWeight *= 1.17;
				break;
			case "Uranus":
			case "uranus":
				modifiedWeight *= 1.05;
				break;
			case "Venus":
			case "venus":
				modifiedWeight *= 0.78;
				break;
			default:
				err.println("Error");
				exit(0);
		}
		out.println(modifiedWeight);
		in.close();
	}
}
