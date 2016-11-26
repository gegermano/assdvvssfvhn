/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cnf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kaique.arantes
 */
@Entity
public class TFACNF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codcnf;    
}
