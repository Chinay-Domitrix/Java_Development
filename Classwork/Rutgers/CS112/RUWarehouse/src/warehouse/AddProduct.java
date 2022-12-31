package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
	public static void main(String[] args) {
		StdIn.setFile(args[0]);
		StdOut.setFile(args[1]);
		var warehouse = new Warehouse();
		var iter = StdIn.readInt();
		for (int i = 0; i < iter; i++) {
			var day = StdIn.readInt();
			var id = StdIn.readInt();
			var name = StdIn.readString();
			var stock = StdIn.readInt();
			var demand = StdIn.readInt();
			warehouse.addProduct(id, name, stock, day, demand);
		}
		StdOut.println(warehouse);
	}
}
