package calculator;

/**
 * File Name:       CalculatorViewController.java
 * Author:          Jason Waid, 040912687
 * Course:          CST8221 - JAP, Lab Section: 311
 * Assignment:      1, Part 1
 * Date:            Oct 18th 2019
 * Professor:       Daniel Cormier
 * Purpose:         Builds the class GUI
 * Class list:      CalculatorViewController, Controller
 *                  
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Builds a calculator GUI
 * 
 * @author Jason Waid
 * @version 1
 * @see calculator
 * @since 1.8.0
 *
 */

public class CalculatorViewControler extends JPanel {

	private static final long serialVersionUID = 896163862031126857L;

	private JTextField display1; // the calculator display1 field reference
	private JTextField display2; // the calculator display2 field reference
	private JLabel error; // the mode/error display label reference
	private JButton dotButton; // the decimal point (dot) button reference
	private JButton[] hexButtons = new JButton[6]; // reference to container for alphabetical hex buttons

	private static final String[] keys = { "*", "+", "A", "B", "C", "D", "E", "F", "7", "8", "9", "/", "4", "5", "6",
			"-", "1", "2", "3", ".", "0", "\u00B1" };

	/**
	 * This default constructor builds all the components of the GUI
	 */
	public CalculatorViewControler() {

		/* reference to the controler for handling events */
		Controler controler = new Controler();

		/* Builds a panel for the error label, text fields, backspace button */
		JPanel topNorth = new JPanel();
		topNorth.setLayout(new BorderLayout());

		/* creates a panel for the check box and mode precision */
		JPanel topSouth = new JPanel();
		topSouth.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		/* text field */
		JPanel display = new JPanel();

		/* Backspace button */
		JButton backspace = new JButton();

		/* building precision panel */
		JPanel precisionPanel = new JPanel();
		precisionPanel.setLayout(new BorderLayout());
		precisionPanel.setBackground(Color.BLACK);
		precisionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		/* sets the check box for Hexadecimal mode, with the text "Hex" */
		JCheckBox checkBox = new JCheckBox("Hex");

		/* creates the radio button for the ".0" precision mode */
		JRadioButton tenthRadioButton = new JRadioButton(".0", false);

		/* creates the radio button for the ".00" precision mode */
		JRadioButton hundredthRadioButton = new JRadioButton(".00", true);

		/* Creates the radio button for scientific precision mode */
		JRadioButton sciRadioButton = new JRadioButton("Sci", false);

		/* Panel for the three radio buttons */
		JPanel radioButtons = new JPanel();

		/* this will hold the keypad and west and east operators */
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		/* for 0 - 9 and A - F */
		JPanel keypad = new JPanel();

		/* for the * and / buttons */
		JPanel leftOperator = new JPanel();

		/* for the + and - buttons */
		JPanel rightOperator = new JPanel();

		/* for C */
		JPanel topOperator = new JPanel();

		/* for = */
		JPanel bottomOperator = new JPanel();

		/* this will hold keypad itself and north and south operators */
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());

		/* Border for the Calculator */
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

		error = new JLabel("F");

		/* sets dimensions for button */
		error.setPreferredSize(new Dimension(52, 55));
		/* sets background color for button */
		error.setBackground(Color.YELLOW);

		/* sets button to be opaque */
		error.setOpaque(true);

