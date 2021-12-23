package Leads;

import br.com.zup.Lead_Colector.Leads.Lead;
import br.com.zup.Lead_Colector.Leads.LeadRepository;
import br.com.zup.Lead_Colector.Leads.LeadService;
import br.com.zup.Lead_Colector.produtos.Produto;
import br.com.zup.Lead_Colector.produtos.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

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
        lead.setProdutosDeInteresse(Arrays.asList(produto));
    }

}
