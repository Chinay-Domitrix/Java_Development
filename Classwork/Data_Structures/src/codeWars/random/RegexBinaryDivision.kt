package codeWars.random

/*
Regular Expression for Binary Numbers Divisible by n
Create a function that will return a regular expression string that is capable of evaluating binary strings (which consist of only 1s and 0s) and determining whether the given string represents a number divisible by n.

Tests:
Inputs 1 <= n <= 18 will be tested
Each n will be tested against random invalid tests and random valid tests (which may or may not pass the regex test itself, accordingly).

Notes:
	- Strings that are not binary numbers should be rejected.
	- Keep your solution under 5000 characters. This means you can't hard-code the answers.
	- Only these characters may be included in your returned string: 01?:*+^$()[]|

Kotlin Notes: The second anti-cheat test checks if you used any of System, io, regex, zip in your code. You won't need to print anything since each test will show what numbers your code is being tested on.

The regex of invalid characters is "[^01?*+^$:()\\[\\]|]"
An opening bracket "{" is not allowed in the regex.
 */
/*fun regexDivisibleBy(n: Int): String {
}*/
