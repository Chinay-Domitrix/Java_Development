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
				var array1 = parseArray(matrixStrings[0]);
				var array2 = parseArray(matrixStrings[1]);
				if ((array1.length != array2.length) || (array1[0].length != array2[0].length)) System.err.println("The sum and difference are not possible");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static int[][] parseArray(String input) {
		var rows = new StringBuilder(input).deleteCharAt(input.length() - 1).deleteCharAt(0).toString()
				.replaceAll("},", "}/").split("/");
		var intArrayList = new ArrayList<int[]>();
		for (var i = 0; i < rows.length; i++)
			intArrayList.add(stream(
					new StringBuilder(rows[i]).deleteCharAt(rows[i].length() - 1).deleteCharAt(0).toString().split(","))
							.mapToInt(Integer::parseInt).toArray());
		return intArrayList.toArray(new int[0][0]);
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
