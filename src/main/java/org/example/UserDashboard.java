

package org.example;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserDashboard extends JFrame {
    private BookDatabase bookDatabase;

    public UserDashboard() {
        this.setTitle("User Dashboard");
        this.setSize(800, 600);
        this.setLocationRelativeTo((Component)null);
        this.bookDatabase = new BookDatabase();

        Login.BackgroundPanel bgpanel = new Login.BackgroundPanel("src/bookbg.jpg");
        bgpanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Agar transparan terhadap background

        JLabel userhello = new JLabel("Hallo User", JLabel.CENTER); // Center-align teks
        userhello.setFont(new Font("Arial", Font.BOLD, 18)); // Sesuaikan font
        userhello.setForeground(Color.WHITE); // Sesuaikan warna teks
        topPanel.add(userhello, BorderLayout.CENTER); // Tambahkan ke topPanel

        // Tambahkan panel teks di bagian atas dari bgpanel
        bgpanel.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = new String[]{"ID", "Title", "Author", "Price", "Stock"};
        Object[][] data = this.bookDatabase.getBooksData();

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300,100));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel purchasePanel = new JPanel(new GridLayout(4, 2, 5, 5));
        purchasePanel.setBorder(BorderFactory.createTitledBorder("Purchase Panel"));

        JLabel idLabel = new JLabel("Book ID: ");
        JTextField idField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField quantityField = new JTextField(10);
        JLabel amountLabel = new JLabel("Enter Amount: ");
        JTextField amountField = new JTextField(10);
        JButton buyButton = new JButton("Buy");

        purchasePanel.add(idLabel);
        purchasePanel.add(idField);
        purchasePanel.add(quantityLabel);
        purchasePanel.add(quantityField);
        purchasePanel.add(amountLabel);
        purchasePanel.add(amountField);
        purchasePanel.add(new JLabel());
        purchasePanel.add(buyButton);

        buyButton.addActionListener((e) -> {
            String bookId = idField.getText();
            String quantityText = quantityField.getText();
            String amountText = amountField.getText();

            try {
                if (bookId.isEmpty() || quantityText.isEmpty() || amountText.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled!");
                }

                int quantity = Integer.parseInt(quantityText);
                int amount = Integer.parseInt(amountText);
                int selectedRow = this.bookDatabase.getRowIndexById(bookId);
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Book ID not found!", "Error", 0);
                    return;
                }

                double price = (Double)this.bookDatabase.getPrice(selectedRow);
                int stock = this.bookDatabase.getStock(selectedRow);
                double totalPrice = price * (double)quantity;
                if (quantity > stock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock!", "Error", 0);
                } else if ((double)amount < totalPrice) {
                    JOptionPane.showMessageDialog(this, "Insufficient amount!", "Error", 0);
                } else {
                    this.bookDatabase.updateStock(selectedRow, stock - quantity);
                    double change = (double)amount - totalPrice;
                    JOptionPane.showMessageDialog(this, "Purchase successful!\nTotal Price: " + totalPrice + "\n Change: " + change);
                    table.setModel(new DefaultTableModel(this.bookDatabase.getBooksData(), columnNames));
                }
            } catch (NumberFormatException var20) {
                JOptionPane.showMessageDialog(this, "Invalid input format!", "Error", 0);
            } catch (IllegalArgumentException var21) {
                JOptionPane.showMessageDialog(this, var21.getMessage(), "Error", 0);
            }

        });
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener((e) -> {
            this.dispose();
            Login.getInstance().Loginpage();
        });
        bottomPanel.add(backButton, BorderLayout.EAST);
        bottomPanel.setOpaque(false); // Agar transparan terhadap background
        bgpanel.add(scrollPane, BorderLayout.CENTER);
        bgpanel.add(purchasePanel, BorderLayout.SOUTH);
        bgpanel.add(bottomPanel, BorderLayout.NORTH);

        this.setContentPane(bgpanel);
        this.setVisible(true);
    }
}
