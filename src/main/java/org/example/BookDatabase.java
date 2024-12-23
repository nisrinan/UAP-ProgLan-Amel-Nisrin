package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    private List<Book> books;
    private String filePath = "books.xlsx";

    // Constructor
    public BookDatabase() {
        books = new ArrayList<>();
        loadBooksFromFile();  // Load books from the Excel file if exists
        initializeDefaultBooks();  // Add default books if the list is empty
    }

    // Menambah buku ke database
    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile(); // Simpan ke file setelah menambahkan buku
    }

    // Menghapus buku dari database
    public void deleteBook(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < books.size()) {
            books.remove(selectedRow);
            saveBooksToFile(); // Simpan ke file setelah menghapus buku
        }
    }

    // Memperbarui buku di database
    public void updateBook(int selectedRow, Book updatedBook) {
        if (selectedRow >= 0 && selectedRow < books.size()) {
            books.set(selectedRow, updatedBook);
            saveBooksToFile(); // Simpan ke file setelah memperbarui buku
        }
    }

    // Menyimpan buku ke file Excel
    private void saveBooksToFile() {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Books");

            // Membuat header kol om
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Title");
            header.createCell(2).setCellValue("Author");
            header.createCell(3).setCellValue("Price");
            header.createCell(4).setCellValue("Stock");

            // Menulis data buku ke baris berikutnya
            int rowNum = 1;
            for (Book book : books) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(book.getId());
                row.createCell(1).setCellValue(book.getTitle());
                row.createCell(2).setCellValue(book.getAuthor());
                row.createCell(3).setCellValue(book.getPrice());
                row.createCell(4).setCellValue(book.getStock());
            }

            // Menyimpan file
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Memuat data buku dari file Excel
    private void loadBooksFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return; // Jika file tidak ada, tidak ada yang dimuat
        }

        try (FileInputStream fileIn = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Membaca setiap baris data buku
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String id = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                String author = row.getCell(2).getStringCellValue();
                double price = row.getCell(3).getNumericCellValue();
                int stock = (int) row.getCell(4).getNumericCellValue();

                Book book = new Book(id, title, author, price, stock);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Menambahkan buku default jika list kosong
    private void initializeDefaultBooks() {
        if (books.isEmpty()) {
            books.add(new Book("001", "The Great Gatsby", "F. Scott Fitzgerald", 100.0, 10));
            books.add(new Book("002", "To Kill a Mockingbird", "Harper Lee", 120.0, 8));
            books.add(new Book("003", "1984", "George Orwell", 110.0, 15));
            saveBooksToFile(); // Save default books to file
        }
    }

    // Mendapatkan semua buku
    public Object[][] getBooksData() {
        Object[][] data = new Object[books.size()][5];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getId();
            data[i][1] = book.getTitle();
            data[i][2] = book.getAuthor();
            data[i][3] = book.getPrice();
            data[i][4] = book.getStock();
        }
        return data;
    }

    public int getRowIndexById(String bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(bookId)) {
                return i;
            }
        }
        return -1; // Jika tidak ditemukan
    }

    public Object getPrice(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < books.size()) {
            return books.get(selectedRow).getPrice();
        }
        return null; // Jika tidak valid
    }

    public int getStock(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < books.size()) {
            return books.get(selectedRow).getStock();
        }
        return 0; // Jika tidak valid
    }

    public void updateStock(int selectedRow, int newStock) {
        if (selectedRow >= 0 && selectedRow < books.size()) {
            books.get(selectedRow).setStock(newStock);
            saveBooksToFile(); // Simpan perubahan ke file
        }
    }
}