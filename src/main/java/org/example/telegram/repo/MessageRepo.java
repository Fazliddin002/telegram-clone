package org.example.telegram.repo;

import org.example.telegram.entity.Message;
import org.example.telegram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {

  List<Message> getByFromAndTo(User from, User to);

}
