package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.customExceptions.LeadEProdutoJaCadastradosException;
import br.com.zup.Lead_Colector.produtos.Produto;
import br.com.zup.Lead_Colector.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public Lead salvarLead(Lead lead){
        verificarLeadEproduto(lead.getEmail(), lead.getProdutosDeInteresse());
        //poderia chamar o produtoRepository salvando primeiro o produto para depois salvar o Lead
        // ou com o cascade no relacionamento
        return leadRepository.save(lead);
    }

    public void verificarLeadEproduto(String emailLead, List<Produto>produtos){
        //conferir no banco(repository) se tem algum Lead com o email fornecido
        if (leadRepository.existsById(emailLead)){
            for (Produto produto : produtos){
                //vai consultar no banco (repository) se existe algum produto com um dos nomes da lista
                // até que toda a lista tenha sido percorrida
                if (produtoRepository.existsByNome(produto.getNome())){
                    //se já estiver cadastrado, estoura a exception.
                    // Problema: se qualquer um dos produtos for repetido,
                    // toda a lista será rejeitada e isso não é o ideal.
                    throw new LeadEProdutoJaCadastradosException("Lead e produto já cadastrados");
                }
            }
        }
    }
}
