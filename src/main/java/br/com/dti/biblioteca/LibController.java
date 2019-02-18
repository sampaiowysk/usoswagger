package br.com.dti.biblioteca;

import br.com.dti.biblioteca.controllers.BookApi;
import br.com.dti.biblioteca.controllers.LibApi;
import br.com.dti.biblioteca.models.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LibController implements LibApi, BookApi {

    private Lib bib;

    @Override
    public ResponseEntity<String> createNewLib() {
        String resposta;
        HttpStatus responseCode;
        try {
            bib = new Lib();
            resposta = "Biblioteca Criada";
            responseCode = HttpStatus.OK;
        }catch(Exception e) {
            resposta = e.getMessage();
            responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resposta, responseCode);
    }

    @Override
    public ResponseEntity<List<String>> printLib() {
        List<String> resposta;
        HttpStatus responseCode;
        try {
            resposta = bib.imprimeLivros();
            responseCode = HttpStatus.OK;
        }catch(Exception e) {
            resposta = new ArrayList<>();
            resposta.add(e.getMessage());
            responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resposta, responseCode);
    }

    @Override
    public ResponseEntity<String> addBook(@Valid Book body) {
        String resposta;
        HttpStatus responseCode;
        try {
            Book livro = bib.addLivro(body.getTitle(), body.getAuthor(), body.getDesc());
            resposta = "Livro Criado: ID - " + livro.getId();
            responseCode = HttpStatus.OK;
        }catch(Exception e) {
            resposta = e.getMessage();
            responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resposta, responseCode);
    }

    @Override
    public ResponseEntity<String> findBook(Long bookId) {
        String resposta;
        HttpStatus responseCode;
        try {
            Book livro = bib.findLivro(bookId);
            resposta = "Busca (ID: " + livro.getId() + ") Livro: " + livro.getTitle();
            responseCode = HttpStatus.OK;
        }catch(Exception e) {
            resposta = e.getMessage();
            responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resposta, responseCode);
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getAcceptHeader() {
        return Optional.empty();
    }
}
