package br.com.cnf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TFADIS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String coddis;
    private String nomedis;
    private String status_dis;

    public String getCoddis() {
        return coddis;
    }

    public void setCoddis(String coddis) {
        this.coddis = coddis;
    }

    public String getNomedis() {
        return nomedis;
    }

    public void setNomedis(String nomedis) {
        this.nomedis = nomedis;
    }

    public String getStatus_dis() {
        return status_dis;
    }

    public void setStatus_dis(String status_dis) {
        this.status_dis = status_dis;
    }

}
