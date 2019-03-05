package com.algorithm.graphic.dpf;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AlgorithmGraphicDpf {

    public void showStrings(String... strings){

        List<String> list = Arrays.asList(strings);

        int i=0;

        Arrays.stream(strings).forEach(p->{
            System.out.println(p);
        });

        Map<String,String> stringMap = list.stream().filter(p->p.startsWith("a")).
                collect(Collectors.toMap(p->p,p->p));

        System.out.println(stringMap);

    }

    public void sortStrings(String... strings){
        List<String> list = Arrays.asList(strings);
        Collections.sort(list,(str1,str2)->str1.compareTo(str2));
        list.forEach(p->{
            System.out.println(p);
        });
    }

    public void toLowercase(String... strings){
        List<String> list = Arrays.asList(strings);
        List<String> lowerlist = list.stream().map(name->{return name.toLowerCase();}).collect(Collectors.toList());
        System.out.println(lowerlist);
    }

    public void toUppercase(String... strings){
        List<? extends String> list = Arrays.asList(strings);
        List<? super String> upperlist = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperlist);
    }

    public void lambdaFunc(String... strings){
        String lambda = "lambda";
        List<? extends String> list = Arrays.asList(strings);
        List<? super String> lambdalist = list.stream().map(p->{
            long tm = System.currentTimeMillis();
            return lambda+":"+p+"-----"+tm;
        }).collect(Collectors.toList());
        lambdalist.forEach(System.out::println);
    }

    public void lambdaThis(String... strings){
        List<? extends String> list = Arrays.asList(strings);
        List<? super String> lambdalist = list.stream().map(p->{
            System.out.println(this.getClass().getName());
            return p.toUpperCase();
        }).collect(Collectors.toList());
        lambdalist.forEach(System.out::println);
    }

    public void lambdaCount(){
        List<Integer> integers = Lists.newArrayList(12,122,65,null,42);
        long counts = integers.stream().filter(p->p != null).limit(3).mapToLong(p->p+45).sum();
        System.out.println(counts);
    }

    public void testTransfer(){
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());

    }

    public void testReduce(){
        List<? extends Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        List<? super Integer> numsWithoutNull = nums.stream().filter(num -> num != null).distinct().
                collect(() -> new ArrayList<Integer>(),
                        (p, r) -> p.add(r),
                        (p, r) -> p.addAll(r));
        System.out.println(numsWithoutNull);
    }

    public static void main(String[] args){

        AlgorithmGraphicDpf dpf = new AlgorithmGraphicDpf();
        dpf.showStrings("abc","def","acds");
        dpf.sortStrings("abc","def","acds");
        dpf.toLowercase("AcsaD","EEDS","asldDDL");
        dpf.toUppercase("AcsaD","EEDS","asldDDL");
        dpf.lambdaFunc("hello","world");
        dpf.lambdaThis("hello","world");
        dpf.lambdaCount();
        dpf.testTransfer();
        dpf.testReduce();

    }
}
