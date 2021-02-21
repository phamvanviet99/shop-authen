package com.shop.repository;

import com.shop.paging.PageAble;

import java.util.Optional;
import java.util.stream.Stream;

public interface JpaRepository<T, ID> {
    T save(T t);

    void update(ID id, T t);

    Optional<T> findById(ID id);

    void delete(ID id);

    Stream<T> findAll();

    Stream<T> findAll(PageAble pageAble);

    long count();


}
