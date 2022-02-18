import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.MouseInfo.*;

import static javax.swing.JFrame.*;

public class RandomStuff extends JPanel implements AdjustmentListener, ActionListener, MouseListener, MouseWheelListener {
	JFrame frame;
	JPanel scrollPanel, buttonPanel, theOnePanel, textPanel;
	JScrollBar hueBar, saturationBar, brightnessBar, iterationsBar, zoomBar, barA, barB, leftPointReBar, leftPointImBar;
	JButton resetButton;
	JTextField hueBarText, saturationBarText, brightnessBarText, iterationsBarText, zoomBarText, textA, textB, leftPointReText, leftPointImText;
	float hue, saturation, brightness;
	final int iterations = 200;
	double zoom;
	int pixelSize = 1;
	ComplexNumber baseForScroll;
	ComplexNumber juliaBase;
	ComplexNumber centerofScreen;
	ComplexNumber startOfScreen; // this is the complex number represented at the screen's x = 0 and y = 0 position
	// all other points are based on this and a scaling factor

	public static void main(String... args) {
		new RandomStuff();
	}

	public RandomStuff() {
		// base init vars
		final int categories = 9;

		frame = new JFrame("ooh shiny");
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		juliaBase = new ComplexNumber(0, 0);
		startOfScreen = new ComplexNumber(-zoom, zoom);
		baseForScroll = new ComplexNumber(-zoom, zoom);

		scrollPanel = new JPanel();
		buttonPanel = new JPanel();
		theOnePanel = new JPanel();
		textPanel = new JPanel();

		//scrollPanel.setSize(10, 10);

		// SCROLL BARS
		// params: (horizontal or vertical, initial x, initial value, later, left/top value, right/bottom value)
		hueBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		hue = hueBar.getValue() / 1000.0f;
		hueBar.addAdjustmentListener(this);
		hueBar.addMouseListener(this);

		saturationBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		saturation = hueBar.getValue() / 1000.0f;
		saturationBar.addAdjustmentListener(this);
		saturationBar.addMouseListener(this);

		brightnessBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		brightness = brightnessBar.getValue() / 1000.0f;
		brightnessBar.addAdjustmentListener(this);
		brightnessBar.addMouseListener(this);

		iterationsBar = new JScrollBar(JScrollBar.HORIZONTAL, 200, 0, 0, 500);
		//iterations = iterationsBar.getValue();
		iterationsBar.addAdjustmentListener(this);
		iterationsBar.addMouseListener(this);

		zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 20, 0, 0, 1990);
		zoom = (double) iterationsBar.getValue() / 20.0;
		zoomBar.addAdjustmentListener(this);
		zoomBar.addMouseListener(this);

		barA = new JScrollBar(JScrollBar.HORIZONTAL, 20, 0, -2000, 2000);
		juliaBase.setRe((double) barA.getValue() / 1000.0);
		barA.addAdjustmentListener(this);
		barA.addMouseListener(this);

		barB = new JScrollBar(JScrollBar.HORIZONTAL, 20, 0, -2000, 2000);
		juliaBase.setIm((double) barB.getValue() / 1000.0);
		barB.addAdjustmentListener(this);
		barB.addMouseListener(this);

		leftPointReBar = new JScrollBar(JScrollBar.HORIZONTAL, 20, 0, -2000, 2000);
		startOfScreen.setRe((double) leftPointReBar.getValue() / 1000.0);
		leftPointReBar.addAdjustmentListener(this);
		leftPointReBar.addMouseListener(this);

		leftPointImBar = new JScrollBar(JScrollBar.HORIZONTAL, 20, 0, -2000, 2000);
		startOfScreen.setIm((double) leftPointImBar.getValue() / 1000.0);
		leftPointImBar.addAdjustmentListener(this);

