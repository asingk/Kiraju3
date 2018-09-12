/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author arvita
 */
public class SatuanProperty {
    private final StringProperty code = new SimpleStringProperty();
    private final StringProperty nama = new SimpleStringProperty();

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }
    
    public StringProperty codeProperty() {
        return code;
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }
    
    public StringProperty namaProperty() {
        return nama;
    }
    
}
