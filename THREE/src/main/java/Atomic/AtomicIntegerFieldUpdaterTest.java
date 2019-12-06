package Atomic;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    static class A {
        volatile int intValue01 = 100;//嘿 这里必须volatile修饰 不然报错 ExceptionInInitializerError 提示你Must be volatile type 厉害了
        //        volatile int intValue02 = 50;
        //不能传入两个值吗？？？
    }

    public final static AtomicIntegerFieldUpdater<A> ATOMIC_INTEGER_UPDATER = AtomicIntegerFieldUpdater.newUpdater(A.class, "intValue01");

    public static void main(String[] args) {
        final A a = new A();
        for (int i = 0; i < 10; i++) {

            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while(ATOMIC_INTEGER_UPDATER.get(a)<=800){
                        if (true) {
                            ATOMIC_INTEGER_UPDATER.addAndGet(a,20);
                            System.out.println(Thread.currentThread().getName() + " 对对应的值做了修改！");
                            System.out.println(ATOMIC_INTEGER_UPDATER.get(a));
                        }
                        else System.exit(0);
                    }
                    System.out.println("当前线程是："+Thread.currentThread().getName());
                }
            }.start();
        }
    }
}

/**
 *是这样 给大家看一组输出
 *
 * "C:\Program Files\Java\jdk1.8.0_162\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2018.3\lib\idea_rt.jar=51111:C:\Program Files\JetBrains\IntelliJ IDEA 2018.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_162\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\rt.jar;C:\Users\pc\IdeaProjects\Horlly.test\THREE\target\classes;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot-starter-web\2.1.6.RELEASE\spring-boot-starter-web-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot-starter\2.1.6.RELEASE\spring-boot-starter-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot\2.1.6.RELEASE\spring-boot-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\2.1.6.RELEASE\spring-boot-autoconfigure-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot-starter-logging\2.1.6.RELEASE\spring-boot-starter-logging-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\pc\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\pc\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.11.2\log4j-to-slf4j-2.11.2.jar;C:\Users\pc\.m2\repository\org\apache\logging\log4j\log4j-api\2.11.2\log4j-api-2.11.2.jar;C:\Users\pc\.m2\repository\org\slf4j\jul-to-slf4j\1.7.26\jul-to-slf4j-1.7.26.jar;C:\Users\pc\.m2\repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;C:\Users\pc\.m2\repository\org\yaml\snakeyaml\1.23\snakeyaml-1.23.jar;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot-starter-json\2.1.6.RELEASE\spring-boot-starter-json-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.9\jackson-databind-2.9.9.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.9\jackson-core-2.9.9.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.9\jackson-datatype-jdk8-2.9.9.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.9\jackson-datatype-jsr310-2.9.9.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.9\jackson-module-parameter-names-2.9.9.jar;C:\Users\pc\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\2.1.6.RELEASE\spring-boot-starter-tomcat-2.1.6.RELEASE.jar;C:\Users\pc\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.21\tomcat-embed-core-9.0.21.jar;C:\Users\pc\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\9.0.21\tomcat-embed-el-9.0.21.jar;C:\Users\pc\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.21\tomcat-embed-websocket-9.0.21.jar;C:\Users\pc\.m2\repository\org\hibernate\validator\hibernate-validator\6.0.17.Final\hibernate-validator-6.0.17.Final.jar;C:\Users\pc\.m2\repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;C:\Users\pc\.m2\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;C:\Users\pc\.m2\repository\com\fasterxml\classmate\1.4.0\classmate-1.4.0.jar;C:\Users\pc\.m2\repository\org\springframework\spring-web\5.1.8.RELEASE\spring-web-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\spring-beans\5.1.8.RELEASE\spring-beans-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\spring-webmvc\5.1.8.RELEASE\spring-webmvc-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\spring-aop\5.1.8.RELEASE\spring-aop-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\spring-context\5.1.8.RELEASE\spring-context-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\spring-expression\5.1.8.RELEASE\spring-expression-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\slf4j\slf4j-api\1.7.26\slf4j-api-1.7.26.jar;C:\Users\pc\.m2\repository\org\springframework\spring-core\5.1.8.RELEASE\spring-core-5.1.8.RELEASE.jar;C:\Users\pc\.m2\repository\org\springframework\spring-jcl\5.1.8.RELEASE\spring-jcl-5.1.8.RELEASE.jar" Atomic.AtomicIntegerFieldUpdaterTest
 * Thread-0 对对应的值做了修改！
 * 180
 * Thread-0 对对应的值做了修改！
 * 200
 * Thread-0 对对应的值做了修改！
 * 220
 * Thread-0 对对应的值做了修改！
 * 240
 * Thread-0 对对应的值做了修改！
 * 260
 * Thread-0 对对应的值做了修改！
 * 280
 * Thread-0 对对应的值做了修改！
 * 300
 * Thread-0 对对应的值做了修改！
 * 320
 * Thread-0 对对应的值做了修改！
 * 340
 * Thread-0 对对应的值做了修改！
 * 360
 * Thread-0 对对应的值做了修改！
 * 380
 * Thread-0 对对应的值做了修改！
 * 400
 * Thread-0 对对应的值做了修改！
 * 420
 * Thread-0 对对应的值做了修改！
 * 440
 * Thread-0 对对应的值做了修改！
 * 460
 * Thread-0 对对应的值做了修改！
 * 480
 * Thread-0 对对应的值做了修改！
 * 500
 * Thread-0 对对应的值做了修改！
 * 520
 * Thread-0 对对应的值做了修改！
 * 540
 * Thread-0 对对应的值做了修改！
 * 560
 * Thread-0 对对应的值做了修改！
 * 580
 * Thread-0 对对应的值做了修改！
 * 600
 * Thread-0 对对应的值做了修改！
 * 620
 * Thread-0 对对应的值做了修改！
 * 640
 * Thread-0 对对应的值做了修改！
 * 660
 * Thread-0 对对应的值做了修改！
 * 680
 * Thread-0 对对应的值做了修改！
 * 700
 * Thread-0 对对应的值做了修改！
 * 720
 * Thread-0 对对应的值做了修改！
 * 740
 * Thread-0 对对应的值做了修改！
 * 760
 * Thread-0 对对应的值做了修改！
 * 780
 * Thread-0 对对应的值做了修改！
 * 800
 * Thread-0 对对应的值做了修改！
 * 820
 * Thread-3 对对应的值做了修改！
 * Thread-2 对对应的值做了修改！
 * 820
 * Thread-1 对对应的值做了修改！
 * 820
 * 820
 *
 * 由上我们可以推测出 为什么从180开始加 因为在休眠1s以后 在短时间内有四个线程先后挤进了while函数并且执行了第一条+20语句
 *
 * 后来发生了什么呢 thread-0强势占用了所有时间片 一直进行下去 循环到直接结束
 *
 * 而另外三个线程只能停止在while循环中第一条语句后面 等待时间片的到来
 * ... ...
 *  等终于到来的时候 它们发现 变量已经最大了 没办法 只能输出被thread-0最后一次修改的变量值 这也说明多线程之间共享着这个变量
 *  （话说回来为什么array里的index变量没有共享 肯定是定义有区别 想办法瞅瞅！！！！！）
 *
 *  我知道了 肯定是关键字volatile的关系，别忘了它的作用 “使多线程不缓存此变量 取修改后的最新值”
 *
 * 其他的线程在这几个强势线程之后 根本没进while循环 默默按抢占顺序输出最后一句话就end了。
 *
 *下面再给一个参考 这个更妙 但不违背上述所说
 *
 * Thread-0 对对应的值做了修改！
 * 120
 * Thread-0 对对应的值做了修改！
 * 140
 * Thread-0 对对应的值做了修改！
 * 160
 * Thread-0 对对应的值做了修改！
 * 180
 * Thread-0 对对应的值做了修改！
 * 200
 * Thread-0 对对应的值做了修改！
 * 220
 * Thread-0 对对应的值做了修改！
 * 240
 * Thread-0 对对应的值做了修改！
 * 260
 * Thread-0 对对应的值做了修改！
 * 280
 * Thread-0 对对应的值做了修改！
 * 300
 * Thread-0 对对应的值做了修改！
 * 320
 * Thread-0 对对应的值做了修改！
 * 340
 * Thread-0 对对应的值做了修改！
 * 360
 * Thread-0 对对应的值做了修改！
 * 380
 * Thread-0 对对应的值做了修改！
 * 400
 * Thread-0 对对应的值做了修改！
 * 440
 * Thread-0 对对应的值做了修改！
 * 480
 * Thread-1 对对应的值做了修改！
 * 480
 * Thread-1 对对应的值做了修改！
 * 520
 * Thread-1 对对应的值做了修改！
 * 540
 * Thread-1 对对应的值做了修改！
 * 560
 * Thread-1 对对应的值做了修改！
 * 580
 * Thread-1 对对应的值做了修改！
 * 600
 * Thread-1 对对应的值做了修改！
 * 620
 * Thread-1 对对应的值做了修改！
 * 640
 * Thread-1 对对应的值做了修改！
 * 660
 * Thread-1 对对应的值做了修改！
 * 680
 * Thread-1 对对应的值做了修改！
 * 700
 * Thread-1 对对应的值做了修改！
 * 720
 * Thread-1 对对应的值做了修改！
 * 740
 * Thread-1 对对应的值做了修改！
 * 760
 * Thread-1 对对应的值做了修改！
 * 780
 * Thread-1 对对应的值做了修改！
 * 800
 * Thread-1 对对应的值做了修改！
 * 820
 * 当前线程是：Thread-1
 * Thread-2 对对应的值做了修改！
 * 820
 * 当前线程是：Thread-2
 * Thread-0 对对应的值做了修改！
 * 820
 * 当前线程是：Thread-0
 * 当前线程是：Thread-7
 * 当前线程是：Thread-6
 * 当前线程是：Thread-5
 * 当前线程是：Thread-4
 * 当前线程是：Thread-3
 * 当前线程是：Thread-9
 * 当前线程是：Thread-8
 */