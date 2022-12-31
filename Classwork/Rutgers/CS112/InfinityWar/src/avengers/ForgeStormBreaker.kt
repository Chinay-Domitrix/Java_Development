package avengers

/**
 * Given the 2D array where the values are the flux intensity data of the
 * Neutron Star, calculate the total flux that Thor has to endure.
 *
 *
 * Steps to implement this class main method:
 *
 *
 * Step 1:
 * ForgeStormbreakerInputFile name is passed through the command line as args[0]
 * Read from the ForgeStormbreakerInputFile with the format:
 * 1. r (int): number of rows
 * 2. c (int): number of columns
 * 3. r lines, each with c values
 * Create and populate a 2D array with r rows and c columns with ForgeStormbreakerInputFile
 * data.
 *
 *
 * Step 2:
 * ForgeStormbreakerOutputFile name is passed through the command line as args[1]
 * Given the 2D array where the values are the flux intensity data of the
 * Neutron Star, calculate the total flux that Thor has to endure by summing
 * all values of the 2D array.
 * Output this number into the output file.
 *
 *
 * Note 1: the contents of the 2D array are guaranteed to be integers!
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 *
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 *
 *
 * To write to a file use StdOut (in the directions, the total flux is 45):
 * StdOut.setFile(outputfilename);
 * StdOut.print(total flux);
 *
 *
 *
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/ *.java
 * 3. java -cp bin avengers/ForgeStormBreaker forgestormbreaker.in forgestormbreaker.out
 *
 * @author Yashas Ravi
 */
fun main(args: Array<String>) {
	if (args.size < 2) {
		StdOut.println("Execute: java ForgeStormBreaker <INput file> <OUTput file>")
		return
	}
	StdIn.setFile(args[0])
	val fluxIntensity = Array(StdIn.readInt()) { IntArray(StdIn.readInt()) }
	fluxIntensity.forEach { it.indices.forEach { i -> it[i] = StdIn.readInt() } }
	StdOut.setFile(args[1])
	StdOut.print(fluxIntensity.sumOf(IntArray::sum))
}
