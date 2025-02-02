// Code adapted from https://github.com/BrickSigma/Java-Calculator
// Commit by BrickSigma: https://github.com/BrickSigma/Java-Calculator/commit/665bc75e463d44770bbdbefb03b4e16127e1595b
// License: Not Found.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner to get user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scientific Calculator");

        // Loop to keep asking for expressions until the user chooses to exit
        while (true) {
            // Ask the user to input an expression (e.g., 3 + 4 * (2 - 1))
            System.out.print("Operator Options: +, -, *, /, ^, "+
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

    // ExpressionParser handles parsing and evaluating mathematical expressions
    private static class ExpressionParser {
        private int pos = -1;
        private int ch;

        // Move to the next character in the expression
        private void nextChar(String expression) {
            ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
        }

        // Skip whitespace characters and check if the current character matches the one to be eaten
        private boolean eat(int charToEat, String expression) {
            while (ch == ' ') nextChar(expression);
            if (ch == charToEat) {
                nextChar(expression);
                return true;
            }
            return false;
        }

        // Parse the entire expression and return the result
        private double parse(String expression) {
            nextChar(expression);
            double x = parseExpression(expression);
            if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char)ch);
            return x;
        }

        // Parse the expression by evaluating the terms and handling addition and subtraction
        private double parseExpression(String expression) {
            double x = parseTerm(expression);
            while (true) {
                if (eat('+', expression)) x += parseTerm(expression);
                else if (eat('-', expression)) x -= parseTerm(expression);
                else return x;
            }
        }

        // Parse the term by evaluating the factors and handling multiplication, division, and exponentiation
        private double parseTerm(String expression) {
            double x = parseFactor(expression);
            while (true) {
                if (eat('*', expression)) x *= parseFactor(expression);
                else if (eat('/', expression)) {
                    double divisor = parseFactor(expression);
                    if (divisor == 0) {
                        throw new RuntimeException("Error: Division by zero.");
                    }
                    x /= divisor;
                }
                else if (eat('^', expression)) x = Math.pow(x, parseFactor(expression));
                else return x;
            }
        }

        // Parse the factor by handling positive/negative signs, parentheses, numbers, and functions
        private double parseFactor(String expression) {
            if (eat('+', expression)) return parseFactor(expression);
            if (eat('-', expression)) return -parseFactor(expression);

            double x;
            int startPos = this.pos;
            if (eat('(', expression)) {
                // Handle parentheses by recursively parsing the expression inside them
                x = parseExpression(expression);
                eat(')', expression);
            }else if (eat('{', expression)) {
                    x = parseExpression(expression);
                    eat('}', expression);
            } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                // Parse numbers (integer or decimal)
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar(expression);
                x = Double.parseDouble(expression.substring(startPos, this.pos));
            } else if (ch >= 'a' && ch <= 'z') {
                // Parse functions (such as sqrt, sin, cos, etc.)
                while (ch >= 'a' && ch <= 'z') nextChar(expression);
                String func = expression.substring(startPos, this.pos);
                x = parseFactor(expression);

                switch (func) {
                    case "sqrt":
                        if (x < 0) throw new RuntimeException("Square root undefined for negative numbers.");
                        x = Math.sqrt(x);
                        break;
                    case "cbrt":
                        x = Math.cbrt(x);
                        break;
                    case "log":
                        if (x <= 0) throw new RuntimeException("Logarithm undefined for non-positive values.");
                        x = Math.log10(x);
                        break;
                    case "ln":
                        if (x <= 0) throw new RuntimeException("Logarithm undefined for non-positive values.");
                        x = Math.log(x);
                        break;
                    case "sin":
                        x = Math.sin(Math.toRadians(x));
                        break;
                    case "cos":
                        x = Math.cos(Math.toRadians(x));
                        break;
                    case "tan":
                        x = Math.tan(Math.toRadians(x));
                        break;
                    case "cot":
                        if (Math.tan(Math.toRadians(x)) == 0) throw new RuntimeException("Cotangent undefined (division by zero).");
                        x = 1 / (Math.tan(Math.toRadians(x)));
                        break;
                    case "sec":
                        if (Math.cos(Math.toRadians(x)) == 0) throw new RuntimeException("Secant undefined (division by zero).");
                        x = 1 / Math.cos(Math.toRadians(x));
                        break;
                    case "cosec":
                        if (Math.sin(Math.toRadians(x)) == 0) throw new RuntimeException("Cosecant undefined (division by zero).");
                        x = 1 / Math.sin(Math.toRadians(x));
                        break;

                    default:
                        throw new RuntimeException("Unknown function: " + func);
                }
            } else {
                throw new RuntimeException("Unexpected: " + (char)ch);
            }
            return x;
        }
    }
}
