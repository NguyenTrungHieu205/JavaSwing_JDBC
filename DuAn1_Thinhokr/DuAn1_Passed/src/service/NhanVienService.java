/*
............/´¯/)........... (\¯'\
............/....//........... ...\\....\
.........../....//............ ....\\....\
...../´¯/..../´¯\.........../¯ '\....\¯'\
.././.../..../..../.|_......_| .\....\....\...\.\..
(.(....(....(..../.)..)..(..(. \....)....)....).)
.\................\/.../....\. ..\/................/
..\................. /........\..................../
....\..............(.......... ..)................/           


 */
package service;

import java.util.ArrayList;
import model.ChucVu;
import model.NhanVien;
import viewModel.ViewModeNhanVien;

public interface NhanVienService {

    ArrayList<ViewModeNhanVien> getList();

    Integer them(NhanVien nv);

    Integer sua(NhanVien nv, String ma);

    ArrayList<ChucVu> getAllcv();

    ArrayList<ViewModeNhanVien> getList1();

    Integer delete(String ma);

    Integer delete1(String ma);

    ArrayList<ViewModeNhanVien> timKiem(String ma);

    ArrayList<ViewModeNhanVien> timKiemNhanVienNghiViec(String ma);
    
    Boolean checkTrungMa(String ma);
    

    
}
