import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

public final class p205 extends EulerSolution {
	private static final int[] PYRAMIDAL_DIE_PDF = {0, 1, 1, 1, 1};
	private static final int[] CUBIC_DIE_PDF = {0, 1, 1, 1, 1, 1, 1};

	public static void main(String[] args) {
		System.out.println(new p205().run());
	}

	@NotNull
	@Contract(pure = true)
	private static int[] convolve(@NotNull int[] a, @NotNull int[] b) {
		int[] c = new int[a.length + b.length - 1];
		for (int i = 0; i < a.length; i++) for (int j = 0; j < b.length; j++) c[i + j] += a[i] * b[j];
		return c;
	}

	@Contract(pure = true)
	private static int sum(int[] array, int end) {
		int sum = 0;
		for (int i = 0; i < end; i++) sum += array[i];
		return sum;
	}

	String run() {
		int[] ninePyramidalPdf = {1};
		for (int i = 0; i < 9; i++) ninePyramidalPdf = convolve(ninePyramidalPdf, PYRAMIDAL_DIE_PDF);
		int[] sixCubicPdf = {1};
		for (int i = 0; i < 6; i++) sixCubicPdf = convolve(sixCubicPdf, CUBIC_DIE_PDF);
		long numer = 0;
		for (int i = 0; i < ninePyramidalPdf.length; i++) numer += (long) ninePyramidalPdf[i] * sum(sixCubicPdf, i);
		long denom = (long) sum(ninePyramidalPdf, ninePyramidalPdf.length) * sum(sixCubicPdf, sixCubicPdf.length);
		return format("%.7f", (double) numer / denom);
	}
}