package br.com.cnf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TFASEM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codsem;
    private String semestre;
    private String ano;

    public String getCodsem() {
        return codsem;
    }

    public void setCodsem(String codsem) {
        this.codsem = codsem;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
