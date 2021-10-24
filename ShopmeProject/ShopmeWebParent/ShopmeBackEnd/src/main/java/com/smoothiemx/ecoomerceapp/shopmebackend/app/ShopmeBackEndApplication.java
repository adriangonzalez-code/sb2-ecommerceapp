package com.smoothiemx.ecoomerceapp.shopmebackend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.smoothiemx.ecommerceapp.shopmecommon.entity"})
public class ShopmeBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopmeBackEndApplication.class, args);
    }

}