
package simplecalculator;

import javax.swing.JOptionPane;

public class SimpleCalculator {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            CalculatorFrame calculator = new CalculatorFrame();
            calculator.setLocationRelativeTo(null);
            calculator.setVisible(true);
        });
    }
}
