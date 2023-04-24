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
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.ChucVu;
import model.NhanVien;
import repository.NhanVienRepository;
import service.ChucVuService;
import service.NhanVienService;
import service.impl.ChucVuServiceImp;
import service.impl.NhanVienServiceImp;
import static view.QuetMaNhanVien.diaChi;
import static view.QuetMaNhanVien.gioiTinh;
import viewModel.ViewModeChucVu;
import viewModel.ViewModeNhanVien;

/**
 *
 * @author Admin
 */
public class FormNhanVien extends javax.swing.JPanel implements Runnable, ThreadFactory {

    LocalDate localDate = LocalDate.now();

    /**
     * Creates new form PanelNhanVien
     */
    public FormNhanVien() {
        initComponents();
        load(nhanVienSe.getList());
        load1(nhanVienSe.getList1());
        comBoBox();
        QrQuet.setSize(500, 500);
        txtNgaySinh.getSettings().setAllowKeyboardEditing(false);

    }

    private NhanVienService nhanVienSe = new NhanVienServiceImp();
    private ChucVuService chucVuSe = new ChucVuServiceImp();
    ArrayList<ViewModeNhanVien> listNhanVien = nhanVienSe.getList();
    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serial = 6441489157408381878L;
    private Executor ex = Executors.newSingleThreadExecutor(this);
    public static String ma = null;

