package com.wxs.jpa.repository;

import com.wxs.jpa.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lijiaxing on 2017/11/26.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    /**
     * 根据条件查询
     * @param name
     * @param password
     * @return
     */
    List<User> findByNameAndPassword(String name, String password);
    List<User> findByNameAndPasswordLike(String name,String password);
    List<User> findByNameLikeAndPasswordLike(String name,String password);


    List<User> findAll(Specification id);

    /**
     * 根据条件删除
     * @param name
     * @return
     */
    @Transactional
    int deleteUserByName(String name);

    @Transactional
    int deleteUserByNameLike(String name);


    /**
     * 根据条件更新
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User u set u.password=?1 where u.name=?2")
    int updatePasswordByName(String password,String name);

}
