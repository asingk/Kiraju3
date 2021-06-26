/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kiraju.implement.DaftarPembelianModel;
import kiraju.implement.MenuItemModel;
import kiraju.implement.PemasokModel;
import kiraju.implement.TransaksiPembelianModel;
import kiraju.interfaces.IDaftarPembelian;
import kiraju.interfaces.IMenuItem;
import kiraju.interfaces.IPemasok;
import kiraju.interfaces.ITransaksiPembelian;
import kiraju.model.MenuItem;
import kiraju.model.TransaksiPembelian;
import kiraju.property.DaftarPembelianProperty;
import kiraju.property.TransaksiPembelianProperty;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author arvita
 */
public class PurchaseOrderController implements Initializable {
    
    private final NumberFormat numberFormat = NumberFormat.getInstance(new Locale("id", "ID"));
//    private final static Logger LOGGER = Logger.getLogger(PurchaseOrderController.class);
    
//    @FXML
//    private ComboBox<String> pemasokComboBox;
//    @FXML
//    private ChoiceBox<String> satuanCB;
    @FXML
    private TextField pemasokTF;
    @FXML
    private TextField produkTF;
    @FXML
    private TextField jumlahTF;
    @FXML
    private TextField hargaTF;
    @FXML
    private Button tambahBtn;
    @FXML
    private Button hapusBtn;
//    @FXML
//    private Button clearBtn;
//    @FXML
//    private Button batalBtn;
    @FXML
    private Button okBtn;
    @FXML
    private TableView<DaftarPembelianProperty> daftarBeliTbl;
    @FXML
    private TableColumn<DaftarPembelianProperty, DaftarPembelianProperty> noClm;
    @FXML
    private TableColumn<DaftarPembelianProperty, String> produkClm;
    @FXML
    private TableColumn<DaftarPembelianProperty, String> hargaClm;
    @FXML
    private TableColumn<DaftarPembelianProperty, String> jumlahClm;
//    @FXML
//    private TableColumn<DaftarPembelianProperty, String> satuanClm;
    @FXML
    private Text totalHarga;
//    @FXML
//    private ComboBox<String> produkCombo;
    @FXML
    private CheckBox diterima;
    
    private Stage dialogStage;
    private boolean okClicked;
    
    private final IPemasok iPemasok = new PemasokModel();
    private final ITransaksiPembelian iTransaksiPembelian = new TransaksiPembelianModel();
    private final IDaftarPembelian iDaftarPembelian = new DaftarPembelianModel();
    private final IMenuItem iMenuItem = new MenuItemModel();
//    private final ISatuan iSatuan = new SatuanModel();
    
    private ObservableList<DaftarPembelianProperty> data = FXCollections.observableArrayList();
    private TransaksiPembelianProperty pembelianProperty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(pemasokTF, iPemasok.getActivePemasokName());
        TextFields.bindAutoCompletion(produkTF, iMenuItem.getActiveMenuName());
        
        hargaTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                hargaTF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        jumlahTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                jumlahTF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        tambahBtn.disableProperty().bind(Bindings.isEmpty(produkTF.textProperty()).or(Bindings.isEmpty(hargaTF.textProperty())).or(Bindings.isEmpty(jumlahTF.textProperty())));
        
