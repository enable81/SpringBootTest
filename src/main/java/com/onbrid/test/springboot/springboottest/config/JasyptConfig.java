package com.onbrid.test.springboot.springboottest.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {

        String key = "f64dafe8c33d8e1560c3c6bce728d5b9f26ea4e9a9a075eb469b7ae3ab6065cb";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key); // 암호화할 때 사용하는 키
        config.setAlgorithm("PBEWITHMD5ANDDES"); // 암호화 알고리즘
        config.setPoolSize("1"); // 인스턴스 pool
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
        config.setStringOutputType("base64"); //인코딩 방식

        encryptor.setConfig(config);

        return encryptor;
    }


    public static void main(String[] args) {

        String key = "f64dafe8c33d8e1560c3c6bce728d5b9f26ea4e9a9a075eb469b7ae3ab6065cb";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHMD5ANDDES");
        pbeEnc.setPassword(key);

        String id = "onams";
        String password = "onbrid8845";

        System.out.println(pbeEnc.encrypt(id));
        System.out.println(pbeEnc.encrypt(password));
    }

}
