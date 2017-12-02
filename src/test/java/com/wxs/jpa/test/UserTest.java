package com.wxs.jpa.test;

import com.wxs.jpa.entity.User;
import com.wxs.jpa.repository.UserRepository;
import com.wxs.jpa.serivice.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;

/**
 * Created by lijiaxing on 2017/11/26.
 */
public class UserTest extends BaseJunitTest {

    @Resource
    private EntityManagerFactory factory;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserService userService;



    @Test
    public void saveTest(){
        User user=new User();
        user.setId(2);
        user.setName("admin");
        user.setPassword("123");
        user.setBirthday(new Date());
        userRepository.save(user);

        System.out.print("成功");

    }
    @Test
    public void findTest(){

        List<User> users = userRepository.findByNameLikeAndPasswordLike("%d%","123");
        for (User user : users) {
            System.out.println(user);
        }


    }

    @Test
    public void TestfindByIdIn(){


        List<User> users = userService.findByIdIn();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void TestFindByUser(){
        User user=new User();
        //user.setName("dmi");
        user.setPassword("12");
        List<User> users = userService.findByUser(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void deleteUserByNameTest(){
            int count = userRepository.deleteUserByName("lily");
        System.out.println("删除数量为"+count);
    }

    @Test
    public void deleteUserByNameLikeTest(){
        int count = userRepository.deleteUserByNameLike("%mi%");
        System.out.println("删除数量为"+count);
    }


    /**
     *
     * 更新
     */
    @Test
    public void updatePasswordByNameTest(){
        int count = userRepository.updatePasswordByName("7749", "小8");
        System.out.println(count);
    }
}
