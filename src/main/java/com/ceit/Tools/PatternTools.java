package com.ceit.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-5
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则验证
public class PatternTools {

    public boolean NullPatern(Object...object)
    {
        if (object == null)
            return false;
        int length = object.length;
        for(int i=0;i<length;i++)
        {
            if(object[i]==null)
                return false;
        }
        return true;
    }
    //验证传进来的数据是否全是数字
    public boolean IntegerPattern(Object...object)
    {
        Pattern p = Pattern.compile("[0-9]+?");
        int length = object.length;
        for(int i=0;i<length;i++)
        {
            Matcher m = p.matcher(String.valueOf(object[i]));
            if(!m.matches())
            {
                return false;
            }
        }
        return true;
    }
    //判断字符串只能够由字符和数字组成
    public boolean StringPattern(String args[])
    {
        int length = args.length;
        Pattern p = Pattern.compile("[a-zA-Z0-9]*");
        for(int i=0;i<length;i++)
        {
            Matcher m = p.matcher(args[i]);
            if(!m.matches())
            {
                return false;
            }
        }

        return true;
    }
    //判断字符串只能够由字符组成
    public boolean OnlyStringPattern(String args[])
    {
        Pattern p = Pattern.compile("[a-zA-Z]");
        int length = args.length;
        for(int i=0;i<length;i++)
        {
            Matcher m = p.matcher(args[i]);
            if(!m.matches())
            {
                return false;
            }
        }

        return true;
    }
    //sql注入
    public boolean SQLPattern(String args[])
    {
        String regex="select*";

        Pattern p = Pattern.compile(regex);
        int length = args.length;
        for(int i=0;i<length;i++)
        {
            Matcher m = p.matcher(args[i]);
            if(m.matches())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @param ，验证的格式有："yyyyMM","yyyyMMdd","yyyyMMdd HH:mm:ss",
     * 							  "yyyy-MM","yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"
     * 	                          "yyyy.MM","yyyy.MM.dd","yyyy.MM.dd HH:mm:ss"
     *                            "yyyy/MM","yyyy/MM/dd","yyyy/MM/dd HH:mm:ss"
     *                            "yyyy_MM","yyyy_MM_dd","yyyy_MM_dd HH:mm:ss"
     * @param sDate
     * @return false/true
     */
    public  boolean verificationOfDateIsCorrect(String sDate) {
        if(null == sDate || "".equals(sDate)){
            return false;
        }
        boolean flag = false;
        Pattern pattern0 = null;
        Matcher match0 = null;

        String datePattern = "(" +
                //第一种情况为月份是大月的有31天。
                "(^\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}" +//年
                "([-/\\._]?)" +//时间间隔符(-,/,.,_)
                "(10|12|0?[13578])" +//大月
                "([-/\\._]?)" +//时间间隔符(-,/,.,_)
                "((3[01]|[12][0-9]|0?[1-9])?)" +//日(31)要验证年月因此出现0/1次
                "([\\s]?)" +//空格
                "((([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))?))$" +//时分秒
                "|" +//或
                //第二种情况为月份是小月的有30天，不包含2月。
                "(^\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}" +//年
                "([-/\\._]?)" +//时间间隔符(-,/,.,_)
                "(11|0?[469])" +//小月不含2月
                "([-/\\._]?)" +//时间间隔符(-,/,.,_)
                "(30|[12][0-9]|0?[1-9])" +//日(30)
                "([\\s]?)" +//空格
                "((([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))?))$" +//时分秒
                "|" +//或
                //第三种情况为平年月份是2月28天的。
                "(^\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}" +//年
                "([-/\\._]?)" +//时间间隔符(-,/,.,_)
                "(0?2)" +//平年2月
                "([-/\\._]?)" +//时间间隔符(-,/,.,_)
                "(2[0-8]|1[0-9]|0?[1-9])" +//日(28)
                "([\\s]?)" +//空格
                "((([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))?))$" +//时分秒
                "|" +//或
                //第四种情况为闰年月份是2月29天的。
                //可以被4整除但不能被100整除的年份。
                //可以被400整除的数亦是能被100整除，因此后两位是00，所以只要保证前两位能被4整除即可。
                "(^((\\d{2})(0[48]|[2468][048]|[13579][26]))|((0[48]|[2468][048]|[13579][26])00)" +
                "([-/\\._]?)" +
                "(0?2)" +
                "([-/\\._]?)" +
                "(29)" +
                "([\\s]?)" +
                "((([0-1]?\\d|2[0-3]):([0-5]?\\d):([0-5]?\\d))?))$" +//时分秒
                ")";;

        pattern0 = Pattern.compile(datePattern);
        match0 = pattern0.matcher(sDate);
        flag = match0.matches();
        return flag;
    }
    public static void main(String arg0[])
    {
        String arg = "false";
        String args="true2";
        PatternTools p = new PatternTools();
        System.out.println(p.NullPatern(null,null));
        String s="select";
    }
    /*
     * 判断格式为MM/dd/yyyy HH:mm
     * */
    public boolean TimePatternMM(String arg)
    {
        Pattern pattern0 = null;
        Matcher match0 = null;
        String datePattern = "(^[1][0-2]|^[0][0-9])([/]{1})([1][0-9]|[0][0-9]|[2][0-9]|[3][0-1])([/]{1})"+
                "([0-9]{4})(\\s)" +
                "([1][0-9]|[0][0-9]|[2][0-4])([:])([6][0]|[0-5][0-9])";
        pattern0 = Pattern.compile(datePattern);
        match0 = pattern0.matcher(arg);
        return match0.matches();
    }
    /*
     * 判断格式为dd/MM/yyyy HH:mm
     * */
    public boolean TimePatternDD(String arg)
    {
        Pattern pattern0 = null;
        Matcher match0 = null;
        String datePattern ="(^[1][0-9]|^[0][0-9]|^[2][0-9]|^[3][0-1])" +//判断前面的天数
                "([/]{1})([1][0-2]|[0][0-9])([/]{1})" +//匹配月
                "([0-9]{4})(\\s)" +//匹配年
                "([1][0-2]|[0][0-9])([:])([6][0]|[0-5][0-9])";//匹配时间
        pattern0 = Pattern.compile(datePattern);
        match0 = pattern0.matcher(arg);
        return match0.matches();
    }
    public boolean TrueAndFalse(String...args)
    {
        String datePattern = "^[t][r][u][e]|[f][a][l][s][e]";
        Pattern pattern0 = null;
        Matcher match0 = null;
        if(args==null)
            return false;
        for(String str:args)
        {
            pattern0 = Pattern.compile(datePattern);
            match0 = pattern0.matcher(str);
            if(!match0.matches())
                return false;
        }
        return true;
    }
    /**
     * 用户登录sql注入验证
     *
     * **/
    public boolean SQLLoginPattern(String[] str) {
        // TODO Auto-generated method stub
        return true;
    }
}