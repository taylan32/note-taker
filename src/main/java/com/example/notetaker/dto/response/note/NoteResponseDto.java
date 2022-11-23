package com.example.notetaker.dto.response.note;

import lombok.Data;

@Data
public class NoteResponseDto {
    private Long id;
    private String title;
    private String description;
}
