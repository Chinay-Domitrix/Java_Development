import static java.lang.System.out;
import static java.lang.Thread.sleep;

class HelloWorld {
	public static void main(String[] args) throws InterruptedException {
		String var1 = "Hello world!", var2 = "My name is Chirag.", var3 = "This just happens to be my first Java project.";
		out.println(var1);
		sleep(1250);
		out.println(var2);
		sleep(1250);
		out.println(var3);
		sleep(1250);
		for (int a = 0; a < var3.length(); a++) {
			out.print(var1.charAt(a));
			sleep(150);
		}
		sleep(450);
		out.println();
		for (int a = 0; a < var3.length(); a++) {
			out.print(var2.charAt(a));
			sleep(150);
		}
		sleep(450);
		out.println();
		for (int a = 0; a < var3.length(); a++) {
			out.print(var3.charAt(a));
			sleep(150);
		}
	}
}