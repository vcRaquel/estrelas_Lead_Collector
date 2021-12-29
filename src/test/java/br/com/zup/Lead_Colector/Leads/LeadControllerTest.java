package br.com.zup.Lead_Colector.Leads;

import br.com.zup.Lead_Colector.produtos.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

//em vez da anotação @SpringBootTest presente nos testes unitários da Service, é usada a anotação
// @WebMvcTest() que recebe como parâmetro a classe controller a ser testada acrescida de .class
//essa anotação irá fazer as requisições para a controller designada no parâmetro
//nas situações boladas/manipuladas no corpo dos métodos de teste
@WebMvcTest
public class LeadControllerTest {
    //o que estiver como injeção de dependência da classe controller a ser testada,
    //será colocado como @MockBean na classe controller de teste
    @MockBean
    private LeadService leadService;

    @Autowired
    private MockMvc mockMvc;

    //colocar como atributo os objetos que os métodos da Controller a ser testada precisam receber
    private Lead lead;
    private List<Lead> leads;
    private Produto produto;
    private List<Produto> produtos;

    //instancia os objetos em um método setup com a anotação @BeforeEach
    @BeforeEach
    public void setup() {
        lead = new Lead();
        lead.setNome("Billy");
        lead.setId(1);

        produto = new Produto();
        produto.setNome("Maçã dourada");
        produto.setId(1);

        produtos = Arrays.asList(produto);
        lead.setProdutosDeInteresse(produtos);
        leads = Arrays.asList(lead);
    }

    @Test
    //o método perform pode estourar uma exception e por isso precisa colocar um throws Exception na assinara do método
    public void testarRotaPataBuscarProdutos() throws Exception{
        //fazer mockito do método relacionado à service que está sendo chamado no método de mapeamento GET
        //na Controller a ser testada.
        //Neste Mockito, para qualquer string passada no parâmetro do método buscarTodosPeloNomeDoProduto,
        //será retornado (thenReturn) a lista de Lead leads instanciada no setup
        Mockito.when(leadService.buscarTodosPeloNomeDoProduto(Mockito.anyString())).thenReturn(leads);

        //Fazer um ResultActions que é um tipo java que irá armazenar
        //o resultado de ações de request e response determinadas e atribuídas a ele
        //para manipular as ações no Resulctions é preciso um objeto MockMvc e por isso, uma injeção de denpendência
        //e é esse objeto que irá fazer as requisições
        ResultActions respostaDaRequisicao = mockMvc.perform(
                    //o perform() é uma execução de uma requisição que será moldada para o teste de comunicação
                    MockMvcRequestBuilders.get("/leads")
                    //o MockMvcRequestBuilders é um construtor de requisição a ser executada pelo perform
                    //o .get() representa o protocolo HTTP da requisição (no caso, GET)
                    // o "/leads" é o endpoint da requisição
                    .param("nomeProduto", "Foice")
                    //o param() irá receber os parametros da requisição por meio da combinação chave, valor
                    //ou nomeDoParametro, valorAtribuido
                    .contentType(MediaType.APPLICATION_JSON))
                    //contentType() descreve o tipo de comunicação da requisiçao.
                    //Como é uma API REST onde a comunicação é feita via json usa-se (MediaType.APPLICATION_JSON)
                .andExpect(
                        //lugar onde será descrito o comportamento de resposta esperado pela execução do perform
                        MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
                //é possível ter mais de um .andExpect mesmo ResultActions.
                // Neste, testamos se o corpo (Path) da requisição é um array. O "$" simboliza o objeto
    }

}
