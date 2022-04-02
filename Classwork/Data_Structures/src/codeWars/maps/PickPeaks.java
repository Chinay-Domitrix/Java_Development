package codeWars.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

public class PickPeaks {
	public static HashMap<String, List<Integer>> getPeaks(int[] arr) {
		var ans = new HashMap<String, List<Integer>>() {{
			asList("pos", "peaks").forEach(s -> put(s, new ArrayList<>()));
		}};
		var posMax = 0;
		var matchAsc = false;
		for (var i = 1; i < arr.length; i++) {
			if (arr[i - 1] < arr[i]) {
				matchAsc = true;
				posMax = i;
			}
			if (matchAsc && (arr[i - 1] > arr[i])) {
				matchAsc = false;
				ans.get("pos").add(posMax);
				ans.get("peaks").add(arr[posMax]);
			}
		}
		return ans;
	}
}
