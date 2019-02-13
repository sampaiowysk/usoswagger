package br.com.dti.biblioteca;

import br.com.dti.biblioteca.models.Book;

import java.util.List;

public interface LibRepository {

    public Book addLivro(String title, String autor, String desc);
    public Book addLivro();
    public Book removeLivro(long i);
    public List<String> imprimeLivros();
    public Book findLivro(long i);

}
