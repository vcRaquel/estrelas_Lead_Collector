package br.com.zup.Lead_Colector.Leads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    public Lead salvarLead(Lead lead){
        //poderia chamar o produtoRepository salvando primeiro o produto para depois salvar o Lead
        // ou com o cascade no relacionamento
        return leadRepository.save(lead);
    }

}
