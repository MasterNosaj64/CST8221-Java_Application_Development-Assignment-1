package calculator;

/**
 * File Name: CalculatorModel.java Author: Jason Waid, 040912687 Course: CST8221
 * - JAP, Lab Section: 311 Assignment: 1, Part 2 Date: Nov 1st 2019 Professor:
 * Daniel Cormier Purpose: The brain of the calculator Class list:
 * CalculatorModel
 * 
 */

/**
 * Class contains all data relating to the calculation of the calculator
 * 
 * @author Jason Waid
 * @version 1
 * @see javax.swing.JPanel
 * @since 1.8.0
 */

public class CalculatorModel {

	/* Constants */
	public static final int PRECISION_0 = 0;
	public static final int PRECISION_00 = 1;
	public static final int PRECISION_SCI = 2;
	public static final int HEXADECIMAL = 3;
	public static final int INTEGER_MODE = 4;
	public static final int FLOAT_MODE = 5;
	public static final int OPERAND_1 = 6;
	public static final int OPERAND_2 = 7;
	public static final int RESULT = 8;

	/* holds arithmetic operation */
	private String operation;
	private String operand1;
	private String operand2;

	/* keeps track of the current state of the calculator */
	private int state;
	private int precisionMode;

	/* determines integer or float */
	private int operationalMode;

	/* keeps track of the error state */
	private boolean errorState;

	String errorMessage = "";


	/**
	 * Constructor initializes all variables to their defaults
	 */
	
	public CalculatorModel() {

		this.operation = null;
		this.operand1 = null;
		this.operand2 = null;
		this.state = OPERAND_1;
		this.precisionMode = PRECISION_00;
		this.operationalMode = FLOAT_MODE;
		this.errorState = false;

	}

	
	/**
	 * sets operand1
	 * @param operand - first operand
	 */
	
	void setOperand1(String operand) {

		operand1 = operand;
		state = OPERAND_1;

	}

	/**
	 * sets operand2
	 * @param operand - second operand
	 */
	
	void setOperand2(String operand) {

		operand2 = operand;
		state = OPERAND_2;
	}

	/**
	 * sets operation
	 * @param operation - operation to be set
	 */

	void setOperation(String operation) {

		this.operation = operation;
		state = OPERAND_2;

	}

	/**
	 * sets operational mode
	 * @param mode - value of one of the many constants see above
	 */

	void setOperationMode(final int mode) {

		this.operationalMode = mode;

	}
	
	/**
	 * checks for integer mode
	 * @return boolean
	 */
	

	public boolean integerMode() {
		return operationalMode == INTEGER_MODE;
	}

	/**
	 * checks for float mode
	 * @return boolean
	 */
	
	public boolean floatMode() {

		return operationalMode == FLOAT_MODE;
	}

	/**
	 * sets floating point precision
	 * @param precision - desired precision mode
	 */
	void setFloatingPointPrecision(final int precision) {

		this.precisionMode = precision;
	}

	/**
	 * gets current precision mode
	 * @return previsionMode - the current mode
	 */
	public int getPrecision() {

		return precisionMode;
	}

	/**
	 * gets result by calling calculate method
	 * @return result - string formated appropriately for the mode
	 */
	
	String getResult() {

		String result = calculate();

		if (result != null) {

			operand1 = result;
		} else
			clear();

		return result;
	}

	/**
	 * sets the error state
	 * @param error - boolean
	 */

	void setErrorState(boolean error) {

		if (operand1 == null || operand2 == null || operation.isEmpty() || Double.isInfinite(OPERAND_1)
				|| Double.isInfinite(OPERAND_2) || Double.isNaN(OPERAND_1) || Double.isNaN(OPERAND_2)) {
			error = true;
		}

		this.errorState = error;
	}

	/**
	 * returns the error state
	 * @return errorState - boolean
	 */

	boolean getErrorState() {

		return errorState;

	}

	/**
	 * returns the error message string
	 * @return errormessage - String
	 */

	public String getErrorMessage() {

		return errorMessage;
	}

	
	/**
	 * resets the error message
	 */
	
	public void resetErrorMessage() {
		errorMessage = "";
	}

	
	/**gets the current state of the calculator
	 * 
	 * @return state - the state
	 */
	
	public int getState() {

		return state;
	}

	/**
	 * the method that does all the calculations and formats the string
	 * @return formated string
	 */

	public String calculate() {

		if (operationalMode == INTEGER_MODE) {

			int op1 = 0;
			int op2 = 0;

			if (op1 == 0 && op2 == 0 && operation == "/") {
				errorState = true;
				clear();
				errorMessage = "Result is undefined";
				return null;
			}

			if (op2 == 0 && operation == "/") {
				errorState = true;
				clear();
				errorMessage = "Cannot divide by zero";
				return null;

			}
			// update state to RESULT stage
			state = RESULT;

			// if mode is hexadecimal the following will apply
			if (precisionMode == HEXADECIMAL) {

				op1 = Integer.parseInt(operand1, 16);
				op2 = Integer.parseInt(operand1, 16);

				switch (operation) {

				case "+":

					return String.format("%x", op1 + op2).toUpperCase();

				case "-":

					return String.format("%x", op1 - op2).toUpperCase();

				case "*":

					return String.format("%x", op1 * op2).toUpperCase();

				case "/":

					return String.format("%x", op1 / op2).toUpperCase();

				}
				// otherwise regular integer arithmetic
			} else {

				switch (operation) {

				case "+":

					return String.format("%d", op1 + op2);

				case "-":

					return String.format("%d", op1 - op2);

				case "*":

					return String.format("%d", op1 * op2);

				case "/":

					return String.format("%d", op1 / op2);

				}
			}
			return null;

		} else if (operationalMode == FLOAT_MODE) {

			double op1 = 0;
			double op2 = 0;

			/** handles the user using float */
			try {
				op1 = Double.valueOf(operand1);
				op2 = Double.valueOf(operand2);
			} catch (NumberFormatException e) {
				errorState = true;
				return null;
			}

			if (op1 == 0 && op2 == 0 && operation == "/") {
				errorState = true;
				clear();
				errorMessage = "Result is undefined";
				return null;
			}

			if (op2 == 0 && operation == "/") {
				errorState = true;
				clear();
				errorMessage = "Cannot divide by zero";
				return null;

			}

			// update state to RESULT stage
			state = RESULT;

			if (precisionMode == PRECISION_0) {

				switch (operation) {

				case "+":

					return String.format("%.1f", op1 + op2);

				case "-":

					return String.format("%.1f", op1 - op2);

				case "*":

					return String.format("%.1f", op1 * op2);

				case "/":

					return String.format("%.1f", op1 / op2);

				}
			}

			if (precisionMode == PRECISION_00) {

				switch (operation) {

				case "+":

					return String.format("%.2f", op1 + op2);

				case "-":

					return String.format("%.2f", op1 - op2);

				case "*":

					return String.format("%.2f", op1 * op2);

				case "/":

					return String.format("%.2f", op1 / op2);

				}
			}

			if (precisionMode == PRECISION_SCI) {

				switch (operation) {

				case "+":

					return String.format("%E", op1 + op2);

				case "-":

					return String.format("%E", op1 - op2);

				case "*":

					return String.format("%E", op1 * op2);

				case "/":

					return String.format("%E", op1 / op2);

				}

				return null;

			}

		}

		return null;

	}
	
	/**
	 * resets the state of the calculator
	 */

	public void clear() {

		state = OPERAND_1;
		errorState = false;
		operation = null;
		operand1 = null;
		operand2 = null;
	}

}