package graphingCalculator;

class CalculatorController {
	private final CalculatorModel calcModel;

	/**
	 * Creates a new instance of the CalculatorModel.
	 */
	CalculatorController() {
		calcModel = new CalculatorModel();
	}

	/**
	 * Evaluates the equation once "Enter" is pushed,
	 * graphs the equation if "Graph" is pushed, or
	 * just interprets the given action
	 */
	String[] update(String action) {
		if (action.equals("Graph")) {
			return calcModel.evaluateGraph();
		}
		return calcModel.performAction(action);
	}
}