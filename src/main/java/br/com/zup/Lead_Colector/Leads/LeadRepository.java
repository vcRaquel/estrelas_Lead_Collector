package br.com.zup.Lead_Colector.Leads;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeadRepository extends CrudRepository<Lead, String> {
    //procurar no banco de dados todos os leads que tenham o
    // produto com o nome do produto na sua lista de produtosDeInteresse
    List<Lead>findAllByProdutosDeInteresseNome(String nomeDoProduto);
}
