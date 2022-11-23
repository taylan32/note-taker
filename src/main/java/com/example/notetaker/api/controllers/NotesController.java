package com.example.notetaker.api.controllers;

import com.example.notetaker.dto.request.note.CreateNoteRequest;
import com.example.notetaker.dto.request.note.UpdateNoteRequest;
import com.example.notetaker.dto.response.note.NoteResponseDto;
import com.example.notetaker.service.NoteService;
import com.example.notetaker.utils.BasePageableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/")
    public ResponseEntity<NoteResponseDto> add(@RequestBody @Valid CreateNoteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.noteService.add(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.noteService.getById(id));
    }
    @GetMapping("/")
    public ResponseEntity<BasePageableModel<NoteResponseDto>> getAll(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(this.noteService.getAll(pageNumber,pageSize));
    }

    @PutMapping("/update")
    public ResponseEntity<NoteResponseDto> update(@RequestBody @Valid UpdateNoteRequest request) {
        return ResponseEntity.ok(this.noteService.update(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.noteService.deleteNote(id));
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<NoteResponseDto> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(this.noteService.getByTitle(title));
    }
    @GetMapping("/getByTitleContaining")
    public ResponseEntity<BasePageableModel<NoteResponseDto>> getByTitleContaining(@RequestParam String title, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(this.noteService.getByTitleContaining(title, pageNumber,pageSize));
    }


}
