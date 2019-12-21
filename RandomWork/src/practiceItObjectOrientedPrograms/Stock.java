package practiceItObjectOrientedPrograms;

import org.jetbrains.annotations.Contract;

final class Stock {
	private String symbol;        // stock symbol, e.g. "YHOO"
	private int totalShares;      // total shares purchased
	private double totalCost;     // total cost for all shares

	/**
	 * Initializes a new Stock with no shares purchased.
	 * Precondition: symbol != null
	 */
	@Contract(value = "null -> fail", pure = true)
	public Stock(String symbol) {
		assert symbol != null;
		this.symbol = symbol;
		totalShares = 0;
		totalCost = 0;
	}

	/**
	 * Returns the total profit or loss earned on this stock,
	 * based on the given price per share.
	 * pre: currentPrice >= 0.0
	 *
	 * @return profit the profit earned
	 */
	@Contract(pure = true)
	public double getProfit(double currentPrice) {
		assert !(currentPrice < 0.0);
		return (totalShares * currentPrice) - totalCost;
	}

	// Records purchase of the given shares at the given price.
	// pre: shares >= 0 && pricePerShare >= 0.0
	public void purchase(int shares, double pricePerShare) {
		assert shares >= 0 && !(pricePerShare < 0.0);
		totalShares += shares;
		totalCost += shares * pricePerShare;
	}

	/**
	 * Returns this Stock's symbol value.
	 *
	 * @return symbol the symbol of the stock
	 */
	@Contract(pure = true)
	public String __getSymbol() {
		return symbol;
	}

	/**
	 * Returns this Stock's total number of shares purchased.
	 */
	@Contract(pure = true)
	public int __getTotalShares() {
		return totalShares;
	}

	/**
	 * Returns this Stock's total cost for all shares.
	 */
	@Contract(pure = true)
	public double __getTotalCost() {
		return totalCost;
	}

// YOUR CODE GOES HERE

}