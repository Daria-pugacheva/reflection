import ru.gb.pugacheva.lesson7reflection.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

public class ClassForTesting{

    public static void main(String[] args) {
        try {
            start(ClassWithTestMethods.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class testClass) throws IllegalAccessException, InstantiationException,InvocationTargetException {
        if (!testClass.isAnnotationPresent(ClassWithTests.class)) {
            throw new IllegalArgumentException("Класс не содержит тесты");
        }

        Object obj = testClass.newInstance(); // полагаю, что при первой попытке так сделать меня смутил этот перечерк...
        //Object obj = testClass.getDeclaredConstructor().newInstance(); - а в таком варианте newInstance() нормально выглядит.
        int numberBeforeSuit = 0;
        int numberAfterSuit =0;
        Set<Integer> prioritiesSet = new TreeSet<>();
        Method[] methods = testClass.getDeclaredMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                prioritiesSet.add(m.getDeclaredAnnotation(Test.class).priority());
            }
            if(m.isAnnotationPresent(BeforeSuite.class)){
                numberBeforeSuit++;
            }
            if(m.isAnnotationPresent(AfterSuite.class)){
                numberAfterSuit++;
            }
        }

        if (numberBeforeSuit > 1 || numberAfterSuit >1){
            throw new RuntimeException("Количество методов с аннотациями BeforeSuit и AfterSuit не " +
                    "может быть больше одного");
        } // Несложно разбить на отдельные проверки для каждого варианта отдельно. Но тут не вижу смысла.

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                m.invoke(obj);
            }
        }

        for (Integer n: prioritiesSet){
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    if(m.getDeclaredAnnotation(Test.class).priority() == n)
                    m.invoke(obj);
                }
            }
        }

        for (Method m: methods){
            if (m.isAnnotationPresent(AfterSuite.class)){
                m.invoke(obj);
            }
        }
    }

}



