package br.com.zup.Lead_Colector.Leads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PutMapping
    public Lead cadastrarLead(@RequestBody Lead lead){
        return leadService.salvarLead(lead);
    }

    @GetMapping()
    //localhost:8080/leads?Produto=Playstation
    //o ? é usado para indicar o @Requestparam que será recebido,
    // em seguida é colocado o nome do atributo a ser filtrado e o valor de atributo a ser encontrado
    public List<Lead> buscarProdutos(@RequestParam String nomeProduto){
        return leadService.buscarTodosPeloNomeDoProduto(nomeProduto);
    }

}
