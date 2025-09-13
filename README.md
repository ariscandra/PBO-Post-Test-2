<a name="top"></a>
    
# PBO | Post-Test 2 | Manajemen Pets Daycare Breakdown

## 📚 Daftar Isi
- [👥 Deskripsi Program](#-deskripsi-program)
- [📖 Penjelasan Kode](#-penjelasan-kode)
- [🖥️ Penjelasan Alur Program](#️-penjelasan-alur-program)

## 👥 Deskripsi Program
**Manajemen Pets Daycare**

Program ini adalah aplikasi manajemen penitipan hewan (Pets Daycare) yang dikembangkan dengan bahasa pemrograman Java menggunakan pendekatan Model-View-Controller (MVC). Berikut ini adalah komponen utamanya:

**1. Fitur Utama**

- **Operasi CRUD**: Program mampu melakukan operasi Create, Read, Update, dan Delete data peliharaan yang dititipkan
- **Manajemen Data Pets**: Mengelola informasi lengkap tentang hewan yang dititipkan termasuk ID, nama, jenis, umur, dan pemilik
- **Pencarian Data**: Fitur search yang memungkinkan pencarian berdasarkan ID, nama, jenis, atau nama pemilik (case-insensitive/tidak peduli dengan besar kecilnya karakter)
  
**2. Arsitektur MVC**

- **Model**: Class `Pet` yang merepresentasikan struktur data dengan properti dan constructor
- **View**: Class `Main` yang menangani tampilan antarmuka pengguna dan input/output
- **Controller**: Class `PetService` yang menangani logika bisnis dan operasi CRUD

**3. Struktur Package**

- **package main**: Berisi class `Main` yang menyimpan menu utama pengguna
- **package service**: Menyimpan class `PetService` dengan logika operasi CRUD
- **package model**: Menyimpan class `Pet` dengan struktur data dan constructor
- **package util**: Menyimpan class `ValidationUtil` dengan fungsi validasi input

**4. Validasi Input**

- **hanyaHurufDanSpasi()**: Memvalidasi bahwa input hanya mengandung huruf dan spasi (untuk nama, jenis, dan nama pemilik)
- **hanyaAngka()**: Memvalidasi bahwa input hanya mengandung angka (untuk field umur)
- **Batas Panjang Input**: Membatasi panjang input untuk setiap field (nama max 30 karakter, jenis max 20 karakter, pemilik max 40 karakter)
- **Rentang Nilai**: Memastikan umur hewan berada dalam rentang yang wajar (0-30 tahun)

**5. Access Modifier & Properties**

- Menggunakan access modifier private untuk properti class dengan getter dan setter
- Terdapat 5 properties dalam class Pet: id, name, jenis, umur, dan pemilik
- Menerapkan constructor untuk inisialisasi objek

**6. Alur Program**

- Program menampilkan menu utama dengan opsi-opsi CRUD dan pencarian
- Pengguna dapat memilih opsi untuk menambah, melihat, mengupdate, menghapus, atau mencari data hewan
- Setiap opsi akan memandu pengguna melalui proses input dengan validasi
- Program akan terus berjalan sampai pengguna memilih opsi keluar

**7. Keamanan Data**

- Program mencegah input yang tidak valid melalui berbagai lapisan validasi
- Menghindari error dengan penanganan exception untuk konversi tipe data
- Memastikan konsistensi data dengan batasan-batasan yang jelas

**8. Kesimpulan**

Program ini memberikan solusi terstruktur untuk mengelola data penitipan hewan harian dengan menerapkan prinsip-prinsip PBO dan arsitektur MVC.

## 📖 Penjelasan Kode

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 1</h3></summary>
  <a href="https://github.com/ariscandra/PBO-Post-Test-1?tab=readme-ov-file#-penjelasan-kode">Lihat disini</a>
</details>

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 2</h3></summary>

  **1. Class Main**
```java
package main;

import model.Pet;
import service.PetService;
import util.ValidasiUtil;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PetService petService = new PetService();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("""
                       _____     _              _       
                      |  __ \\   | |            (_)      
                      | |__) |__| |_ ___  _ __  _  __ _ 
                      |  ___/ _ \\ __/ _ \\| '_ \\| |/ _` |
                      | |  |  __/ || (_) | |_) | | (_| |
                      |_|   \\___|\\__\\___/| .__/|_|\\__,_|
                                         | |            
                                         |_|            
                    """);

            System.out.println("\n=== Haloooo Admin Aris, Good to see you back! Moga sehat selalu! ===");
            System.out.println("\n+==== Petopia Pets Daycare ====+");
            System.out.println("| [1] Tambah Data Pets         |");
            System.out.println("| [2] Lihat Semua Data Pets    |");
            System.out.println("| [3] Update Data Pets         |");
            System.out.println("| [4] Hapus Data Pets          |");
            System.out.println("| [5] Cari Data Pets           |");
            System.out.println("| [0] Keluar                   |");
            System.out.println("+==============================+");
            System.out.print("Pilih menu (0-5): ");

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahPet(scanner, petService);
                case 2 -> lihatSemuaPets(petService);
                case 3 -> updatePet(scanner, petService);
                case 4 -> hapusPet(scanner, petService);
                case 5 -> cariPet(scanner, petService);
                case 0 -> System.out.println("Program selesai. See you soon Mimin!");
                default -> System.out.println("Pilihan tidak valid, min! Silakan pilih 0-5.");
            }
        } while (pilihan != 0);

        scanner.close();
    }

    private static void tambahPet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Tambah Data Pet ---");
        
        String name = validasiInput(scanner, "Nama pet: ", "nama", 30);
        String jenis = validasiInput(scanner, "Jenis pet: ", "jenis", 20);
        String umur = validasiUmur(scanner);
        String pemilik = validasiInput(scanner, "Nama Pemilik: ", "pemilik", 40);

        Pet newPet = new Pet(null, name, jenis, umur, pemilik);
        petService.tambahPet(newPet);
        System.out.println(newPet.getNama() + " berhasil ditambahkan dengan ID: " + newPet.getId());
    }

    private static void lihatSemuaPets(PetService petService) {
        System.out.println("\n--- Daftar Pets di Petopia ---");
        List<Pet> pets = petService.getDaftar();
        if (pets.isEmpty()) {
            System.out.println("Belum ada pets yang dititipkan :(\n");
        } else {
            System.out.println("ID | Nama | Jenis | Umur | Pemilik");
            System.out.println("------------------------------------");
            for (Pet pet : pets) {
                System.out.printf("%s | %s | %s | %s Tahun | %s\n",
                        pet.getId(), pet.getNama(), pet.getJenis(), pet.getUmur(), pet.getPemilik());
                System.out.println("------------------------------------");
            }
        }
    }

    private static void updatePet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Update Data Pet ---");
        System.out.print("Masukkan ID pet yang akan diupdate: ");
        String id = scanner.nextLine();

        List<Pet> pets = petService.getDaftar();
        Pet petCocok = null;
        for (Pet pet : pets) {
            if (pet.getId().equals(id)) {
                petCocok = pet;
                break;
            }
        }

        if (petCocok == null) {
            System.out.println("Pet dengan ID " + id + " tidak ada, min!");
            return;
        }

        System.out.println("Data saat ini:");
        System.out.println("Nama: " + petCocok.getNama());
        System.out.println("Jenis: " + petCocok.getJenis());
        System.out.println("Umur: " + petCocok.getUmur());
        System.out.println("Pemilik: " + petCocok.getPemilik());

        System.out.println("\nMasukkan data pet yang baru:");
        String name = validasiInput(scanner, "Nama Baru: ", "nama", 30);
        String jenis = validasiInput(scanner, "Jenis Baru: ", "jenis", 20);
        String umur = validasiUmur(scanner);
        String pemilik = validasiInput(scanner, "Pemilik Baru: ", "pemilik", 40);

        Pet petBaru = new Pet(id, name, jenis, umur, pemilik);
        if (petService.updatePet(id, petBaru)) {
            System.out.println("Data pet berhasil diperbarui!");
        } else {
            System.out.println("Gagal memperbarui data pet!");
        }
    }

    private static void hapusPet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Hapus Data Pet ---");
        System.out.print("Masukkan ID pet yang akan dihapus: ");
        String id = scanner.nextLine();

        if (petService.hapusPet(id)) {
            System.out.println("Pet dengan ID " + id + " berhasil dihapus!");
        } else {
            System.out.println("Pet dengan ID " + id + " tidak ada, min!");
        }
    }

    private static void cariPet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Cari Data Pet ---");
        System.out.print("Masukkan keyword (ID/Nama/Jenis/Umur/Pemilik): ");
        String keyword = scanner.nextLine();

        List<Pet> hasil = petService.cariPet(keyword);
        if (hasil.isEmpty()) {
            System.out.println("Tidak ditemukan pet dengan kata kunci '" + keyword + "'");
        } else {
            System.out.println("Hasil pencarian:");
            System.out.println("ID | Nama | Jenis | Umur | Pemilik");
            System.out.println("------------------------------------");
            for (Pet pet : hasil) {
                System.out.printf("%s | %s | %s | %s Tahun | %s\n",
                        pet.getId(), pet.getNama(), pet.getJenis(), pet.getUmur(), pet.getPemilik());
                System.out.println("------------------------------------");
            }
        }
    }

    private static String validasiInput(Scanner scanner, String prompt, String field, int maxLength) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            if (input.length() > maxLength) {
                System.out.printf("Error: %s tidak boleh lebih dari %d karakter.\n", field, maxLength);
                continue;
            }
            
            if (ValidasiUtil.hanyaHurufDanSpasi(input)) {
                return input;
            } else {
                System.out.printf("Error: %s hanya boleh mengandung huruf dan spasi.\n", field);
            }
        }
    }

    private static String validasiUmur(Scanner scanner) {
        while (true) {
            System.out.print("Umur: ");
            String input = scanner.nextLine();
            
            if (input.length() > 2) {
                System.out.println("Error: Umur tidak boleh lebih dari 2 digit.");
                continue;
            }
            
            if (ValidasiUtil.hanyaAngka(input)) {
                try {
                    int umur = Integer.parseInt(input);
                    if (umur < 0 || umur > 30) {
                        System.out.println("Error: Umur harus antara 0 dan 30 tahun.");
                        continue;
                    }
                    return input;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Format angka tidak valid.");
                }
            } else {
                System.out.println("Error: Umur hanya boleh mengandung angka.");
            }
        }
    }
}
```

<p align="justify">Sebagai titik jalannya program dan menangani antarmuka pengguna. Bagian-bagian penting lainnya:</p>

- Method main(): Menampilkan menu utama dan mengarahkan ke fungsi yang sesuai berdasarkan pilihan user
- Method tambahPet(): Menangani proses penambahan data pet dengan validasi input
- Method lihatSemuaPets(): Menampilkan semua data pets yang tersimpan
- Method updatePet(): Mengupdate data pet berdasarkan ID
- Method hapusPet(): Menghapus data pet berdasarkan ID
- Method cariPet(): Mencari data pet berdasarkan keyword (ID, nama, jenis, atau pemilik)
- Method validasiInput(): Memvalidasi input text (hanya huruf dan spasi)
- Method validasiUmur(): Memvalidasi input umur (hanya angka dan retang 0-30)
  
  **2. Class Pet**
```java
package model;

public class Pet {
    private String id;
    private String nama;
    private String jenis;
    private String umur;
    private String pemilik;

    public Pet(String id, String nama, String jenis, String umur, String pemilik) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.umur = umur;
        this.pemilik = pemilik;
    }

    // Ini getter ama setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public String getUmur() { return umur; }
    public void setUmur(String umur) { this.umur = umur; }
    public String getPemilik() { return pemilik; }
    public void setPemilik(String pemilik) { this.pemilik = pemilik; }
}
```

<p align="justify">Sebagai model data untuk representasi objek pet. Bagian penting lainnya mencakup: </p>

- Atribut: id, nama, jenis, umur, pemilik (semua private)
- Constructor: Untuk inisialisasi objek Pet dengan semua atributnya
- Getter dan Setter: Method untuk mengakses dan mengubah nilai atribut (menerapkan encapsulation)

  **3. Class PetService**
```java
package service;

import model.Pet;
import java.util.ArrayList;
import java.util.List;

public class PetService {
    private ArrayList<Pet> pets = new ArrayList<>();
    private int hitungId = 1;

    // tambah
    public void tambahPet(Pet pet) {
        pet.setId(String.valueOf(hitungId++));
        pets.add(pet);
    }

    // daftar
    public List<Pet> getDaftar() {
        return pets;
    }

    // apdet
    public boolean updatePet(String id, Pet newPet) {
        for (Pet pet : pets) {
            if (pet.getId().equals(id)) {
                pet.setNama(newPet.getNama());
                pet.setJenis(newPet.getJenis());
                pet.setUmur(newPet.getUmur());
                pet.setPemilik(newPet.getPemilik());
                return true;
            }
        }
        return false;
    }

    // apus
    public boolean hapusPet(String id) {
        return pets.removeIf(pet -> pet.getId().equals(id));
    }

    // cari
    public List<Pet> cariPet(String keyword) {
        List<Pet> hasil = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getId().toLowerCase().contains(keyword.toLowerCase()) ||
            	pet.getNama().toLowerCase().contains(keyword.toLowerCase()) ||
                pet.getJenis().toLowerCase().contains(keyword.toLowerCase()) ||
                pet.getPemilik().toLowerCase().contains(keyword.toLowerCase())) {
                hasil.add(pet);
            }
        }
        return hasil;
    }
}
```

<p align="justify">Menangani logika bisnis dan operasi CRUD. Bagian terpentingnya yaitu:</p>

- ArrayList<Pet> pets: Menyimpan koleksi data pets
- Method tambahPet(): Menambahkan pet baru dengan ID otomatis
- Method getDaftar(): Mengembalikan semua data pets
- Method updatePet(): Memperbarui data pet berdasarkan ID
- Method hapusPet(): Menghapus pet berdasarkan ID
- Method cariPet(): Mencari pet berdasarkan keyword di semua field

  **4. Class ValidasiUtil**
```java
package util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidasiUtil {
    public static boolean hanyaHurufDanSpasi(String input) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean hanyaAngka(String input) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
```

<p align="justify">Menyediakan fungsi untuk validasi input. Bagian pentingnya ialah sebagai berikut:</p>

- Method hanyaHurufDanSpasi(): Memvalidasi input hanya mengandung huruf dan spasi
- Method hanyaAngka(): Memvalidasi input hanya mengandung angka

  **5. Kesimpulan**
  
<p align="justify">Program ini menerapkan konsep MVC dengan jelas dimana:</p>

- Model (Pet) mengatur struktur data
- View (Main) menangani tampilan dan interaksi user
- Controller (PetService) mengatur logika bisnis dan manipulasi data
- Util (ValidasiUtil) menyediakan fungsi bantu untuk validasi
  
</details>

## 🖥️ Penjelasan Alur Program

### Versi Post-Test 1

<details>
<summary><h3>Menu Utama</h3></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/160529b6-3faa-4619-a260-b163aa4f6c1e" alt="" width="500px">
</div>

<p align="justify">Gambar di atas merupakan tampilan menu utama program ketika pertama dijalankan.</p>

**1. Jika input tidak valid**
<div align="center">
  <img src="https://github.com/user-attachments/assets/f3498574-ac7e-4051-9a81-2c6808623bb8" alt="" width="500px">
</div>

<p align="justify">Jika pengguna menginput di luar daripada opsi (0-4) di menu utama, maka akan ada dialog teks seperti pada gambar di atas. Menu akan diulang, pengguna diminta untuk menginput lagi.</p>

**2. Jika opsi 0(Keluar) dipilih**
<div align="center">
  <img src="https://github.com/user-attachments/assets/17ec4145-6c43-4fd0-a33c-2ef6d7d46657" alt="" width="500px">
</div>

<p align="justify">Program akan berhenti berjalan jika pengguna menginput opsi untuk keluar (0). Menu akan berhenti berulang, program selesai.</p>

</details>

<details>
<summary><h3>Menambah Data Pet</h3></summary>

**1. Validasi input dan jika berupa selain huruf dan spasi**
<div align="center">
  <img src="https://github.com/user-attachments/assets/b80c4e30-3f77-4224-8042-927d36d062fb" alt="" width="500px">
</div>

<p align="justify">Jika pengguna mengisi field input nama pet, jenis, dan nama pemilik dengan angka. Maka, akan muncul dialog teks di atas. Pengguna diminta mengulang inputnya.</p>

**2. Validasi input dan jika lebih dari jumlah karakter yang ditentukan**
<div align="center">
  <img src="https://github.com/user-attachments/assets/0e9d08cc-c6d7-4a1b-90a5-4ec632b2fc40" alt="" width="500px">
</div>

<p align="justify">Jika pengguna mengisi field input nama pet dengan karakter lebih dari 30, jenis lebih dari 20, dan nama pemilik lebih dari 40 karakter. Maka, akan muncul dialog teks di atas. Pengguna diminta mengulang inputnya hingga validasi sukses.</p>

**3. Validasi input dan jika umur lebih dari 2 digit atau berupa selain angka**
<div align="center">
  <img src="https://github.com/user-attachments/assets/3042bf6c-fc79-41ad-b6d0-3c27d1fee06a" alt="" width="500px">
</div>

<p align="justify">Jika pengguna menginput lebih dari 3 digit angka atau memasukkan huruf pada field input umur. Maka, akan muncul dialog teks seperti pada gambar di atas.</p>

**4. Validasi input dan jika umur di luar rentang 0-30 tahun**
<div align="center">
  <img src="https://github.com/user-attachments/assets/dcd1329d-d8ca-4a34-9b85-ef5ca8b74d51" alt="" width="500px">
</div>

<p align="justify">Jika pengguna memasukkan umur pet di bawah 0 atau lebih dari 30 tahun, maka akan diminta input ulang.</p>

**5. Berhasil menambah data**
<div align="center">
  <img src="https://github.com/user-attachments/assets/4805e553-e2fa-4d30-9019-6c85aad5afa3" alt="" width="500px">
</div>

<p align="justify">Pada gambar di atas merupakan tampilan apabila proses penambahan data pet berhasil.</p>

</details>

<details>
<summary><h3>Melihat Data Pet</h3></summary>

**1. Jika data pet pada ArrayList masih kosong**
<div align="center">
  <img src="https://github.com/user-attachments/assets/bf990e63-b24d-4c18-82a5-d06b7f12f08e" alt="" width="500px">
</div>

<p align="justify">Akan muncul teks seperti pada gambar di atas jika ArrayList masih kosong.</p>

**2. Tampilan daftar pet jika memiliki data**
<div align="center">
  <img src="https://github.com/user-attachments/assets/dee21aad-ba92-4776-8112-85424dfece3e" alt="" width="500px">
</div>

</details>

<details>
<summary><h3>Memperbarui Data Pet</h3></summary>

**1. Validasi ID dan jika gagal**
<div align="center">
  <img src="https://github.com/user-attachments/assets/64e5fc8c-6b99-430b-80b2-d836fca3e4ca" alt="" width="500px">
</div>

<p align="justify">Jika pengguna memasukkan id yang tidak ada atau tidak cocok dengan yang ada pada ArrayList. Maka, akan muncul teks seperti pada gambar.</p>

**2. Tampilan pembaruan data pet jika berhasil**
<div align="center">
  <img src="https://github.com/user-attachments/assets/e7fa40c6-8ef8-467d-a361-3efef7c821ca" alt="" width="500px">
</div>

<p align="justify">Perlu diketahui, bahwa logika dan proses validasi input seperti batas karakter, rentang umur, dll. pada bagian update ini kurang lebih sama dengan yang ada pada proses penambahan data pet. Bedanya, hanya di cara penyimpanannya di ArrayList menggunakan variabel khusus untuk bagian update. Pada gambar di atas merupakan tampilan jika pembaruan data pet berhasil.</p>

</details>

<details>

<summary><h3>Menghapus Data Pet</h3></summary>

**1. Validasi ID dan jika gagal**
<div align="center">
  <img src="https://github.com/user-attachments/assets/ae6042d6-9e36-43b4-aec3-7a0077e32df5" alt="" width="500px">
</div>

<p align="justify">Sama seperti di bagian update, pengguna diminta memasukkan ID pet, dan jika proses validasi gagal. Maka akan diminta input ulang.</p>

**2. Jika data pet berhasil dihapus**
<div align="center">
  <img src="https://github.com/user-attachments/assets/2bfe34c2-344e-42b7-bf99-91809a0ea644" alt="" width="500px">
</div>

<p align="justify">Jika proses validasi berhasil (ID cocok dengan data dalam ArrayList). Maka, data berhasil dihapus.</p>

</details>

### Versi Post-Test 2

<details>
<summary><h3>Pilihan Pencarian Pada Menu Utama</h3></summary>
  
<div align="center">
  <img src="https://github.com/user-attachments/assets/5c00c6ec-5139-45fd-97c1-7bf9080f5abc" alt="" width="500px">
</div>

<p align="justify">Melanjutkan dari Post Test pertama, disini saya menambahkan fitur pencarian data pet sebagai opsi di menu utama.</p>

</details>

<details>
<summary><h3>Melakukan Pencarian</h3></summary>

**1. Jika keyword yang dimasukkan tidak ditemukan**
<div align="center">
  <img src="https://github.com/user-attachments/assets/79030d13-3b2c-4c88-a48c-23f6902309f9" alt="" width="500px">
</div>

<p align="justify">Untuk mencari data pet yang diinginkan, pengguna diminta memasukkan kata kunci yang berupa ID/nama/jenis/umur/pemilik dari pet. Jika setelah proses pencocokan keyword yang dimasukkan tidak terdapat pada daftar pet yang ada, maka pengguna diberikan teks yang memberitahukan bahwa keyword yang diinput tidak ditemukan.</p>

**2. Jika keyword berhasil ditemukan**
<div align="center">
  <img src="https://github.com/user-attachments/assets/285a1513-951f-4c3c-a991-9624a15a205b" alt="" width="500px">
</div>

<p align="justify">Gambar di atas dapat terlihat memanggil data pet yang ditemukan jika keyword yang dimasukkan pengguna cocok dengan daftar pet yang ada.</p>

</details>

---
> [!NOTE]
> **🎉 Terimakasih! 🎉**
> Vouloir, c’est pouvoir. 🙏

---
[⬆️ Kembali ke Atas](#top)
