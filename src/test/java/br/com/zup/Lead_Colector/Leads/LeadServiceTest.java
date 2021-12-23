package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.produtos.Produto;
import br.com.zup.Lead_Colector.produtos.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LeadServiceTest {
    // ??? o fato de ter duas injeções de dependência de repositorys diferentes
    // não fere o critério de responsabilidade única?
    // O adequado seria ter um método na service de produto que fizesse a ação pretendida na repository de produto?
    @MockBean
    LeadRepository leadRepository;
    @MockBean
    ProdutoRepository produtoRepository;

    //injeção de dependência da classe a ser testada para ter o objeto verdadeiro dela
    @Autowired
    LeadService leadService;

    //Atributos
    private Lead lead;
    private Produto produto;
    private List<Produto>produtos;

    //método pra ser executado antes dos outros métodos e criar as instâncias necessárias
    @BeforeEach
    public void setup(){
        lead = new Lead();
        lead.setNome("Arthur Dent");
        lead.setEmail("arthur.dent@dontpanic.com");

        produto = new Produto();
        produto.setNome("Chá");
        produto.setId(1);

        //Arrays.asList é usado para criar uma lista array já passando os itens da lista
        // e atribui ao lead por meio do setProdutosDeInteresse
        produtos = Arrays.asList(produto);
        lead.setProdutosDeInteresse(produtos);
    }

    @Test
    public void testarBuscarProdutosExistentesCaminhoPositivo(){
        Mockito.when(produtoRepository.existsByNome(Mockito.anyString())).thenReturn(true);
        Mockito.when(produtoRepository.findByNome(Mockito.anyString())).thenReturn(produto);

        List<Produto> listaAtualizada = leadService.buscarProdutos(produtos);

        for (Produto produtoDaListaAtualizada : listaAtualizada){
            //"esse produto que está na lista atualizada é o mesmo produto que ele retornou do repositório?
            // ou: "o produto que está vindo do repositório, tá entrando na lista?"
            Assertions.assertEquals(produtoDaListaAtualizada, produto);
            Assertions.assertEquals(produtoDaListaAtualizada.getId(),produto.getId());

            //testar se o método está retornando uma lista/ O que o método está me retornando é uma lista mesmo?
            Assertions.assertTrue(listaAtualizada instanceof List<?>);
        }

    }

}
