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
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModeChucVu {
    private String id;
    private String maCv;
    private String tenCv;

    public ViewModeChucVu() {
    }

    public ViewModeChucVu(String id, String maCv, String tenCv) {
        this.id = id;
        this.maCv = maCv;
        this.tenCv = tenCv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaCv() {
        return maCv;
    }

    public void setMaCv(String maCv) {
        this.maCv = maCv;
    }

    public String getTenCv() {
        return tenCv;
    }

    public void setTenCv(String tenCv) {
        this.tenCv = tenCv;
    }

   
    @Override
    public String toString() {
        return  tenCv ;
    }
    
}
