package com.example.notetaker.dto.request.note;

import lombok.Data;


@Data
public class UpdateNoteRequest {

    // TODO: add validation
    private Long id;
    private String title;
    private String description;
}
