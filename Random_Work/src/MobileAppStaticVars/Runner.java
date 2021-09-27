package MobileAppStaticVars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static Random_Work.src.MobileAppStaticVars.StaticArrayLists.arrayList;
import static java.lang.Integer.MAX_VALUE;

public class Runner {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(arrayList);
			addRandomNumbersAndBooleanList();
		}
	}

	private static void addRandomNumbersAndBooleanList() {
		ArrayList<Object> objectArrayList = new ArrayList<>();
		objectArrayList.add(new Random().nextInt());
		objectArrayList.add(new Random().nextInt((int) (Math.random() * MAX_VALUE)));
		objectArrayList.add(new Random().nextLong());
		objectArrayList.add(new Random().nextBoolean());
		objectArrayList.add(new Random().nextFloat());
		objectArrayList.add(new Random().nextDouble());
		Collections.shuffle(objectArrayList);
		arrayList.addAll(objectArrayList);
	}
}
