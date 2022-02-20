package ks.f4_demo;

public class Test {

    public static void main(String[] args) {
        // 调用kotlin中使用object关键字创建的单例；在Java中需要借助INSTANCE
        P1.INSTANCE.send();
        // 调用伴生对象的方法
        B12.Companion.sendMessage();
    }
}
