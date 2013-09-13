package com.ceit.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-30
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao<T extends Serializable, P extends Serializable> {
    public T getObject(Class clazz, P id);
    public boolean delete(T t);
    public boolean update(T t);
    public boolean save(T t);
    public List<T> getPageCount(int start, int end, String hql);
    public List<T> getAllObject(String hql);
    public long getCount(String hql);
    public T getObject(String sql, String args[], Object... objects);
    public boolean saveList(List<T> lists);
    public boolean updateList(List<T> lists);
    public T getQueryObject(String hql);
    public boolean deleteById(Class clazz, P p);
    public boolean delete(String hql, String args[], Object... objects);
    public List<T> getByKeyWord(String key, String keywordValue, Class clazz);
}
