package com.wxs.jpa.serivice;

import com.wxs.jpa.entity.User;
import com.wxs.jpa.repository.UserRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by lijiaxing on 2017/11/27.
 */
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;


    /**
     * in查询
     * @return
     */
    public List<User> findByIdIn(){
        List<User> users=userRepository.findAll(new Specification(){


            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                CriteriaBuilder.In<Integer> in = cb.in(root.<Integer>get("id"));
                for (int i=7;i<9;i++){
                    in.value(i);
                }
                CriteriaQuery where = query.where(in);
                return null;
            }
        });
        return users;
    }

    /**
     * 根据user进行各条件的查询
     * @param user
     * @return
     */
    public List<User> findByUser(final User user){
        List<User> users=userRepository.findAll(new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate name=null;
                if(user.getName()!=null){
                    name =cb.like(root.<String>get("name"),"%"+user.getName()+"%");
                }
                Predicate password=null;
                if(user.getPassword()!=null){
                    password=cb.equal(root.<String>get("password"),user.getPassword());
                }
                if(name!=null) query.where(name);
                if(password!=null) query.where(password);

                return null;
            }
        });
        return users;
    }
}
