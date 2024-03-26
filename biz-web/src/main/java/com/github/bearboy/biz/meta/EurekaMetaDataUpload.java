package com.github.bearboy.biz.meta;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ClassUtils;

import java.lang.management.ManagementFactory;

@Configuration(proxyBeanMethods = false)
public class EurekaMetaDataUpload {
    private final static boolean HOTSPOT_JVM = ClassUtils.isPresent(String.valueOf(OperatingSystemMXBean.class), null);
    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @Scheduled(fixedRate = 1000 * 40)
    public void uploadMetaData() {
        InstanceInfo info = applicationInfoManager.getInfo();
        info.getMetadata().put("cpu-usage", getCpuUsage());
        info.setIsDirty();
        System.out.println("开始上报meta 数据到 eureka server");
    }

    String getCpuUsage() {
        if (HOTSPOT_JVM) {
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)
                    ManagementFactory.getOperatingSystemMXBean();
            double usage = operatingSystemMXBean.getSystemCpuLoad() * 100*100;
            return String.valueOf(usage);
        } else {
            return "0";
        }
    }
}
