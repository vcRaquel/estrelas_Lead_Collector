package br.com.zup.Lead_Colector.Leads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PutMapping
    public Lead cadastrarLead(@RequestBody Lead lead){
        return leadService.salvarLead(lead);
    }
}
