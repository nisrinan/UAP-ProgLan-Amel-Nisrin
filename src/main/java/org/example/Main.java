package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = Login.getInstance(); // Ambil instance dari Login
            login.Loginpage(); // Tampilkan halaman login
        });
    }
}