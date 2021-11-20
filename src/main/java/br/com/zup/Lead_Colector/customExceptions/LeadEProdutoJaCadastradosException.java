package br.com.zup.Lead_Colector.customExceptions;

public class LeadEProdutoJaCadastradosException extends RuntimeException{
    public LeadEProdutoJaCadastradosException(String message) {
        super(message);
    }
}
