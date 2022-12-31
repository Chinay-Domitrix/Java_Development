package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 *
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
	private final String fileName;
	private ArrayList<CharFreq> sortedCharFreqList;
	private TreeNode huffmanRoot;
	private String[] encodings;

	/**
	 * Constructor used by the driver, sets filename
	 * DO NOT EDIT
	 *
	 * @param file The file we want to encode
	 */
	public HuffmanCoding(String file) {
		this.fileName = file;
	}

	/**
	 * Reads from filename character by character, and sets sortedCharFreqList
	 * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
	 */
	public void makeSortedList() {
		StdIn.setFile(fileName);
		var charFreqs = new int[128];
		while (StdIn.hasNextChar()) charFreqs[StdIn.readChar()]++;
		sortedCharFreqList = new ArrayList<>();
		var totalValues = Arrays.stream(charFreqs).filter(j -> j > 0).sum();
		IntStream.range(0, charFreqs.length).filter(i -> charFreqs[i] > 0).forEach(i -> sortedCharFreqList.add(new CharFreq((char) i, (double) charFreqs[i] / totalValues)));
		if (sortedCharFreqList.size() == 1)
			sortedCharFreqList.add(new CharFreq((char) (sortedCharFreqList.get(0).getCharacter() + 1), 0));
		sortedCharFreqList.sort(null);
	}

	/**
	 * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
	 * in huffmanRoot
	 */
	public void makeTree() {
	Queue<TreeNode> source = new Queue<>(), target = new Queue<>();
	sortedCharFreqList.forEach(c -> source.enqueue(new TreeNode(c, null, null)));
	while (!source.isEmpty() || (target.size() != 1)) {
		var left = (target.isEmpty() ? source : (source.isEmpty() ? target : (target.isEmpty() ? source : ((source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()) ? source : target)))).dequeue();
		var right = (target.isEmpty() ? source : (source.isEmpty() ? target : (target.isEmpty() ? source : ((source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()) ? source : target)))).dequeue();
		target.enqueue(huffmanRoot = new TreeNode(new CharFreq(null, left.getData().getProbOcc() + right.getData().getProbOcc()), left, right));
	}
}

	/**
	 * Uses huffmanRoot to create a string array of size 128, where each
	 * index in the array contains that ASCII character's bitstring encoding. Characters not
	 * present in the huffman coding tree should have their spots in the array left null.
	 * Set encodings to this array.
	 */
	public void makeEncodings() {
		encodings = new String[128];
		makeEncodings(huffmanRoot, "");
	}

	private void makeEncodings(TreeNode node, String encoding) {
		if (node == null) return;
		if (node.getData().getCharacter() != null) encodings[(int) node.getData().getCharacter()] = encoding;
		makeEncodings(node.getLeft(), encoding + "0");
		makeEncodings(node.getRight(), encoding + "1");
	}

	/**
	 * Using encodings and filename, this method makes use of the writeBitString method
	 * to write the final encoding of 1's and 0's to the encoded file.
	 *
	 * @param encodedFile The file name into which the text file is to be encoded
	 */
	public void encode(String encodedFile) {
		StdIn.setFile(fileName);
		var encoding = new StringBuilder();
		while (StdIn.hasNextChar()) encoding.append(encodings[StdIn.readChar()]);
		writeBitString(encodedFile, encoding.toString());
	}

	/**
	 * Writes a given string of 1's and 0's to the given file byte by byte
	 * and NOT as characters of 1 and 0 which take up 8 bits each
	 * DO NOT EDIT
	 *
	 * @param filename  The file to write to (doesn't need to exist yet)
	 * @param bitString The string of 1's and 0's to write to the file in bits
	 */
	public static void writeBitString(String filename, String bitString) {
		var bytes = new byte[(bitString.length() / 8) + 1];
		int bytesIndex = 0, byteIndex = 0, currentByte = 0;
//		Pad the string with initial zeroes and then a one in order to bring its length to a multiple of 8. When reading, the 1 signifies the end of padding.
		var padding = 8 - (bitString.length() % 8);
		var pad = "0".repeat(padding - 1) + "1";
		bitString = pad + bitString;
//		For every bit, add it to the right spot in the corresponding byte, and store bytes in the array when finished
		for (char c : bitString.toCharArray()) {
			if ((c != '1') && (c != '0')) {
				System.out.println("Invalid characters in bitstring");
				return;
			}
			if (c == '1') currentByte += 1 << (7 - byteIndex);
			byteIndex++;
			if (byteIndex == 8) {
				bytes[bytesIndex] = (byte) currentByte;
				bytesIndex++;
				currentByte = 0;
				byteIndex = 0;
			}
		}
//		Write the array of bytes to the provided file
		try {
			var out = new FileOutputStream(filename);
			out.write(bytes);
			out.close();
		} catch (Exception e) {
			System.err.println("Error when writing to file!");
		}
	}

	/**
	 * Using a given encoded file name, this method makes use of the readBitString method
	 * to convert the file into a bit string, then decodes the bit string using the
	 * tree, and writes it to a decoded file.
	 *
	 * @param encodedFile The file which has already been encoded by encode()
	 * @param decodedFile The name of the new file we want to decode into
	 */
	public void decode(String encodedFile, String decodedFile) {
		StdOut.setFile(decodedFile);
		var bitString = readBitString(encodedFile);
		var node = huffmanRoot;
		for (char c : bitString.toCharArray()) {
			node = (c == '0') ? node.getLeft() : node.getRight();
			if (node.getData().getCharacter() != null) {
				StdOut.print(node.getData().getCharacter());
				node = huffmanRoot;
			}
		}
	}

	/**
	 * Reads a given file byte by byte, and returns a string of 1's and 0's
	 * representing the bits in the file
	 * DO NOT EDIT
	 *
	 * @param filename The encoded file to read from
	 * @return String of 1's and 0's representing the bits in the file
	 */
	public static String readBitString(String filename) {
		var bitString = new StringBuilder();
		try {
			var in = new FileInputStream(filename);
			var file = new File(filename);
			var bytes = new byte[(int) file.length()];
			in.read(bytes);
			in.close();
//			For each byte read, convert it to a binary string of length 8 and add it to the bit string
			for (byte b : bytes)
				bitString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//			Detect the first 1 signifying the end of padding, then remove the first few characters, including the 1
			for (int i = 0; i < 8; i++) if (bitString.charAt(i) == '1') return bitString.substring(i + 1);
			return bitString.substring(8);
		} catch (Exception e) {
			System.out.println("Error while reading file!");
			return "";
		}
	}

	/*
	 * Getters used by the driver.
	 * DO NOT EDIT or REMOVE
	 */

	public String getFileName() {
		return fileName;
	}

	public ArrayList<CharFreq> getSortedCharFreqList() {
		return sortedCharFreqList;
	}

	public TreeNode getHuffmanRoot() {
		return huffmanRoot;
	}

	public String[] getEncodings() {
		return encodings;
	}
}
