/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.centralplains.daas;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName ExtremelyClient
 * @Decription TODO
 * @Date 2019/1/7 14:17
 * @Version 1.0
 */
public class ExtremelyClient {
    Socket socket = null;
    BufferedReader reader = null;
    PrintWriter writer = null;
    BufferedReader bufferedReader = null;
    ObjectInputStream objectInputStream = null;

    public ExtremelyClient() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            socket = new Socket("localhost", 8631);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String sql) {
        try {
            writer = new PrintWriter(socket.getOutputStream());
            writer.println(sql);
            writer.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            try {
                Object results = objectInputStream.readObject();
                System.out.println(results);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                objectInputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        ExtremelyClient client = new ExtremelyClient();
        //"insert into user(id,name,age,sex)values(1,'feifei',26,'男')"
        //select * from user;
        String s = "select * from user;";
        String i = "insert into user(id,name,age,sex)values(1,'feifei',26,'男')";
//        client.run(s);
        long t = System.currentTimeMillis();
        PrintWriter printWriter = new PrintWriter(new File("D:\\L临时数据\\script\\user.sql"));
        for (int j = 10; j < 20; j++) {
            ExtremelyClient client = new ExtremelyClient();
            String str = "insert into user(id,name,age,sex)values(" + j + ",feifei" + j + "," + j + "," + (j % 2 == 0 ? "女" : "男") + ");";
            printWriter.append(str);
            client.run(str);
        }
        System.out.println("jieshu--");
        long e = System.currentTimeMillis();
        System.out.println("耗时:" + (e - t));
    }
}

class Test2 {
    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        ExtremelyClient client = new ExtremelyClient();
        String sql = "select * from user where  name='feifei18';";
        client.run(sql);
        long e = System.currentTimeMillis();
        System.out.println("耗时:" + (e - s));
    }
}
