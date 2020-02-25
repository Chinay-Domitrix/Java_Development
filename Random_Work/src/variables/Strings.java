package variables;

import static java.lang.System.out;

class Strings {
	public static void main(final String[] args) {
		/*Create a string with a constructor*/
		final String s1 = "Who let the dogs out? ";
		/* Just using "" creates a string, so no need to write it the previous way.*/
		final String s2 = "Who who who who!";
		/*Java defined the operator + on strings to concatenate:*/
		final String s3 = s1 + s2;
		out.println(s3);
	}
}
