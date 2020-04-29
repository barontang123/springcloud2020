package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.tool.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    IdGeneratorSnowflake idGeneratorSnowflake;

    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"成功");
    }

    @GetMapping("/order/getorderId")
    public CommonResult getorderId()
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i=1 ;i<=20;i++)
        {
            threadPool.submit(()->{System.out.println(idGeneratorSnowflake.snowlakeId());});
        }

        threadPool.shutdown();
        return new CommonResult(200,"成功");
    }
}
