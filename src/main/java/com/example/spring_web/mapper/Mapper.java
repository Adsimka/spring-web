package com.example.spring_web.mapper;

public interface Mapper<A, B> {

    B map(A entity);
}
