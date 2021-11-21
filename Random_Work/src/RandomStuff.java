import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomStuff {
	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("Classwork/dataStructures/src/queue/planes/PassengerInfo.tsv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.useDelimiter("\t");
		String name = scanner.next();
		String destination = scanner.next();
		String flightTime = scanner.next();
		System.out.println(name + " " + destination + " " + flightTime);
//		scanner.nextLine();
		System.out.println(scanner.next());
	}
}
