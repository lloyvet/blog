package com.lloyvet.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zihao Shen
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.lloyvet.hospital.mapper"})
@EnableAspectJAutoProxy
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

}