        daftarBeliTbl.setEditable(true);
        noClm.setCellValueFactory((TableColumn.CellDataFeatures<DaftarPembelianProperty, DaftarPembelianProperty> p) -> new ReadOnlyObjectWrapper(p.getValue()));
        noClm.setCellFactory((TableColumn<DaftarPembelianProperty, DaftarPembelianProperty> param) -> new TableCell<DaftarPembelianProperty, DaftarPembelianProperty>() {
            @Override protected void updateItem(DaftarPembelianProperty item, boolean empty) {
                super.updateItem(item, empty);
                if (this.getTableRow() != null && item != null) {
                    setText(this.getTableRow().getIndex()+1+"");
                } else {
                    setText("");
                }
            }
        });
        noClm.setSortable(false);
        produkClm.setCellValueFactory(cellData -> cellData.getValue().produkProperty());
        produkClm.setCellFactory(TextFieldTableCell.forTableColumn());
        produkClm.setOnEditCommit((t) -> {
            ((DaftarPembelianProperty) t.getTableView().getItems().get(t.getTablePosition().getRow())).setProduk(t.getNewValue());
        });
        hargaClm.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
        hargaClm.setCellFactory(TextFieldTableCell.<DaftarPembelianProperty>forTableColumn());
        hargaClm.setOnEditCommit((event) -> {
            ((DaftarPembelianProperty) event.getTableView().getItems().get(event.getTablePosition().getRow())).setHarga(event.getNewValue());
            hitungTotal();
        });
        jumlahClm.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
        jumlahClm.setCellFactory(TextFieldTableCell.<DaftarPembelianProperty>forTableColumn());
        jumlahClm.setOnEditCommit((event) -> {
            ((DaftarPembelianProperty) event.getTableView().getItems().get(event.getTablePosition().getRow())).setJumlah(event.getNewValue());
            hitungTotal();
        });
//        satuanClm.setCellValueFactory(cellData -> cellData.getValue().satuanProperty());
        daftarBeliTbl.setItems(data);
        daftarBeliTbl.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectTable(newValue)));
    }    
    
    private void selectTable(DaftarPembelianProperty property) {
        if(null != property) {
//            produkCombo.setValue(property.getProduk());
//            hargaTF.setText(property.getHarga());
//            jumlahTF.setText(property.getJumlah());
            hapusBtn.setDisable(false);
//            clearBtn.setDisable(false);
//            satuanCB.setValue(property.getSatuan());
        }else{
            produkTF.setText("");
            hargaTF.setText("");
            jumlahTF.setText("");
            hapusBtn.setDisable(true);
//            clearBtn.setDisable(true);
//            satuanCB.getSelectionModel().selectFirst();
        }
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void simpanAction(ActionEvent event) {
        MenuItem item = iMenuItem.getByNama(produkTF.getText());
        if(null != item.getCode()) {
            DaftarPembelianProperty property = new DaftarPembelianProperty();
            property.setProduk(produkTF.getText());
            property.setHarga(hargaTF.getText());
            property.setJumlah(jumlahTF.getText());
            property.setMenuItemCode(item.getCode());
            boolean isExisted = false;
            for(int i = 0; i < data.size(); i++) {
                isExisted = data.get(i).getMenuItemCode().equalsIgnoreCase(item.getCode());
            }
            if(!isExisted){
                data.add(property);
                okBtn.setDisable(false);
                clearAction();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Salah!");
                alert.setHeaderText("Produk telah dipilih");
                alert.setContentText("Silahkan pilih produk lain");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Salah!");
            alert.setHeaderText("Produk tidak tersedia");
            alert.setContentText("Silahkan pilih produk yang tersedia");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void hapusAction(ActionEvent event) {
        DaftarPembelianProperty property = daftarBeliTbl.getSelectionModel().getSelectedItem();
        data.remove(property);
        if(data.isEmpty()) {
            okBtn.setDisable(true);
        }
        clearAction();
    }
    
    @FXML
    private void batalAction(ActionEvent event) {
        dialogStage.close();
    }
    
    @FXML
    private void okeAction(ActionEvent event) {
        okClicked = true;
        TransaksiPembelian pembelian = new TransaksiPembelian();
        if(null != pembelianProperty) {
            pembelian.setId(pembelianProperty.getId());
        }
        pembelian.setTanggal(new Date());
        if(null != pemasokTF.getText() && !pemasokTF.getText().isEmpty()){
            pembelian.setPemasokId(iPemasok.getPemasokIdByNama(pemasokTF.getText()));
        }
        pembelian.setTotal(Integer.valueOf(totalHarga.getText().replace(".", "")));
//        pembelian.setMtdByrId(new MetodePembayaran(CommonConstant.METODE_BAYAR_TUNAI));
//        pembelian.setIsLunas((CommonConstant.METODE_BAYAR_TUNAI == 1));
        pembelian.setStatus(diterima.isSelected());
        int id = iTransaksiPembelian.insertOrUpdate(pembelian);
        if(null != pembelianProperty) {
            iDaftarPembelian.deleteByTrxPembelianId(pembelian);
        }
        iDaftarPembelian.insertList(data, id);
        dialogStage.close();
        
    }
    
    private void hitungTotal() {
        int total = 0;
        for(int i=0; i<data.size(); i++) {
            int harga = Integer.parseInt(data.get(i).getHarga());
            int jumlah = Integer.parseInt(data.get(i).getJumlah());
            total += harga*jumlah;
        }
        totalHarga.setText(numberFormat.format(total));
    }

    void iniValue(TransaksiPembelianProperty pembelianProperty) {
        if(null != pembelianProperty) {
            this.pembelianProperty = pembelianProperty;
            if(null != pembelianProperty.getPemasokNama()) {
                pemasokTF.setText(pembelianProperty.getPemasokNama());
            }
            data.addAll(iDaftarPembelian.getListBeli(new TransaksiPembelian(pembelianProperty.getId())));
            hitungTotal();
            okBtn.setDisable(false);
        }
    }
    
//    @FXML
    private void clearAction() {
        daftarBeliTbl.getSelectionModel().clearSelection();
        selectTable(null);
        hitungTotal();
    }
    
}
