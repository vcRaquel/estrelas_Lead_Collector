package br.com.zup.Lead_Colector.produtos;


import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    //vai verificar se o produto já existe no cadastro do banco de dados
    // irá pegar o nome do método e transformar em Query para o banco de dados
    //poderia ser feito com a anotação @Query("SELECT * FROM produtos WHERE nome =1%) ou usar :nome no lugar de =1%
    //=1% significa primeira posição
    // a Query acima retornaria o objeto inteiro e não apenas uma afirmação de se ele existe ou não
    boolean existsByNome(String nome);
    //a diferença entre um exists e find é que o retorno do primeiro é boolean e mais rápido
    // e do segundo o retorno é um objeto e mais demorado
    Produto findByNome(String Nome);

}
