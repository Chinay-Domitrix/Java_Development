package MobileAppStaticVars;

import java.util.ArrayList;
import java.util.Random;

import static MobileAppStaticVars.StaticArrayLists.arrayList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.*;
import static java.lang.System.out;
import static java.util.Collections.shuffle;

public class Runner {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			out.println(arrayList);
			addRandomNumbersAndBooleanList();
		}
	}

	private static void addRandomNumbersAndBooleanList() {
		ArrayList<Object> objectArrayList = new ArrayList<>();
		objectArrayList.add(new Random().nextInt());
		objectArrayList.add(new Random().nextInt(toIntExact(round(random() * MAX_VALUE))));
		objectArrayList.add(new Random().nextLong());
		objectArrayList.add(new Random().nextBoolean());
		objectArrayList.add(new Random().nextFloat());
		objectArrayList.add(new Random().nextDouble());
		shuffle(objectArrayList);
		arrayList.addAll(objectArrayList);
	}
}
