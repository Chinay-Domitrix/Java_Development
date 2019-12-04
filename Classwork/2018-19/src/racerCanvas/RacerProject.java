package racerCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Scanner;

import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.PLAIN;
import static java.lang.Math.random;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.util.stream.IntStream.range;
import static javax.imageio.ImageIO.read;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class RacerProject {
	public static void main(final String[] args) throws Throwable {
		class RacerCanvas {
			private final JFrame frame;
			private final int[] textVars = new int[3];
			private DrawRacers racers;
			private boolean showText = false;
			private String racerPlaces;

			private RacerCanvas() {
				frame = new JFrame("Race");
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setSize(800, 1000);
			}

			private void delay() throws InterruptedException {
				sleep(50);
			}

			/**
			 * In charge of moving the first racer, who is displayed at the top.
			 *
			 * @param x the amount of pixels the first racer is moved by
			 */
			private void moveRacer1(final int x) {
				racers.changeRacer1Position(x);
			}

			private void moveRacer2(final int x) {
				racers.changeRacer2Position(x);
			}

			private void moveRacer3(final int x) {
				racers.changeRacer3Position(x);
			}

			private void repaint() {
				frame.repaint();
			}

			private void setFiles(final String path1, final String path2, final String path3) throws Throwable {
				racers = new DrawRacers(path1, path2, path3);
				frame.add(racers);
				frame.setVisible(true);
			}

			private void setPlaces(final String s) {
				racerPlaces = s;
				textVars[0] = 50;
				textVars[1] = 50;
				textVars[2] = 12;
			}

			private void showText(final boolean b) {
				showText = b;
			}

			class DrawRacers extends JPanel {
				private static final long serialVersionUID = 1147664699985236040L;
				final BufferedImage[] i;
				final int[] racerX = new int[3];
				final int[] racerY = new int[3];

				private DrawRacers(final String racerPathOne, final String racerPathTwo, final String racerPathThree)
						throws Throwable {
					setBackground(black);
					i = new BufferedImage[3];
					i[0] = read(new File(racerPathOne));
					i[1] = read(new File(racerPathTwo));
					i[2] = read(new File(racerPathThree));
				}

				private void changeRacer1Position(final int x) {
					racerX[0] = x;
					racerY[0] = 0;
				}

				private void changeRacer2Position(final int x) {
					racerX[1] = x;
					racerY[1] = 300;
				}

				private void changeRacer3Position(final int x) {
					racerX[2] = x;
					racerY[2] = 500;
				}

				@Override
				public void paintComponent(final Graphics g) {
					super.paintComponent(g);
					Color x = null;
					ImageObserver y = null;
					g.drawImage(i[0], racerX[0], racerY[0], x, y);
					g.drawImage(i[1], racerX[1], racerY[1], x, y);
					g.drawImage(i[2], racerX[2], racerY[2], x, y);
					g.setColor(white);
					if (showText) {
						g.setFont(new Font("TimesRoman", PLAIN, textVars[2]));
						g.drawString(racerPlaces, textVars[0], textVars[1]);
					}
				}
			}
		}
//		Creates a new JFrame
		final RacerCanvas race = new RacerCanvas();
		try (Scanner in = new Scanner(System.in)) {
//		    Sets the path for the images (Must be modified to be usable on the user's device)
			final String path = "C:\\Users\\Chirag\\Data\\Devlopment\\ClassWork\\resources\\racerCanvas\\",
					fileType = ".png";
//		    Forces user to finalize the file path of the images
			out.println(
					"What is the exact name of the images? (On Chirag's laptop they are ISS, USAS, and Orbiter, in that order.)");
			final String f1 = path + in.nextLine() + fileType, f2 = path + in.nextLine() + fileType,
					f3 = path + in.nextLine() + fileType;
//		    Sets the lap quantity
			out.print("How many laps will the racers do? ");
			final int pL = in.nextInt();
//		    Displays the images on the JFrame
			race.setFiles(f1, f2, f3);
//			This is where most of my variables are initialized
			int stationWin = 0;
			int stationSecond = 0;
			int stationLast = 0;
			int shuttleWin = 0;
			int shuttleSecond = 0;
			int shuttleLast = 0;
			int satWin = 0;
			int satSecond = 0;
			int satLast = 0;
//			Allows for the racers to do their laps
			for (int i = 0; i < pL; i++) {
//			    Sets the initial x-position
				int xpos1 = 0;
				int xpos2 = 0;
				int xpos3 = 0;
				int count1 = 0;
				int count2 = 0;
				int count3 = 0;
				final int xpos1Add = (int) (random() * 11 + 5);
				final int xpos2Add = (int) (random() * 11 + 5);
				final int xpos3Add = (int) (random() * 11 + 5);
//			    Primary functionality loop for the racers
				do {
					xpos1 += xpos1Add;
					xpos2 += xpos2Add;
					xpos3 += xpos3Add;
					if (xpos1 < 800) {
						race.moveRacer1(xpos1);
						count1++;
					}
					if (xpos2 < 800) {
						race.moveRacer2(xpos2);
						count2++;
					}
					if (xpos3 < 800) {
						race.moveRacer3(xpos3);
						count3++;
					}
					race.repaint();
					race.delay();
				} while ((xpos1 <= 800) || (xpos2 <= 800) || (xpos3 <= 800));
//		    	Determines the round winners
				String third;
				String second;
				String winner;
				if ((count1 <= count2) && (count1 <= count3)) {
					winner = "Space Station";
					stationWin++;
					if (count2 <= count3) {
						second = "Shuttle";
						shuttleSecond++;
						third = "Satellite";
						satLast++;
					} else {
						second = "Satellite";
						satSecond++;
						third = "Shuttle";
						shuttleLast++;
					}
				} else if ((count2 <= count1) && (count2 <= count3)) {
					winner = "Shuttle";
					shuttleWin++;
					if (count1 <= count3) {
						second = "Space Station";
						stationSecond++;
						third = "Satellite";
						satLast++;
					} else {
						second = "Satellite";
						satSecond++;
						third = "Space Station";
						stationLast++;
					}
				} else {
					winner = "Satellite";
					satWin++;
					if (count1 <= count2) {
						second = "Space Station";
						stationSecond++;
						third = "Shuttle";
						shuttleLast++;
					} else {
						second = "Shuttle";
						shuttleSecond++;
						third = "Space Station";
						stationLast++;
					}
				}
//  			Sets the round winner text.
				race.setPlaces(format("The %s is in first place. The %s is in second place. The %s is in third place.", winner, second, third));
//	    		Gets the text to show up
				race.showText(true);
				race.repaint();
//			    Allows for the text to remain on the screen
				range(0, 75).forEach(j -> {
					try {
						race.delay();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
//			    Removes the text
				race.showText(false);
			}
//		    Sets the text for the final counts.
			race.setPlaces(format("The Space Station won %d races, came second in %d races, and came last in %d races. The Shuttle won %d races, came second in %d races, and came last in %d races. The Satellite won %d races, came second in %d races, and came last in %d races.", stationWin, stationSecond, stationLast, shuttleWin, shuttleSecond, shuttleLast, satWin, satSecond, satLast));
//		    Allows the text above to appear, but doesn't get rid of it
			race.showText(true);
			race.repaint();
		}
	}
}