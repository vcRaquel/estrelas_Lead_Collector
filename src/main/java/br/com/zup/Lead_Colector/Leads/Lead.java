package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.produtos.Produto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    private String email;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'Não informado'")
    //irá dizer o tipo e tamanho da coluna na tabela do BD
    //o default irá atribuir a frase e não com null caso não seja preenchido
    private String nome;
    //deu erro no primeiro run pq não tinha a anotação do tipo de relacionamento dessa lista nessa entidade
    @ManyToMany
    private List<Produto> produtosDeInteresse;

    //não fez construtor, pois por padrão já tem um construtor vazio

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutosDeInteresse() {
        return produtosDeInteresse;
    }

    public void setProdutosDeInteresse(List<Produto> produtosDeInteresse) {
        this.produtosDeInteresse = produtosDeInteresse;
    }
}
