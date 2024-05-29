package com.test.threadlocal;

public class Demo2 {

    private ThreadLocal<String> local = new ThreadLocal<>();

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Demo1 demo = new Demo1();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                synchronized (Demo2.class) {
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("---------------");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });

            thread.setName("线程" + i);
            thread.start();
        }
    }

}
