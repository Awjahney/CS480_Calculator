import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Create a JFrame to hold the Calculator GUI
        JFrame frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //Create the Calculator GUI and add it to the frame
        CalculatorGUI calculatorPanel = new CalculatorGUI();
        frame.add(calculatorPanel);

        frame.setVisible(true); //Make JFrame visible on screen
    }
}