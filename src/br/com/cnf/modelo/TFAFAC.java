package br.com.cnf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TFAFAC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codfac;
    private String nomefac;
    private String cnpj_fac;
    private String telefone_fac;
    private String email_fac;
    private String status_fac;

    public String getCodfac() {
        return codfac;
    }

    public void setCodfac(String codfac) {
        this.codfac = codfac;
    }

    public String getNomefac() {
        return nomefac;
    }

    public void setNomefac(String nomefac) {
        this.nomefac = nomefac;
    }

    public String getCnpj_fac() {
        return cnpj_fac;
    }

    public void setCnpj_fac(String cnpj_fac) {
        this.cnpj_fac = cnpj_fac;
    }

    public String getTelefone_fac() {
        return telefone_fac;
    }

    public void setTelefone_fac(String telefone_fac) {
        this.telefone_fac = telefone_fac;
    }

    public String getEmail_fac() {
        return email_fac;
    }

    public void setEmail_fac(String email_fac) {
        this.email_fac = email_fac;
    }

    public String getStatus_fac() {
        return status_fac;
    }

    public void setStatus_fac(String status_fac) {
        this.status_fac = status_fac;
    }

}
