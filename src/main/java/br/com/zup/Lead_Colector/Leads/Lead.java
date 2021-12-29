package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.produtos.Produto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'Não informado'")
    //irá dizer o tipo e tamanho da coluna na tabela do BD
    //o default irá atribuir a frase e não com null caso não seja preenchido
    private String nome;
    //deu erro no primeiro run pq não tinha a anotação do tipo de relacionamento dessa lista nessa entidade
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //foi acrescentado o cascade Merge para que o nome do produto fosse enviado para o banco de dados
    //o Persist vai salvar no banco de dados um relacionamento entre as duas tabelas
    //o Merge irá receber o produto e salvar o produto na tabela (mescla de informações)
    //persist pq o all é perigoso pois força mudanças não desejadas
    // só pode ser usado quando se tem muita certeza do que está fazendo
    //avalie e inclua de acordo com a sua necessidade
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produto> getProdutosDeInteresse() {
        return produtosDeInteresse;
    }

    public void setProdutosDeInteresse(List<Produto> produtosDeInteresse) {
        this.produtosDeInteresse = produtosDeInteresse;
    }

}
