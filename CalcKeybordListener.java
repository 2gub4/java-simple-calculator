import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.function.Consumer;

public class CalcKeybordListener extends KeyAdapter {

    private final Consumer<String> iCb;
    private final String[] charsNotToBeIgnored =
            {"+", "-", "•", "÷", "√", "x²", "=", "±", "CE", ".", "*", "^", "/", "C", "%"};

    public CalcKeybordListener(Consumer<String> cB) {
        this.iCb = cB;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (iCb == null) return;

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ENTER) {
            iCb.accept("=");
            e.consume();
            return;
        }
        if (keyCode == KeyEvent.VK_BACK_SPACE) {
            iCb.accept("◀");
            e.consume();
            return;
        }
        if (keyCode == KeyEvent.VK_C) {
            iCb.accept("C");
            e.consume();
            return;
        }
        char keyChar = e.getKeyChar();
        String c = String.valueOf(keyChar);

        if (c.equals("-")) {
            iCb.accept("-");
            e.consume();
            return;
        }

        c = switch (c) {
            case "/" -> "÷";
            case "*" -> "•";
            case "^" -> "x²";
            default -> c;
        };

        if (Arrays.asList(charsNotToBeIgnored).contains(c) || Pomocnicze.isNumeric(c)) {
            iCb.accept(c);
            e.consume();
        }
    }
}