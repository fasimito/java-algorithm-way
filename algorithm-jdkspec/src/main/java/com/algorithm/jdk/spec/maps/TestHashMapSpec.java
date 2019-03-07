package com.algorithm.jdk.spec.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestHashMapSpec {
    String name;
    public TestHashMapSpec(String name){
        this.name = name;
    }

    /**
     * start override
     */
    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        TestHashMapSpec ts = (TestHashMapSpec)o;
        return Objects.equals(name,ts.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * end override
     * /


     /**
     * if the equals and hashCode method had never override, the main would print: null
     * 从某个角度说，这两个对象是一样的，因为名称一样，name 属性都是 hello，当我们使用这个 key 时，
     * 按照逻辑，应该返回 hello 给我们。但是，由于没有重写 hashcode 方法，JDK 默认使用 Objective 类的
     * hashcode 方法，返回的是一个虚拟内存地址，而每个对象的虚拟地址都是不同的，所以，这个肯定不会返回  hello 。
     * 只有通过重写equals和hashCode方法
     * @param args
     */
    public static void  main(String[] args){
        Map<TestHashMapSpec,String> mapSpecStringMap = new HashMap<>(4);
        mapSpecStringMap.put(new TestHashMapSpec("hello"),"world");
        String world = mapSpecStringMap.get(new TestHashMapSpec("hello"));
        System.out.println(world);
    }
}
