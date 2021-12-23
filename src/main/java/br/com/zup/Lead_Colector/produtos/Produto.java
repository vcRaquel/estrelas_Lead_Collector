package br.com.zup.Lead_Colector.produtos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id //já garante que será único e não null
    @GeneratedValue(strategy = GenerationType.IDENTITY)//corresponde ao auto_increment
    private int id;
    @Column(unique = true, nullable = false)//o nome será único na regra de negócio
    private String nome;

    //não colocou construtor
    // pois quando não declara nenhum construtor, por padrão o java considera um construtor vazio

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
