package warehouse;

/*
 * Use this class to put it all together.
 */
public class Everything {
	public static void main(String[] args) {
		StdIn.setFile(args[0]);
		StdOut.setFile(args[1]);
		var warehouse = new Warehouse();
		var iter = StdIn.readInt();
		for (int i = 0; i < iter; i++) {
			var query = StdIn.readString();
			switch (query) {
				case "add" -> {
					var day = StdIn.readInt();
					var id = StdIn.readInt();
					var name = StdIn.readString();
					var stock = StdIn.readInt();
					warehouse.addProduct(id, name, stock, day, StdIn.readInt());
				}
				case "restock" -> warehouse.restockProduct(StdIn.readInt(), StdIn.readInt());
				case "purchase" -> warehouse.purchaseProduct(StdIn.readInt(), StdIn.readInt(), StdIn.readInt());
				case "delete" -> warehouse.deleteProduct(StdIn.readInt());
			}
		}
		StdOut.println(warehouse);
	}
}