package com.idea4j.jvm.oom;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 -Xmx20m -Xmn4m -XX:+UseConcMarkSweepGC -verbose:gc -Xlog:gc,gc+ref=debug,gc+heap=debug,gc+age=trace:file=/tmp/logs/gc_%p.log:tags,uptime,time,level -Xlog:safepoint:file=/tmp/logs/safepoint_%p.log:tags,uptime,time,level -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log -XX:-OmitStackTraceInFastThrow
 -Xmx20m -XX:+UseG1GC -verbose:gc -Xlog:gc,gc+ref=debug,gc+heap=debug,gc+age=trace:file=/tmp/logs/gc_%p.log:tags,uptime,time,level -Xlog:safepoint:file=/tmp/logs/safepoint_%p.log:tags,uptime,time,level -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log -XX:-OmitStackTraceInFastThrow
 */
public class OOMTest {

    private static final int _1MB = 1024 * 1024;

    static List<byte[]> byteList = new ArrayList<byte[]>();


    private static void oom(HttpExchange exchange) {

        try {
            String response = "oom begin";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; ; i++) {
            byte[] bytes = new byte[_1MB];
            byteList.add(bytes);
            System.out.println(i + "MB");
            memPrint();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    /**
     * committed 指的是当前可⽤的内存⼤⼩，它的⼤⼩包括已经使⽤ 的内存
     * used 指的是实际被使⽤的内存⼤⼩，它的值总是⼩于 committed
     */
    private static void memPrint() {
        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(memoryPoolMXBean.getName()+" committed:" + memoryPoolMXBean.getUsage().getCommitted() + "used:" + memoryPoolMXBean.getUsage().getUsed());
        }
    }

    public static void main(String[] args) throws IOException {
        srv();
    }

    private static void srv() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8888), 1);
        HttpContext context = server.createContext("/");
        context.setHandler(OOMTest::oom);
        server.start();

    }


}
