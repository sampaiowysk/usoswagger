package br.com.dti.biblioteca;

import br.com.dti.biblioteca.models.Book;
import br.com.dti.biblioteca.models.InlineResponse200;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.dti.biblioteca.controllers.LibApi;
import br.com.dti.biblioteca.controllers.BookApi;
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
    public ResponseEntity<InlineResponse200> createNewLib() {
        try {
            bib = new Lib();
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName("Biblioteca Criada");
            return new ResponseEntity<InlineResponse200>(resposta, HttpStatus.OK);
        }catch(Exception e) {
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName(e.getMessage());
            return new ResponseEntity<InlineResponse200>(resposta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<InlineResponse200>> printLib() {
        try {
            List<String> livros = bib.imprimeLivros();
            InlineResponse200 resposta = new InlineResponse200();
            List<InlineResponse200> retorno = new ArrayList<InlineResponse200>();
            for(String livro: livros) {
                resposta.setBookName(livro);
                retorno.add(resposta);
            }
            return new ResponseEntity<List<InlineResponse200>>(retorno, HttpStatus.OK);
        }catch(Exception e) {
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName(e.getMessage());
            List<InlineResponse200> retorno = new ArrayList<InlineResponse200>();
            retorno.add(resposta);
            return new ResponseEntity<List<InlineResponse200>>(retorno,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<InlineResponse200> addBook(@Valid Book body) {
        try {
            Book livro = bib.addLivro(body.getTitle(), body.getAuthor(), body.getDesc());
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName("Livro Criado: ID - " + livro.getId());
            return new ResponseEntity<InlineResponse200>(resposta, HttpStatus.OK);
        }catch(Exception e) {
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName(e.getMessage());
            return new ResponseEntity<InlineResponse200>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<InlineResponse200> findBook(Long bookId) {
        try {
            Book livro = bib.findLivro(bookId);
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName("Busca (ID: "+livro.getId()+") Livro: " + livro.getTitle());
            return new ResponseEntity<InlineResponse200>(resposta, HttpStatus.OK);
        }catch(Exception e) {
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName(e.getMessage());
            return new ResponseEntity<InlineResponse200>(resposta,HttpStatus.INTERNAL_SERVER_ERROR);
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
