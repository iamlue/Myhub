package com.lue.service;

import java.awt.List;
import java.util.ArrayList;

import com.lue.pojo.Anime;

public interface AnimeService {
    public void saveAnime(Anime anime);

    public ArrayList<Anime> selectAnime(String title, Integer pageIndexs, Integer pageCount);

    public Anime selectAnimeByid(Integer id);

    public void deleteAnime(String num);

    public Integer selectAnimeCountBytitle(String title);

    public void editAnime(Anime anime);
}
