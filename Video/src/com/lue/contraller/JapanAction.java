package com.lue.contraller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lue.pojo.Japan;
import com.lue.service.JapanService;

@Controller
public class JapanAction {
    @Autowired
    private JapanService japanService;

    @RequestMapping(value = "/saveJapan.do", method = RequestMethod.POST)
    @ResponseBody
    public void saveJapan(HttpServletRequest req) {
        //�ļ��ϴ�������
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) req;
        Japan japan = new Japan();
        japan.setBrief(req.getParameter("brief"));
        japan.setHref(req.getParameter("href"));
        japan.setLocal(req.getParameter("local"));
        japan.setNum(req.getParameter("num"));
        japan.setTitle(req.getParameter("title"));
        japan.setName(req.getParameter("name"));
        System.out.println(japan.toString());
        MultipartFile file = mRequest.getFile("image");
        japan.setImage("/image/" + file.getOriginalFilename());
        try {
            File file2 = new File("D://image//" + file.getOriginalFilename());
            System.out.println(file2.getAbsolutePath());
            file.transferTo(new File("F://image/" + file.getOriginalFilename()));
            japanService.saveJapan(japan);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/selectJapan.do", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Japan> selectJapan(HttpServletRequest req) {
        ArrayList<Japan> list = null;
        try {
            String name = req.getParameter("name");
            String num = req.getParameter("num");
            list = japanService.selectJapan(name, num);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @RequestMapping(value = "/deletejapan.do", method = RequestMethod.GET)
    @ResponseBody
    public Japan deletejapan(HttpServletRequest req) {
        String num = req.getParameter("num");
        ArrayList<Japan> japans = japanService.selectJapan("", num);
        Japan japan = japans.get(0);
        japanService.deleteJapan(num);
        return japan;
    }

    @RequestMapping(value = "Japan/runVideo.do", method = RequestMethod.GET)
    @ResponseBody
    public void runVideo(HttpServletRequest req) {
        try {
            String id = req.getParameter("id");
            Japan japan = japanService.selectJapanByid(Integer.parseInt(id));
            System.out.println(japan.getLocal());
            Runtime.getRuntime().exec("\"D:\\Program Files (x86)\\PotPlayer\\PotPlayerMini64.exe\""+" " + japan.getLocal());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
