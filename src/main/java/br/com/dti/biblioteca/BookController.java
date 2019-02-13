package br.com.dti.biblioteca;

import br.com.dti.biblioteca.models.InlineResponse200;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.dti.biblioteca.models.Book;
import br.com.dti.biblioteca.controllers.BookApi;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookController implements BookApi {

    private LibRepository libRepository;

    @Override
    public ResponseEntity<InlineResponse200> addBook(@Valid Book body) {
        try {
            Book livro = libRepository.addLivro(body.getTitle(), body.getAuthor(), body.getDesc());
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
            Book livro = libRepository.findLivro(bookId);
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName("Busca (ID: "+livro.getId()+") Livro: " + livro.getTitle());
            return new ResponseEntity<InlineResponse200>(resposta, HttpStatus.OK);
        }catch(Exception e) {
            InlineResponse200 resposta = new InlineResponse200();
            resposta.setBookName(e.getMessage());
            return new ResponseEntity<InlineResponse200>(resposta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
