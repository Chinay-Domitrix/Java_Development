package Classwork.Computer_Science_in_the_21st_Century.src.parameteredMethods;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.System.out;

class FullName {
	@SuppressWarnings("SameReturnValue")
	@NotNull
	@Contract(pure = true)
	private static String firstName() {
		return "Chirag";
	}

	@NotNull
	private static String fullName() {
		return firstName() + ' ' + lastName();
	}

	@NotNull
	@Contract(pure = true)
	private static String lastName() {
		return "Baviskar";
	}

	public static void main(final String[] args) {
		out.println(fullName());
	}
}
