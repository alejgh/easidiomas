package com.easidiomas.usersservice.persistence;

import com.easidiomas.usersservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends Neo4jRepository<User, Long> {

    User findByUsername(String username);
}
