package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.investments;

import org.jetbrains.annotations.Contract;

public final class Name {
	private final String name;

	@Contract(pure = true)
	public Name(String name) {
		this.name = name;
	}

	@Contract(pure = true)
	@Override
	public final String toString() {
		return name;
	}
}
