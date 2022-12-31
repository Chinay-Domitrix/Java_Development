package leetCode.reverseString;

import org.jetbrains.annotations.NotNull;

public class Solution {
	public void reverseString(@NotNull char[] s) {
		// reverse the array in O(1) space with a for loop
			for (int i = 0; i < s.length / 2; i++) {
				char temp = s[i];
				s[i] = s[s.length - i - 1];
				s[s.length - i - 1] = temp;
			}
	}
}
