package com.solution.ntq.repository;


import com.solution.ntq.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends org.springframework.data.repository.Repository<User, String> {
    void save(User user);

    User findById(String id );

    boolean existsById(String id);


}
