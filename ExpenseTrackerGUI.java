import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}

public class ExpenseTrackerGUI {
    private List<Expense> expenses = new ArrayList<>();

    private JFrame frame;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField categoryField;
    private JTextArea expensesTextArea;

    public ExpenseTrackerGUI() {
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        descriptionField = new JTextField();
        amountField = new JTextField();
        categoryField = new JTextField();
        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(addButton);

        expensesTextArea = new JTextArea();
        expensesTextArea.setEditable(false);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(expensesTextArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addExpense() {
        String description = descriptionField.getText();
        String amountText = amountField.getText();
        String category = categoryField.getText();

        if (!amountText.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                Expense expense = new Expense(description, amount, category);
                expenses.add(expense);
                updateExpensesTextArea();
                clearInputFields();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Amount is required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateExpensesTextArea() {
        expensesTextArea.setText("");
        for (Expense expense : expenses) {
            expensesTextArea.append("Description: " + expense.getDescription() + "\n");
            expensesTextArea.append("Amount: $" + expense.getAmount() + "\n");
            expensesTextArea.append("Category: " + expense.getCategory() + "\n\n");
        }
    }

    private void clearInputFields() {
        descriptionField.setText("");
        amountField.setText("");
        categoryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseTrackerGUI();
            }
        });
    }
}
