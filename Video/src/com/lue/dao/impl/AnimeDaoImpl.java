package com.lue.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lue.dao.AnimeDao;
import com.lue.dao.mapper.AnimeMapper;
import com.lue.pojo.Anime;

public class AnimeDaoImpl implements AnimeDao {
    private AnimeMapper animeMapper;

    @Override
    public void saveAnime(Anime anime) {
        animeMapper.saveAnime(anime);

    }

}
