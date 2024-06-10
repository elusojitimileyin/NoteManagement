package Semicolon.Africa.NoteManagement.data.repository;

import Semicolon.Africa.NoteManagement.data.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {

    boolean existsByTitleAndUsername(String title, String username);

    Note findByTitle(String title);
}
