package Database;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

//inheritance
public class Transaksi extends Customer{
    static Connection con;
    public String ps;
    public Integer pilihps;

    String url = "jdbc:mysql://localhost:3306/programpenyewaanps";
        String username = "root";
        String password = "";

        Date date = new Date();
        String tanggal = String.format("%tF", date);
        

        @Override
        public void jenisps() {
           System.out.print("Inputkan jenisps       = ");
           pilihps = input.nextInt();

        //percabangan
           if(pilihps == 1){
               jenisps = "ps 3";
           }

           else if(pilihps == 2){
               jenisps = "ps 4";
           }

           else if(pilihps == 3){
               jenisps = "ps 5";
           }

            
        }

    @Override
    public void hargasewa() {
        //percabangan
        if(pilihps == 1){
            hargasewa = 10000;
        }

        else if(pilihps == 2){
            hargasewa = 15000;
        }

        else if(pilihps == 3){
            hargasewa = 20000;
        }
       
    }

//==============================================================================================================================================================================================================================================================================
//pengolahan database 
public void tambahData() throws SQLException, ClassNotFoundException {
    //exception
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);

        System.out.println("===============================");
        String text = "=====TAMBAH DATA PROGRAM PENYEWAAN PS=====";
        //method string
        System.out.println(text.toUpperCase());
        System.out.println("===============================");

        noPemesanan();
        namaCustomer();
        jenisps();
        hargasewa();
        lamapenyewaan();
        System.out.println("\n=====Ringkasan PROGRAM PENYEWAAN PS=====");
        System.out.println("jenisps                = " + jenisps);
        System.out.println("hargasewa                 = " + hargasewa);
        totalHarga();
        tanggal();
        waktu();

        
        String sql = "INSERT INTO programpenyewaanps (noPemesanan, namaCustomer, jenisps, hargasewa, lamapenyewaan, totalharga) VALUES ('"+noPemesanan+"','"+namaCustomer+"','"+jenisps+"','"+hargasewa+"','"+lamapenyewaan+"', '"+totalHarga+"')";