    public void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel3.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 230));

        ex.execute(this);
    }

    public void catChuoi() {

    }

    private void comBoBox() {
        ArrayList< ChucVu> listChucVu = nhanVienSe.getAllcv();
        cbxChucVu.removeAll();
        for (ChucVu x : listChucVu) {
            cbxChucVu.addItem(x);
        }
    }

    private void load(ArrayList<ViewModeNhanVien> listNhanVien) {

        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setColumnCount(0);
        model.addColumn("MÃ NHÂN VIÊN");
        model.addColumn("TÊN");
        model.addColumn("TÊN ĐÊM");
        model.addColumn("HỌ");
        model.addColumn("NGÀY SINH");
        model.addColumn("GIỚI TÍNH");
        model.addColumn("CHỨC VỤ");
        model.addColumn("ĐỊA CHỈ");
        model.addColumn("SỐ ĐIỆN THOẠI");
        model.addColumn("EMAIL");
        model.addColumn("GHI CHÚ");

        model.setRowCount(0);
        for (ViewModeNhanVien x : listNhanVien) {
            model.addRow(new Object[]{x.getMaNv(), x.getTenNv(), x.getTenDem(), x.getHo(), x.getNgaySinh(), x.getGioiTinh() == 1 ? "Nam" : "Nữ",
                x.getChucVu(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getGhiChu()});
        }
    }

    private void load1(ArrayList<ViewModeNhanVien> listNhanVien) {

        DefaultTableModel model = (DefaultTableModel) tblNhanVien1.getModel();
        model.setColumnCount(0);
        model.addColumn("MÃ NHÂN VIÊN");
        model.addColumn("TÊN");
        model.addColumn("TÊN ĐÊM");
        model.addColumn("HỌ");
        model.addColumn("NGÀY SINH");
        model.addColumn("GIỚI TÍNH");
        model.addColumn("CHỨC VỤ");
        model.addColumn("ĐỊA CHỈ");
        model.addColumn("SỐ ĐIỆN THOẠI");
        model.addColumn("EMAIL");
        model.addColumn("GHI CHÚ");
        model.setRowCount(0);
        for (ViewModeNhanVien x : listNhanVien) {
            model.addRow(new Object[]{x.getMaNv(), x.getTenNv(), x.getTenDem(), x.getHo(), x.getNgaySinh(), x.getGioiTinh() == 1 ? "Nam" : "Nữ",
                x.getChucVu(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getGhiChu()});
        }

    }

    private NhanVien them() {
        NhanVien nv = new NhanVien();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        nv.setMaNv(txtMaNhanVien.getText().trim());
        nv.setTenNv(txtTen.getText().trim());
        nv.setTenDem(txtTenDem.getText().trim());
        nv.setHo(txtHo.getText().trim());
        nv.setNgaySinh(txtNgaySinh.getText());
        int gioiTinh = rdNam.isSelected() ? 1 : 0;
        nv.setGioiTinh(gioiTinh);
        nv.setEmail(txtEmail.getText().trim());
        ChucVu cv = (ChucVu) cbxChucVu.getModel().getSelectedItem();
        nv.setChucvu(cv);
        nv.setDiaChi(txtDiaChi.getText().trim());
        nv.setSdt(txtSoDienThoai.getText().trim());
        nv.setMatKhau(txtMatKhau.getText().trim());
        nv.setGhiChu(txtGhiChu.getText().trim());
        return nv;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Menu = new javax.swing.JPopupMenu();
        Xoa = new javax.swing.JMenuItem();
        Sua = new javax.swing.JMenuItem();
        QrQuet = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        thoat = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelKH = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenDem = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        txtMaNhanVien = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbxChucVu = new javax.swing.JComboBox<ChucVu>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtNgaySinh = new com.github.lgooddatepicker.components.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        addChucVu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnQuet = new javax.swing.JButton();
        btnRefesh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien1 = new javax.swing.JTable();
        btnXoa1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemNhanVienNghiViec = new javax.swing.JTextField();

        Xoa.setText("Xoa");
        Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaMouseClicked(evt);
            }
        });
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });
        Menu.add(Xoa);

        Sua.setText("Sua");
        Menu.add(Sua);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        thoat.setText("thoat");
        thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(thoat)
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(thoat)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout QrQuetLayout = new javax.swing.GroupLayout(QrQuet.getContentPane());
        QrQuet.getContentPane().setLayout(QrQuetLayout);
        QrQuetLayout.setHorizontalGroup(
            QrQuetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        QrQuetLayout.setVerticalGroup(
            QrQuetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1200, 800));

        PanelKH.setBackground(new java.awt.Color(158, 195, 192));
        PanelKH.setForeground(new java.awt.Color(255, 255, 255));
        PanelKH.setPreferredSize(new java.awt.Dimension(1300, 800));

        jLabel4.setText("Mã Nhân Viên");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Tên");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Tên Đệm");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Họ");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Ngày Sinh");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Giới Tính");
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Địa Chỉ");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("SDT");
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        rdNam.setBackground(new java.awt.Color(36, 47, 65));
        buttonGroup1.add(rdNam);
        rdNam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNam.setForeground(new java.awt.Color(255, 255, 255));
        rdNam.setText("Nam");

        rdNu.setBackground(new java.awt.Color(36, 47, 65));
        buttonGroup1.add(rdNu);
        rdNu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNu.setForeground(new java.awt.Color(255, 255, 255));
        rdNu.setText("Nữ");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add (2).png"))); // NOI18N
        btnThem.setBackground(new java.awt.Color(153, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delele.png"))); // NOI18N
        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnsua.setBackground(new java.awt.Color(102, 204, 255));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsua.setPreferredSize(new java.awt.Dimension(100, 30));
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        jLabel13.setText("Mật Khẩu");
        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setText("Chức Vụ");
        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));

        cbxChucVu.setModel(new javax.swing.DefaultComboBoxModel<ChucVu>());
        cbxChucVu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxChucVu.setForeground(new java.awt.Color(255, 255, 255));

        jLabel17.setText("Ghi Chú");
        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNhanVien);

        jLabel1.setText("TÌM KIẾM :");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimKiemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseReleased(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        txtMatKhau.setText("jPasswordField1");

        addChucVu.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        addChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChucVuActionPerformed(evt);
            }
        });

        jLabel3.setText("Email");
        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("NHÂN VIÊN");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        btnQuet.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\qrcode_scan_icon_136286.png")); // NOI18N
        btnQuet.setBackground(new java.awt.Color(204, 255, 204));
        btnQuet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQuet.setPreferredSize(new java.awt.Dimension(100, 30));
        btnQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetActionPerformed(evt);
            }
        });

        btnRefesh.setText("REFESH");
        btnRefesh.setBackground(new java.awt.Color(255, 255, 102));
        btnRefesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKHLayout = new javax.swing.GroupLayout(PanelKH);
        PanelKH.setLayout(PanelKHLayout);
        PanelKHLayout.setHorizontalGroup(
            PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(PanelKHLayout.createSequentialGroup()
                            .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelKHLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PanelKHLayout.createSequentialGroup()
                                            .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                            .addComponent(txtMaNhanVien))
                                        .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12))
                                    .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelKHLayout.createSequentialGroup()
                                            .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(32, 32, 32)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefesh))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        PanelKHLayout.setVerticalGroup(
            PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKHLayout.createSequentialGroup()
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel12)
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelKHLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnQuet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdNam)
                                .addComponent(rdNu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefesh, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Nhân Viên", PanelKH);

        jPanel1.setBackground(new java.awt.Color(158, 195, 192));

        tblNhanVien1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVien1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien1);

        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa1.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        jLabel2.setText("TÌM KIẾM :");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        txtTimKiemNhanVienNghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemNhanVienNghiViecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemNhanVienNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiemNhanVienNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân Viên Nghỉ Việc", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        String ma = txtMaNhanVien.getText().toLowerCase().trim();
        String ten = txtTen.getText().toLowerCase().trim();
        String tenDem = txtTenDem.getText().toLowerCase().trim();
        String ho = txtHo.getText().toLowerCase().trim();
        String diaChi = txtSoDienThoai.getText().toLowerCase().trim();
        String matKhau = txtMatKhau.getText().toLowerCase().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();
        if (ngaySinh.isEmpty() || ma.isEmpty() || ten.isEmpty() || tenDem.isEmpty() || ho.isEmpty()
                || diaChi.isEmpty() || matKhau.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đầy Đủ Thông Tin ", "Thông Báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String chekcSdt = "[0][0-9]{9}";
        if (!sdt.matches(chekcSdt)) {
            JOptionPane.showMessageDialog(this, "Nhâp Đúng số Điện thoại "
                    + "VD:0965670192");
            return;
        }
        String checkEmail = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        if (!txtEmail.getText().matches(checkEmail)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng Email");
            return;
        }
        if (localDate.getYear() - txtNgaySinh.getDate().getYear() < 18) {
            JOptionPane.showMessageDialog(this, "Chưa đủ 18 tuổi");
            return;
        }
        if (localDate.getYear() - txtNgaySinh.getDate().getYear() > 55) {
            JOptionPane.showMessageDialog(this, "Quá số tuổi");
            return;
        }
        if (nhanVienSe.checkTrungMa(txtMaNhanVien.getText())) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Xác nhân thêm.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                NhanVien nv = them();
                int ketQua = nhanVienSe.them(nv);
                if (ketQua < 0) {
                    JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                    load(nhanVienSe.getList());
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Trùng Mã");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed

        String ma = txtMaNhanVien.getText().toLowerCase().trim();
        String ten = txtTen.getText().toLowerCase().trim();
        String tenDem = txtTenDem.getText().toLowerCase().trim();
        String ho = txtHo.getText().toLowerCase().trim();
        String diaChi = txtSoDienThoai.getText().toLowerCase().trim();
        String matKhau = txtMatKhau.getText().toLowerCase().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();

        if (ngaySinh.isEmpty() || ma.isEmpty() || ten.isEmpty() || tenDem.isEmpty() || ho.isEmpty()
                || diaChi.isEmpty() || matKhau.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đầy Đủ Thông Tin ", "Thông Báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (localDate.getYear() - txtNgaySinh.getDate().getYear() < 18) {
            JOptionPane.showMessageDialog(this, "Chưa đủ 18 tuổi");
            return;
        }
        if (localDate.getYear() - txtNgaySinh.getDate().getYear() > 55) {
            JOptionPane.showMessageDialog(this, "Quá số tuổi");
            return;
        }
        String chekcSdt = "[0][0-9]{9}";
        if (!sdt.matches(chekcSdt)) {
            JOptionPane.showMessageDialog(this, "Nhâp Đúng số Điện thoại "
                    + "VD:0965670192");
            return;
        }
        String checkEmail = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        if (!txtEmail.getText().trim().matches(checkEmail)) {
            JOptionPane.showMessageDialog(this, "Sai Dinh Dang email");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?", "Xác nhân sửa.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (!nhanVienSe.checkTrungMa(txtMaNhanVien.getText().trim())) {

                NhanVien nv = new NhanVien();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                nv.setMaNv(txtMaNhanVien.getText().trim());
                nv.setTenNv(txtTen.getText().trim());
                nv.setTenDem(txtTenDem.getText().trim());
                nv.setHo(txtHo.getText().trim());
                nv.setNgaySinh(txtNgaySinh.getText());
                int gioiTinh = rdNam.isSelected() ? 1 : 0;
                nv.setGioiTinh(gioiTinh);
                nv.setEmail(txtEmail.getText().trim());
                ChucVu cv = (ChucVu) cbxChucVu.getModel().getSelectedItem();
                nv.setChucvu(cv);
                nv.setDiaChi(txtDiaChi.getText().trim());
                nv.setSdt(txtSoDienThoai.getText().trim());
                nv.setMatKhau(txtMatKhau.getText().trim());
                nv.setGhiChu(txtGhiChu.getText().trim());
                nhanVienSe.sua(nv, txtMaNhanVien.getText().trim());
                load(nhanVienSe.getList());
                JOptionPane.showMessageDialog(this, "Sửa thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Mã Nhân Viên Không Tồn Tại!");

                return;
            }
        } else {
            return;
        }


    }//GEN-LAST:event_btnsuaActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked

        int index = tblNhanVien.getSelectedRow();
        txtMaNhanVien.setText(nhanVienSe.getList().get(index).getMaNv());
        txtTen.setText(nhanVienSe.getList().get(index).getTenNv());
        txtTenDem.setText(nhanVienSe.getList().get(index).getTenDem());
        txtHo.setText(nhanVienSe.getList().get(index).getHo());
        txtNgaySinh.setDate(LocalDate.parse(nhanVienSe.getList().get(index).getNgaySinh()));
        if (tblNhanVien.getValueAt(index, 5).toString().equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }
        txtMatKhau.setText(nhanVienSe.getList().get(index).getMatKhau());
        int socv = cbxChucVu.getItemCount();
        ChucVu sp = new ChucVu();
        sp.setTenCv(tblNhanVien.getValueAt(index, 6).toString());
        cbxChucVu.setSelectedItem(sp.getTenCv());
        for (int j = 0; j < socv; j++) {
            ChucVu sp1 = cbxChucVu.getItemAt(j);
            if (sp.getTenCv().equals(sp1.getTenCv())) {
                cbxChucVu.setSelectedIndex(j);
            }
        }

        txtGhiChu.setText(nhanVienSe.getList().get(index).getGhiChu());
        txtDiaChi.setText(tblNhanVien.getValueAt(index, 7).toString());
        txtSoDienThoai.setText(tblNhanVien.getValueAt(index, 8).toString());
        txtEmail.setText(tblNhanVien.getValueAt(index, 9).toString());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa ?", "Xác nhân xóa.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            nhanVienSe.delete(txtMaNhanVien.getText().trim());
            JOptionPane.showMessageDialog(this, "Xoá Thành Công");
            load(nhanVienSe.getList());
            load1(nhanVienSe.getList1());
        } else {
            return;
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hoàn tác ?", "Xác nhân hoàn tác.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            nhanVienSe.delete1(txtTimKiemNhanVienNghiViec.getText());
            load(nhanVienSe.getList());
            load1(nhanVienSe.getList1());
            JOptionPane.showMessageDialog(this, "Chuyển Trạng Thái Thành Công");
        } else {
            return;
        }
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void tblNhanVien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVien1MouseClicked
        int index = tblNhanVien1.getSelectedRow();
        txtTimKiemNhanVienNghiViec.setText(tblNhanVien1.getValueAt(index, 0).toString());
    }//GEN-LAST:event_tblNhanVien1MouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblNhanVien.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemNhanVienNghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemNhanVienNghiViecActionPerformed
        ArrayList<ViewModeNhanVien> nv = nhanVienSe.timKiemNhanVienNghiViec(txtTimKiemNhanVienNghiViec.getText());
        DefaultTableModel model = (DefaultTableModel) tblNhanVien1.getModel();
        model.setColumnCount(0);
        model.addColumn("MÃ NHÂN VIÊN");
        model.addColumn("TÊN");
        model.addColumn("TÊN ĐÊM");
        model.addColumn("HỌ");
        model.addColumn("NGÀY SINH");
        model.addColumn("GIỚI TÍNH");
        model.addColumn("CHỨC VỤ");
        model.addColumn("ĐỊA CHỈ");
        model.addColumn("SỐ ĐIỆN THOẠI");
        model.addColumn("MẬT KHẨU");
        model.addColumn("GHI CHÚ");
        model.setRowCount(0);
        for (ViewModeNhanVien x : nv) {
            model.addRow(new Object[]{x.getMaNv(), x.getTenNv(), x.getTenDem(), x.getHo(), x.getNgaySinh(), x.getGioiTinh() == 1 ? "Nam" : "Nữ",
                x.getChucVu(), x.getDiaChi(), x.getSdt(), x.getMatKhau(), x.getGhiChu()});
        }
    }//GEN-LAST:event_txtTimKiemNhanVienNghiViecActionPerformed

    private void txtTimKiemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseReleased

    }//GEN-LAST:event_txtTimKiemMouseReleased

    private void txtTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMousePressed

    }//GEN-LAST:event_txtTimKiemMousePressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased

        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblNhanVien.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaActionPerformed

        int xoa = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Sao Không Ạ?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (xoa == JOptionPane.YES_OPTION) {
            int dong = tblNhanVien.getSelectedRow();
            String id = tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0).toString();
            nhanVienSe.delete(id);
            load(nhanVienSe.getList());
            load1(nhanVienSe.getList1());
            JOptionPane.showMessageDialog(this, "Xoá Thành Công");
        }

    }//GEN-LAST:event_XoaActionPerformed

    private void XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaMouseClicked

    }//GEN-LAST:event_XoaMouseClicked

    private void addChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChucVuActionPerformed
        new FormChucVu().setVisible(true);
    }//GEN-LAST:event_addChucVuActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetActionPerformed
        // TODO add your handling code here:
        initWebCam();
        QrQuet.setVisible(true);

    }//GEN-LAST:event_btnQuetActionPerformed

    private void thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatActionPerformed
        QrQuet.setVisible(false);
        webcam.close();
    }//GEN-LAST:event_thoatActionPerformed

    private void btnRefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshActionPerformed
        // TODO add your handling code here:
        txtMaNhanVien.setText("");
        txtHo.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        //txtMatKhau.setText("");
        txtNgaySinh.setText("");
        txtSoDienThoai.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        //comBoBox();
    }//GEN-LAST:event_btnRefeshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Menu;
    private javax.swing.JPanel PanelKH;
    private javax.swing.JDialog QrQuet;
    private javax.swing.JMenuItem Sua;
    private javax.swing.JMenuItem Xoa;
    private javax.swing.JButton addChucVu;
    private javax.swing.JButton btnQuet;
    private javax.swing.JButton btnRefesh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnsua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<ChucVu> cbxChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblNhanVien1;
    private javax.swing.JButton thoat;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JPasswordField txtMatKhau;
    private com.github.lgooddatepicker.components.DatePicker txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemNhanVienNghiViec;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {

            try {
                Thread.sleep(100);

            } catch (Exception e) {
                System.out.println(e);
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
            }
            if (result != null) {

                boolean check = true;
                if (check) {
                    txtGhiChu.setText(result.getText());
                    String chuoi = txtGhiChu.getText();
                    String nganCach = "|";

                    int soCmt = chuoi.indexOf(nganCach); // lấy ra vị trí
                    String soCanCuocCongDan = chuoi.substring(0, soCmt);
//        txtSoCmt.setText(soCanCuocCongDan);

                    int soCMTCu = chuoi.indexOf(nganCach, soCmt + 1);// lấy ra vị trí
                    String sochungMinhThu = chuoi.substring(soCmt + 1, soCMTCu);
                    System.out.println(sochungMinhThu);
//     

                    int hoTen = chuoi.indexOf(nganCach, soCMTCu + 1);// lấy ra vị trí
                    String hoVaTen = chuoi.substring(soCMTCu + 1, hoTen);

                    String khoangTrang = " ";

                    int ho = hoVaTen.indexOf(khoangTrang);
                    String ho1 = hoVaTen.substring(0, ho);
                    txtHo.setText(ho1);
//                    System.out.println(ho1);

//                    txtHo.setText(ho1);
//      txtHoTen.setText(hoVaTen);
                    int ten = hoVaTen.lastIndexOf(khoangTrang, hoVaTen.length()) + 1;
                    String ten1 = hoVaTen.substring(ten, hoVaTen.length());
                    txtTen.setText(ten1);
//                    System.out.println(ten1);
//                    txtTen.setText(ten1);

                    int tenDem = hoVaTen.indexOf(ten, ho + 1);
                    String tenDem1 = hoVaTen.substring(ho + 1, ten);
                    txtTenDem.setText(tenDem1);
//                    System.out.println(tenDem1);
//                    txtTenDem.setText(tenDem1);
//        txtHoTen.setText(hoVaTen);

                    int ngaySinh1 = chuoi.indexOf(nganCach, hoTen + 1);// lấy ra vị trí
                    String ngaySinh = chuoi.substring(hoTen + 1, ngaySinh1);
                    SimpleDateFormat simDate = new SimpleDateFormat("dd-MM-yyyy");
                    String catNgaySinh = ngaySinh.substring(0, 2);
                    String catThangSinh = ngaySinh.substring(2, 4);
                    String catNamSinh = ngaySinh.substring(4, 8);
                    String layNgaySinh = catNamSinh + "-" + catThangSinh + "-" + catNgaySinh;
                    txtNgaySinh.setDate(LocalDate.parse(layNgaySinh));
//        System.out.println(catNgaySinh);
//        System.out.println(catThangSinh);
//        System.out.println(catNamSinh);
//        
//        txtNgaySinh.setText(gepNgaySing);
//
//                try {
//                    System.out.println(simDate.parse(gepNgaySing));
////       
//                } catch (ParseException ex) {
//                   
//                }
                    int i = chuoi.indexOf(nganCach, ngaySinh1 + 1);// lấy ra vị trí
                    String gioiTinh = chuoi.substring(ngaySinh1 + 1, i);
                    System.out.println(gioiTinh);

                    if (gioiTinh.equals("Nam")) {
                        rdNam.setSelected(true);
                    } else {
                        rdNu.setSelected(true);
                    }

                    int diaChi1 = chuoi.indexOf(nganCach, i + 1);// lấy ra vị trí
                    String diaChi = chuoi.substring(i + 1, diaChi1);
                    System.out.println(diaChi);
                    txtDiaChi.setText(diaChi);
//                    txtMaNhanVien.setText("");
                    txtGhiChu.setText("");
                    webcam.close();
                    QrQuet.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Quét Mã Thành Công");

                } else {
                    JOptionPane.showMessageDialog(this, "Quét Thất Bại");
                }

//
//         int b = chuoi.lastIndexOf(nganCach, chuoi.length()) + 1;// lấy ra vị trí
//        String ngayCap = chuoi.substring(b, chuoi.length());
//        String catNgayCap = ngayCap.substring(0, 2);
//        String catThangCap = ngayCap.substring(2, 4);
//        String catNamCap = ngayCap.substring(4, 8);
//               
//        String cap=catNgayCap+"-"+ catThangCap+"-"+catNamCap;
//        
//        
//        txtNgayCap.setText(cap);
//        
//        
//        System.out.println(cap);
//        049202000284||Khuất Lưu Huy Nhật|24022002|Nam|Cẩm Bào, Cẩm Yên, Thạch Thất, Hà Nội|31052021
//         int f = chuoi.indexOf(nganCach, e + 1);// lấy ra vị trí
//        String ngaySinh = chuoi.substring(e + 1, f);
//        SimpleDateFormat simDate = new SimpleDateFormat("dd-MM-yyyy");
//        String catNgaySinh = ngaySinh.substring(0, 2);
//        String catThangSinh = ngaySinh.substring(2, 4);
//        String catNamSinh = ngaySinh.substring(4, 8);
//        String gepNgaySing =   catNgaySinh + "-" + catThangSinh + "-" + catNamSinh;
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
