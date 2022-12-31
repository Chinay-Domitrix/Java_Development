package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        var warehouse = new Warehouse();
        var iter = StdIn.readInt();
        for (int i = 0; i < iter; i++) {
            var query = StdIn.readString();
            if (query.equals("add")) {
                var day = StdIn.readInt();
                var id = StdIn.readInt();
                var name = StdIn.readString();
                var stock = StdIn.readInt();
                warehouse.addProduct(id, name, stock, day, StdIn.readInt());
            } else if (query.equals("delete")) {
                warehouse.deleteProduct(StdIn.readInt());
            }
        }
        StdOut.println(warehouse);
    }
}
