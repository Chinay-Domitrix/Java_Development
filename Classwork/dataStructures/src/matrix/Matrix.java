package matrix;

import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.Arrays.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
	public static void main(String[] args) {
		try (var fileReader = new Scanner(new File("Classwork/dataStructures/src/matrix/MatrixInput.txt"))) {
			while (fileReader.hasNextLine()) {
				var matrixStrings = fileReader.nextLine().split("\t");
				var array1 = parseArray(matrixStrings[0]);
				var array2 = parseArray(matrixStrings[1]);
				out.println("Array 1:");
				for (var is : array1)
					out.println(stringifyMatrix(is));
				out.println("Array 2:");
				for (var is : array2)
					out.println(stringifyMatrix(is));
				if ((array1.length != array2.length) || (array1[0].length != array2[0].length))
					err.println("The sum and difference are not possible");
				else {
					out.println("Sum:");
					for (var is : add(array1, array2))
						out.println(stringifyMatrix(is));
					out.println("Difference:");
					for (var is : subtract(array1, array2))
						out.println(stringifyMatrix(is));
				}
				if ((array1[0].length != array2.length)) 
					err.println("Multiplication is not possible.");
				else {
					out.println("Product:");
					for (var is : multiply(array1, array2))
						out.println(stringifyMatrix(is));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static int[][] parseArray(String input) {
		var rows = new StringBuilder(input).deleteCharAt(input.length() - 1).deleteCharAt(0).toString()
				.replaceAll("},", "}/").split("/");
		var intArrayList = new ArrayList<int[]>();
		for (String row : rows)
			intArrayList.add(stream(
					new StringBuilder(row).deleteCharAt(row.length() - 1).deleteCharAt(0).toString().split(","))
					.mapToInt(Integer::parseInt).toArray());
		return intArrayList.toArray(new int[0][0]);
	}
	
	static int[][] add(int[][] A, int[][] B) {
		var C = new int[A.length][A[0].length];
		for (var i = 0; i < A.length; i++)
			for (var j = 0; j < A[0].length; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}

	static int[][] subtract(int[][] A, int[][] B) {
		var C = new int[A.length][A[0].length];
		for (var i = 0; i < A.length; i++)
			for (var j = 0; j < A[0].length; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}

	static int[][] multiply(int[][] A, int[][] B) {
		var C = new int[A.length][B[0].length];
		for (var i = 0; i < C.length; i++)
			for (var j = 0; j < C[0].length; j++)
				for (var k = 0; k < B.length; k++)
					C[i][j] += A[i][k] * B[k][j];
		return C;
	}

	static String stringifyMatrix(int[] array) {
		return Arrays.toString(array).replaceAll(",", "").replace("[", "").replace("]", "");
	}
}
