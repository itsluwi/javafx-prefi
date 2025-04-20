package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label displayLabel;

    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;

    @FXML
    public void initialize() {
        displayLabel.setText("0");
    }

    @FXML
    private void onButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "CLEAR":
                clear();
                break;
            case "BCKSPC":
                backspace();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            case "=":
                calculate();
                break;
            default:
                appendNumber(buttonText);
        }
    }

    private void appendNumber(String value) {
        if (startNewInput) {
            currentInput.setLength(0);
            startNewInput = false;
        }

        if (value.equals(".") && currentInput.toString().contains(".")) return;

        currentInput.append(value);
        displayLabel.setText(currentInput.toString());
    }

    private void setOperator(String op) {
        if (currentInput.length() == 0) return;

        firstOperand = Double.parseDouble(currentInput.toString());
        operator = op;
        startNewInput = true;
    }

    private void calculate() {
        if (operator.isEmpty() || currentInput.isEmpty()) return;

        double secondOperand = Double.parseDouble(currentInput.toString());
        double result = 0;

        switch (operator) {
            case "+": result = firstOperand + secondOperand; break;
            case "-": result = firstOperand - secondOperand; break;
            case "*": result = firstOperand * secondOperand; break;
            case "/":
                if (secondOperand == 0) {
                    displayLabel.setText("Error");
                    return;
                }
                result = firstOperand / secondOperand;
                break;
        }

        displayLabel.setText(String.valueOf(result));
        currentInput = new StringBuilder(String.valueOf(result));
        operator = "";
        startNewInput = true;
    }

    private void clear() {
        currentInput.setLength(0);
        firstOperand = 0;
        operator = "";
        displayLabel.setText("0");
    }

    private void backspace() {
        if (currentInput.length() > 0) {
            currentInput.setLength(currentInput.length() - 1);
            displayLabel.setText(currentInput.length() == 0 ? "0" : currentInput.toString());
        }

    }
}
