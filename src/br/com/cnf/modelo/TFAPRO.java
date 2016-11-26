package br.com.cnf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TFAPRO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codprof;
    private String nomeprof;
    private String cpf_prof;
    private String idade_prof;
    private String telefone_prof;
    private String email_prof;
    private String status_prof;

    public String getCodprof() {
        return codprof;
    }

    public void setCodprof(String codprof) {
        this.codprof = codprof;
    }

    public String getNomeprof() {
        return nomeprof;
    }

    public void setNomeprof(String nomeprof) {
        this.nomeprof = nomeprof;
    }

    public String getCpf_prof() {
        return cpf_prof;
    }

    public void setCpf_prof(String cpf_prof) {
        this.cpf_prof = cpf_prof;
    }

    public String getIdade_prof() {
        return idade_prof;
    }

    public void setIdade_prof(String idade_prof) {
        this.idade_prof = idade_prof;
    }

    public String getTelefone_prof() {
        return telefone_prof;
    }

    public void setTelefone_prof(String telefone_prof) {
        this.telefone_prof = telefone_prof;
    }

    public String getEmail_prof() {
        return email_prof;
    }

    public void setEmail_prof(String email_prof) {
        this.email_prof = email_prof;
    }

    public String getStatus_prof() {
        return status_prof;
    }

    public void setStatus_prof(String status_prof) {
        this.status_prof = status_prof;
    }

}
