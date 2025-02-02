// Code adapted from https://github.com/BrickSigma/Java-Calculator
// Commit by BrickSigma: https://github.com/BrickSigma/Java-Calculator/commit/665bc75e463d44770bbdbefb03b4e16127e1595b
// License: Not Found.

// Import the Scanner class to read user input
import java.util.Scanner;

public class ScientificCalculator {

    public static void main(String[] args) {
        // Create a scanner to get user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scientific Calculator");

        // Loop to keep asking for expressions until the user chooses to exit
        while (true) {
            // Ask the user to input an expression (e.g., 3 + 4 * (2 - 1))
            System.out.print("Operator Options: +, -, *, /, ^, " +
                    "sin(), cos(), tan(), cot(), ln(), log(), sqrt(), cbrt(), (), {}\n");
            System.out.print("\nEnter an expression (or type 'exit' to quit): ");
            String expression = scanner.nextLine().trim();

            // Check if the user wants to exit the calculator
            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the calculator.");
                break;  // Exit the loop and close the program
            }

            try {
                // Call the function to evaluate the expression and store the result
                double result = evaluateExpression(expression);
                System.out.println("Result: " + result);  // Output the result
            } catch (Exception e) {
                // Catch any errors and print them
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Close the scanner
        scanner.close();
    }

    // Function to evaluate a mathematical expression
    public static double evaluateExpression(String expression) throws Exception {
        // Check if the expression contains only valid characters (digits, operators, parentheses, and functions)
        if (!expression.matches("[0-9\\+\\-\\*/^\\(\\)\\{\\}a-zA-Z.]+")) {
            throw new RuntimeException("Invalid characters in expression. Only digits, operators, parentheses, and functions are allowed. No Spaces.");
        }

        // Call the parser to evaluate the expression directly
        return new ExpressionParser().parse(expression);
    }

    // ExpressionParser class handles parsing and evaluating mathematical expressions
    private static class ExpressionParser {
        private int pos = -1;   // The current position in the expression
        private int ch;     // The current character being processed

        // Move to the next character in the expression
        private void nextChar(String expression) {
            ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
        }

        // Eat (skip) a specific character if it matches the current one, and move to the next character
        private boolean eat(int charToEat, String expression) {
            while (ch == ' ') nextChar(expression); // Skip spaces
            if (ch == charToEat) {
                nextChar(expression);   // Move to the next character
                return true;    // Successfully matched the character
            }
            return false;   // The character did not match
        }

        // Parse the entire expression and return the result
        private double parse(String expression) {
            nextChar(expression);   // Initialize the character position
            double x = parseExpression(expression); // Parse the main expression
            if (pos < expression.length())
                throw new RuntimeException("Unexpected: " + (char) ch);  // Handle invalid characters
            return x;
        }

        // Parse the expression and handle addition and subtraction operations
        private double parseExpression(String expression) {
            double x = parseTerm(expression);   // Start by parsing the first term
            while (true) {
                if (eat('+', expression)) x += parseTerm(expression);   // If '+' found, add the next term
                else if (eat('-', expression)) x -= parseTerm(expression);  // If '-' found, subtract the next term
                else return x;  // Return the result if no more addition or subtraction
            }
        }

        // Parse terms and handle multiplication, division, and exponentiation
        private double parseTerm(String expression) {
            double x = parseFactor(expression); // Parse the first factor (number or expression)
            while (true) {
                if (eat('*', expression)) x *= parseFactor(expression); // If '*' found, multiply the next factor
                else if (eat('/', expression)) {
                    double divisor = parseFactor(expression);   // Parse the divisor for division
                    if (divisor == 0) {
                        throw new RuntimeException("Error: Division by zero."); // Handle division by zero
                    }
                    x /= divisor;   // Perform the division
                } else if (eat('^', expression))
                    x = Math.pow(x, parseFactor(expression));  // Handle exponentiation (^) operator
                else return x;  // Return the result if no more multiplication, division, or exponentiation
            }
        }

        // Parse the factor by handling positive/negative signs, parentheses, numbers, and functions
        private double parseFactor(String expression) {
            if (eat('+', expression)) return parseFactor(expression);   // Handle positive signs
            if (eat('-', expression)) return -parseFactor(expression);  // Handle negative signs

            double x;
            int startPos = this.pos;    // Save the current position for later use
            if (eat('(', expression)) {
                // Handle parentheses by recursively parsing the expression inside them
                x = parseExpression(expression);
                eat(')', expression);   // Ensure the closing parenthesis is matched
            } else if (eat('{', expression)) {
                x = parseExpression(expression);    // Handle curly braces the same way as parentheses
                eat('}', expression);   // Ensure the closing curly brace is matched
            } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                // Parse numbers (integer or decimal)
                while ((ch >= '0' && ch <= '9') || ch == '.')
                    nextChar(expression); // Move through the digits of the number
                x = Double.parseDouble(expression.substring(startPos, this.pos));   // Convert the parsed number to a double
            } else if (ch >= 'a' && ch <= 'z') {
                // Parse functions (such as sqrt, sin, cos, etc.)
                while (ch >= 'a' && ch <= 'z') nextChar(expression);    // Move through the function name
                String func = expression.substring(startPos, this.pos); // Get the function name
                x = parseFactor(expression);    // Parse the argument for the function

                // Handle the different mathematical functions
                switch (func) {
                    case "sqrt":
                        if (x < 0) throw new RuntimeException("Square root undefined for negative numbers.");
                        x = Math.sqrt(x);   // Calculate the square root
                        break;
                    case "cbrt":
                        x = Math.cbrt(x);   // Calculate the cube root
                        break;
                    case "log":
                        if (x <= 0) throw new RuntimeException("Logarithm undefined for non-positive values.");
                        x = Math.log10(x);  // Calculate the base-10 logarithm
                        break;
                    case "ln":
                        if (x <= 0) throw new RuntimeException("Logarithm undefined for non-positive values.");
                        x = Math.log(x);    // Calculate the natural logarithm
                        break;
                    case "sin":
                        x = Math.sin(Math.toRadians(x));    // Calculate the sine of the angle (in radians)
                        break;
                    case "cos":
                        x = Math.cos(Math.toRadians(x));    // Calculate the cosine of the angle (in radians)
                        break;
                    case "tan":
                        x = Math.tan(Math.toRadians(x));    // Calculate the tangent of the angle (in radians)
                        break;
                    case "cot":
                        if (Math.tan(Math.toRadians(x)) == 0)
                            throw new RuntimeException("Cotangent undefined (division by zero).");
                        x = 1 / (Math.tan(Math.toRadians(x)));  // Calculate the cotangent
                        break;
                    case "sec":
                        if (Math.cos(Math.toRadians(x)) == 0)
                            throw new RuntimeException("Secant undefined (division by zero).");
                        x = 1 / Math.cos(Math.toRadians(x));    // Calculate the secant
                        break;
                    case "cosec":
                        if (Math.sin(Math.toRadians(x)) == 0)
                            throw new RuntimeException("Cosecant undefined (division by zero).");
                        x = 1 / Math.sin(Math.toRadians(x));    // Calculate the cosecant
                        break;

                    default:
                        throw new RuntimeException("Unknown function: " + func);    // Handle unknown functions
                }
            } else {
                throw new RuntimeException("Unexpected: " + (char) ch); // Handle unexpected characters
            }
            return x;   // Return the parsed result
        }
    }
}