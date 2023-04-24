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
package service.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ChucVu;
import model.NhanVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.NhanVienRepository;
import service.NhanVienService;
import viewModel.ViewModeNhanVien;

public class NhanVienServiceImp implements NhanVienService {

    private NhanVienRepository nhanVienRepo = new NhanVienRepository();

    @Override
    public ArrayList<ViewModeNhanVien> getList() {
        try {
            return nhanVienRepo.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer them(NhanVien nv) {
        try {
            return nhanVienRepo.them(nv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer sua(NhanVien nv, String ma) {
        try {
            return nhanVienRepo.sua(nv, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ChucVu> getAllcv() {
        try {
            return (ArrayList<ChucVu>) nhanVienRepo.getAllcv();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ViewModeNhanVien> getList1() {
        try {
            return nhanVienRepo.getAll1();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer delete(String ma) {
        try {
            return nhanVienRepo.delete(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer delete1(String ma) {
        try {
            return nhanVienRepo.delete1(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ViewModeNhanVien> timKiem(String ma) {
        try {
            return nhanVienRepo.timKiem(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ViewModeNhanVien> timKiemNhanVienNghiViec(String ma) {
        try {
            return nhanVienRepo.timKiemNhanVienNghiViec(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<ViewModeNhanVien> listNhanVien = nhanVienRepo.tatCaNhanVien();
        for (ViewModeNhanVien x : listNhanVien) {
            if (x.getMaNv().equals(ma)) {
                return false;
            }
        }
        return true;
    }

}
