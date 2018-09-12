/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import kiraju.model.JenisMenu;
import kiraju.property.JenisMenuProperty;
import kiraju.util.Choice;

/**
 *
 * @author arvita
 */
public interface IJenisMenu {
    List<JenisMenu> getAll();
    ObservableList<JenisMenuProperty> getAllProperty();
    boolean insert(JenisMenu jenisMenu, Stage stage);
    boolean update(JenisMenu jenisMenu, Stage stage);
    void delete(short id);
    List<JenisMenu> getAllActive();
    ObservableList<Choice> getAllActiveChoice();
    JenisMenu getByNama(String nama);
}
