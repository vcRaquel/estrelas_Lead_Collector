package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.customExceptions.LeadEProdutoJaCadastradosException;
import br.com.zup.Lead_Colector.produtos.Produto;
import br.com.zup.Lead_Colector.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public Lead salvarLead(Lead lead) {
        List<Produto>produtos = buscarProdutos(lead.getProdutosDeInteresse());
        //colocando a lista de produtos atualizada no lead
        lead.setProdutosDeInteresse(produtos);
        //salvando o lead no banco de dados
        return leadRepository.save(lead);
    }

    private List<Produto> buscarProdutos(List<Produto> produtos) {
        //instancia uma lista que irá armazenar os produtos atualizados(que já constavam no banco de dados)
        // e os cadastrados
        List<Produto>listaAtualizada = new ArrayList<>();
        for (Produto produto : produtos) {
            //vai consultar no banco (repository) se existe algum produto com um dos nomes da lista
            // até que toda a lista tenha sido percorrida
            if (produtoRepository.existsByNome(produto.getNome())) {
//                //se já estiver cadastrado, tira o produto que só tem o nome:
//                produtos.remove(produto); //desistiu dessa abordagem pois ao excluir dentro de um for
//                altera o tamanho da lista (posições) enquanto ela está sendo percorrida gerando uma exception

                // adiciona na lista instanciada acima o objeto do banco de dados referente a esse nome com todas as informações
                listaAtualizada.add(produtoRepository.findByNome(produto.getNome()));
            }else{
                listaAtualizada.add(produto);
            }
        }
        return listaAtualizada;
    }
}