		/* sets border for the button */
		error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));
		/* ensures the text for the button is centered */
		error.setHorizontalAlignment(JLabel.CENTER);
		/* sets the font and style for error label */
		error.setFont(new Font(error.getFont().getName(), error.getFont().getStyle(), 20));

		display.setLayout(new GridLayout(2, 0));
		display.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		display1 = new JTextField();
		/* 14 columns and a height of 30 */
		display1.setPreferredSize(new Dimension(14, 30));
		display1.setBackground(Color.WHITE);
		display1.setEditable(false);
		display1.setHorizontalAlignment(JTextField.RIGHT);
		/* empty border between the two text fields */
		display1.setBorder(BorderFactory.createEmptyBorder());

		display2 = new JTextField();
		display2.setPreferredSize(new Dimension(14, 30));
		display2.setBackground(Color.WHITE);
		display2.setEditable(false);
		display2.setHorizontalAlignment(JTextField.RIGHT);

		/* force 0.0 to display by default */
		display2.setText("0.0");
		display2.setBorder(BorderFactory.createEmptyBorder());

		backspace.setPreferredSize(new Dimension(52, 55));
		backspace.setBackground(Color.YELLOW);
		backspace.setOpaque(true);
		backspace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK));

		/* uses unicode for left arrow */
		backspace.setText("\u21DA");
		backspace.setFont(new Font(backspace.getFont().getName(), Font.BOLD, 25));

		/* tool tip for backspace button */
		backspace.setToolTipText("Backspace (Alt-B)");

		/* sets the key for backspace */
		backspace.setMnemonic('b');
		backspace.setActionCommand("\u21DA");
		backspace.addActionListener(controler);

		/* adds display 1 and 2 to the display panel */
		display.add(display1);
		display.add(display2);

		topNorth.add(error, BorderLayout.WEST);
		topNorth.add(display, BorderLayout.CENTER);
		topNorth.add(backspace, BorderLayout.EAST);

		/* sets background color for check box and the radio buttons */
		checkBox.setBackground(Color.GREEN);
		tenthRadioButton.setBackground(Color.YELLOW);
		hundredthRadioButton.setBackground(Color.YELLOW);
		sciRadioButton.setBackground(Color.YELLOW);

		/* sets up action listener for the above buttons */
		checkBox.addActionListener(controler);
		tenthRadioButton.addActionListener(controler);
		hundredthRadioButton.addActionListener(controler);
		sciRadioButton.addActionListener(controler);

		/* groups the radio buttons together */
		radioButtons.setLayout(new GridLayout(1, 1, 0, 0));
		radioButtons.add(tenthRadioButton);
		radioButtons.add(hundredthRadioButton);
		radioButtons.add(sciRadioButton);

		precisionPanel.add(checkBox, BorderLayout.WEST);
		precisionPanel.add(radioButtons, BorderLayout.EAST);

		/* adds the boxPanel to the north panel in the southern position */
		topSouth.add(precisionPanel, BorderLayout.CENTER);

		top.add(topNorth, BorderLayout.NORTH);
		top.add(topSouth, BorderLayout.SOUTH);

		/* numerical Keypad */
		mainPanel.setLayout(new BorderLayout());

		/* sets the layout for the keypad buttons */
		keypad.setLayout(new GridLayout(6, 3, 3, 3));
		/* sets the border for the panel */
		keypad.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		keypad.setBackground(Color.WHITE);

		leftOperator.setLayout(new GridLayout(2, 1, 2, 2));
		leftOperator.setBackground(Color.BLACK);
		leftOperator.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.BLACK));
		leftOperator.setPreferredSize(new Dimension(52, 55));

		rightOperator.setLayout(new GridLayout(2, 1, 0, 2));
		rightOperator.setBackground(Color.BLACK);
		rightOperator.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
		rightOperator.setPreferredSize(new Dimension(52, 55));

		topOperator.setLayout(new BorderLayout());
		topOperator.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		bottomOperator.setLayout(new BorderLayout());
		bottomOperator.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		subPanel.add(topOperator, BorderLayout.NORTH);
		subPanel.add(keypad, BorderLayout.CENTER);
		subPanel.add(bottomOperator, BorderLayout.SOUTH);

		mainPanel.add(rightOperator, BorderLayout.EAST);
		mainPanel.add(leftOperator, BorderLayout.WEST);
		mainPanel.add(subPanel, BorderLayout.CENTER);

		/* for loop for displaying buttons */
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals(".")) {
				dotButton = createButton(".", ".", Color.BLACK, Color.MAGENTA, new Controler());
				keypad.add(dotButton);
			} else if (keys[i].equals("\u00B1")) {
				keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.MAGENTA, new Controler()));
			} else if (keys[i].equals("+") || keys[i].equals("-")) {
				rightOperator.add(createButton(keys[i], keys[i], Color.BLACK, Color.CYAN, new Controler()));
			} else if (keys[i].equals("*") || keys[i].equals("/")) {
				leftOperator.add(createButton(keys[i], keys[i], Color.BLACK, Color.CYAN, new Controler()));
			} else if (keys[i].equals("A")) {
				hexButtons[0] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, new Controler()));
			} else if (keys[i].equals("B")) {
				hexButtons[1] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, new Controler()));
			} else if (keys[i].equals("C")) {
				hexButtons[2] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, new Controler()));
			} else if (keys[i].equals("D")) {
				hexButtons[3] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, new Controler()));
			} else if (keys[i].equals("E")) {
				hexButtons[4] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, new Controler()));
			} else if (keys[i].equals("F")) {
				hexButtons[5] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, new Controler()));
			} else {
				keypad.add(createButton(keys[i], keys[i], Color.BLACK, Color.BLUE, new Controler()));
			}
		}

		topOperator.add(createButton("C", "C", Color.BLACK, Color.RED, new Controler()));
		bottomOperator.add(createButton("=", "=", Color.BLACK, Color.YELLOW, new Controler()));

		add(top, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.SOUTH);

	}

	/**
	 * This method is used to create the actual buttons for operands and the keypad
	 * nums
	 * 
	 * @param text
	 *            - the button text label
	 * @param ac
	 *            - the action command for the button
	 * @param fg
	 *            - the foreground color
	 * @param bg
	 *            - the background color
	 * @param handler
	 *            - the reference to the instance of the event handler class for the
	 *            created button
	 * 
	 * @return button - reference to the created button
	 */
	private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {

		/* Creates a button object using the text passed into the method */
		JButton button = new JButton(text);

		button.setForeground(fg);
		button.setBackground(bg);

		/* sets the action command for the button */
		if (ac != null) {
			button.setActionCommand(ac);
		}

		button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), 20));

		button.addActionListener(handler);

		return button;
	}

	/**
	 * private inner class inside the CalculatorViewController class.
	 * 
	 * @author Jason Waid
	 * @version 1
	 * @see calculator
	 * @since 1.8.0
	 *
	 */
	private class Controler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			display2.setText(event.getActionCommand());
		}

	}

}
