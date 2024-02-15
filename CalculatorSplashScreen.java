package calculator;

/**
 * File Name:       CalculatorSplashScreen.java
 * Author:          Jason Waid, 040912687
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 1
 * Date:            
 * Professor:       Daniel Cormier
 * Purpose:         displays a splash screen 
 * Class list:      CalculatorSplashScreen                
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.Color;

/**
 * displays the splash screen.
 * 
 * @author Jason Waid
 * @version 1
 * @see calculator
 * @since 1.8.0
 *
 */
public class CalculatorSplashScreen extends JWindow {

	private static final long serialVersionUID = 6248477390124803341L;
	private final int duration;

	/**
	 * default constructor sets the duration
	 * 
	 * @param duration
	 *            - set the duration time of splash
	 */
	public CalculatorSplashScreen(int duration) {
		this.duration = duration;
	}

	/**
	 * creates and shows the splash screen
	 */
	public void showSplashWindow() {
		/* Creates the main content pane */
		JPanel content = new JPanel(new BorderLayout());

		/* Sets the dimensions of the screen */
		int width = 480 + 10;
		int height = 370 + 10;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;

		/* Creates the actual splash screen */
		JLabel splash = new JLabel(new ImageIcon(getClass().getResource("SplashScreen2.gif")));
		JLabel label = new JLabel("Jason Waid - 040912687", JLabel.CENTER);

		Color customColor = new Color(0, 0, 0);

		/* Sets location and the size of the window */
		setBounds(x, y, width, height);

		content.setBackground(Color.GRAY);
		label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
		content.add(splash, BorderLayout.CENTER);
		content.add(label, BorderLayout.SOUTH);
		content.setBorder(BorderFactory.createLineBorder(customColor, 10));

		setContentPane(content);

		setVisible(true);

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {

		}
		dispose();

	}
}
