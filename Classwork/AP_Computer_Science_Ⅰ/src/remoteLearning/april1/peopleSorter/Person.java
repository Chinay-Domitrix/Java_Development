package remoteLearning.april1.peopleSorter;

import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.compare;

class Person implements Comparable<Person> {
	private String birthMonth;
	private int socialSecurityNumber;

	public Person(String birthMonth, int socialSecurityNumber) {
		this.birthMonth = birthMonth;
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(int socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	@Override
	public String toString() {
		return "BirthMonth = '" + birthMonth + '\'' + " and SocialSecurityNumber = " + socialSecurityNumber;
	}

	@Override
	public int compareTo(@NotNull Person otherPerson) {
		return (birthMonth.compareTo(otherPerson.birthMonth) != 0) ? birthMonth.compareTo(otherPerson.birthMonth) : compare(socialSecurityNumber, otherPerson.socialSecurityNumber);
	}
}