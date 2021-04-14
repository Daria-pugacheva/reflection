package ru.gb.pugacheva.lesson7reflection;

@ClassWithTests
public class ClassWithTestMethods {

    public ClassWithTestMethods() {
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println(1);
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println(2);
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println(3);
    }

    @Test(priority = 2)
    public void test4() {
        System.out.println(22);
    }

    @Test(priority = 10)
    public void test10() {
        System.out.println(10);
    }

    @Test(priority = 5)
    public void test5() {
        System.out.println(5);
    }

    @Test(priority = 8)
    public void test8() {
        System.out.println(8);
    }

    @Test(priority = 6)
    public void test6() {
        System.out.println(6);
    }

    @Test(priority = 6)
    public void test7() {
        System.out.println(66);
    }


}
