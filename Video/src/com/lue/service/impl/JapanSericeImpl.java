package com.lue.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lue.dao.mapper.JapanMapper;
import com.lue.pojo.Japan;
import com.lue.service.JapanService;

@Service
public class JapanSericeImpl implements JapanService {
    @Autowired
    private JapanMapper jappanMapper;

    @Override
    public void saveJapan(Japan japan) {
        // TODO Auto-generated method stub
        jappanMapper.saveJapan(japan);

    }

    @Override
    public ArrayList<Japan> selectJapan(String name, String num) {
        return jappanMapper.selectJapan(name, num);
    }

    @Override
    public void deleteJapan(String num) {
        jappanMapper.deleteJapan(num);

    }

    @Override
    public Japan selectJapanByid(int id) {
        // TODO Auto-generated method stub
        return jappanMapper.selectJapanByid(id);
    }

}
