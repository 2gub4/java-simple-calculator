import javax.swing.*;
import java.awt.event.ActionListener;


public class Buttons {
    public JButton[] numeric;
    public JButton[] operators;
    public JButton[] functional;
    public JButton[] memoryManagement;


    Buttons(int numericButtonCount, String[] operatorsLabels, String[] memoryManagementLabels, String[] functionalButtonsLabels, ActionListener listener) {
        this.numeric = generateNumericButtons(numericButtonCount, listener);
        this.memoryManagement = generateButtons(memoryManagementLabels, listener);
        this.functional = generateButtons(functionalButtonsLabels, listener);
        this.operators = generateButtons(operatorsLabels, listener);
    }

    public static JButton[] generateNumericButtons(int count, ActionListener listener) {
        var buttons = new JButton[count];
        for (int i = 0; i < count; i++) {
            buttons[i] = new JButton(Integer.toString(i+1));
            buttons[i].addActionListener(listener);
            buttons[i].setFocusable(false);
        }
        buttons[count-1].setText("0");
        return buttons;
    }

    public static JButton[] generateButtons(String[] buttonLabels, ActionListener listener) {
        var bs = new JButton[buttonLabels.length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = new JButton(buttonLabels[i]);
            bs[i].addActionListener(listener);
            bs[i].setFocusable(false);
        }
        return bs;
    }
}