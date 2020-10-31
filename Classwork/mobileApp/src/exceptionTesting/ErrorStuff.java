package exceptionTesting;

public class ErrorStuff {
	public static void main(String[] args) {
		method();
		
	}
	static void method() throws ArithmeticException {
		throw new ArithmeticException();
	}
}
