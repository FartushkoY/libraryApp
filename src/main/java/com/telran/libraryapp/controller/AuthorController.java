package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.getNameAuthorByID(id);
        if (authorOptional.isPresent()) {
            return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody @Valid Author author) {
        authorService.add(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody @Valid Author author) {
        boolean isUpdated = authorService.updateAuthor(author);
        return new ResponseEntity<>(author, isUpdated ? HttpStatus.OK : HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<Author> deleteAuthor(@RequestParam Long id) {
        authorService.removeAuthor(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAuthorByName")
    public List<Author> getAuthorByNameOrSurname(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String surname) {
        return authorService.returnAuthorByNameOrSurname(name, surname);
    }

    @GetMapping("/findAuthorByRandomWord")
    public ResponseEntity<List<Author>> getAuthorByRandomWord(@RequestParam String randomWord) {
        List<Author> authors = authorService.getAuthorByRandomWord(randomWord);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
