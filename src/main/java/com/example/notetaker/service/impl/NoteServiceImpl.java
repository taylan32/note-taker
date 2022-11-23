package com.example.notetaker.service.impl;

import com.example.notetaker.dto.request.note.CreateNoteRequest;
import com.example.notetaker.dto.request.note.UpdateNoteRequest;
import com.example.notetaker.dto.response.note.NoteResponseDto;
import com.example.notetaker.entity.Note;
import com.example.notetaker.exception.GenericException;
import com.example.notetaker.repository.NoteRepository;
import com.example.notetaker.service.NoteService;
import com.example.notetaker.utils.BasePageableModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteResponseDto add(CreateNoteRequest request) {

        Note mappedNote = this.modelMapper.map(request, Note.class);
        Note savedNote = this.noteRepository.save(mappedNote);
        return this.modelMapper.map(savedNote, NoteResponseDto.class);

    }

    @Override
    @Transactional
    public NoteResponseDto update(UpdateNoteRequest request) {
        Note note = getNoteById(request.getId());
        Note noteToUpdate = this.modelMapper.map(request, Note.class);
        noteToUpdate.setCreatedAt(note.getCreatedAt());
        Note updatedNote = this.noteRepository.save(noteToUpdate);
        return this.modelMapper.map(updatedNote, NoteResponseDto.class);
    }

    @Override
    public NoteResponseDto getById(Long id) {
        Note note = getNoteById(id);
        return this.modelMapper.map(note, NoteResponseDto.class);
    }

    @Override
    public BasePageableModel<NoteResponseDto> getAll(int pageNumber, int pageSize) {
        Page<Note> data = this.noteRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        BasePageableModel<NoteResponseDto> response = new BasePageableModel<>();
        response.setStat(data, Arrays.asList(this.modelMapper.map(data.getContent(), NoteResponseDto[].class)));
        return response;
    }

    @Override
    public NoteResponseDto deleteNote(Long id) {
        Note note = getNoteById(id);
        this.noteRepository.delete(note);
        return this.modelMapper.map(note, NoteResponseDto.class);
    }

    @Override
    public NoteResponseDto getByTitle(String title) {
        Note note = this.noteRepository.findByTitle(title).orElseThrow(() -> GenericException.builder()
                .message(String.format("Note not found with name %s",title))
                .date(new Date())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
        return this.modelMapper.map(note, NoteResponseDto.class);
    }

    @Override
    public BasePageableModel<NoteResponseDto> getByTitleContaining(String title, int pageNumber, int pageSize) {
        Page<Note> data = this.noteRepository.getByTitleContainingIgnoreCase(title, PageRequest.of(pageNumber -1, pageSize));
        BasePageableModel<NoteResponseDto> response = new BasePageableModel<>();
        response.setStat(data, Arrays.asList(this.modelMapper.map(data.getContent(), NoteResponseDto[].class)));
        return response;
    }

    private Note getNoteById(Long id) {
        return this.noteRepository.findById(id).orElseThrow(() -> GenericException.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .date(new Date())
                .message(String.format("Requested note not found with id=%d",id))
                .build());
    }

}
