package Classwork.dataStructures.matrix;

import static java.util.Arrays.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
	public static void main(String[] args) {
		try (Scanner fileReader = new Scanner(new File("Classwork/dataStructures/matrix/MatrixInput.txt"))) {
			while (fileReader.hasNextLine()) {
				var matrixStrings = fileReader.nextLine().split("\t");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static int[][] parseArray(String input) {
		var rows = new StringBuilder(input).deleteCharAt(input.length() - 1).deleteCharAt(0).toString()
				.replaceAll("},", "}\\").split("\\");
		var intArray = new int[rows.length][];
		for (var i = 0; i < rows.length; i++)
			intArray[i] = stream(
					new StringBuilder(rows[i]).deleteCharAt(0).deleteCharAt(rows[i].length() - 1).toString().split(","))
							.mapToInt(Integer::parseInt).toArray();
		return intArray;
	}
	
	static int[][] add(int A[][], int B[][], int size) {
		int C[][] = null;
		try {
			C = new int[size][size];
			for (var i = 0; i < size; i++)
				for (var j = 0; j < size; j++)
					C[i][j] = A[i][j] + B[i][j];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Incompatible matricies");
		}
		return C;
	}
}
