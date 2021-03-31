package com.lue.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.lue.pojo.Japan;

public interface JapanMapper {

    void saveJapan(Japan japan);

    ArrayList<Japan> selectJapan(@Param(value = "name") String name, @Param(value = "num") String num);

    void deleteJapan(String num);

    Japan selectJapanByid(@Param(value = "id") int id);

}
