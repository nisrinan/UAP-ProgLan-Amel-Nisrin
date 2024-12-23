📚 Sistem Manajemen Toko Buku
📖 Gambaran Umum
Sistem Manajemen Toko Buku adalah aplikasi berbasis Java yang dirancang untuk memfasilitasi pengelolaan toko buku. Aplikasi ini memungkinkan administrator untuk mengelola inventaris buku, termasuk menambah, memperbarui, dan menghapus buku, sambil menyediakan antarmuka yang ramah pengguna bagi pelanggan untuk menjelajahi dan membeli buku.

🚀 Fitur
Dasbor Admin:

Menambah, memperbarui, dan menghapus buku.
Melihat semua buku dalam format tabel.
Antarmuka yang ramah pengguna untuk mengelola inventaris buku.
Dasbor Pengguna:

Menjelajahi buku yang tersedia.
Membeli buku dengan pembaruan stok secara real-time.
Validasi input untuk transaksi pembelian.
Persistensi Data:

Data buku disimpan dalam file Excel (books.xlsx), memastikan data tetap ada antara sesi.
UI Responsif:

Antarmuka grafis pengguna (GUI) yang intuitif dibangun menggunakan Swing.
🛠️ Teknologi yang Digunakan
Java: Bahasa pemrograman utama yang digunakan untuk membangun aplikasi.
Apache POI: Perpustakaan yang digunakan untuk membaca dan menulis file Excel.
Swing: Toolkit GUI Java untuk membangun antarmuka pengguna.
📁 Struktur Proyek

Verify

Open In Editor
Run
Copy code
MultipleFiles/
├── AdminDashboard.java
├── Book.java
├── BookDatabase.java
├── Login.java
├── Main.java
├── UserDashboard.java
├── pom.xml
├── books.xlsx
├── henry-be-lc7xcWebECc-unsplash.jpg
└── MultipleFiles/ (berisi berbagai file konfigurasi dan biner)
📦 Instalasi
Kloning Repositori:

bash

Verify

Open In Editor
Run
Copy code
git clone <repository-url>
cd <repository-directory>
Membangun Proyek:

Pastikan Anda telah menginstal Maven.
Jalankan perintah berikut untuk membangun proyek:
bash

Verify

Open In Editor
Run
Copy code
mvn clean install
Menjalankan Aplikasi:

Eksekusi kelas Main untuk memulai aplikasi:
bash

Verify

Open In Editor
Run
Copy code
java -cp target/<your-jar-file>.jar org.example.Main
🎮 Cara Menggunakan
Login:

Setelah meluncurkan aplikasi, Anda akan disajikan dengan layar login.
Pilih untuk masuk sebagai Admin atau Pengguna.
Fungsi Admin:

Admin dapat menambah buku baru dengan mengisi detail buku dalam formulir yang disediakan.
Memperbarui detail buku yang ada dengan memilih buku dari daftar dan memodifikasi informasinya.
Menghapus buku dari inventaris sesuai kebutuhan.
Fungsi Pengguna:

Pengguna dapat melihat daftar buku yang tersedia.
Untuk membeli buku, masukkan ID buku dan jumlah yang ingin dibeli, bersama dengan jumlah uang yang Anda bayar.
Aplikasi akan memvalidasi input dan memproses pembelian.
📄 Lisensi
Proyek ini dilisensikan di bawah Lisensi MIT - lihat file LICENSE untuk detail.

📞 Kontak
Untuk pertanyaan atau umpan balik, silakan hubungi:

Email: your-email@example.com
GitHub: your-github-profile