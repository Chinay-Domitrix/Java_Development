package leetCode.maximum69Number

	class Solution {
		fun maximum69Number(num: Int) = num.toString().replaceFirst('6', '9').toInt()
	}
