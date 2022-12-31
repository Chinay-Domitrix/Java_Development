package warehouse;

import java.util.stream.IntStream;

/**
 * This class implements a warehouse on a Hash Table like structure,
 * where each entry of the table stores a priority queue.
 * Due to your limited space, you are unable to simply rehash to get more space.
 * However, you can use your priority queue structure to delete less popular items
 * and keep the space constant.
 *
 * @author Ishaan Ivaturi
 */
public class Warehouse {
	@SuppressWarnings("FieldMayBeFinal")
	private Sector[] sectors;

	/**
	 * Initializes every sector to an empty sector
	 */
	public Warehouse() {
		sectors = new Sector[10];
		IntStream.range(0, 10).forEach(i -> sectors[i] = new Sector());
	}

	/**
	 * Provided method, code the parts to add their behavior
	 *
	 * @param id     The id of the item to add
	 * @param name   The name of the item to add
	 * @param stock  The stock of the item to add
	 * @param day    The day of the item to add
	 * @param demand Initial demand of the item to add
	 */
	public void addProduct(int id, String name, int stock, int day, int demand) {
		evictIfNeeded(id);
		addToEnd(id, name, stock, day, demand);
		fixHeap(id);
	}

	/**
	 * Add a new product to the end of the correct sector
	 * <p>
	 * Requires proper use of {@link Sector#add(Product)}
	 *
	 * @param id     The id of the item to add
	 * @param name   The name of the item to add
	 * @param stock  The stock of the item to add
	 * @param day    The day of the item to add
	 * @param demand Initial demand of the item to add
	 */
	private void addToEnd(int id, String name, int stock, int day, int demand) {
		sectors[id % 10].add(new Product(id, name, stock, day, demand));
	}

	/**
	 * Fix the heap structure of the sector, assuming the item was already added
	 * <p>
	 * Requires proper use of {@link Sector#swim(int)} and {@link Sector#getSize()}
	 *
	 * @param id The id of the item which was added
	 */
	private void fixHeap(int id) {
		sectors[id % 10].swim(sectors[id % 10].getSize());
	}

	/**
	 * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
	 * <p>
	 * Requires proper use of {@link Sector#swap(int, int)}, {@link Sector#deleteLast()}, and {@link Sector#sink(int)}
	 *
	 * @param id The id of the item which is about to be added
	 */
	private void evictIfNeeded(int id) {
		if (sectors[id % 10].getSize() == 5) {
			sectors[id % 10].swap(1, 5);
			sectors[id % 10].deleteLast();
			sectors[id % 10].sink(1);
		}
	}

	/**
	 * Update the stock of some item by some amount
	 * <p>
	 * Requires proper use of {@link Sector#getSize()}, {@link Sector#get(int)}, and {@link Product#updateStock(int)}
	 *
	 * @param id     The id of the item to restock
	 * @param amount The amount by which to update the stock
	 */
	public void restockProduct(int id, int amount) {
		IntStream.rangeClosed(1, sectors[id % 10].getSize()).filter(i -> (sectors[id % 10].get(i) != null) && (sectors[id % 10].get(i).getId() == id)).findFirst().ifPresent(i -> sectors[id % 10].get(i).updateStock(amount));
	}

	/**
	 * Delete some arbitrary product while maintaining the heap structure in O(log(n))
	 * <p>
	 * Requires proper use of {@link Sector#getSize()}, {@link Sector#get(int)}, {@link Sector#swap(int, int)}, {@link Sector#deleteLast()}, {@link Sector#sink(int)} and/or {@link Sector#swim(int)}, and {@link Product#getId()}
	 *
	 * @param id The id of the product to delete
	 */
	public void deleteProduct(int id) {
		IntStream.rangeClosed(1, sectors[id % 10].getSize()).filter(i -> (sectors[id % 10].get(i) != null) && (sectors[id % 10].get(i).getId() == id)).findFirst().ifPresent(i -> {
			sectors[id % 10].swap(i, sectors[id % 10].getSize());
			sectors[id % 10].deleteLast();
			sectors[id % 10].sink(i);
		});
	}

	/**
	 * Simulate a purchase order for some product
	 * <p>
	 * Requires proper use {@link Sector#getSize()}, {@link Sector#sink(int)}, {@link Sector#get(int)}, {@link Product#getId()}, {@link Product#getStock()}, {@link Product#setLastPurchaseDay(int)}, {@link Product#updateStock(int)}, and {@link Product#updateDemand(int)}
	 *
	 * @param id     The id of the purchased product
	 * @param day    The current day
	 * @param amount The amount purchased
	 */
	public void purchaseProduct(int id, int day, int amount) {
		IntStream.rangeClosed(1, sectors[id % 10].getSize()).filter(i -> (sectors[id % 10].get(i) != null) && (sectors[id % 10].get(i).getId() == id)).findFirst().ifPresent(i -> {
			var product = sectors[id % 10].get(i);
			if (product.getStock() >= amount) {
				product.setLastPurchaseDay(day);
				product.updateStock(-amount);
				product.updateDemand(amount);
				sectors[id % 10].sink(i);
			}
		});
	}

	/**
	 * Construct a better scheme to add a product, where empty spaces are always filled
	 *
	 * @param id     The id of the item to add
	 * @param name   The name of the item to add
	 * @param stock  The stock of the item to add
	 * @param day    The day of the item to add
	 * @param demand Initial demand of the item to add
	 */
	public void betterAddProduct(int id, String name, int stock, int day, int demand) {
		IntStream.rangeClosed(1, sectors[id % 10].getSize()).filter(i -> (sectors[id % 10].get(i) == null)).findFirst().ifPresentOrElse(i -> sectors[id % 10].set(i, new Product(id, name, stock, day, demand)), () -> addProduct(id, name, stock, day, demand));
	}

	private boolean isSectorFull(Sector sector) {
		return sector.getSize() == 5;
	}

	/*
	 * Returns the string representation of the warehouse
	 */
	public String toString() {
		StringBuilder warehouseString = new StringBuilder("[\n");
		for (int i = 0; i < 10; i++) warehouseString.append("\t").append(sectors[i].toString()).append("\n");
		return warehouseString + "]";
	}

	/*
	 * Do not remove this method, it is used by Autolab
	 */
	@SuppressWarnings("unused")
	public Sector[] getSectors() {
		return sectors;
	}

	private int id(int id) {
		return id % 10;
	}
}
