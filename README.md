# Scientific Calculator

## Description
This is a scientific calculator built in Java that can evaluate a wide range of mathematical expressions. It supports arithmetic operations, trigonometric functions, logarithmic functions, and more. The calculator also handles parentheses and multiple operators with correct precedence.

#### Note:
This is a partially adapted version of the original Parser.java file with my own updates. The original project can be found here: [Java-Calculator](https://github.com/BrickSigma/Java-Calculator).

### Features:
- Arithmetic operations: `+`, `-`, `*`, `/`, `^` (exponentiation)
- Trigonometric functions: `sin()`, `cos()`, `tan()`, `cot()`, `sec()`, `cosec()`
- Logarithmic functions: `log()` (base 10), `ln()` (natural logarithm)
- Square root and cube root: `sqrt()`, `cbrt()`
- Parentheses support: `()`, `{}` for grouping
- Handles multistep mathematical expressions
- Error handling for invalid input, division by zero, and undefined functions

## Prerequisites
To run this project, you need:
- Java 8 or later

## Installation
1. Clone this repository to your local machine:

       git clone https://github.com/yourusername/ScientificCalculator.git
2. Navigate to the project directory:

       cd ScientificCalculator
3. Compile the Java files:

       javac Main.java
4. Run the calculator:

       java Main

## Usage
After running the program, you will be prompted to enter a mathematical expression. You can input an expression involving numbers, operators, and functions (like `sin()`, `sqrt()`, etc.).

### Example expressions:
- `3 + 5 * (2 - 1)`
- `sqrt(16)`
- `sin(30)`
- `log(100)`
- `2 ^ 3`
- `tan(45)`

To exit the program, type `exit`. 

### Supported Operators:
- `+` : Addition
- `-` : Subtraction (binary and unary)
- `*` : Multiplication
- `/` : Division
- `^` : Exponentiation 
- `sin()`, `cos()`, `tan()`, `cot()`, `sec()`, `cosec()` : Trigonometric functions 
- `log()`, `ln()` : Logarithmic functions 
- `sqrt()`, `cbrt()` : Square and cube roots
- `()` and `{}` : Parentheses for grouping

## Example Input/Output:
#### Input:
    Enter an expression (or type 'exit' to quit): 3 + 5 * (2 - 1)

#### Output:
    Result: 8.0

## Error Handling
If the expression contains invalid characters or results in errors like division by zero, the program will output an error message with the details.

#### Example:
`Error: Division by zero.`

## Contributing
Feel free to fork this repository, make improvements, and submit pull requests. If you find bugs or issues, please open an issue on the GitHub repository page.

## License
This project is licensed under the MIT License.