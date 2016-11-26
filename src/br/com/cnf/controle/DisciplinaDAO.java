/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cnf.controle;

import br.com.cnf.modelo.TFADIS;
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
public class DisciplinaDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("cnf");

    private final EntityManager manager = entityManagerFactory.createEntityManager();

    public void insert(String nome, String status) {

        TFADIS disciplina = new TFADIS();

        disciplina.setNomedis(nome);
        disciplina.setStatus_dis(status);

        manager.getTransaction().begin();
        manager.persist(disciplina);
        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
    }

    public void update(TFADIS disciplina) {

        TFADIS a = manager.find(TFADIS.class, disciplina.getCoddis() + "");

        manager.getTransaction().begin();

        a.setNomedis(disciplina.getNomedis());
        a.setStatus_dis(disciplina.getStatus_dis());

        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
    }

    public void updateStatus(String codigo, String status) {
        TFADIS disciplina = manager.find(TFADIS.class, codigo + "");

        manager.getTransaction().begin();

        disciplina.setStatus_dis(status);

        manager.getTransaction().commit();

    }

    public void delete(String codigo) {
        TFADIS disciplina = manager.find(TFADIS.class, codigo + "");

        manager.getTransaction().begin();
        manager.remove(disciplina);
        manager.getTransaction().commit();

    }

    public TFADIS listaPorCodigo(String codigo) {
        return buscaPorCodigo(codigo);
    }

    public TableModel listaPorNome(String nome) {
        String[] nomesColunas = {"Codigo", "nome", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFADIS disciplina : buscaPorNome(nome)) {
            lista.add(new String[]{disciplina.getCoddis(), disciplina.getNomedis(),
                disciplina.getStatus_dis()});
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

        String[] nomesColunas = {"Codigo", "nome", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFADIS disciplina : buscaTodos()) {
            lista.add(new String[]{disciplina.getCoddis(), disciplina.getNomedis(),
                disciplina.getStatus_dis()});
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

        String[] nomesColunas = {"Codigo", "nome", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFADIS disciplina : buscaPorNomeEStatus(nome, status)) {
            lista.add(new String[]{disciplina.getCoddis(), disciplina.getNomedis(),
                disciplina.getStatus_dis()});
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

    private List<TFADIS> buscaTodos() {
        return (List<TFADIS>) manager.createQuery("FROM TFADIS ORDER BY nomedis").getResultList();
    }

    private List<TFADIS> buscaPorNomeEStatus(String nome, String status) {
        return (List<TFADIS>) manager.createQuery("FROM TFADIS WHERE nomedis LIKE '%" + nome + "%' AND status_dis = '" + status + "' ORDER BY nomedis").getResultList();
    }

    private List<TFADIS> buscaPorNome(String nome) {
        return (List<TFADIS>) manager.createQuery("FROM TFADIS WHERE nomedis LIKE '%" + nome + "%' ORDER BY nomedis").getResultList();
    }

    private TFADIS buscaPorCodigo(String codigo) {
        return (TFADIS) manager.createQuery("FROM TFADIS WHERE coddis = '" + codigo + "'").getSingleResult();
    }
}
