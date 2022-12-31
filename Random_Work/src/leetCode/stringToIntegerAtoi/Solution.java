package leetCode.stringToIntegerAtoi;

class Solution {
	public int myAtoi(String s) {
		var i = 0;
		var sign = 1;
		var result = 0;
		while ((i < s.length()) && (s.charAt(i) == ' ')) i++;
		if ((i < s.length()) && ((s.charAt(i) == '+') || (s.charAt(i) == '-'))) {
			sign = (s.charAt(i) == '+') ? 1 : -1;
			i++;
		}
		while ((i < s.length()) && Character.isDigit(s.charAt(i))) {
			if ((result > (Integer.MAX_VALUE / 10)) || ((result == (Integer.MAX_VALUE / 10)) && ((s.charAt(i) - '0') > 7)))
				return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			result = (result * 10) + (s.charAt(i) - '0');
			i++;
		}
		return result * sign;
	}
}
