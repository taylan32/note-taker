package com.example.notetaker.service;

import com.example.notetaker.dto.request.note.CreateNoteRequest;
import com.example.notetaker.dto.request.note.UpdateNoteRequest;
import com.example.notetaker.dto.response.note.NoteResponseDto;
import com.example.notetaker.utils.BasePageableModel;

public interface NoteService {

    NoteResponseDto add(CreateNoteRequest request);
    NoteResponseDto update(UpdateNoteRequest request);

    NoteResponseDto getById(Long id);
    BasePageableModel<NoteResponseDto> getAll(int pageNumber, int pageSize);
    NoteResponseDto deleteNote(Long id);

    NoteResponseDto getByTitle(String title);

    BasePageableModel<NoteResponseDto> getByTitleContaining(String title, int pageNuber, int pageSize);


}
