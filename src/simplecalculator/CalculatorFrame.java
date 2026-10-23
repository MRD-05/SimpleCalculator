/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package simplecalculator;

/**
 *
 * @author maher
 */
public class CalculatorFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CalculatorFrame.class.getName());

    /**
     * Creates new form CalculatorFrame
     */
    private String expression = "";
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = false;

    public CalculatorFrame() {
        initComponents();
    }

    private void appendNumber(String number) {

        String current = txtDisplay.getText();

        if (current.equals("Error")) {
            clearAll();
            current = "0";
        }

        if (startNewNumber) {
            txtDisplay.setText(number);
            startNewNumber = false;
        } else if (current.equals("0")) {
            if (!number.equals("0")) {
                txtDisplay.setText(number);
            }
        } else {
            txtDisplay.setText(current + number);
        }
    }

    private void setOperator(String op) {

        if (txtDisplay.getText().equals("Error")) {
            return;
        }

        if (startNewNumber && !expression.isEmpty()) {
            // Replace the last operator if another is pressed
            expression = expression.substring(
                    0, expression.length() - 2
            ) + op + " ";
        } else {
            expression += txtDisplay.getText() + " " + op + " ";
        }

        operator = op;
        startNewNumber = true;
    }

    private double evaluateExpression(String input) {

        String[] tokens = input.trim().split("\\s+");

        java.util.List<Double> terms = new java.util.ArrayList<>();
        java.util.List<String> operators = new java.util.ArrayList<>();

        double current = Double.parseDouble(tokens[0]);

        // First pass: multiplication and division
        for (int i = 1; i < tokens.length; i += 2) {

            String op = tokens[i];
            double next = Double.parseDouble(tokens[i + 1]);

            switch (op) {
                case "*":
                    current *= next;
                    break;

                case "/":
                    if (next == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    current /= next;
                    break;

                case "+":
                case "-":
                    terms.add(current);
                    operators.add(op);
                    current = next;
                    break;
            }
        }

        terms.add(current);

        // Second pass: addition and subtraction
        double result = terms.get(0);

        for (int i = 0; i < operators.size(); i++) {

            if (operators.get(i).equals("+")) {
                result += terms.get(i + 1);
            } else {
                result -= terms.get(i + 1);
            }
        }

        return result;
    }

    private void calculateResult() {

        if (expression.isEmpty()) {
            return;
        }

        try {
            String fullExpression = expression;

            if (startNewNumber) {
                // An operator was pressed without a new operand
                return;
            }

            fullExpression += txtDisplay.getText();

            double result = evaluateExpression(fullExpression);

            if (!Double.isFinite(result)) {
                throw new ArithmeticException("Invalid result");
            }

            if (result == Math.rint(result)
                    && Math.abs(result) < 1e15) {

                txtDisplay.setText(
                        String.valueOf((long) result)
                );

            } else {
                txtDisplay.setText(String.valueOf(result));
            }

        } catch (ArithmeticException | NumberFormatException e) {
            txtDisplay.setText("Error");
        }

        expression = "";
        operator = "";
        startNewNumber = true;
    }

    private void appendDecimal() {

        if (startNewNumber) {
            txtDisplay.setText("0.");
            startNewNumber = false;
            return;
        }

        String current = txtDisplay.getText();

        if (current.equals("Error")) {
            clearAll();
            current = "0";
        }

        if (!current.contains(".")) {
            txtDisplay.setText(current + ".");
        }
    }

    private void clearEntry() {
        txtDisplay.setText("0");

        if (!operator.isEmpty()) {
            startNewNumber = true;
        } else {
            startNewNumber = false;
        }
    }

    private void clearAll() {
        firstNumber = 0;
        operator = "";
        expression = "";
        startNewNumber = false;
        txtDisplay.setText("0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDisplay = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnAllClear = new javax.swing.JButton();
        btnDivide = new javax.swing.JButton();
        btnMultiply = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnSubtract = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btnEquals = new javax.swing.JButton();
        btnDecimal = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Calculator");
        setPreferredSize(new java.awt.Dimension(350, 450));
        setResizable(false);

        txtDisplay.setEditable(false);
        txtDisplay.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtDisplay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplay.setText("0");

        btnClear.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnClear.setText("C");
        btnClear.setPreferredSize(new java.awt.Dimension(65, 50));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAllClear.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnAllClear.setText("AC");
        btnAllClear.setPreferredSize(new java.awt.Dimension(65, 50));
        btnAllClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllClearActionPerformed(evt);
            }
        });

        btnDivide.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnDivide.setText("÷");
        btnDivide.setPreferredSize(new java.awt.Dimension(65, 50));
        btnDivide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivideActionPerformed(evt);
            }
        });

        btnMultiply.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnMultiply.setText("×");
        btnMultiply.setPreferredSize(new java.awt.Dimension(65, 50));
        btnMultiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplyActionPerformed(evt);
            }
        });

        btn7.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn7.setText("7");
        btn7.setPreferredSize(new java.awt.Dimension(65, 50));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn8.setText("8");
        btn8.setPreferredSize(new java.awt.Dimension(65, 50));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn9.setText("9");
        btn9.setPreferredSize(new java.awt.Dimension(65, 50));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btnSubtract.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnSubtract.setText("−");
        btnSubtract.setPreferredSize(new java.awt.Dimension(65, 50));
        btnSubtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubtractActionPerformed(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn4.setText("4");
        btn4.setPreferredSize(new java.awt.Dimension(65, 50));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn5.setText("5");
        btn5.setPreferredSize(new java.awt.Dimension(65, 50));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn6.setText("6");
        btn6.setPreferredSize(new java.awt.Dimension(65, 50));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnAdd.setText("+");
        btnAdd.setPreferredSize(new java.awt.Dimension(65, 50));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn2.setText("2");
        btn2.setPreferredSize(new java.awt.Dimension(65, 50));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn3.setText("3");
        btn3.setPreferredSize(new java.awt.Dimension(65, 50));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn1.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn1.setText("1");
        btn1.setPreferredSize(new java.awt.Dimension(65, 50));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btnEquals.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnEquals.setText("=");
        btnEquals.setPreferredSize(new java.awt.Dimension(65, 50));
        btnEquals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualsActionPerformed(evt);
            }
        });

        btnDecimal.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btnDecimal.setText(".");
        btnDecimal.setPreferredSize(new java.awt.Dimension(65, 50));
        btnDecimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecimalActionPerformed(evt);
            }
        });

        btn0.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        btn0.setText("0");
        btn0.setPreferredSize(new java.awt.Dimension(65, 50));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDisplay, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAllClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDivide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMultiply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSubtract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnEquals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnAllClear, btnClear, btnDecimal, btnDivide, btnEquals, btnMultiply, btnSubtract});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAllClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDivide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMultiply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubtract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnAllClear, btnClear, btnDecimal, btnDivide, btnEquals, btnMultiply, btnSubtract});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDecimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecimalActionPerformed
        // TODO add your handling code here:
        appendDecimal();
    }//GEN-LAST:event_btnDecimalActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearEntry();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAllClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllClearActionPerformed
        // TODO add your handling code here:
        clearAll();
    }//GEN-LAST:event_btnAllClearActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
        appendNumber("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
        appendNumber("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
        appendNumber("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        appendNumber("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        appendNumber("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
        appendNumber("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        appendNumber("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
        appendNumber("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        appendNumber("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        // TODO add your handling code here:
        appendNumber("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        setOperator("+");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSubtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubtractActionPerformed
        // TODO add your handling code here:
        setOperator("-");
    }//GEN-LAST:event_btnSubtractActionPerformed

    private void btnMultiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplyActionPerformed
        // TODO add your handling code here:
        setOperator("*");
    }//GEN-LAST:event_btnMultiplyActionPerformed

    private void btnDivideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivideActionPerformed
        // TODO add your handling code here:
        setOperator("/");
    }//GEN-LAST:event_btnDivideActionPerformed

    private void btnEqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualsActionPerformed
        // TODO add your handling code here:
        calculateResult();
    }//GEN-LAST:event_btnEqualsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAllClear;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDecimal;
    private javax.swing.JButton btnDivide;
    private javax.swing.JButton btnEquals;
    private javax.swing.JButton btnMultiply;
    private javax.swing.JButton btnSubtract;
    private javax.swing.JTextField txtDisplay;
    // End of variables declaration//GEN-END:variables
}
