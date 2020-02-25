package forLoops;

class ForLoopsLetsTry1 {
	public static void main(String[] args) throws InterruptedException {
		loop1();
		Thread.sleep(1000);
		loop2();
		Thread.sleep(1000);
		loop3();
		Thread.sleep(1000);
		loop4();
		Thread.sleep(1000);
		loop5();
	}

	private static void loop1() throws InterruptedException {
		int counter = 0;
		System.out.print("1.\t");
		for (int i = 0; i < 9; i++) {
			counter++;
			System.out.print(counter + " ");
			Thread.sleep(500);
		}
		System.out.print("\n");
	}

	private static void loop2() throws InterruptedException {
		int counter = 5;
		System.out.print("2.\t");
		for (int i = 0; i < 4; i++) {
			counter++;
			System.out.print(counter + " ");
			Thread.sleep(500);
		}
		System.out.print("\n");
	}

	private static void loop3() throws InterruptedException {
		int counter = 11;
		System.out.print("3.\t");
		for (int i = 10; i > 0; i--) {
			counter--;
			System.out.print(counter + " ");
			Thread.sleep(500);
		}
		System.out.print("\n");
	}

	private static void loop4() throws InterruptedException {
		int counter = (-1);
		System.out.print("4.\t");
		for (int i = 0; i < 2; i++) {
			counter++;
			System.out.print(counter + " ");
			Thread.sleep(500);
		}
		System.out.print("\n");
	}

	private static void loop5() throws InterruptedException {
		int counter = 0;
		System.out.print("5.\t");
		for (int i = 0; i < 6; i++) {
			counter += 5;
			System.out.print(counter + " ");
			Thread.sleep(500);
		}
		System.out.print("\n");
	}
}
