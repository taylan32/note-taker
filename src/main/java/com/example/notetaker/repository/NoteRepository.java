package com.example.notetaker.repository;

import com.example.notetaker.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByTitle(String title);
    Page<Note> getByTitleContainingIgnoreCase(String title, Pageable pageable);

}
