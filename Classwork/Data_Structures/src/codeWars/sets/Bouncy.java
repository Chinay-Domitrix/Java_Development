package codeWars.sets;

import java.util.List;

/*
A strict bouncy array of numbers, of length three or longer, is an array that each term (neither the first nor the last element) is strictly higher or lower than its neighbours.

For example, the array arr = [7,9,6,10,5,11,10,12,13,4,2,5,1,6,4,8] (length = 16)
The two longest bouncy sub-arrays of arr are [7,9,6,10,5,11,10,12] (length = 8) and [4,2,5,1,6,4,8] (length = 7)
According to the given definition, the arrays [8,1,4,6,7], [1,2,2,1,4,5], are not bouncy.
For the special case of length 2 arrays, we will consider them strict bouncy if the two elements are different.
The arrays [-1,4] and [0,-10] are both bouncy, while [0,0] is not.
An array of length 1 will be considered strict bouncy itself.
You will be given an array of integers, and you should output the longest strict bouncy subarray.
In the case of having more than one bouncy subarray of same length, it should be output the subarray with its first term of the lowest index in the original array.
Let's see the result for the first given example.
arr = [7,9,6,10,5,11,10,12,13,4,2,5,1,6,4,8]
longest_bouncy_list(arr) === [7,9,6,10,5,11,10,12]
Features of the random tests
length of the array inputs up to 1000
-500 <= arr[i] <= 500
 */
public class Bouncy {
	public static List<Integer> longestBouncyList(List<Integer> arr) {
		int start = 0;
		int end = 0;
		int maxLength = 0;
		int maxLengthStart = 0;
		int maxLengthEnd = 0;
		for (int i = 0; i < arr.size(); i++) {
			int currentLength = 1;
			int currentStart = i;
			int currentEnd = i;
			boolean isBouncy = false;
			while (currentEnd + 1 < arr.size() && !isBouncy) {
				if (arr.get(currentEnd) > arr.get(currentEnd + 1)) {
					isBouncy = true;
				} else {
					currentEnd++;
					currentLength++;
				}
			}
			if (isBouncy) {
				while (currentStart - 1 >= 0 && !isBouncy) {
					if (arr.get(currentStart - 1) > arr.get(currentStart)) {
						isBouncy = true;
					} else {
						currentStart--;
						currentLength++;
					}
				}
			}
			if (currentLength > maxLength) {
				maxLength = currentLength;
				maxLengthStart = currentStart;
				maxLengthEnd = currentEnd;
			}
		}
		return arr.subList(maxLengthStart, maxLengthEnd + 1);
	}
}
