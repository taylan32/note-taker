package com.example.nodetaker.dto.request.note;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateNoteRequest {
    @NotBlank(message = "Title is not allowed to be empty")
    @Size(max = 50, message = "Title can have at most 50 characters")
    private String title;

    @NotBlank(message = "Description is not allowed to be empty")
    @Size(max = 4000, message= "Description can have at most 4000 characters")
    private String description;
}
