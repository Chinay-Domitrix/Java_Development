package warehouse;

import java.util.stream.IntStream;

public class PurchaseProduct {
	public static void main(String[] args) {
		StdIn.setFile(args[0]);
		StdOut.setFile(args[1]);
		var warehouse = new Warehouse();
		var iter = StdIn.readInt();
		IntStream.range(0, iter).mapToObj(i -> StdIn.readString()).forEach(query -> {
			if (query.equals("add")) {
				var day = StdIn.readInt();
				var id = StdIn.readInt();
				var name = StdIn.readString();
				var stock = StdIn.readInt();
				warehouse.addProduct(id, name, stock, day, StdIn.readInt());
			} else if (query.equals("purchase")) {
				var day = StdIn.readInt();
				var id = StdIn.readInt();
				warehouse.purchaseProduct(id, day, StdIn.readInt());
			}
		});
		StdOut.println(warehouse);
	}
}
