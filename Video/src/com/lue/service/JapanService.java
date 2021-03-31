package com.lue.service;

import java.util.ArrayList;

import com.lue.pojo.Japan;

public interface JapanService {

    void saveJapan(Japan japan);


    ArrayList<Japan> selectJapan(String name, String num);


    void deleteJapan(String num);

    Japan selectJapanByid(int id);

}
