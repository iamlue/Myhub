package com.lue.contraller;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lue.pojo.Anime;
import com.lue.pojo.ViUtil;
import com.lue.service.AnimeService;
import com.lue.utils.VideoFormatFilter;

import net.sf.json.JSONObject;

@Controller
public class AnimeAction {
    @Autowired
    private AnimeService animeService;
    private String title;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        System.out.println("����");
        return "index";
    }

    @RequestMapping(value = "/saveAnime.do", method = RequestMethod.POST)
    @ResponseBody
    public int saveAnime(HttpServletRequest req) {
        //�ļ��ϴ�������
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) req;
        Anime anime = new Anime();
        anime.setLocal(req.getParameter("local"));
        anime.setTitle(req.getParameter("title"));
        anime.setName(req.getParameter("name"));
        System.out.println(anime.toString());
        MultipartFile file = mRequest.getFile("image");
        System.out.println(file.getOriginalFilename());
        anime.setImage("/image/" + file.getOriginalFilename());
        try {
            File file2 = new File("D://image//" + file.getOriginalFilename());
            System.out.println(file2.getAbsolutePath());
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmsss");
            String filename = file.getOriginalFilename();
            String suff = "";
            if (filename.contains("jpg")) {
                suff = ".jpg";
            }
            String imagepath = "/Anime/" + format.format(date).toString() + suff;
            anime.setImage(imagepath);
            file.transferTo(new File("F://image/Anime/" + format.format(date).toString() + suff));
            animeService.saveAnime(anime);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/selectAnime.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject selectAnime(HttpServletRequest req) {
        JSONObject data = null;
        try {
            data = new JSONObject();
            Integer pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
            Integer pageCount = Integer.parseInt(req.getParameter("pageCount"));
            String title = req.getParameter("title");
            Integer count = animeService.selectAnimeCountBytitle(title);
            Integer pagetotal = count / pageCount + 1;
            ArrayList<Anime> animes = animeService.selectAnime(title, pageIndex * pageCount, pageCount);
            data.put("data", animes);
            data.put("pagetotal", pagetotal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @RequestMapping(value = "/selectAnimeByid.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectAnimeByid(HttpServletRequest req) {
        JSONObject data = null;
        try {
            data = new JSONObject();
            Integer id = Integer.parseInt(req.getParameter("id"));
            Anime anime = animeService.selectAnimeByid(id);
            data.put("data", anime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @RequestMapping(value = "/editAnime.do", method = RequestMethod.POST)
    @ResponseBody
    public int editAnime(HttpServletRequest req) {
        //�ļ��ϴ�������
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) req;
        Anime anime = new Anime();
        anime.setId(Integer.parseInt(req.getParameter("id")));
        anime.setLocal(req.getParameter("local"));
        anime.setTitle(req.getParameter("title"));
        anime.setName(req.getParameter("name"));
        System.out.println(anime.toString());
        MultipartFile file = mRequest.getFile("image");
        System.out.println(file.isEmpty());
        try {
            if (!file.isEmpty()) {
                File file2 = new File("D://image//" + file.getOriginalFilename());
                System.out.println(file2.getAbsolutePath());
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmsss");
                String filename = file.getOriginalFilename();
                String suff = "";
                if (filename.contains("jpg")) {
                    suff = ".jpg";
                }
                String imagepath = "/Anime/" + format.format(date).toString() + suff;
                anime.setImage(imagepath);
                file.transferTo(new File("F://image/Anime/" + format.format(date).toString() + suff));
            }
            animeService.editAnime(anime);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    /*@RequestMapping(value ="/deleteAnime.do",method=RequestMethod.GET)
    @ResponseBody
    public Anime deleteAnime(HttpServletRequest req) {
        String num = req.getParameter("num");
        ArrayList<Anime> animes = animeService.selectAnime("", num);
        Anime anime = animes.get(0);
        animeService.deleteAnime(num);
        return anime;
    }*/
    @RequestMapping(value = "/Anime/runVideo.do", method = RequestMethod.GET)
    @ResponseBody
    public void runVideo(HttpServletRequest req) {
        String path = req.getParameter("path");
        try {
            System.out.println(path);
            Runtime.getRuntime().exec("\"D:\\Program Files (x86)\\PotPlayer\\PotPlayerMini64.exe\""+" " + path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/anime/showVideo.do", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<String> showVideo(HttpServletRequest req) {
        ArrayList<String> list = null;
        try {
            String id = req.getParameter("id");
            Anime anime = animeService.selectAnimeByid(Integer.parseInt(id));
            String local = anime.getLocal();
            File file = new File(local);
            File[] flist = file.listFiles(new VideoFormatFilter());
            list = new ArrayList<>();
            for (int i = 0; i < flist.length; i++) {
                list.add(flist[i].getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
