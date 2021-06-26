/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kiraju.implement.JenisMenuModel;
import kiraju.implement.MenuItemModel;
import kiraju.implement.SatuanModel;
import kiraju.interfaces.IJenisMenu;
import kiraju.interfaces.IMenuItem;
import kiraju.interfaces.ISatuan;
import kiraju.model.JenisMenu;
import kiraju.model.MenuItem;
import kiraju.model.Satuan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author arvita
 */
public class ImporProdukController implements Initializable {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ImporProdukController.class);
    
    private Stage dialogStage;
    private File selectedFile;
    private boolean isOkClicked;
    
    private final IMenuItem iMenuItem = new MenuItemModel();
    private final IJenisMenu iJenisMenu = new JenisMenuModel();
    private final ISatuan iSatuan = new SatuanModel();
    
    @FXML
    private Text file;
    @FXML
    private Button imporBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    private void pilihAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
        selectedFile = fileChooser.showOpenDialog(dialogStage);
        if(selectedFile != null){
            file.setText(selectedFile.getName());
            imporBtn.setDisable(false);
        }
    }
    
    @FXML
    private void imporAction() {
        try {
            long start = System.nanoTime();
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                reader.readLine();
                int suksesCount = 0;
                List<String> error = new ArrayList();
                while ((line = reader.readLine()) != null) {
                    String[] menu = line.split(",");
                    MenuItem menuItem = new MenuItem();
                    if(menu.length > 0){
                        menuItem.setCode(menu[0].toUpperCase());
                    }
                    if(menu.length > 1){
                        menuItem.setNama(menu[1]);
                    }
                    if(menu.length > 2 && null != menu[2] && !menu[2].isEmpty()){
                        menuItem.setHargaTotal(Integer.parseInt(menu[2]));
                    }
//                    if("y".equalsIgnoreCase(menu[4])){
//                        menuItem.setStokFlag(true);
//                        String stok = null != menu[5] ? menu[5] : "0";
//                        menuItem.setStok(Integer.parseInt(stok));
//                    }else{
//                        menuItem.setStokFlag(false);
//                    }
                    menuItem.setStokFlag(false);
                    menuItem.setStok(0);
                    if(menu.length > 3){
                        JenisMenu jenisMenu = iJenisMenu.getByNama(menu[3]);
                        menuItem.setJenisMenuId(jenisMenu);
                    }
                    menuItem.setStatus(Boolean.TRUE);
                    if(menu.length > 6 && "y".equalsIgnoreCase(menu[6])) {
                        menuItem.setIsJual(true);
                    }else{
                        menuItem.setIsJual(false);
                    }
                    List<String> satuanList = iSatuan.getCodeList();
                    if(menu.length > 7 && satuanList.contains(menu[7])){
                        menuItem.setSatuan(new Satuan(menu[7]));
                    }
                    if(null != menuItem.getNama()){
                        if(iMenuItem.impor(menuItem)){
                            suksesCount++;
                        }else{
                            error.add(menuItem.getNama());
                        }
                    }
                    
                }
                String report = suksesCount + " produk berhasil ditambahkan\n";
                if(!error.isEmpty()){
                    String errorReport = error.size() + " produk gagal ditambahkan:\n";
                    for (int i=0; i<error.size(); i++) {
                        errorReport += "-" + error.get(i) + "\n";
                    }
                    report += errorReport;
                }
                System.out.println("lama: " + (System.nanoTime() - start));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Proses import telah selesai");
                alert.setContentText(report);
                alert.showAndWait();
                
                isOkClicked = true;
                dialogStage.close();
            }
        } catch (IOException ex) {
            LOGGER.error("error when reading CSV file", ex);
        }
    }
    
    @FXML
    private void batalAction() {
        dialogStage.close();
    }
    
    @FXML
    private void downloadImportFile() throws IOException  {
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setTitle("Pilih Direktori/Folder");
//        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//        File dirTarget = directoryChooser.showDialog(dialogStage);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("impor_produk.csv");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV files", "*.csv");
        fileChooser.getExtensionFilters().add(filter);
        File importedFile = fileChooser.showSaveDialog(dialogStage);
        if(importedFile != null){
            OutputStream outputStream;
            try (InputStream inputStream = this.getClass().getResourceAsStream("doc/impor_produk.csv")) {
                if(!importedFile.getName().endsWith(".csv")) {
                    importedFile = new File(importedFile + ".csv");
                }
                outputStream = new FileOutputStream(importedFile);
                byte[] buffer = new byte[1024];
                int length;
                while((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }
            outputStream.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText("File impor produk telah berhasil didownload");
//            alert.setContentText(report);

            alert.showAndWait();
        }
    }
    
    //JUnit
    public void testMethod() throws URISyntaxException, IOException {
        downloadImportFile();
    }

    boolean isOkClicked() {
        return isOkClicked;
    }
    
}
