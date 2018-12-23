package com.hdu.rps.service;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/5.
 */

public interface LoginService {

   String findByUserEmailAndUserPassword(String useremail, String userpassword);
   //int findByUserEmailAndUserPasswordAndUserJob(String useremail, String userpassword, String userjob);
   ArrayList<String> findEmailByJob(String job);
}
