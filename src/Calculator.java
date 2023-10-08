import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
public class Calculator extends JFrame implements ActionListener {
    private final JTextField display;
    private String input = "";
    private String currentOperation = "";
    //Button creation method
    public void createButton(String text, int x, int y, int width, int height, String name, JTextField display) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBackground(new Color(253, 127, 57));
        button.setForeground(Color.white);
        button.addActionListener(this);
        button.setText(text);
        button.setBounds(x, y, width, height);
        button.setName(name);
        this.add(button);
    }
    public void createNumberButton(String text, int x, int y, int width, int height, String name, JTextField display) {
        JButton numberButton = new JButton();
        numberButton.setBorderPainted(false);
        numberButton.setFocusPainted(false);
        numberButton.setOpaque(true);
        numberButton.setBackground(Color.white);
        numberButton.setForeground(Color.black);
        numberButton.addActionListener(this);
        numberButton.setText(text);
        numberButton.setBounds(x, y, width, height);
        numberButton.setName(name);
        this.add(numberButton);
    }
    Calculator() {
        // Declaring frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Calculator");
        this.setSize(350, 420);
        this.setResizable(false);
        this.setVisible(true);
        //Styling frame
        ImageIcon icon = new ImageIcon("Calculator-icon.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0x3A3B3C));

        display = new JTextField();
        display.setBounds(5, 5, 335, 80);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        this.add(display);

        createButton("+", 250, 100, 100, 55, "+", display);
        createButton("-", 250, 155, 100, 55, "-", display);
        createButton("x", 250, 210, 100, 55, "x", display);
        createButton("÷", 250, 265, 100, 55, "÷", display);
        createButton("=", 250, 320, 100, 75, "=", display);
        createButton("C", 5, 310, 81, 80, "C", display);

        // Creating number buttons and passing the display component
        createNumberButton("1", 5, 100, 83, 70, "1", display);
        createNumberButton("2", 86, 100, 81, 70, "2", display);
        createNumberButton("3", 167, 100, 83, 70, "3", display);
        createNumberButton("4", 5, 170, 83, 70, "4", display);
        createNumberButton("5", 86, 170, 83, 70, "5", display);
        createNumberButton("6", 167, 170, 83, 70, "6", display);
        createNumberButton("7", 5, 240, 81, 70, "7", display);
        createNumberButton("8", 86, 240, 81, 70, "8", display);
        createNumberButton("9", 167, 240, 83, 70, "9", display);
        createNumberButton("0", 86, 310, 81, 80, "0", display);

        this.revalidate();
        this.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonName = clickedButton.getName();
        if (buttonName.equals("C")) {
            input = "";
            currentOperation = "";
            // Clear the display field
            display.setText("");
        } else if (buttonName.equals("=")) {
            if (!input.isEmpty() && !currentOperation.isEmpty()) {
                String[] parts = input.split(Pattern.quote(currentOperation));

                if (parts.length == 2) {
                    double num1 = Double.parseDouble(parts[0]);
                    double num2 = Double.parseDouble(parts[1]);
                    double calculatedResult = 0;

                    switch (currentOperation) {
                        case "+":
                            calculatedResult = num1 + num2;
                            break;
                        case "-":
                            calculatedResult = num1 - num2;
                            break;
                        case "x":
                            calculatedResult = num1 * num2;
                            break;
                        case "÷":
                            if (num2 != 0) {
                                calculatedResult = num1 / num2;
                            } else {
                                display.setText("You can't divide by 0!");
                                return;
                            }
                            break;
                    }
                    if (calculatedResult == (int) calculatedResult) {
                        input = String.valueOf((int) calculatedResult);
                    } else {
                        input = String.valueOf(calculatedResult);
                    }
                    currentOperation = "";
                    display.setText(input);
                }
            }
        } else {
            input += buttonName;
            if (buttonName.matches("[+x÷-]")) {
                currentOperation = buttonName;
            }
            display.setText(input);
        }
    }
}
