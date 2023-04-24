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

import java.util.ArrayList;
import java.util.List;
import model.ChucVu;
import repository.ChucVuRepository;
import service.ChucVuService;
import viewModel.ViewModeChucVu;

/**
 *
 * @author Admin
 */
public class ChucVuServiceImp implements ChucVuService{
private ChucVuRepository chucVuRepo=new ChucVuRepository();
    @Override
    public ArrayList<ViewModeChucVu> getAll() {
        try {
            return  (ArrayList<ViewModeChucVu>) chucVuRepo.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer them(ChucVu cv) {
        try {
            return chucVuRepo.them(cv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer sua(ChucVu cv, String ma) {
        try {
          return chucVuRepo.sua(cv, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer delete( String ma) {
     try {
          return chucVuRepo.delete(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ViewModeChucVu> getAll1() {
         try {
            return  (ArrayList<ViewModeChucVu>) chucVuRepo.getAll1();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer delete1(String ma) {
      try {
          return chucVuRepo.delete1( ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        List<ViewModeChucVu> list = chucVuRepo.getAll();
        for (ViewModeChucVu x : list) {
            if (x.getMaCv().equals(ma)) {
                return false;
            }
        }
        return true;
    }
    
}
