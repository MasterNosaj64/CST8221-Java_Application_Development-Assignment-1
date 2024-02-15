package calculator;

/**
 * File Name:       CalculatorViewController.java
 * Author:          Jason Waid, 040912687
 * Course:          CST8221 - JAP, Lab Section: 311
 * Assignment:      1, Part 2
 * Date:            Nov 1st 2019
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
import javax.swing.ButtonGroup;
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

	private static final String[] keys = { "A", "B", "C", "D", "E", "F", "7", "8", "9", "/", "4", "5", "6", "-", "1",
			"2", "3", ".", "0", "\u00B1", "*", "+" };

	/* This default constructor builds all the components of the GUI */

	public CalculatorViewControler() {

		CalculatorModel model = new CalculatorModel();

		/* reference to the controller for handling events */
		Controller controler = new Controller(model);

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

		ButtonGroup group = new ButtonGroup();

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

		group.add(checkBox);
		group.add(sciRadioButton);
		group.add(tenthRadioButton);
		group.add(hundredthRadioButton);

		precisionPanel.add(checkBox, BorderLayout.WEST);
		precisionPanel.add(radioButtons, BorderLayout.EAST);

		/* adds the boxPanel to the north panel in the southern position */
		topSouth.add(precisionPanel, BorderLayout.CENTER);

		top.add(topNorth, BorderLayout.NORTH);
		top.add(topSouth, BorderLayout.SOUTH);

		/* numerical Keypad */
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(50, 380));

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
				dotButton = createButton(".", ".", Color.BLACK, Color.MAGENTA, controler);
				keypad.add(dotButton);
			} else if (keys[i].equals("\u00B1")) {
				keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.MAGENTA, controler));
			} else if (keys[i].equals("+") || keys[i].equals("-")) {
				rightOperator.add(createButton(keys[i], keys[i], Color.BLACK, Color.CYAN, controler));
			} else if (keys[i].equals("*") || keys[i].equals("/")) {
				leftOperator.add(createButton(keys[i], keys[i], Color.BLACK, Color.CYAN, controler));
			} else if (keys[i].matches("[A-F]")) {
				hexButtons[i] = (JButton) keypad
						.add(createButton(keys[i], keys[i], Color.WHITE, Color.BLUE, controler));
				hexButtons[i].setEnabled(false);
			} else {
				keypad.add(createButton(keys[i], keys[i], Color.BLACK, Color.BLUE, controler));
			}

		}
		// changed action cmd to cl since C is for hex
		topOperator.add(createButton("C", "CL", Color.BLACK, Color.RED, controler));
		bottomOperator.add(createButton("=", "=", Color.BLACK, Color.YELLOW, controler));

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
	 * @version 2
	 * @see calculator
	 * @since 1.8.0
	 *
	 */
	private class Controller implements ActionListener {

		CalculatorModel model;

		boolean integerFlag = false;
		boolean backspaceFlag = false;
		boolean overrideFlag = true;

		public Controller(CalculatorModel model) {
			this.model = model;
		}

		/**
		 * This method is used determine which logic to use following a numpad key press
		 * 
		 * @param key
		 *            - the key being pressed
		 */

		public void keypadNums(String key) {

			switch (model.getState()) {
			case CalculatorModel.OPERAND_1:
				if (overrideFlag == true) {
					display2.setText(key);
					overrideFlag = false;

					break;
				} else {
					display2.setText(display2.getText().concat(key));
					break;
				}

			case CalculatorModel.OPERAND_2:
				if (overrideFlag == true) {
					display2.setText(key);
					overrideFlag = false;
					break;
				} else {
					display2.setText(display2.getText().concat(key));
					break;
				}

			case CalculatorModel.RESULT:
				display1.setText("");
				display2.setText(key);
				break;

			default:
				model.setErrorState(true);
				break;
			}
		}

		/**
		 * This method is used determine which logic to use following a equal key press
		 * 
		 */

		public void keypadEquals() {

			switch (model.getState()) {
			case CalculatorModel.OPERAND_2:
				model.setOperand2(display2.getText());
				String calculation = model.getResult();

				if (calculation != null) {
					display1.setText("");
					display2.setText(calculation);
					break;
				} else {
					model.setErrorState(true);
					break;
				}
				// prints result again if we are already at the result stage
			case CalculatorModel.RESULT:
				display2.setText(model.getResult());
				break;
			}
		}

		/**
		 * This method is used determine which logic to use following a operation key
		 * press
		 * 
		 * @param key
		 *            - the key being pressed
		 */

		public void keypadOperations(String key) {

			switch (model.getState()) {
			case CalculatorModel.OPERAND_1:
				overrideFlag = true;

				model.setOperand1(display2.getText());
				model.setOperation(key);
				display1.setText(display2.getText().concat(key));
				break;

			case CalculatorModel.OPERAND_2:
				if (overrideFlag == true) {
					model.setOperation(key);
					display1.setText(display2.getText().concat(key));
					break;
				} else {
					break;
				}
			case CalculatorModel.RESULT:
				model.setOperation(key);
				display1.setText(display2.getText().concat(key));
				display2.getText();
				overrideFlag = true;
				break;

			default:
				model.setErrorState(true);
				break;
			}

		}

		/**
		 * This method controls the look and feel of the calculator
		 * 
		 * @param enable
		 *            - boolean used to control features such and dot button, Hex and
		 *            float mode
		 */

		public void display(boolean enable) {
			if (enable == true) {
				dotButton.setEnabled(enable);
				dotButton.setBackground(Color.BLUE);
				error.setText("F");
				error.setBackground(Color.YELLOW);

			} else {
				dotButton.setEnabled(enable);
				dotButton.setBackground(new Color(178, 156, 250));
				error.setText("H");
				error.setBackground(Color.GREEN);
			}
		}

		/**
		 * This method contains the default display for all modes of operation
		 *
		 */

		public void defaultDisplays() {

			switch (model.getPrecision()) {
			case CalculatorModel.PRECISION_0:
				display1.setText("");
				display2.setText("0.0");
				break;
			case CalculatorModel.PRECISION_00:
				display1.setText("");
				display2.setText("0.00");
				break;
			default:// everything else shares the same format by default
				display1.setText("");
				display2.setText("0");
				break;
			}
		}

		/**
		 * This method handles all of the key press action events for the calculator
		 *
		 * @param event
		 *            - an action event following a key press
		 */

		@Override
		public void actionPerformed(ActionEvent event) {

			boolean keypadError = false;

			/* Cases for handling button events */
			switch (event.getActionCommand()) {
			/* These handle numpad button events */
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "A":
			case "B":
			case "C":
			case "D":
			case "E":
			case "F":

				if (model.getErrorState() == false) {
					keypadNums(event.getActionCommand());

					if (model.getErrorState() == true) {
						keypadError = true;
					}

				}

				backspaceFlag = true;
				break;
			/* These handle operator button events */
			case "+":
			case "-":
			case "/":
			case "*":

				if (model.getErrorState() == false) {
					keypadOperations(event.getActionCommand());

					if (model.getErrorState() == true) {
						keypadError = true;
					}

				}

				backspaceFlag = true;
				break;

			/* This handles equals button events */
			case "=":

				if (model.getErrorState() == false) {
					keypadEquals();

					if (model.getErrorState() == true) {
						keypadError = true;
					}

				}

				backspaceFlag = false;
				break;

			/* This handles plus minus sign button events */
			case "\u00B1":

				if (model.getErrorState() == false) {

					if (display2.getText().startsWith("-")) {
						display2.setText(display2.getText().replace("-", ""));
					} else if (display2.getText().startsWith("+")) {
						display2.setText(display2.getText().replace("", "-"));
					} else {
						display2.setText("-".concat(display2.getText()));
					}

					if (model.getErrorState() == true) {
						keypadError = true;
					}
				}
				backspaceFlag = true;
				break;

			/* This handles backspace button events */
			case "\u21DA":

				/* stops backspace from being used when it shouldn't be */
				if (backspaceFlag == false) {
					break;
				}

				if (model.getErrorState() == false) {

					if (overrideFlag == false) {

						if (display2.getText().length() > 1) {

							display2.setText(display2.getText().substring(0, display2.getText().length() - 1));

							if (display2.getText().length() == 1 && display2.getText().contains("-")) {
								defaultDisplays();
								model.clear();

								overrideFlag = true;

							}

						} else {

							defaultDisplays();
							model.clear();

							overrideFlag = true;

						}

					}

				}
				break;

			/* This handles clear button events */
			case "CL":

				if (model.getErrorState() == true) {
					display(!model.integerMode());
				}

				defaultDisplays();
				model.clear();

				overrideFlag = true;

				break;
			/* This handles dot button events */
			case ".":

				if (model.getErrorState() == false) {

					if (integerFlag == false) {

						if (overrideFlag == true) {

							display2.setText(".");
							overrideFlag = false;

						} else {
							/* if dot is already present, move it to new location */
							if (display2.getText().contains(".")) {
								display2.setText(display2.getText().replace(".", ""));
							}
							/* adds dot to the end of display2 string */
							display2.setText(display2.getText().concat("."));
						}

					}
				}
				break;
			/* This handles .0 precision button events */
			case ".0":

				integerFlag = false;
				model.setFloatingPointPrecision(CalculatorModel.PRECISION_0);
				model.setOperationMode(CalculatorModel.FLOAT_MODE);

				for (int i = 0; i < hexButtons.length; i++) {

					hexButtons[i].setEnabled(false);

				}
				/*
				 * if no error state is detected, the displays are set to their default values
				 */
				if (model.getErrorState() == false) {
					display(true);
					defaultDisplays();
				}

				break;
			/* This handles .00 precision button events */
			case ".00":

				integerFlag = false;
				model.setFloatingPointPrecision(CalculatorModel.PRECISION_00);
				model.setOperationMode(CalculatorModel.FLOAT_MODE);

				for (int i = 0; i < hexButtons.length; i++) {

					hexButtons[i].setEnabled(false);

				}

				/*
				 * if no error state is detected, the displays are set to their default values
				 */
				if (model.getErrorState() == false) {
					display(true);
					defaultDisplays();
				}

				break;
			/* This handles Sci precision button events */
			case "Sci":

				integerFlag = false;
				model.setOperationMode(CalculatorModel.FLOAT_MODE);
				model.setFloatingPointPrecision(CalculatorModel.PRECISION_SCI);

				for (int i = 0; i < hexButtons.length; i++) {

					hexButtons[i].setEnabled(false);

				}
				/*
				 * if no error state is detected, the displays are set to their default values
				 */
				if (model.getErrorState() == false) {
					display(true);
					defaultDisplays();
				}
				break;
			/* This handles Hex precision button events */
			case "Hex":

				integerFlag = true;
				model.setOperationMode(CalculatorModel.INTEGER_MODE);
				model.setFloatingPointPrecision(CalculatorModel.HEXADECIMAL);

				for (int i = 0; i < hexButtons.length; i++) {

					hexButtons[i].setEnabled(true);

				}

				/*
				 * if no error state is detected, the displays are set to their default values
				 */
				if (model.getErrorState() == false) {
					display(false);
					defaultDisplays();
				}
				break;

			default:
				break;
			}

			/*
			 * if an error was detected in the above code, this will allow it to display in
			 * the calculator
			 */
			if (keypadError == true) {

				error.setText("E");
				error.setBackground(Color.RED);
				display2.setText(model.getErrorMessage());
				model.resetErrorMessage();

			}

		}

	}

}
