package org.example.yugong.base;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author qiaobao
 * @since 2025/5/19
 */
public class SerializeTest {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("test");
        user.setAge(17);
        int testTimes = 100000;

        // 测试 ObjectOutputStream
        long start1 = System.currentTimeMillis();
        byte[] objectOutputStreamBytes = null;
        for (int i = 0; i < testTimes; i++) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(os)) {
                out.writeObject(user);
            }
            objectOutputStreamBytes = os.toByteArray();
            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(objectOutputStreamBytes))) {
                User deserializedUser = (User) in.readObject();
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("ObjectOutputStream 序列化和反序列化耗时: " + (end1 - start1) + " ms");
        System.out.println("ObjectOutputStream 序列化后字节数组长度: " + objectOutputStreamBytes.length + " bytes");

        // 测试 ByteBuffer
        long start2 = System.currentTimeMillis();
        byte[] byteBufferBytes = null;
        for (int i = 0; i < testTimes; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            byte[] userName = user.getName().getBytes();
            int userAge = user.getAge();
            byteBuffer.putInt(userName.length);
            byteBuffer.put(userName);
            byteBuffer.putInt(4);
            byteBuffer.putInt(userAge);
            byteBuffer.flip();
            byteBufferBytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(byteBufferBytes);

            ByteBuffer readBuffer = ByteBuffer.wrap(byteBufferBytes);
            int nameLength = readBuffer.getInt();
            byte[] nameBytes = new byte[nameLength];
            readBuffer.get(nameBytes);
            String name = new String(nameBytes);
            readBuffer.getInt(); // 跳过 age 长度
            int age = readBuffer.getInt();
            User deserializedUser = new User();
            deserializedUser.setName(name);
            deserializedUser.setAge(age);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("ByteBuffer 序列化和反序列化耗时: " + (end2 - start2) + " ms");
        System.out.println("ByteBuffer 序列化后字节数组长度: " + byteBufferBytes.length + " bytes");

        // 测试 FastJSON
        long start3 = System.currentTimeMillis();
        byte[] fastJsonBytes = null;
        for (int i = 0; i < testTimes; i++) {
            String jsonString = JSON.toJSONString(user);
            fastJsonBytes = jsonString.getBytes();
            User deserializedUser = JSON.parseObject(jsonString, User.class);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("FastJSON 序列化和反序列化耗时: " + (end3 - start3) + " ms");
        System.out.println("FastJSON 序列化后字节数组长度: " + fastJsonBytes.length + " bytes");

        // 测试 Hessian
        long start4 = System.currentTimeMillis();
        byte[] hessianBytes = null;
        for (int i = 0; i < testTimes; i++) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(bos);
            ho.writeObject(user);
            hessianBytes = bos.toByteArray();

            ByteArrayInputStream bis = new ByteArrayInputStream(hessianBytes);
            HessianInput hi = new HessianInput(bis);
            User deserializedUser = (User) hi.readObject();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Hessian 序列化和反序列化耗时: " + (end4 - start4) + " ms");
        System.out.println("Hessian 序列化后字节数组长度: " + hessianBytes.length + " bytes");
    }
}