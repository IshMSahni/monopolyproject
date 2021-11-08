package test.monopoly.util;

import java.lang.reflect.Method;

public class Reflection {

    public static Method get_method(Class clazz, String method_name) {
        for(Method m: clazz.getDeclaredMethods()){
            if(m.getName().equals(method_name)){
                m.setAccessible(true);
                return m;
            }
        }
        return null;
    }

}
