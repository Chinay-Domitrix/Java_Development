package remoteLearning.march16.library;

class Fiction extends Books implements Awards.Fiction {
	private final boolean booker;
	private final boolean nobel;
	private final boolean faulkner;

	public Fiction(String author, String title, String publicationDate, boolean booker, boolean nobel,
	               boolean faulkner) {
		this.author = author;
		this.title = title;
		this.publicationDate = publicationDate;
		genre = "Fiction";
		this.booker = booker;
		this.nobel = nobel;
		this.faulkner = faulkner;
	}

	@Override
	public boolean getBooker() {
		return booker;
	}

	@Override
	public boolean getNobel() {
		return nobel;
	}

	@Override
	public boolean getFaulkner() {
		return faulkner;
	}
}
