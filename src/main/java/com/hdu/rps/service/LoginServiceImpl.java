package com.hdu.rps.service;

import com.hdu.rps.mapper.UserMapper;
import com.hdu.rps.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/5.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = Logger.getLogger(String.valueOf(LoginServiceImpl.this));
    private User user = null;
    private String job = null;
    private String password = null;
    private ArrayList<String> emailList;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String findByUserEmailAndUserPassword(String useremail, String userpassword) {
        try {
            user = userMapper.selectByUserEmail(useremail);
        }catch (Exception e) {
            System.out.println("LoginService"+e.getMessage());
            logger.info("-------无该用户---------");
            return "-1";
        }
        if(user == null) {  //无该用户
            logger.info("-------无该用户---------");
            return "-1";
        }
        job = user.getUserjob();//注意，这里有可能返回空值，为了防止空指针出错，将来建立新用户时一定要插入userjob的值
        password = user.getUserpassword();
        if(password.equals(userpassword)) {
            logger.info("--------找到该账号-------");
            return user.getUserno()+"|"+job;
        } else {
            logger.info("-------密码错误---------");
            return "-2";
        }
    }
    
//    @Override
//    public int findByUserEmailAndUserPasswordAndUserJob(String useremail, String userpassword, String userjob) {
//        try {
//            user = userMapper.selectByUserEmail(useremail);
//        }catch (Exception e) {
//            System.out.println("LoginService"+e.getMessage());
//            logger.info("-------无该用户---------");
//            return -1;
//        }
//        if(user == null) {  //无该用户
//            logger.info("-------无该用户---------");
//            return -1;
//        }
//        job = user.getUserjob();
//        password = user.getUserpassword();
//        if(!job.equals(userjob)) {
//            logger.info("-------无该用户---------");
//            return -1;
//        }
//        if(userjob.equals(job) & password.equals(userpassword)) {
//            logger.info("--------找到该账号-------");
//            return user.getUserno();
//        } else {
//            logger.info("-------密码错误---------");
//            return -2;
//        }
//    }

    @Override
    public ArrayList<String> findEmailByJob(String job) {
        emailList = (ArrayList<String>) userMapper.findEmailByJob(job);
        return emailList;
    }
}
