import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(30, 30, 100, 25);
        add(fromLabel);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(30, 70, 100, 25);
        add(toLabel);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 110, 100, 25);
        add(amountLabel);

        String[] currencies = {"USD", "INR", "EUR", "GBP", "JPY"};
        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(100, 30, 100, 25);
        add(fromCurrency);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(100, 70, 100, 25);
        add(toCurrency);

        amountField = new JTextField();
        amountField.setBounds(100, 110, 100, 25);
        add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(220, 110, 100, 25);
        convertButton.addActionListener(this);
        add(convertButton);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(30, 160, 300, 25);
        add(resultLabel);

        initExchangeRates();
        setVisible(true);
    }

    private void initExchangeRates() {
        exchangeRates = new HashMap<>();

        // base: USD
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("INR", 83.0);
        exchangeRates.put("EUR", 0.93);
        exchangeRates.put("GBP", 0.80);
        exchangeRates.put("JPY", 156.4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double inUSD = amount / exchangeRates.get(from);
            double converted = inUSD * exchangeRates.get(to);

            resultLabel.setText(String.format("Result: %.2f %s", converted, to));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}