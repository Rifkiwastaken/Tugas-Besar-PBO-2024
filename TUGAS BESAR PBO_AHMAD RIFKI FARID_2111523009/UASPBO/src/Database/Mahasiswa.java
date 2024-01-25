package Database;

public class Mahasiswa {
    public String keterangan;
    public String namaMahasiswa;
    public String nimMahasiswa;
    public String jurusanMahasiswa;
    public String fakultasMahasiswa;

    //constructor
    public Mahasiswa(){
        keterangan = "TUGAS BESAR PEMOGRAMAN BERORIENTASI OBJEK";
        namaMahasiswa = "Ahmad Rifki Farid";
        nimMahasiswa = "2111523009";
        jurusanMahasiswa = "Sistem Informasi";
        fakultasMahasiswa = "Fakultas Teknologi Informasi";
    }

    public void display(){
        System.out.println(keterangan);
        System.out.println("Nama Mahasiswa      = " +namaMahasiswa);
        System.out.println("NIM Mahasiswa       = " +nimMahasiswa);
        System.out.println("Jurusan Mahasiswa   = " +jurusanMahasiswa);
        System.out.println("Fakultas Mahasiswa  = " +fakultasMahasiswa);
    }
}
