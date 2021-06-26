/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraju.interfaces;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import kiraju.model.Laporan;
import kiraju.model.TransaksiPembelian;
import kiraju.property.TransaksiPembelianProperty;

/**
 *
 * @author arvita
 */
public interface ITransaksiPembelian {
    ObservableList<TransaksiPembelianProperty> getAllProp(int bulan);
    int insertOrUpdate(TransaksiPembelian pembelian);
    void remove(TransaksiPembelian pembelian);
    List<Laporan> getLaporanPembelian(LocalDate tglDari, LocalDate tglSampai);
    List getChartByBulan(int bulan);
    List getChartByTahun(int tahun);
    ObservableList<TransaksiPembelianProperty> getAllPropByTgl(LocalDate localDate);
}
