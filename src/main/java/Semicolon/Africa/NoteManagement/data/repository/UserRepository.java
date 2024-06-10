package Semicolon.Africa.NoteManagement.data.repository;

import Semicolon.Africa.NoteManagement.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String userId);

    boolean existsByUsername(String username);
}
