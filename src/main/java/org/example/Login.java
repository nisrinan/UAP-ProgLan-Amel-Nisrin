package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private static Login instance;

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public void Loginpage() {
        JFrame frame = new JFrame("Book Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(16000, 2000);
        frame.setLayout(new GridBagLayout());

        BackgroundPanel bgpanel = new BackgroundPanel("src/bookbg.jpg");
        bgpanel.setLayout(new GridBagLayout());

        Font font = new Font("Arial", Font.BOLD, 30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcome = new JLabel("Welcome to Amel & Nisrina's Book Store");
        welcome.setFont(font);
        welcome.setForeground(Color.WHITE);
        bgpanel.add(welcome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel welcome2 = new JLabel("Choose Login Option");
        welcome2.setForeground(Color.WHITE);
        bgpanel.add(welcome2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton adminlogin = new JButton("Login as Admin");
        adminlogin.setForeground(Color.BLACK);
        adminlogin.setBackground(Color.LIGHT_GRAY);
        bgpanel.add(adminlogin, gbc);

        adminlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminDashboard admindasboard = new AdminDashboard();
                admindasboard.Admindasboardcomponents();

            }
        });


        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton userlogin = new JButton("Login as User");
        userlogin.setForeground(Color.BLACK);
        userlogin.setBackground(Color.LIGHT_GRAY);
        bgpanel.add(userlogin, gbc);

        userlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserDashboard();
            }
        });

        frame.setContentPane(bgpanel);
        frame.setVisible(true);
    }

static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                // Memuat gambar sebagai Image
                ImageIcon imageIcon = new ImageIcon(imagePath);
                backgroundImage = imageIcon.getImage(); // Mendapatkan objek Image dari ImageIcon
            } catch (Exception e) {
                System.out.println("Gagal memuat gambar: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Menggambar gambar sebagai background
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}