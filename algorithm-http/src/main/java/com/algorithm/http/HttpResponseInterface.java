package com.algorithm.http;

@FunctionalInterface
public interface HttpResponseInterface<R,T> {
    <T>  T getResponseMsg(R r);
}
