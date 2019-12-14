/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.property;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author arvita
 */
public class DaftarPembelianProperty {
    
    private final NumberFormat numberFormat = NumberFormat.getInstance(new Locale("id", "ID"));
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty produk = new SimpleStringProperty();
    private final StringProperty harga = new SimpleStringProperty();
    private final StringProperty jumlah = new SimpleStringProperty();
    private final StringProperty menuItemCode = new SimpleStringProperty();
    private final StringProperty hargaFormatted = new SimpleStringProperty();
    private final StringProperty satuan = new SimpleStringProperty();
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public IntegerProperty idProperty() {
        return id;
    }

    public String getProduk() {
        return produk.get();
    }

    public void setProduk(String produk) {
        this.produk.set(produk);
    }
    
    public StringProperty produkProperty() {
        return produk;
    }

    public String getHarga() {
        return harga.get();
    }

    public void setHarga(String harga) {
        this.harga.set(harga);
    }
    
    public StringProperty hargaProperty() {
        return harga;
    }

    public String getJumlah() {
        return jumlah.get();
    }

    public void setJumlah(String jumlah) {
        this.jumlah.set(jumlah);
    }
    
    public StringProperty jumlahProperty() {
        return jumlah;
    }

    public String getHargaFormatted() {
        return hargaFormatted.get();
    }

    public void setHargaFormatted(int hargaFormatted) {
        this.hargaFormatted.set(numberFormat.format(hargaFormatted));
    }
    
    public StringProperty hargaFormattedProperty() {
        return hargaFormatted;
    }

    public String getMenuItemCode() {
        return menuItemCode.get();
    }

    public void setMenuItemCode(String menuItemCode) {
        this.menuItemCode.set(menuItemCode);
    }
    
    public StringProperty menuItemCodeProperty() {
        return menuItemCode;
    }

    public String getSatuan() {
        return satuan.get();
    }

    public void setSatuan(String satuan) {
        this.satuan.set(satuan);
    }
    
    public StringProperty satuanProperty() {
        return satuan;
    }
}
