package com.lue.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.lue.pojo.Anime;

public interface AnimeMapper {
    public void saveAnime(Anime anime);

    public ArrayList<Anime> selectAnime(@Param(value = "title") String title, @Param(value = "pageIndexs") Integer pageIndexs, @Param(value = "pageCount") Integer pageCount);

    public Anime selectAnimeByid(Integer id);

    public void deleteAnime(String num);

    public Integer selectAnimeCountBytitle(@Param(value = "title") String title);

    public void editAnime(Anime anime);
}
