package com.example.nodetaker.service;

import com.example.nodetaker.dto.request.note.CreateNoteRequest;
import com.example.nodetaker.dto.request.note.UpdateNoteRequest;
import com.example.nodetaker.dto.response.note.NoteResponseDto;
import com.example.nodetaker.utils.BasePageableModel;

import java.util.List;

public interface NoteService {

    NoteResponseDto add(CreateNoteRequest request);
    NoteResponseDto update(UpdateNoteRequest request);

    NoteResponseDto getById(Long id);
    BasePageableModel<NoteResponseDto> getAll(int pageNumber, int pageSize);
    NoteResponseDto deleteNote(Long id);

    NoteResponseDto getByTitle(String title);

    BasePageableModel<NoteResponseDto> getByTitleContaining(String title, int pageNuber, int pageSize);


}
