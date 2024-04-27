import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PersonalFinanceTracker extends JFrame implements ActionListener {
    private JTextField incomeField, expenseField;
    private JButton addIncomeButton, addExpenseButton, generateReportButton;
    private JTextArea reportTextArea;
    private ArrayList<Double> incomeList, expenseList;
    private double totalIncome, totalExpense;

    public PersonalFinanceTracker() {
        setTitle("Personal Finance Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        incomeField = new JTextField(20);
        expenseField = new JTextField(20);
        addIncomeButton = new JButton("Add Income");
        addExpenseButton = new JButton("Add Expense");
        generateReportButton = new JButton("Generate Report");
        reportTextArea = new JTextArea();
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
        totalIncome = 0;
        totalExpense = 0;

        // Create layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Income: "));
        inputPanel.add(incomeField);
        inputPanel.add(addIncomeButton);
        inputPanel.add(new JLabel("Expense: "));
        inputPanel.add(expenseField);
        inputPanel.add(addExpenseButton);
        inputPanel.add(generateReportButton);

        // Add action listeners
        addIncomeButton.addActionListener(this);
        addExpenseButton.addActionListener(this);
        generateReportButton.addActionListener(this);

        // Create layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(reportTextArea), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIncomeButton) {
            // Add income and update total
            try {
                double income = Double.parseDouble(incomeField.getText());
                incomeList.add(income);
                totalIncome += income;
                incomeField.setText("");
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(this, "Invalid income amount. Please enter a valid number.");
            }
        } else if (e.getSource() == addExpenseButton) {
            // Add expense and update total
            try {
                double expense = Double.parseDouble(expenseField.getText());
                expenseList.add(expense);
                totalExpense += expense;
                expenseField.setText("");
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(this, "Invalid expense amount. Please enter a valid number.");
            }
        } else if (e.getSource() == generateReportButton) {
            // Generate and display financial report
            DecimalFormat df = new DecimalFormat("#.##");
            StringBuilder report = new StringBuilder();
            report.append("----- Personal Finance Report -----\n");
            report.append("Total Income: Rs.").append(df.format(totalIncome)).append("\n");
            report.append("Total Expenses: Rs.").append(df.format(totalExpense)).append("\n");
            report.append("Net Income: Rs.").append(df.format(totalIncome - totalExpense)).append("\n");
            report.append("\nIncome Details:\n");
            for (double income : incomeList) {
                report.append("Rs.").append(df.format(income)).append("\n");
            }
            report.append("\nExpense Details:\n");
            for (double expense : expenseList) {
                report.append("Rs.").append(df.format(expense)).append("\n");
            }

            reportTextArea.setText(report.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PersonalFinanceTracker tracker = new PersonalFinanceTracker();
            tracker.setVisible(true);
        });
    }
}
