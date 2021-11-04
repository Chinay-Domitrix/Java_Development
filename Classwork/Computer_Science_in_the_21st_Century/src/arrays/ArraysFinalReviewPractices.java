package arrays;

import java.util.Scanner;

import static java.lang.System.*;

class ArraysFinalReviewPractices {
	public static void main(final String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int[] a = new int[30], wipe;
			int ph, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
			out.print("Which program do you want to run? ");
			switch (in.nextInt()) {
				default:
					err.println("Error");
					exit(0);
					break;
				case 1:
					for (int x : a) {
						x = (int) (Math.random() * 31) + 5;
						out.print(x + ' ');
					}
					break;
				case 2:
					out.print("Original: ");
					for (int x : a) {
						x = (int) (Math.random() * 31) + 5;
						out.print(x + ' ');
					}
					out.println();
					out.print("Modified: ");
					for (int x : a) {
						if (x % 2 == 0)
							x *= 3;
						out.print(x + ' ');
					}
					break;
				case 3:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i42 : a)
						out.print(i42 + " ");
					break;
				case 4:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i41 : a)
						out.print(i41 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i40 : a)
						if (i40 > max)
							max = i40;
					out.println("Maximum: " + max);
					break;
				case 5:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i39 : a)
						out.print(i39 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i38 : a)
						if (i38 > max)
							max = i38;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 4: ");
					for (int i37 : a)
						if (i37 > max && i37 % 2 == 0)
							max = i37;
					out.println("Maximum: " + max);
					break;
				case 6:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i36 : a)
						out.print(i36 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i35 : a)
						if (i35 > max)
							max = i35;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 4: ");
					for (int i34 : a)
						if (i34 > max && i34 % 2 == 0)
							max = i34;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 5: ");
					for (int i33 : a)
						if (i33 < min && i33 % 2 == 0)
							min = i33;
					out.println("Minimum: " + min);
					break;
				case 7:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i32 : a)
						out.print(i32 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i31 : a)
						if (i31 > max)
							max = i31;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 4: ");
					for (int i30 : a)
						if (i30 > max && i30 % 2 == 0)
							max = i30;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 5: ");
					for (int i29 : a)
						if (i29 < min && i29 % 2 == 0)
							min = i29;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("Modification 6: ");
					for (int i28 : a)
						if (i28 > max && i28 % 2 == 1)
							max = i28;
					out.println("Maximum: " + max);
					break;
				case 8:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i27 : a)
						out.print(i27 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i26 : a)
						if (i26 > max)
							max = i26;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 4: ");
					for (int i25 : a)
						if (i25 > max && i25 % 2 == 0)
							max = i25;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 5: ");
					for (int i24 : a)
						if (i24 < min && i24 % 2 == 0)
							min = i24;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("Modification 6: ");
					for (int i23 : a)
						if (i23 > max && i23 % 2 == 1)
							max = i23;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 7: ");
					for (int i22 : a)
						if (i22 < min && i22 % 2 == 1)
							min = i22;
					out.println("Minimum: " + min);
					break;
				case 9:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i21 : a)
						out.print(i21 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i20 : a)
						if (i20 > max)
							max = i20;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 4: ");
					for (int i19 : a)
						if (i19 > max && i19 % 2 == 0)
							max = i19;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 5: ");
					for (int i18 : a)
						if (i18 < min && i18 % 2 == 0)
							min = i18;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("Modification 6: ");
					for (int i17 : a)
						if (i17 > max && i17 % 2 == 1)
							max = i17;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 7: ");
					for (int i16 : a)
						if (i16 < min && i16 % 2 == 1)
							min = i16;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("Modification 8: ");
					ph = 0;
					for (int i = 0; i < a.length; i++) {
						if (i == 0) {
							ph = a[0];
						} else if (i == a.length - 1) {
							a[i - 1] = a[i];
							a[i] = ph;
						} else {
							a[i - 1] = a[i];
						}
					}
					for (int i15 : a)
						out.print(i15);
					break;
				case 10:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.println();
					out.print("Modification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.println();
					out.print("Modification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i14 : a)
						out.print(i14 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 3: ");
					for (int i13 : a)
						if (i13 > max)
							max = i13;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 4: ");
					for (int i12 : a)
						if (i12 > max && i12 % 2 == 0)
							max = i12;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 5: ");
					for (int i11 : a)
						if (i11 < min && i11 % 2 == 0)
							min = i11;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("Modification 6: ");
					for (int i10 : a)
						if (i10 > max && i10 % 2 == 1)
							max = i10;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("Modification 7: ");
					for (int i9 : a)
						if (i9 < min && i9 % 2 == 1)
							min = i9;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("Modification 8: ");
					ph = 0;
					for (int i = 0; i < a.length; i++) {
						if (i == 0) {
							ph = a[0];
						} else if (i == a.length - 1) {
							a[i - 1] = a[i];
							a[i] = ph;
						} else {
							a[i - 1] = a[i];
						}
					}
					for (int i8 : a)
						out.print(i8 + " ");
					a = wipe.clone();
					out.println();
					out.print("Modification 9: ");
					for (int i = 0; i < (a.length / 2); i++) {
						ph = a[i];
						a[i] = a[a.length - (i + 1)];
						a[a.length - (i + 1)] = ph;
					}
					for (int i7 : a)
						out.print(i7 + " ");
					break;
				case 11:
					out.print("Original: ");
					for (int i = 0; i < a.length; i++) {
						a[i] = (int) (Math.random() * 31) + 5;
						out.print(a[i] + " ");
					}
					wipe = a.clone();
					out.print("\nModification 1: ");
					for (int i = 0; i < a.length; i++) {
						if (a[i] % 2 == 0)
							a[i] *= 3;
						out.print(a[i] + " ");
					}
					a = wipe.clone();
					out.print("\nModification 2: ");
					for (int i = 0; i < (a.length); i++) {
						if (i % 2 == 1) {
							ph = a[i - 1];
							a[i - 1] = a[i];
							a[i] = ph;
						}
					}
					for (int i6 : a)
						out.print(i6 + " ");
					a = wipe.clone();
					out.print("\nModification 3: ");
					for (int i5 : a)
						if (i5 > max)
							max = i5;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("\nModification 4: ");
					for (int i4 : a)
						if (i4 > max && i4 % 2 == 0)
							max = i4;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("\nModification 5: ");
					for (int i3 : a)
						if (i3 < min && i3 % 2 == 0)
							min = i3;
					a = wipe.clone();
					out.println("Minimum: " + min);
					out.print("\nModification 6: ");
					for (int i2 : a)
						if (i2 > max && i2 % 2 == 1)
							max = i2;
					a = wipe.clone();
					out.println("Maximum: " + max);
					out.print("\nModification 7: ");
					for (int i1 : a)
						if (i1 < min && i1 % 2 == 1)
							min = i1;
					a = wipe.clone();
					out.print("\nModification 8: ");
					ph = 0;
					for (int i = 0; i < a.length; i++) {
						if (i == 0)
							ph = a[0];
						else if (i == a.length - 1) {
							a[i - 1] = a[i];
							a[i] = ph;
						} else
							a[i - 1] = a[i];
					}
					for (int element : a)
						out.print(element + " ");
					a = wipe.clone();
					out.print("\nModification 9: ");
					for (int i = 0; i < (a.length / 2); i++) {
						ph = a[i];
						a[i] = a[a.length - (i + 1)];
						a[a.length - (i + 1)] = ph;
					}
					for (int item : a)
						out.print(item + " ");
					a = wipe.clone();
					out.print("\nModification 10: ");
					for (int i = 0; i < a.length; i++)
						for (int j = 0; j < (a.length - 1); j++)
							if (a[j] > a[j + 1]) {
								ph = a[j];
								a[j] = a[j + 1];
								a[j + 1] = ph;
							}
					for (int value : a)
						out.print(value + " ");
					break;
			}
		}
	}
}
