package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/monitor")
@CrossOrigin
public class MonitorController {


    @ApiOperation("系统监控")
    @GetMapping
    public Result<?> getServerInfo() {
        Map<String, Object> data = new HashMap<>();
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        double systemLoadAverage = osBean.getSystemLoadAverage();
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long maxMemory = runtime.maxMemory();
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        data.put("runTime", compilationMXBean.getTotalCompilationTime());
        data.put("JITName", compilationMXBean.getName());
        data.put("totalMemory", totalMemory);
        data.put("freeMemory", freeMemory);
        data.put("maxMemory", maxMemory);
        data.put("OSName", osBean.getName());
        data.put("OSVersion", osBean.getVersion());
        data.put("OSArch", osBean.getArch());
        data.put("OSCore", osBean.getAvailableProcessors());
        if (systemLoadAverage == -1) {
            data.put("sysLoadAverage", "N/A"); // 或者其他默认值
        } else {
            data.put("sysLoadAverage", systemLoadAverage);
        }
        try {
            // 获取主机名
            String hostname = InetAddress.getLocalHost().getHostName();
            data.put("hostname", hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // 获取线程信息
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        data.put("thread", threadMXBean.getThreadCount());
        data.put("DaemonThread", threadMXBean.getDaemonThreadCount());

        // 获取类加载信息
        data.put("LoadedClass", ManagementFactory.getClassLoadingMXBean().getLoadedClassCount());
        data.put("TotalLoadedClass", ManagementFactory.getClassLoadingMXBean().getTotalLoadedClassCount());
        data.put("UnloadedClass", ManagementFactory.getClassLoadingMXBean().getUnloadedClassCount());

        // 获取运行时信息
        data.put("JVMName", ManagementFactory.getRuntimeMXBean().getVmName());
        data.put("JVMVersion", ManagementFactory.getRuntimeMXBean().getVmVersion());
        data.put("JVMVendor", ManagementFactory.getRuntimeMXBean().getVmVendor());

        // 磁盘信息
        File[] roots = File.listRoots();
        for (File root : roots) {
            data.put(root.getPath() + "totalSpace", root.getTotalSpace());
            data.put(root.getPath() + "freeSpace", root.getUsableSpace());
        }
        return Result.success(data);
    }

}
