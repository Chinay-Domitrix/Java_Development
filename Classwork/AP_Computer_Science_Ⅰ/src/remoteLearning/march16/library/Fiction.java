package remoteLearning.march16.library;

class Fiction extends Books implements Awards.Fiction {
	private boolean booker;
	private boolean nobel;
	private boolean faulkner;

	public Fiction(String author, String title, String publicationDate, boolean booker, boolean nobel, boolean faulkner) {
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