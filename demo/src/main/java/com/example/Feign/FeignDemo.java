package com.example.Feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "bankAccountClient", url = "${bank.account.url}")
public class FeignDemo {

}
