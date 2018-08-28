package com.hanyun.platform.pay.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.test
 * @Author: dewen.li
 * @Date: 2018-08-10 上午11:24
 */
public class GenFormatDicBean {
    public static void main(String[] args){
        String path = "/Users/ldwtxwhspring/data/tmp/totp2.txt";
        try {
            GenBean(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GenBean(String filepath) throws Exception{
        File file  = new File(filepath);
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            int i = 0;
            while (it.hasNext()) {
                String drname = "KEY";
                String line = it.nextLine();
                if(!line.equals("") && null!=line) {
                    String[] strarr = line.split("\\s+");
//                    PinyinHelper.toHanyuPinyinStringArray(strarr[1],);
                    StringBuffer sb = new StringBuffer();
                    sb.append("/** " + strarr[1] + "*/\n");
                    sb.append("public static final String  " + drname + i++ + "=\"" + strarr[0] + "\";");
                    sb.append("\n");
                    System.out.println(sb.toString());
                    sb.append(strarr[0]);
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
