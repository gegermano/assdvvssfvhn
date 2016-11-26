/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cnf.controle;

import br.com.cnf.modelo.TFASEM;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Kaique
 */
public class SemestreDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("cnf");

    private final EntityManager manager = entityManagerFactory.createEntityManager();

    public void insert(String ano, String sem) {

        TFASEM semestre = new TFASEM();

        semestre.setAno(ano);
        semestre.setSemestre(sem);

        manager.getTransaction().begin();
        manager.persist(semestre);
        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
    }

    public void update(TFASEM sem) {

        TFASEM semestre = manager.find(TFASEM.class, sem.getCodsem() + "");
        System.out.println("SDAFSGHDKJFGFTHDGFSDASGDHJ"+sem.getCodsem());
        manager.getTransaction().begin();

        semestre.setAno(sem.getAno());
        semestre.setSemestre(sem.getSemestre());

        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
    }

    public TableModel listaTodos() {
        String[] nomesColunas = {"Codigo", "Ano", "Semestre"};
        List<String[]> lista = new ArrayList();
        for (TFASEM semestre : buscaTodos()) {
            lista.add(new String[]{semestre.getCodsem(), semestre.getAno(), semestre.getSemestre()});
        }
        TableModel tabela = new DefaultTableModel(
                lista.toArray(new String[lista.size()][]), nomesColunas) {

            public boolean isCellEditable(int row, int col) {
                return false;
            }

        };

        return tabela;
    }

    public TFASEM listaPorCodigo(String codigo) {
        return buscaPorCodigo(codigo);
    }

    public TableModel listaPorAno(String ano) {
        String[] nomesColunas = {"Codigo", "Ano", "Semestre"};
        List<String[]> lista = new ArrayList();
        for (TFASEM semestre : buscaPorAno(ano)) {
            lista.add(new String[]{semestre.getCodsem(), semestre.getAno(), semestre.getSemestre()});
        }
        TableModel tabela = new DefaultTableModel(
                lista.toArray(new String[lista.size()][]), nomesColunas) {

            public boolean isCellEditable(int row, int col) {
                return false;
            }

        };

        return tabela;
    }

    public void delete(String codigo) {
        TFASEM semestre = manager.find(TFASEM.class, codigo + "");

        manager.getTransaction().begin();
        manager.remove(semestre);
        manager.getTransaction().commit();
    }

    private List<TFASEM> buscaTodos() {
        return (List<TFASEM>) manager.createQuery("FROM TFASEM ORDER BY ano").getResultList();
    }

    private TFASEM buscaPorCodigo(String codigo) {
        return (TFASEM) manager.createQuery("FROM TFASEM WHERE codsem = '" + codigo + "'").getSingleResult();
    }

    private List<TFASEM> buscaPorAno(String ano) {
        return (List<TFASEM>) manager.createQuery("FROM TFASEM WHERE ano LIKE '%" + ano + "%' ORDER BY ano").getResultList();
    }
}
