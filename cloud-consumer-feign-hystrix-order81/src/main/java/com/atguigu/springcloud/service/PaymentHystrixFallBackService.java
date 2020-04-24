package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallBackService implements PaymentHystrixService {
    @Override
    public String paymentInf_OK(Integer id) {
        return "paymentInf_OK 错误";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout 错误";
    }
}
