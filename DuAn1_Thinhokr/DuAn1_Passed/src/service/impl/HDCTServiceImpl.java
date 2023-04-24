/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import model.HDChiTiet;
import repository.HDCTRepository;
import service.HDCTService;

/**
 *
 * @author phamtuyetnga
 */
public class HDCTServiceImpl implements HDCTService{
    private final HDCTRepository hdctRepo = new HDCTRepository();

    @Override
    public Boolean saveHD(HDChiTiet hdct) {
        return hdctRepo.saveHoaDon(hdct);
    }
    
}
