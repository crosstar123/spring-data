package com.wxs.jpa.test;

import com.wxs.jpa.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by lijiaxing on 2017/11/29.
 * EntityManager使用测试
 *
 */
@Service
public class UserManagerTest extends BaseJunitTest {

    @Resource
    private EntityManagerFactory factory;

    private EntityManager manager;

    @Before
    public void getEntity(){
        manager=factory.createEntityManager();
    }

    @Test
    public void testRemove(){
        User user=new User();
        user.setId(9);

        //创建EntityManager对象
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);

        transaction.commit();

    }

    /**
     *  保存
     */
    @Test
    public void testSave(){
        User user=new User();
        user.setName("校长");
        user.setBirthday(new Date());
        user.setPassword("666");
        //创建EntityManager对象
        EntityManager entityManager = factory.createEntityManager();
        //创建事务
        EntityTransaction transaction = entityManager.getTransaction();
        ///开启事务
        transaction.begin();
        //将实体转换为 removed 状态，并且在调用 flush() 方法或提交事物后删除数据库中的数据。
        entityManager.persist(user);

        //提交
        transaction.commit();
    }

    /**
     * 使用em.persist()方法进行批量保存
     */
    @Test
    public void testSaveBatch(){
        User user1=new User( "小818","888",new Date());
        User user2=new User("小199","555",new Date());
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.flush();
        manager.clear();
        transaction.commit();
    }

    /**
     * 使用em.merge()方法进行批量更新
     */
    @Test
    public void testUpdateBatch(){
        User user1=new User( 11,"小818","888",new Date());
        User user2=new User(12,"小199","555",new Date());
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(user1);
        manager.merge(user2);
        manager.flush();
        manager.clear();
        transaction.commit();
    }



}
