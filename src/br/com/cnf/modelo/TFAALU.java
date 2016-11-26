package br.com.cnf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TFAALU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codaluno;
    private String nomealuno;
    private String cpf_aluno;
    private String idade_aluno;
    private String telefone_aluno;
    private String email_aluno;
    private String status_aluno;

    public String getCodaluno() {
        return codaluno;
    }

    public void setCodaluno(String codaluno) {
        this.codaluno = codaluno;
    }

    public String getNomealuno() {
        return nomealuno;
    }

    public void setNomealuno(String nomealuno) {
        this.nomealuno = nomealuno;
    }

    public String getCpf_aluno() {
        return cpf_aluno;
    }

    public void setCpf_aluno(String cpf_aluno) {
        this.cpf_aluno = cpf_aluno;
    }

    public String getIdade_aluno() {
        return idade_aluno;
    }

    public void setIdade_aluno(String idade_aluno) {
        this.idade_aluno = idade_aluno;
    }

    public String getTelefone_aluno() {
        return telefone_aluno;
    }

    public void setTelefone_aluno(String telefone_aluno) {
        this.telefone_aluno = telefone_aluno;
    }

    public String getEmail_aluno() {
        return email_aluno;
    }

    public void setEmail_aluno(String email_aluno) {
        this.email_aluno = email_aluno;
    }

    public String getStatus_aluno() {
        return status_aluno;
    }

    public void setStatus_aluno(String status_aluno) {
        this.status_aluno = status_aluno;
    }

}
