package com.ceit.daoImpl;

import com.ceit.dao.BaseDao;
import com.ceit.Tools.HibernateFactory;
import com.ceit.Tools.MyException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-30
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class BaseDaoImpl<T extends Serializable, P
        extends Serializable> implements BaseDao<T,P> {


    @Override
    public T getObject(Class clazz, P id) {
        Session session = null;
        Transaction transaction = null;
        T t  = null;
        try
        {
            session = HibernateFactory.getSession();
            transaction = session.beginTransaction();
            t = (T) session.get(clazz,id);
            transaction.commit();
        }catch(Exception e)
        {
            t =  null;
            transaction.rollback();
            new MyException(e);
        }finally {
            HibernateFactory.closeSession();
            return t;
        }
    }

    @Override
    public boolean delete(T t) {
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try
        {
            session = HibernateFactory.getSession();
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            flag = true;
        }catch(Exception e)
        {
            flag = false;
            transaction.rollback();
            new MyException(e);
        }finally {
            HibernateFactory.closeSession();
            return flag;
        }

    }

    @Override
    public boolean update(T t) {
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try
        {
            session = HibernateFactory.getSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            flag = true;
        }catch(Exception e)
        {
            flag = false;
            transaction.rollback();
            new MyException(e);
        }finally {
            HibernateFactory.closeSession();
            return flag;
        }
    }

    @Override
    public boolean save(T t) {
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try
        {
            session = HibernateFactory.getSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            flag = true;
        }catch(Exception e)
        {
            flag = false;
            transaction.rollback();
            new MyException(e);
        }finally {
            HibernateFactory.closeSession();
            return flag;
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public List<T> getPageCount(int start, int end, String hql) {
        Session session = null;
        List<T> list = null;
        Transaction transactoin = null;
        try
        {
            session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            Query query = session.createQuery(hql);
            int count = end - start;
            query.setMaxResults(count);
            query.setFirstResult(start);
            list= query.list();
            transactoin.commit();
        }catch(Exception e)
        {
            list  = new ArrayList<T>();
            transactoin.rollback();
            new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
            return list;
        }

    }

    @Override
    public List<T> getAllObject(String hql) {
        Session session = null;
        List<T> list = null;
        Transaction transactoin = null;
        try
        {
            session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            Query query = session.createQuery(hql);
            list= query.list();
            transactoin.commit();
        }catch(Exception e)
        {
            list  = new ArrayList<T>();
            transactoin.rollback();
            new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
            return list;
        }
    }

    @Override
    public long getCount(String hql) {
        Session session = null;
        long number = 0;
        Transaction transactoin = null;
        try
        {
            session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            Query query = session.createQuery(hql);
            number = (Long) query.uniqueResult();
            transactoin.commit();
        }catch(Exception e)
        {
            number = 0;
            transactoin.rollback();
            throw new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
            return number;
        }
    }

    @Override
    public T getObject(String sql, String[] args, Object... objects) {
        Session session = null;
        T t  =  null;
        Transaction transactoin = null;
        try
        {
            session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            Query query = session.createQuery(sql);
            int length = objects.length;
            for(int i = 0;i<length;i++)
            {
                Object obj = objects[i];
                String type = obj.getClass().getSimpleName();
                if("Integer".equals(type))
                {
                    query.setInteger(args[i], (Integer) obj);
                }else if("Date".equals(type))
                {
                    query.setDate(args[i], (Date) obj);
                }else if("String".equals(type))
                {
                    query.setString(args[i], (String) obj);
                }else if("Long".equals(type))
                {
                    query.setLong(args[i], (Long) obj);
                }
            }
            t = (T) query.uniqueResult();
            transactoin.commit();
        }catch(Exception e)
        {
            t = null;
            transactoin.rollback();
            new MyException(e);

        }finally
        {
            HibernateFactory.closeSession();
            return t;
        }
    }

    @Override
    public boolean saveList(List<T> lists) {
        Transaction transactoin = null;
        boolean flag = false;
        try
        {
            Session session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            int length = lists.size();
            for(int i = 0;i<length ;i++)
            {
                session.save(lists.get(i));
                if(i%20==0)
                {
                    session.flush();
                }
            }
            transactoin.commit();
            flag = true;
        }catch(Exception e)
        {
            flag = false;
            transactoin.rollback();
            new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
            return flag;
        }
    }

    @Override
    public boolean updateList(List<T> lists) {
        Transaction transactoin = null;
        boolean flag = false;
        try
        {
            Session session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            int length = lists.size();
            for(int i = 0;i<length ;i++)
            {
                session.saveOrUpdate(lists.get(i));
                if(i%20==0)
                {
                    session.flush();
                }
            }
            transactoin.commit();
            flag = true;
        }catch(Exception e)
        {
            flag = false;
            transactoin.rollback();
            new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
            return flag;
        }
    }

    @Override
    public T getQueryObject(String hql) {
        T object = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            session = HibernateFactory.getSession();
            transaction = session.beginTransaction();
            Query q = session.createQuery(hql);
            object  = (T) q.uniqueResult();
            transaction.commit();
        }catch(Exception e)
        {
            object = null;
            transaction.rollback();
            throw new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
            return object;
        }
    }

    @Override
    public boolean deleteById(Class clazz,P p) {
        boolean flag = false;
        Session session = null;
        Transaction transaction = null;
        try
        {
            session = HibernateFactory.getSession();
            transaction = session.beginTransaction();
            T object = (T) session.get(clazz,p);
            session.delete(object);
            transaction.commit();
            flag = true;
        }catch(Exception e)
        {
            flag = false;
            transaction.rollback();
            new MyException(e);
        }finally
        {
            HibernateFactory.closeSession();
           return flag;
        }
    }

    @Override
    public boolean delete(String hql, String[] args, Object... objects) {
        Session session = null;
        boolean flag = false;
        Transaction transactoin = null;
        try
        {
            session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            Query query = session.createQuery(hql);
            int length = objects.length;
            for(int i = 0;i<length;i++)
            {
                Object obj = objects[i];
                String type = obj.getClass().getSimpleName();
                if("Integer".equals(type))
                {
                    query.setInteger(args[i], (Integer) obj);
                }else if("Date".equals(type))
                {
                    query.setDate(args[i], (Date) obj);
                }else if("String".equals(type))
                {
                    query.setString(args[i], (String) obj);
                }else if("Long".equals(type))
                {
                    query.setLong(args[i], (Long) obj);
                }
            }
            int i = query.executeUpdate();
            if (i>0)
                flag = true;
            transactoin.commit();
        }catch(Exception e)
        {
            flag = false;
            transactoin.rollback();
            new MyException(e);

        }finally
        {
            HibernateFactory.closeSession();
            return flag;
        }
    }

    @Override
    public List<T> getByKeyWord(String kqyWord,String keywordValue,Class clazz) {

        Session session = null;
        List<T> t = null;
        Transaction transactoin = null;
        try
        {
            session = HibernateFactory.getSession();
            transactoin = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.ilike(kqyWord,keywordValue, MatchMode.ANYWHERE));
            t = criteria.list();
            transactoin.commit();
        }catch(Exception e)
        {
            t = new ArrayList<T>();
            transactoin.rollback();
            new MyException(e);

        }finally
        {
            HibernateFactory.closeSession();
            return t;
        }
    }
}
