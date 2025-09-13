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