package practiceItObjectOrientedPrograms

internal class Stock constructor(symbol: String?) {
	/**
	 * Stock symbol, e.g. "YHOO"
	 */
	val symbol: String?


	var totalShares: Int
		/**
		 * Returns this Stock's total number of shares purchased.
		 */
		private set

	/**
	 * Total cost for all shares
	 */
	var totalCost: Double
		private set

	/**
	 * Returns the total profit or loss earned on this stock,
	 * based on the given price per share.
	 * pre: currentPrice >= 0.0
	 *
	 * @return profit the profit earned
	 */
	fun getProfit(currentPrice: Double): Double {
		assert(currentPrice >= 0.0)
		return totalShares * currentPrice - totalCost
	}

	/**
	 * Records purchase of the given shares at the given price.<br></br>
	 * pre: shares >= 0 && pricePerShare >= 0.0
	 */
	fun purchase(shares: Int, pricePerShare: Double) {
		assert(shares >= 0 && pricePerShare >= 0.0)
		totalShares += shares
		totalCost += shares * pricePerShare
	}

	/**
	 * Initializes a new Stock with no shares purchased.
	 * Precondition: symbol != null
	 */
	init {
		assert(symbol != null)
		this.symbol = symbol
		totalShares = 0
		totalCost = 0.0
	}

//	YOUR CODE GOES HERE
}