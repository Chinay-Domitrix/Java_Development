package codeWars.maps;

import java.util.HashMap;
import java.util.Map.Entry;

import static java.util.Arrays.stream;

/*
There is an array with some numbers. All numbers are equal except for one. Try to find it!

Kata.findUniq(new double[]{ 1, 1, 1, 2, 1, 1 }); // => 2
Kata.findUniq(new double[]{ 0, 0, 0.55, 0, 0 }); // => 0.55

It’s guaranteed that array contains at least 3 numbers.

The tests contain some very huge arrays, so think about performance.

This is the first kata in series:

1) Find the unique number (this kata)
2) Find the unique string
3) Find the unique float

Use Maps
*/
public class UniqueNumber {
	public static double findUniq(double[] arr) {
		var map = new HashMap<Double, Integer>(arr.length);
		stream(arr).forEach(d -> map.put(d, map.getOrDefault(d, 0) + 1));
		return map.entrySet().stream().filter(e -> e.getValue() == 1).map(Entry::getKey).findFirst().get();
	}
}
