package com.telran.libraryapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telran.libraryapp.entity.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    private Long tagId;

    @NotNull(message = "{validation.tag.name}")
    @Length(max = 45, message = "{validation.tag.name}")

    private String name;

    private String description;

    private List<Book> books;
}
