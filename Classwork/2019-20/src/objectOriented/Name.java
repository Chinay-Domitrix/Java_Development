package objectOriented;

public final class Name {
	private final String name;

	public Name(String name) {
		this.name = name;
	}

	@Override
	public final String toString() {
		return name;
	}
}