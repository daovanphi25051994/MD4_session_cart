package com.codegym.service;

public interface IService<T> {
    Iterable<T> getAll();
    T save(T model);
}
