package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.customExceptions.LeadEProdutoJaCadastradosException;
import br.com.zup.Lead_Colector.produtos.Produto;
import br.com.zup.Lead_Colector.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public Lead salvarLead(Lead lead) {
        //pegar a lista de produtos que já estão cadastrados
        // e juntar com os produtos que estão sendo recebidos na requisição
        List<Produto> produtos = buscarProdutos(lead.getProdutosDeInteresse());

        //pegar o lead (pelo email) que está no banco de dados com a lista que já existe e só adicionar essa lista
        Optional<Lead> leadOptional = leadRepository.findById(lead.getEmail());

        //se o Lead com o email informado na requisição estiver presente no banco de dados
        // (e consequentemente dentro do optional)
        if (leadOptional.isPresent()){

            //tirando o Lead que veio do banco de dentro do optional para ficar mais visível e didático:
            Lead leadDoBanco = leadOptional.get();

            //um for que irá percorrer a lista de produtos adicionando apenas os produtos novos
            for (Produto produto : produtos){

                //se nos produtos do lead do banco NÃO (!) contiver o produto
                if (!leadDoBanco.getProdutosDeInteresse().contains(produto)){
                    //adiciona o produto dentro da lista
                    leadDoBanco.getProdutosDeInteresse().add(produto);
                }
            }

            //irá salvar o lead do banco com a nova lista de produtos
            return leadRepository.save(leadDoBanco);
        }
        //se o Lead com o email informado na requisição NÂO estiver presente no banco de dados:
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

    public List<Lead>buscarTodosPeloNomeDoProduto(String nomeProduto){
        return leadRepository.findAllByProdutosDeInteresseNome(nomeProduto);
    }
}
