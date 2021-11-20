package br.com.zup.Lead_Colector.Leads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {
    @Autowired
    LeadRepository leadRepository;

    public Lead salvarLead(Lead lead){
        return leadRepository.save(lead);
    }

}
