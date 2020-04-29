package com.atguigu.springcloud.alibaba.tool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class IdGeneratorSnowflake {

    private  long workId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workId,datacenterId);

    @PostConstruct
    public  void  init()
    {
        try{
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerid:{}",workId);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            log.warn("当前机器的workerid获取获取失败",ex);
            workId = NetUtil.getLocalhostStr().hashCode();
        }

    }

    public synchronized long snowlakeId()
    {
        return snowflake.nextId();
    }

    public synchronized long snowlakeId(long workerId, long datacenterId)
    {
        Snowflake snowflake = IdUtil.createSnowflake(workId,datacenterId);
        return snowflake.nextId();
    }


    public static void main(String[] args)
    {
        System.out.println(new IdGeneratorSnowflake().snowlakeId());
    }

}
