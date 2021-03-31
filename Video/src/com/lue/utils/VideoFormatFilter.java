package com.lue.utils;

import java.io.File;
import java.io.FileFilter;

public class VideoFormatFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String fileName = pathname.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (suffix.contains("mp4")) {
            return true;
        } else if (suffix.contains("avi")) {
            return true;
        } else if (suffix.contains("mkv")) {
            return true;
        } else if (suffix.contains("rmvb")) {
            return true;
        } else {
            return false;
        }
    }

}
