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

    Lib bib;

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
        try {
            List<String> livros = bib.imprimeLivros();
            return new ResponseEntity<>(livros, HttpStatus.OK);
        }catch(Exception e) {
            String resposta =e.getMessage();
            List<String> retorno = new ArrayList<>();
            retorno.add(resposta);
            return new ResponseEntity<>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> addBook(@Valid Book body) {
        try {
            Book livro = bib.addLivro(body.getTitle(), body.getAuthor(), body.getDesc());
            String resposta ="Livro Criado: ID - " + livro.getId();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }catch(Exception e) {
            String resposta = e.getMessage();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> findBook(Long bookId) {
        try {
            Book livro = bib.findLivro(bookId);
            String resposta = "Busca (ID: "+livro.getId()+") Livro: " + livro.getTitle();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }catch(Exception e) {
            String resposta = e.getMessage();
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
