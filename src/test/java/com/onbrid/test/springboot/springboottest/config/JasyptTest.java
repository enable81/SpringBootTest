package com.onbrid.test.springboot.springboottest.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;


public class JasyptTest {

    @Test
    void encryptTest(){
        String id = "onams";
        String password = "onbrid8845";
        String url = "jdbc:log4jdbc:mariadb://localhost:43306/ONAMS?allowMultiQueries=true&autocommit=false";
        String email = "coton.membership@gmail.com";
        String emailpw = "lnbl shpo pgiq ganh";

        System.out.println(jasyptEncoding(id));
        System.out.println(jasyptEncoding(password));
        System.out.println(jasyptEncoding(url));
        System.out.println(jasyptEncoding(email));
        System.out.println(jasyptEncoding(emailpw));
    }

    public String jasyptEncoding(String value) {
        String key = "f64dafe8c33d8e1560c3c6bce728d5b9f26ea4e9a9a075eb469b7ae3ab6065cb";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHMD5ANDDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

}