        Statement statement = con.createStatement();
        statement.execute(sql);
        System.out.println("Data Berhasil Diinputkan!");
    }
    
    catch (SQLException e){
        System.err.println("\nTerjadi kesalahan input data");
    }
    catch (InputMismatchException e) {
        System.err.println("\nInputlah dengan angka saja");
       }
}
//==============================================================================================================================================================================================================================================================================
//pengolahan database 
public void lihatdata() throws SQLException, ClassNotFoundException{
    
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);

        System.out.println("====================================");
        String text = "=====MENAMPILKAN DATA PEMESANAN=====";
        System.out.println(text.toUpperCase());
        System.out.println("====================================");

        String sql = "SELECT * FROM programpenyewaanps";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

            System.out.println("\n-----------------------------------------------------------------------------------------------------");
            String format1 = "|%-2s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n";
            System.out.printf(format1,"No", "noPemesanan","namaCustomer","jenisps","hargasewa","lamapenyewaan","totalharga","Tanggal");
            System.out.println("\n-----------------------------------------------------------------------------------------------------");

        int i=1;
        while (result.next()){
            String format2 = "|%-2s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n";
            System.out.printf(format2, i, result.getInt("noPemesanan"),result.getString("namaCustomer"),result.getString("jenisps"),result.getInt("hargasewa"),result.getInt("lamapenyewaan"),result.getInt("totalharga"));
            System.out.println("\n-----------------------------------------------------------------------------------------------------");

            i++;
        }
}
//==============================================================================================================================================================================================================================================================================
//pengolahan database 
public void ubahdata() throws SQLException, ClassNotFoundException{
    System.out.println("=============================");
    String text = "=====UBAH DATA PEMBELIAN=====";
    System.out.println(text.toUpperCase());
    System.out.println("=============================");
    
    try (Scanner inputan = new Scanner (System.in)) {
        //exception
        try{
            lihatdata();
        
            System.out.print("Masukkan noPemesanan  yang Akan diubah : ");
            noPemesanan = Integer.parseInt(inputan.nextLine());

            String sql = "SELECT * FROM programpenyewaanps WHERE noPemesanan = " +noPemesanan;

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if(result.next()){
                System.out.print("Nama Pelanggan ["+result.getString("namaCustomer")+"]\t: ");
                namaCustomer = inputan.next();

                System.out.println("\n");

                sql = "UPDATE programpenyewaanps SET namaCustomer='"+namaCustomer+"' WHERE noPemesanan='"+noPemesanan+"' ";
        
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Data Berhasil diperbarui!");
                }
            }
            statement.close();
        }

        catch (SQLException e){
            System.err.println("Terjadi kesalahan Ubah data");
            System.err.println(e.getMessage());
            
        }
    } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
     
}
//=======================================================================================================================================================================================================================
//pengolahan database 
public void hapusdata() throws SQLException, ClassNotFoundException {
    System.out.println("==============================");
    String text = "=====HAPUS DATA PEMBELIAN=====";
    System.out.println(text.toUpperCase());
    System.out.println("==============================");
    
    try (Scanner inputan = new Scanner (System.in)) {
        lihatdata();
        
        //exception
        try{
            
            System.out.print("Ketik nomor pemesanan yang akan dihapus : ");
            noPemesanan = Integer.parseInt(inputan.nextLine());
            
            String sql = "DELETE FROM programpenyewaanps WHERE noPemesanan = "+ noPemesanan;
            Statement statement = con.createStatement();
            
            if(statement.executeUpdate(sql) > 0){
                System.out.println("Berhasil menghapus data dengan nomor pemesanan ("+noPemesanan+")");
            }
        }
        catch(SQLException e){
             System.out.println("Terjadi kesalahan dalam menghapus data barang");
             System.err.println(e.getMessage());
             }
    } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
}
//==========================================================================================================================================================================================================================================================
//pengolahan database 
public void caridata() throws SQLException, ClassNotFoundException {
    System.out.println("=============================");
    String text = "=====Cari Data PEMBELIAN=====";
    System.out.println(text.toUpperCase());
    System.out.println("=============================");

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url, username, password);

    
    try (Scanner inputan = new Scanner (System.in)) {
        System.out.print("Masukkan Nomor Pemesanan : ");
        
        String keyword = inputan.nextLine();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM programpenyewaanps WHERE noPemesanan LIKE '%"+keyword+"%'";
        ResultSet result = statement.executeQuery(sql);
        
        tanggal();

        //exception
        try{
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
        String format1 = "|%-2s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n";
        System.out.printf(format1,"No", "noPemesanan","namaCustomer","jenisps","hargasewa","lamapenyewaan","totalharga","Tanggal");
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
         
        int i=1;
        while(result.next()){
            String format2 = "|%-2s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n";
            System.out.printf(format2, i, result.getInt("noPemesanan"),result.getString("namaCustomer"),result.getString("jenisps"),result.getInt("hargasewa"),result.getInt("lamapenyewaan"),result.getInt("totalharga"));
            System.out.println("\n-----------------------------------------------------------------------------------------------------");

            i++;
        }
        System.out.println("Berhasil mencari data pemesanan"); 
            
        }
        catch(SQLException e){
             System.out.println("Terjadi kesalahan dalam mencari data pemesanan");
             System.err.println(e.getMessage());
             }
    }
    
}

//===========================================================================================================================================================================================================================================================================================================================================================================================
//pengolahan database 
public void resetdata() throws SQLException, ClassNotFoundException {
    System.out.println("==============================");
    String text = "=====RESET DATA PEMBELIAN=====";
    System.out.println(text.toUpperCase());
    System.out.println("==============================");
    
    lihatdata();
    
    //exception
    try{
        
        String sql = "DELETE FROM programpenyewaanps";
        Statement statement = con.createStatement();
        
        if(statement.executeUpdate(sql) > 0){
            System.out.println("Berhasil mereset data pemesanan");
        }
    }
    catch(SQLException e){
         System.out.println("Terjadi kesalahan dalam mereset data pemesanan");
         System.err.println(e.getMessage());
         }
    
}
}
