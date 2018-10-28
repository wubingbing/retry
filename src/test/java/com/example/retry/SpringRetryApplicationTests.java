package com.example.retry;

import com.example.retry.functionInterface.WorkerInterfaceTest;
import com.sun.istack.internal.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.SystemException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static org.springframework.test.context.transaction.TestTransaction.start;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRetryApplicationTests {

    Logger logger = Logger.getLogger(SpringRetryApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    @Test
    public void testArrayList() {
        ArrayList list = new ArrayList();

        list.add(new String("aaa"));
        list.add(new String("bbb"));

        list.stream().forEach(a -> {
            logger.info("good " + a.toString());
        });

        String ccc = (String) list.set(1, new String("ccc"));

        logger.info("xi  " + ccc);

        list.stream().forEach(a -> {
            logger.info(a.toString());
        });

    }

    /**
     * 上述代码调用forEach()方法，并使用匿名内部类实现Comsumer接口
     * 能执行lamdba 表达式是调用内部类实现
     * 带有@FunctionalInterface标示的接口
     */
    @Test
    public void testFunctionArrayList() {
        ArrayList<String> list = new ArrayList(Arrays.asList("I", "love", "you", "too"));
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String str) {
                if (str.length() > 3)
                    System.out.println(str);
            }
        });
    }

    @Test
    public void testNullObjectException() {
        ArrayList<String> list = new ArrayList(Arrays.asList("22", "333", "5555"));
        ArrayList<String> arrayList = new ArrayList();

        list.forEach(str -> {
            if (str.length() > 3)
                arrayList.add(str);
        });

        list.forEach(str -> System.out.println("xixi"+str));


    }


    @Test
    public void testRemoveIf() {

        ArrayList<String> list = new ArrayList(Arrays.asList("22", "333", "5555"));


        list.removeIf(str ->

                str.length() > 3
        );

        logger.info("xii" + list.size());
    }


    @Test
    public void testThread() {
        String variable = "Outer Method Variable";
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("->" + variable);

            }
        }).start();

        new Thread(() -> {
        }).start();
    }


    @Test
    public void testFunctionInterface() {
        String varibale = "goood";

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    @Test
    public void testWorkSpace() {

        new Thread(() -> {

            for (int i = 0; i < 3; i++) {

                System.out.println("xiixi");
            }
        }).start();


    }

    @Test
    public void testFunctionTwo() {

        ArrayList<Integer> list = new ArrayList();
        WorkerInterfaceTest.execute(() -> {
            for (int i = 0; i < 3; i++) {

                list.add(i);
            }
        });


        for (Integer s : list
        ) {
            System.out.println("giiid"+s);

        }

    }


    @Test
    public void testDefault(){

        Consumer<Integer> consumer = (x) -> {
            int num = x * 2;
            System.out.println("xiix"+num);
        };
        Consumer<Integer> consumer1 = (x) -> {
            int num = x * 3;
            System.out.println("haha"+num);
        };


        consumer.andThen(consumer1);


    }


}
