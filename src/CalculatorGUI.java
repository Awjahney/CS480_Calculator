import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JPanel Frame;
    private JTextField CalculationArea;
    private JPanel CalculatorButtons;
    private JButton EqualButton;
    private JButton Num0Button;
    private JButton Num9Button;
    private JButton DecimalButton;
    private JButton PlusButton;
    private JButton Num3Button;
    private JButton Num5Button;
    private JButton Num6Button;
    private JButton MinusButton;
    private JButton BackButton;
    private JButton MultiplyButton;
    private JButton Num8Button;
    private JButton DivideButton;
    private JButton LParenthesesButton;
    private JButton Num1Button;
    private JButton Num4Button;
    private JButton Num7Button;
    private JButton RParenthesesButton;
    private JButton Num2Button;
    private JButton ClearButton;
    private JButton LCurlyBracketButton;
    private JButton RCurlyBracketButton;
    private JPanel CalculationPanel;
    private JButton CotangentButton;
    private JButton SecantButton;
    private JButton CosecantButton;
    private JButton TangentButton;
    private JButton CosineButton;
    private JButton SineButton;
    private JButton LogBaseEButton;
    private JButton ArcsineButton;
    private JButton ArccosineButton;
    private JButton ArctangentButton;
    private JButton LogBase10Button;
    private JButton SquareRootButton;
    private JButton ExponentButton;
    private JButton PercentButton;

    public CalculatorGUI() {
        //Setup JFrame
        Frame = new JFrame("Scientific Calculator");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(400,600);
        Frame.setLayout(new GridLayout());

        //Calculation Display
        CalculationArea = new JTextField();
        CalculationArea.setFont(new Font("Arial", Font.BOLD, 24));
        CalculationArea.setHorizontalAlignment(JTextField.CENTER);
        CalculationArea.setEditable(false);
        Frame.add(CalculationArea,GridLayout.CENTER);

        //Button Panel
        CalculatorButtons = new JPanel();
        CalculatorButtons.setLayout(new GridLayout(6, 6));

        //Define Buttons
        String[] buttons = {"atan(x)", "cot(θ)", "{", "}", "C", "⬅",
                "acos(x)", "sec(θ)", "(", ")", "√", "/",
                "asin(x)", "csc(θ)", "7", "8", "9", "*",
                "log_10(x)", "tan(θ)", "4", "5", "6", "-",
                "ln(x)", "cos(θ)", "1", "2", "3", "+",
                "%", "sin(θ)", "e^x", "0", ".", "="
        };

        //Add Buttons
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            CalculatorButtons.add(button);
        }

        Frame.add(CalculatorButtons,GridLayout.CENTER);
        Frame.setVisible(true);
    }
    //Event Handling for Buttons
    private class ButtonClickListener implements ActionListener {
        @Override

    }
}
