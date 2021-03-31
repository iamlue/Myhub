package com.lue.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lue.dao.AnimeDao;
import com.lue.dao.mapper.AnimeMapper;
import com.lue.pojo.Anime;
import com.lue.service.AnimeService;

@Service
public class AnimeServiceImpl implements AnimeService {
    @Autowired
    private AnimeMapper animeMapper;

    @Override
    public void saveAnime(Anime anime) {
        try {
            animeMapper.saveAnime(anime);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public ArrayList<Anime> selectAnime(String title, Integer pageIndexs, Integer pageCount) {
        ArrayList<Anime> selectAnime = null;
        try {
            selectAnime = animeMapper.selectAnime(title, pageIndexs, pageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectAnime;

    }

    @Override
    public Anime selectAnimeByid(Integer id) {
        return animeMapper.selectAnimeByid(id);
    }

    @Override
    public void deleteAnime(String num) {
        try {
            animeMapper.deleteAnime(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Integer selectAnimeCountBytitle(String title) {

        return animeMapper.selectAnimeCountBytitle(title);
    }

    @Override
    public void editAnime(Anime anime) {
        animeMapper.editAnime(anime);

    }

}
