package codeWars.sets;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.util.stream.IntStream.range;

public class Kahumo {
	public static double[] serve(double food, double flavor, int mouths) {
		double[] eachFood = new double[mouths];
		if (flavor != 1)
			eachFood = range(0, mouths).mapToDouble(i -> round((food * (1 - flavor)) / (1 - pow(flavor, mouths)) * pow(flavor, i) * 1000) / 1000.0).toArray();
		else for (int i = 0; i < mouths; i++) eachFood[i] = round(food / mouths * pow(flavor, i) * 1000) / 1000.0;
		return eachFood;
	}
}
