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
import viewModel.ViewModeChucVu;

/**
 *
 * @author Admin
 */
public interface ChucVuService {

    ArrayList<ViewModeChucVu> getAll();

    ArrayList<ViewModeChucVu> getAll1();

    Integer them(ChucVu cv);

    Integer sua(ChucVu cv, String ma);

    Integer delete(String ma);

    Integer delete1(String ma);
    
    Boolean checkTrungMa(String ma);
}
