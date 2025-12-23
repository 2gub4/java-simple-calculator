import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class SimpleCalculator implements ActionListener {
    JTextField textBox;
    ValueHolder values;
    String expression;
    CalcLogs logFile;
    boolean ongoingExpression, exceptionThrown;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double[] size = {screenSize.getWidth(), screenSize.getHeight()};
    private static final Map<String, DoubleBinaryOperator> twoValExpressions = Map.of(
            "+", CalcFunc::add,
            "-", CalcFunc::subtract,
            "•", CalcFunc::multiply,
            "÷", CalcFunc::divide
    );
    private static final Map<String, DoubleUnaryOperator> oneValExpressions = Map.of(
            "x²", CalcFunc::number_squared,
            "√", CalcFunc::square_root
    );
    private final String[] operatorsLabels = new String[]{"+", "-", "•", "÷", "√", "x²", "="};
    private final String[] functionalLabels = new String[]{".", "%", "±", "◀", "CE", "C"};
    private final String[] memoryLabels = new String[]{"M", "M+", "M-"};
    private final Buttons buttons = new Buttons(10, operatorsLabels, memoryLabels, functionalLabels, this);

    SimpleCalculator() {
        initUI();
        this.values = new ValueHolder();
        this.logFile = new CalcLogs("calcLogs.txt");
    }

    public void execute(String target) {
        String text = textBox.getText();
        if (Pomocnicze.isNumeric(target)) {
            if (this.exceptionThrown || this.ongoingExpression) {
                text = text.equals("-") && (this.expression == null || !this.expression.equals("-")) ? text : "";
                this.exceptionThrown = false;
                this.ongoingExpression = false;
            }
            text += target;
            textBox.setText(text);
        } else {
            switch (target) {
                case "." -> {
                    if (!Pomocnicze.isNumber(text)) return;
                    if (!text.contains(".")) textBox.setText(text + ".");
                }
                case "C" -> {
                    textBox.setText("");
                    this.values.resetValues();
                    this.expression = null;
                    this.exceptionThrown = false;
                    this.ongoingExpression = false;
                }
                case "◀" -> {
                    if (text.isEmpty() || this.exceptionThrown) return;
                    text = text.substring(0, text.length() - 1);
                    if (text.endsWith(".")) text = text.substring(0, text.length() - 1);
                    textBox.setText(text);
                    this.ongoingExpression = false;
                }
                case "CE" -> {
                    if (Pomocnicze.isNumber(text)) textBox.setText("");
                }
                case "±" -> {
                    if (text.isEmpty() || (text.length() == 1 && Arrays.asList(operatorsLabels).contains(text))) {
                        textBox.setText("-");
                        this.ongoingExpression = false;
                    } else if (text.startsWith("-")) {
                        textBox.setText(text.substring(1));
                    } else {
                        textBox.setText("-" + text);
                    }
                }
                case "%" -> {
                    if (!Pomocnicze.isNumber(text)) return;
                    double p = Math.abs(Double.parseDouble(text) / 100);
                    textBox.setText(Pomocnicze.format(p));
                }
                case "=" -> handleEquals(text);
                default -> {
                    if (target.startsWith("M")) {
                        handleMemory(target, text);
                    } else if (oneValExpressions.containsKey(target)) {
                        handleOneValExpression(target, text);
                    } else if (twoValExpressions.containsKey(target)) {
                        handleTwoValExpression(target, text);
                    }
                }
            }
        }
        textBox.requestFocus();
    }

    private void handleOneValExpression(String target, String text) {
        if (!Pomocnicze.isNumber(text)) return;
        try {
            double val = Double.parseDouble(text);
            double res = oneValExpressions.get(target).applyAsDouble(val);
            String resultString = Pomocnicze.format(res);
            String logEntry = target + "(" + Pomocnicze.format(val) + ") = " + resultString;
            IO.println(logEntry);
            this.logFile.write(logEntry);
            textBox.setText(resultString);
            this.values.setFirstValue(res);
            this.ongoingExpression = true;
            this.expression = null;
        } catch (Exception e) {
            triggerError();
        }
    }

    private void handleTwoValExpression(String target, String text) {
        if (text.isEmpty() || text.equals("-") || Arrays.asList(operatorsLabels).contains(text)) return;
        try {
            this.values.setFirstValue(Double.parseDouble(text));
            this.ongoingExpression = true;
            this.expression = target;
            textBox.setText(target);
        } catch (NumberFormatException ignored) {}
    }

    private void handleEquals(String text) {
        if (this.expression == null || text.isEmpty() || Arrays.asList(operatorsLabels).contains(text)) return;
        try {
            this.values.setSecondValue(Double.parseDouble(text));
            double res = twoValExpressions.get(this.expression).applyAsDouble(this.values.getFirstValue(), this.values.getSecondValue());
            this.values.setResult(res);
            String resultString = Pomocnicze.format(res);
            String logEntry = Pomocnicze.format(this.values.getFirstValue()) + " " + this.expression + " " + Pomocnicze.format(this.values.getSecondValue()) + " = " + resultString;
            IO.println(logEntry);
            this.logFile.write(logEntry);
            textBox.setText(resultString);
            this.values.setFirstValue(res);
        } catch (Exception e) {
            triggerError();
        } finally {
            this.ongoingExpression = true;
        }
    }

    private void handleMemory(String target, String text) {
        if (target.length() == 1) {
            textBox.setText(Pomocnicze.format(this.values.getMemorizedValue()));
        } else if (Pomocnicze.isNumber(text)) {
            double value = Double.parseDouble(text);
            char operator = target.endsWith("-") ? '-' : '+';
            String logEntry = Pomocnicze.format(this.values.getMemorizedValue()) + " " + operator + " " + Pomocnicze.format(value);
            this.values.setMemorizedValue(value, operator);
            logEntry += " = " + Pomocnicze.format(this.values.getMemorizedValue());
            IO.println(logEntry);
            try { this.logFile.write(logEntry); } catch (IOException ignored) {}
            textBox.setText("");
        }
    }

    private void triggerError() {
        this.exceptionThrown = true;
        textBox.setText("Error");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        execute(((JButton) e.getSource()).getText());
    }

    void initUI() {
        var f = new JFrame("Simple Calculator");
        Container c = f.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        textBox = new JTextField(16);
        textBox.setEditable(false);
        textBox.addActionListener(this);
        textBox.addKeyListener(new CalcKeybordListener(this::execute));
        textBox.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipady = 10;
        c.add(textBox, gbc);
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        int memX = 0;
        for (var b : buttons.memoryManagement) {
            gbc.gridx = memX;
            gbc.gridy = 1;
            gbc.insets = new Insets(2, (memX == 0) ? 5 : 2, 6, (memX == 2) ? 5 : 2);
            c.add(b, gbc);
            memX++;
        }
        int numX = 0;
        for (var b : buttons.numeric) {
            if (b.getText().equals("0")) continue;
            int col = numX % 3;
            gbc.gridx = col;
            gbc.gridy = (numX / 3) + 2;
            gbc.insets = new Insets(2, (col == 0) ? 5 : 2, 2, (col == 2) ? 5 : 2);
            c.add(b, gbc);
            numX++;
        }
        for (var b : buttons.numeric) {
            if (b.getText().equals("0")) {
                gbc.gridx = 1;
                gbc.gridy = 5;
                gbc.insets = new Insets(2, 2, 2, 2);
                c.add(b, gbc);
                break;
            }
        }
        int opIdx = 0;
        for (var b : buttons.operators) {
            gbc.gridx = 3;
            if (b.getText().equals("=")) {
                gbc.gridy = 7;
                gbc.insets = new Insets(2, 5, 10, 5);
            } else {
                gbc.gridy = 1 + opIdx;
                gbc.insets = new Insets(1, 5, 1, 5);
                opIdx++;
            }
            c.add(b, gbc);
        }
        int funcX = 0;
        for (var b : buttons.functional) {
            int col = funcX % 3;
            gbc.gridx = col;
            gbc.gridy = 6 + (funcX / 3);
            int bottom = (funcX >= 3) ? 10 : 2;
            gbc.insets = new Insets(2, (col == 0) ? 5 : 2, bottom, (col == 2) ? 5 : 2);
            c.add(b, gbc);
            funcX++;
        }
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setIconImage(new ImageIcon("kalk.png").getImage());
        f.setLocation((int) size[0] / 2 - (f.getWidth() / 2), (int) size[1] / 2 - (f.getHeight() / 2));
        f.setVisible(true);
    }
}