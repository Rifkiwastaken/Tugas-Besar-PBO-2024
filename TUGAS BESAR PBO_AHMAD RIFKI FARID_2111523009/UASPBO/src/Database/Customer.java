package Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//implementasi interface
public class Customer implements Pemesanan{
    public Integer noPemesanan;
    public String namaCustomer;
    public String jenisps;
    public Integer hargasewa;
    public Integer lamapenyewaan;
    public Integer totalHarga;
    
    Scanner input = new Scanner(System.in);

    //constructor
    public Customer(){
        noPemesanan     = 001;
        namaCustomer    = "talbott";
        jenisps      = "ps 3";
        hargasewa      = 10000;
        lamapenyewaan    = 2;
    }
    
    //method date
    public void tanggal()
    {
        Date date = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("EEEE, dd MMM yyyy");
        System.out.println("Tanggal Transaksi           = " + tanggal.format(date));
    }

    public void waktu()
    {
        Date time = new Date();    
        SimpleDateFormat waktu = new SimpleDateFormat("HH:mm:ss");  
        System.out.println("Waktu Transaksi             = " + waktu.format(time));
    }

    @Override
    public void noPemesanan() {
        System.out.print("Inputkan No Pemesanan         = ");
        noPemesanan = input.nextInt();
    }

    @Override
    public void namaCustomer() {
        System.out.print("Inputkan Nama Customer        = ");
        namaCustomer = input.next();
    }

    @Override
    public void jenisps() {
       System.out.print("Inputkan Jenis ps              = ");
       jenisps = input.next();
    }

    @Override
    public void hargasewa() {
        System.out.print("Inputkan Harga penyewaan          = ");
        hargasewa = input.nextInt();
        
    }

    @Override
    public void lamapenyewaan() {
        System.out.print("Inputkan Lama  penyewaan       = ");
        lamapenyewaan = input.nextInt();
    }

    @Override
    public void totalHarga() {
        //proses matematika
        totalHarga = hargasewa * lamapenyewaan;
        System.out.println("Total Harga                 = " + totalHarga);
        
    }
    
}
