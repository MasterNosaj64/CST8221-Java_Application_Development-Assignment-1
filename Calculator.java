package calculator;

/**
 * File Name:       Calculator.java
 * Author:          Jason Waid, 040912687
 * Course:          CST8221 - JAP, Lab Section: 311
 * Assignment:      1, Part 1
 * Date:            Oct 18th 2019
 * Professor:       Daniel Cormier
 * Purpose:         launches the application.
 * Class list:      Calculator                 
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * launches the application.
 * 
 * @author Jason Waid
 * @version 1
 * @see calculator
 * @since 1.8.0
 *
 */
public class Calculator {

	/**
	 * THe application launched from here
	 * 
	 * @param args
	 *            - command line arguments
	 */
	public static void main(String[] args) {

		CalculatorSplashScreen launch = new CalculatorSplashScreen(5000);
		CalculatorViewControler controler = new CalculatorViewControler();

		// launch splash screen and show for 5 secs
		launch.showSplashWindow();

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new JFrame();
				frame.setTitle("Calculator");
				frame.setMinimumSize(new Dimension(380, 540));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(controler);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}

		});

	}

}
