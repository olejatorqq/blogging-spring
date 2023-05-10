package com.example.bloggingsite.repos;

import com.example.bloggingsite.domain.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
