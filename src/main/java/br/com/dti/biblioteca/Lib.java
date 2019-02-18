package br.com.dti.biblioteca;

import br.com.dti.biblioteca.models.Book;

import java.util.ArrayList;
import java.util.List;

public class Lib {
    List<Book> biblioteca;
    private static long id;

    public Lib() {
        id=0;
        Book Catalogo = new Book();
        Catalogo.setAuthor("GSA");
        Catalogo.setDesc("Testejava");
        Catalogo.setId(id);
        Catalogo.setTitle("Catalogo");
        biblioteca = new ArrayList<>();
        biblioteca.add(Catalogo);
        id++;
    }

    public Book addLivro(String title, String autor, String desc) {
        Book livro = new Book();
        livro.setAuthor(autor);
        livro.setDesc(desc);
        livro.setId(id);
        livro.setTitle(title);
        biblioteca.add(livro);
        id++;
        return livro;
    }

    public Book addLivro() {
        Book livro = new Book();
        livro.setId(id);
        biblioteca.add(livro);
        id++;
        return livro;
    }

    public Book removeLivro(long i) {
        Book livro = findLivro(i);
        biblioteca.remove(livro);
        return livro;
    }

    public List<String> imprimeLivros() {
        List<String> livros = new ArrayList<>();
        for(Book livro: biblioteca) {
            livros.add("ID: "+livro.getId()+"; Titulo: "+livro.getTitle()+"; Autor: "+livro.getAuthor()+";");
        }
        return livros;
    }

    public Book findLivro(long i) {
        Book l = new Book();
        l.setId(i);
        for(Book livro: biblioteca) {
            if(livro.getId()==i) {
                l = livro;
            }
        }
        return l;
    }

}