/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.interfaces;

import javafx.collections.ObservableList;
import kiraju.model.Diskon;
import kiraju.property.DiskonPajakProperty;
import kiraju.util.Choice;

/**
 *
 * @author arvita
 */
public interface IDiskon {
    ObservableList<DiskonPajakProperty> getAllProperty();
    void saveOrupdate(Diskon diskon);
    ObservableList<Choice> getAllActive();
    Diskon getById(int id);
}
