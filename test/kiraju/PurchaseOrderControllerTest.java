/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import kiraju.property.TransaksiPembelianProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arvita
 */
public class PurchaseOrderControllerTest {
    
    public PurchaseOrderControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class PurchaseOrderController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        PurchaseOrderController instance = new PurchaseOrderController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDialogStage method, of class PurchaseOrderController.
     */
    @Test
    public void testSetDialogStage() {
        System.out.println("setDialogStage");
        Stage dialogStage = null;
        PurchaseOrderController instance = new PurchaseOrderController();
        instance.setDialogStage(dialogStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOkClicked method, of class PurchaseOrderController.
     */
    @Test
    public void testIsOkClicked() {
        System.out.println("isOkClicked");
        PurchaseOrderController instance = new PurchaseOrderController();
        boolean expResult = false;
        boolean result = instance.isOkClicked();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniValue method, of class PurchaseOrderController.
     */
    @Test
    public void testIniValue() {
        System.out.println("iniValue");
        TransaksiPembelianProperty pembelianProperty = null;
        PurchaseOrderController instance = new PurchaseOrderController();
        instance.iniValue(pembelianProperty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
