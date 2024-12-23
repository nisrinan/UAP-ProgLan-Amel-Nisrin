package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard {
    public void Admindasboardcomponents(){
        JFrame frame = new JFrame("Book Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(16000, 2000);
        frame.setLayout(new GridBagLayout());
//        frame.setLayout(new BorderLayout());
//        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Login.BackgroundPanel bgpanel = new Login.BackgroundPanel("src/bookbg.jpg");
        bgpanel.setLayout(new GridBagLayout());
        frame.setContentPane(bgpanel);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);

        Font font = new Font("Arial",Font.BOLD,30);
        JLabel header = new JLabel("Hello Admin");
        header.setFont(font);
        header.setForeground(Color.WHITE);
        header.setSize(30,30);
        bgpanel.add(header,gbc);

        BookDatabase bookDatabase = new BookDatabase();

        gbc.gridx = 0;
        gbc.gridy = 5;
        String[] columnNames = {"ID", "Title", "Author", "Price", "Stock"};
        JTable table = new JTable(bookDatabase.getBooksData(), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,100));
        scrollPane.setForeground(Color.BLACK);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        bgpanel.add(scrollPane,gbc);

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        gbc.gridy++;
        JButton addbook = new JButton("Add Book");
        addbook.setBackground(Color.LIGHT_GRAY);
        addbook.setForeground(Color.BLACK);
        bgpanel.add(addbook,gbc);

        gbc.gridy++;
        JButton update = new JButton("Update Book");
        update.setBackground(Color.LIGHT_GRAY);
        update.setForeground(Color.BLACK);
        bgpanel.add(update,gbc);

        gbc.gridy++;
        JButton delete = new JButton("Delete Book");
        delete.setBackground(Color.LIGHT_GRAY);
        delete.setForeground(Color.BLACK);
        bgpanel.add(delete,gbc);

        gbc.gridy++;
        JButton back = new JButton("Back");
        back.setBackground(Color.LIGHT_GRAY);
        back.setForeground(Color.BLACK);
        bgpanel.add(back,gbc);


        addbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField idField = new JTextField(20);
                JTextField bookNameField = new JTextField(20);
                JTextField authorField = new JTextField(20);
                JTextField priceField = new JTextField(20);
                JTextField stockField = new JTextField(20);

                JPanel addBookPanel = new JPanel();
                addBookPanel.setLayout(new BoxLayout(addBookPanel, BoxLayout.Y_AXIS));
                addBookPanel.add(new JLabel("Book ID: "));
                addBookPanel.add(idField);
                addBookPanel.add(new JLabel("Book Name: "));
                addBookPanel.add(bookNameField);
                addBookPanel.add(new JLabel("Author: "));
                addBookPanel.add(authorField);
                addBookPanel.add(new JLabel("Price: "));
                addBookPanel.add(priceField);
                addBookPanel.add(new JLabel("Stock: "));
                addBookPanel.add(stockField);

                int option = JOptionPane.showConfirmDialog(null, addBookPanel, "Add Book", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String id = idField.getText();
                        String bookName = bookNameField.getText();
                        String author = authorField.getText();
                        double price = Double.parseDouble(priceField.getText());
                        int stock = Integer.parseInt(stockField.getText());

                        Book newBook = new Book(id, bookName, author, price, stock);
                        bookDatabase.addBook(newBook);

                        table.setModel(new javax.swing.table.DefaultTableModel(bookDatabase.getBooksData(), columnNames));
                        JOptionPane.showMessageDialog(frame, "Book added successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid price or stock input!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a book to update!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField idField = new JTextField((String) table.getValueAt(selectedRow, 0));
                JTextField bookNameField = new JTextField((String) table.getValueAt(selectedRow, 1));
                JTextField authorField = new JTextField((String) table.getValueAt(selectedRow, 2));
                JTextField priceField = new JTextField(table.getValueAt(selectedRow, 3).toString());
                JTextField stockField = new JTextField(table.getValueAt(selectedRow, 4).toString());

                JPanel updateBookPanel = new JPanel();
                updateBookPanel.setLayout(new BoxLayout(updateBookPanel, BoxLayout.Y_AXIS));
                updateBookPanel.add(new JLabel("Book ID: "));
                updateBookPanel.add(idField);
                updateBookPanel.add(new JLabel("Book Name: "));
                updateBookPanel.add(bookNameField);
                updateBookPanel.add(new JLabel("Author: "));
                updateBookPanel.add(authorField);
                updateBookPanel.add(new JLabel("Price: "));
                updateBookPanel.add(priceField);
                updateBookPanel.add(new JLabel("Stock: "));
                updateBookPanel.add(stockField);

                int option = JOptionPane.showConfirmDialog(null, updateBookPanel, "Update Book", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String id = idField.getText();
                        String bookName = bookNameField.getText();
                        String author = authorField.getText();
                        double price = Double.parseDouble(priceField.getText());
                        int stock = Integer.parseInt(stockField.getText());

                        Book updatedBook = new Book(id, bookName, author, price, stock);
                        bookDatabase.updateBook(selectedRow, updatedBook);

                        table.setModel(new javax.swing.table.DefaultTableModel(bookDatabase.getBooksData(), columnNames));
                        JOptionPane.showMessageDialog(frame, "Book updated successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid price or stock input!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a book to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this book?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    bookDatabase.deleteBook(selectedRow);
                    table.setModel(new javax.swing.table.DefaultTableModel(bookDatabase.getBooksData(), columnNames));
                    JOptionPane.showMessageDialog(frame, "Book deleted successfully!");
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Sembunyikan jendela admin
                Login.getInstance().Loginpage(); // Tampilkan halaman login
            }
        });

    }
}
