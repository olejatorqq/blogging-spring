package com.example.bloggingsite.repos;

import com.example.bloggingsite.domain.Message;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


// Needs for realize MessageRepo interface (for skip Error)
// Field messageRepo in ... required a bean of type ... that could not be found.
@Component
public class RealizeMessageRepo implements MessageRepo{
    @Override
    public List<Message> findByTag(String tag) {
        return null;
    }

    @Override
    public <S extends Message> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Message> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Message> findAll() {
        return null;
    }

    @Override
    public Iterable<Message> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Message entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Message> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
