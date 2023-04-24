/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.math.BigDecimal;
import repository.DiemRepository;
import service.DiemService;

/**
 *
 * @author Dell
 */
public class DiemServiceImpl implements DiemService {

    private DiemRepository diemRepo = new DiemRepository();
    
    @Override
    public Integer insertDiem() {
        try {
            return diemRepo.insertDiem();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateSoDiem(Float soDiem, String maKH) {
        try {
            return diemRepo.updateSoDiem(soDiem, maKH);
        } catch (Exception e) {
            return null;
        }
    }

}