		scrollPanel.setLayout(new GridLayout(categories, 1));
		scrollPanel.add(hueBar);
		scrollPanel.add(saturationBar);
		scrollPanel.add(brightnessBar);
		scrollPanel.add(iterationsBar);
		scrollPanel.add(zoomBar);
		scrollPanel.add(barA);
		scrollPanel.add(barB);
		scrollPanel.add(leftPointReBar);
		scrollPanel.add(leftPointImBar);

		// buttons
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);

		buttonPanel.add(resetButton);

		// text
		hueBarText = new JTextField("Hue");
		saturationBarText = new JTextField("Saturation");
		brightnessBarText = new JTextField("Brightness");
		iterationsBarText = new JTextField("Iterations: " + iterations);
		zoomBarText = new JTextField("Zoom: " + zoom);
		textA = new JTextField("Re: " + juliaBase.getRe());
		textB = new JTextField("Im: " + juliaBase.getIm());
		leftPointReText = new JTextField("x: " + juliaBase.getIm());
		leftPointImText = new JTextField(":y " + juliaBase.getIm());


		textPanel.setLayout(new GridLayout(categories, 1));
		textPanel.add(hueBarText);
		textPanel.add(saturationBarText);
		textPanel.add(brightnessBarText);
		textPanel.add(iterationsBarText);
		textPanel.add(zoomBarText);
		textPanel.add(textA);
		textPanel.add(textB);
		textPanel.add(leftPointReText);
		textPanel.add(leftPointImText);
		frame.addMouseListener(this);
		frame.addMouseWheelListener(this);


		theOnePanel.setLayout(new BorderLayout());
		theOnePanel.add(scrollPanel, BorderLayout.CENTER);
		theOnePanel.add(buttonPanel, BorderLayout.EAST);
		theOnePanel.add(textPanel, BorderLayout.WEST);

		frame.setLayout(new BorderLayout());
		frame.add(theOnePanel, BorderLayout.SOUTH);
		frame.add(this, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.getHSBColor(hue, saturation, brightness));
		//g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.drawImage(drawJulia(), 0, 0, null);
	}

	public BufferedImage drawJulia() {
		// Julia time!
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < this.getWidth(); x += pixelSize) { // iterating through each x,y point and converting it to a complex number
			for (int y = 0; y < this.getHeight(); y += pixelSize) {
				int c = 0; // stores the color as an HSB integer
				int failPoint = -1; // iterations var represents the max iterations allowed
				// TODO: attach that to the zoom for consistency
				//System.out.println(startOfScreen);
				ComplexNumber point = startOfScreen.add(new ComplexNumber((double) (x - frame.getWidth() / 2.0) / zoom, (double) (y - frame.getHeight() / 2.0) / zoom));
				//System.out.println(point);
				for (int iter = 0; iter < iterations; iter++) {
					if (point.magnitude() >= 2.0) { // if the point is outside of the r = 2 circle, it escapes to infinity
						failPoint = iter; // how many iterations it takes to find that info out determines the color
						break;
					}
					point = point.square().add(juliaBase);
				}
				if (failPoint > -1) { // point escapes set after failPoint number of iterations
					c = Color.HSBtoRGB(((hue * ((float) iterations / (float) (failPoint)))) % 1, saturation, 1);
				} else { // point never escapes set
					c = Color.HSBtoRGB(0, saturation, 1);
				}

				image.setRGB(x, y, c);
			}
		}
		return image;

	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e.getSource() == hueBar) {
			hue = hueBar.getValue() / 1000f;

		}
		if (e.getSource() == saturationBar) {
			saturation = saturationBar.getValue() / 1000f;
		}
		if (e.getSource() == brightnessBar) {
			brightness = brightnessBar.getValue() / 1000f;
		}
		if (e.getSource() == iterationsBar) {
			//iterations = iterationsBar.getValue();
			iterationsBarText.setText("Iterations: " + iterations);
		}
		if (e.getSource() == zoomBar) {
			zoom = Math.pow(10, (double) zoomBar.getValue() / 100);
			//startOfScreen = new ComplexNumber(-juliaBase.getRe() / zoom * pixelSize, -juliaBase.getRe() / zoom * pixelSize);
			baseForScroll.setRe(startOfScreen.getRe() / zoom);
			baseForScroll.setIm(startOfScreen.getIm() / zoom);
			zoomBarText.setText("Zoom: " + (int) zoom);
			leftPointReBar.setValue(0);
			leftPointImBar.setValue(0);
		}
		if (e.getSource() == barA) {
			juliaBase.setRe((double) barA.getValue() / 1000.0);
			textA.setText("A: " + juliaBase.getRe());
		}
		if (e.getSource() == barB) {
			juliaBase.setIm((double) barB.getValue() / 1000.0);
			textB.setText("B: " + juliaBase.getIm());
		}/*
     if(e.getSource() == leftPointReBar){
        startOfScreen.setRe(baseForScroll.getRe() + (double)leftPointReBar.getValue() / (zoom));
        //System.out.println(startOfScreen.getRe() + " re");
        leftPointReText.setText("x: " + startOfScreen.getRe());
     }
     if(e.getSource() == leftPointImBar){
        startOfScreen.setIm(baseForScroll.getIm() + (double)leftPointImBar.getValue() / (zoom));
        leftPointImText.setText("y: " + startOfScreen.getIm());
     }*/
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resetButton) {
			hueBar.setValue(0);
			saturationBar.setValue(0);
			brightnessBar.setValue(0);
			hue = 0;
			saturation = 0;
			brightness = 0;
			repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		pixelSize = 1;
		repaint();

		int x = e.getX();
		int y = e.getY();
		System.out.println("x: " + x + ", y: " + y);
		if (y > this.getHeight() / 10) {
			System.out.println("success");
			startOfScreen.setRe(startOfScreen.getRe() + (double) (x - frame.getWidth() / 2) / (zoom));
			startOfScreen.setIm(startOfScreen.getIm() + (double) (y - frame.getHeight() / 2) / (zoom));
			//baseForScroll.setRe(startOfScreen.getRe());
			//baseForScroll.setIm(startOfScreen.getIm());
			repaint();
		}
	}

	public void mousePressed(MouseEvent e) {
		pixelSize = 2;
		repaint();
	}

	// if the mouse clicks anywhere inside of the Julia display, the entire display will move to that coordinate
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		zoomBar.setValue(zoomBar.getValue() + 10 * -1 * Integer.signum(e.getWheelRotation()));
		zoom = Math.pow(10, (double) zoomBar.getValue() / 100);
		repaint();
	}


	public class ComplexNumber {
		private double real;
		private double imaginary;

		public ComplexNumber(double real, double imaginary) {
			this.real = real;
			this.imaginary = imaginary;
		}

		public double getRe() {
			return real;
		}

		public double getIm() {
			return imaginary;
		}

		public void setRe(double re) {
			real = re;
		}

		public void setIm(double im) {
			imaginary = im;
		}

		public double magnitude() {
			return Math.sqrt(real * real + imaginary * imaginary);
		}

		public ComplexNumber multiply(ComplexNumber other) {
			return new ComplexNumber(getRe() * other.getRe() - other.getIm() * getIm(), getRe() * other.getIm() + getIm() * other.getRe());

		}

		public ComplexNumber divide(ComplexNumber other) {
			double denom = Math.pow(other.magnitude(), 2.0);
			double firstNum = getRe() * other.getRe() + getIm() * other.getIm();
			double secondNum = getIm() * other.getRe() - getRe() * other.getIm();
			return new ComplexNumber(firstNum / denom, secondNum / denom);
		}

		public ComplexNumber add(ComplexNumber other) {
			return new ComplexNumber(getRe() + other.getRe(), getIm() + other.getIm());
		}

		public ComplexNumber subtract(ComplexNumber other) {
			return new ComplexNumber(getRe() - other.getRe(), getIm() - other.getIm());
		}

		public ComplexNumber square() {
			return multiply(this);
		}

		public String toString() {
			return real + " + " + imaginary + "i";
		}
	}

}
