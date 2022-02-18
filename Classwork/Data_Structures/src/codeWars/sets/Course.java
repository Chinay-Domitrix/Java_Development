package codeWars.sets;

import static java.lang.System.arraycopy;
import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Course {
	public static boolean checkCourse(char[][] map) {
		char[][] tempMap = new char[map.length][map[0].length];
		for (int y = 0; y < map.length; y++) arraycopy(map[y], 0, tempMap[y], 0, map[0].length);
		int[] ship_loc = new int[2];
		for (int y = 0; y < tempMap.length; y++)
			if (tempMap[y][0] == 'X') {
				ship_loc[0] = y;
				break;
			}
		for (int x = 0; x < tempMap[0].length; x++) if (tempMap[0][x] == 'N') tempMap[0][x] = 'S';
		while (true) {
			for (int y = -1; y <= 1; y++)
				for (int x = -1; x <= 1; x++)
					if (((ship_loc[0] + y) >= 0) && ((ship_loc[0] + y) < tempMap.length) && ((ship_loc[1] + x) >= 0) && ((ship_loc[1] + x) < tempMap[0].length))
						if ((tempMap[ship_loc[0] + y][ship_loc[1] + x] == 'N') || (tempMap[ship_loc[0] + y][ship_loc[1] + x] == 'S'))
							return false;
						else if (ship_loc[1] == tempMap[0].length - 1 && y == 0) return true;
			char[][] updatedMap = new char[tempMap.length][tempMap[0].length];
			stream(updatedMap).forEach(line -> fill(line, '0'));
			for (int y = 0; y < tempMap.length; y++)
				for (int x = 0; x < tempMap[0].length; x++)
					if ((tempMap[y][x] == 'S') && (y < (tempMap.length - 1))) updatedMap[y + 1][x] = 'S';
					else if ((tempMap[y][x] == 'S') && (y == (tempMap.length - 1))) updatedMap[y - 1][x] = 'N';
					else if ((tempMap[y][x] == 'N') && (y != 0)) updatedMap[y - 1][x] = 'N';
					else if ((tempMap[y][x] == 'N') && (y == 0)) updatedMap[y + 1][x] = 'S';
					else if (tempMap[y][x] == 'X') {
						updatedMap[y][x + 1] = 'X';
						ship_loc[0] = y;
						ship_loc[1] = x + 1;
					}
			tempMap = updatedMap;
		}
	}
}
