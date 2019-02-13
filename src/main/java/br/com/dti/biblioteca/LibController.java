package br.com.dti.biblioteca;

import br.com.dti.biblioteca.models.InlineResponse200;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import br.com.dti.biblioteca.controllers.LibApi;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibController implements LibApi {

    Lib bib;

    @Override
    public ResponseEntity<InlineResponse200> createNewLib() {
        try {
            bib = new Lib();
            System.out.println("OI");
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
}
