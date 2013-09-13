package com.ceit.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-11
 * Time: 下午7:43
 * To change this template use File | Settings | File Templates.
 */
public class MyException extends Exception{
    public MyException(Exception e)
    {
        e.printStackTrace();
    }
}
