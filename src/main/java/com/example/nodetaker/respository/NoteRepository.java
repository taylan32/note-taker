package com.example.nodetaker.respository;

import com.example.nodetaker.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByTitle(String title);
    Page<Note> getByTitleContainingIgnoreCase(String title, Pageable pageable);

}
