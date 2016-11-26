/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cnf.controle;

import br.com.cnf.modelo.TFAFAC;
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
public class FaculdadeDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("cnf");

    private final EntityManager manager = entityManagerFactory.createEntityManager();

    public void insert(String nome, String cnpj, String email, String telefone, String status) {

        TFAFAC faculdade = new TFAFAC();

        faculdade.setNomefac(nome);
        faculdade.setCnpj_fac(cnpj);
        faculdade.setEmail_fac(email);
        faculdade.setTelefone_fac(telefone);
        faculdade.setStatus_fac(status);

        manager.getTransaction().begin();
        manager.persist(faculdade);
        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
    }

    public void update(TFAFAC faculdade) {

        TFAFAC a = manager.find(TFAFAC.class, faculdade.getCodfac() + "");

        manager.getTransaction().begin();

        a.setNomefac(faculdade.getNomefac());
        a.setCnpj_fac(faculdade.getCnpj_fac());
        a.setEmail_fac(faculdade.getEmail_fac());
        a.setStatus_fac(faculdade.getStatus_fac());
        a.setTelefone_fac(faculdade.getTelefone_fac());

        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
    }

    public void updateStatus(String codigo, String status) {
        TFAFAC faculdade = manager.find(TFAFAC.class, codigo + "");

        manager.getTransaction().begin();

        faculdade.setStatus_fac(status);

        manager.getTransaction().commit();

    }

    public void delete(String codigo) {
        TFAFAC faculdade = manager.find(TFAFAC.class, codigo + "");

        manager.getTransaction().begin();
        manager.remove(faculdade);
        manager.getTransaction().commit();

    }

    public TFAFAC listaPorCodigo(String codigo) {
        return buscaPorCodigo(codigo);
    }

    public TableModel listaPorNome(String nome) {
        String[] nomesColunas = {"Codigo", "nome", "CPF", "Email", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFAFAC faculdade : buscaPorNome(nome)) {
            lista.add(new String[]{faculdade.getCodfac(), faculdade.getNomefac(), faculdade.getCnpj_fac(),
                faculdade.getEmail_fac(), faculdade.getStatus_fac()});
        }
        TableModel tabela = new DefaultTableModel(
                lista.toArray(new String[lista.size()][]), nomesColunas) {

                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }

                };

        return tabela;
    }

    public TableModel listaTodos() throws Exception {

        String[] nomesColunas = {"Matricula", "nome", "CPF", "Email", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFAFAC faculdade : buscaTodos()) {
            lista.add(new String[]{faculdade.getCodfac(), faculdade.getNomefac(), faculdade.getCnpj_fac(),
                faculdade.getEmail_fac(), faculdade.getStatus_fac()});
        }
        TableModel tabela = new DefaultTableModel(
                lista.toArray(new String[lista.size()][]), nomesColunas) {

                    @Override
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }

                };

        return tabela;
    }

    public TableModel listaPorNomeStatus(String nome, String status) throws Exception {

        String[] nomesColunas = {"Codigo", "nome", "CPF", "Email", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFAFAC faculdade : buscaPorNomeEStatus(nome, status)) {
            lista.add(new String[]{faculdade.getCodfac(), faculdade.getNomefac(), faculdade.getCnpj_fac(),
                faculdade.getEmail_fac(), faculdade.getStatus_fac()});
        }
        TableModel tabela = new DefaultTableModel(
                lista.toArray(new String[lista.size()][]), nomesColunas) {

                    @Override
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }

                };

        return tabela;
    }

    private List<TFAFAC> buscaTodos() {
        return (List<TFAFAC>) manager.createQuery("FROM TFAFAC ORDER BY nomefac").getResultList();
    }

    private List<TFAFAC> buscaPorNomeEStatus(String nome, String status) {
        return (List<TFAFAC>) manager.createQuery("FROM TFAFAC WHERE nomefac LIKE '%" + nome + "%' AND status_fac = '" + status + "' ORDER BY nomefac").getResultList();
    }

    private List<TFAFAC> buscaPorNome(String nome) {
        return (List<TFAFAC>) manager.createQuery("FROM TFAFAC WHERE nomefac LIKE '%" + nome + "%' ORDER BY nomefac").getResultList();
    }

    private TFAFAC buscaPorCodigo(String codigo) {
        return (TFAFAC) manager.createQuery("FROM TFAFAC WHERE codfac = '" + codigo + "'").getSingleResult();
    }
}
