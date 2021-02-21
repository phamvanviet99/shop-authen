package com.shop.validate;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Validator<T> {
    private T value;

    private Validator(T value) {
        this.value = value;
    }

    public static <T> Validator<T> of(T value){
        return new Validator(value);
    }

    public <U> Validator<T> validate(Function<T, U> transform, Predicate<U> condition, Supplier<RuntimeException> exception){
        if (condition.test(transform.apply(value))){
            throw exception.get();
        }
        return this;
    }

    public T get(){
        return value;
    }

}
