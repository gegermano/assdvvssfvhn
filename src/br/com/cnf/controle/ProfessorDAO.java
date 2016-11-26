/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cnf.controle;

import br.com.cnf.modelo.TFAPRO;
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
public class ProfessorDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("cnf");

    private final EntityManager manager = entityManagerFactory.createEntityManager();

    public void insert(String nome, String cpf, String email, String telefone, String idade, String status) {

        TFAPRO professor = new TFAPRO();

        professor.setNomeprof(nome);
        professor.setCpf_prof(cpf);
        professor.setEmail_prof(email);
        professor.setTelefone_prof(telefone);
        professor.setIdade_prof(idade);
        professor.setStatus_prof(status);

        manager.getTransaction().begin();
        manager.persist(professor);
        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
    }

    public void update(TFAPRO professor) {

        TFAPRO a = manager.find(TFAPRO.class, professor.getCodprof() + "");

        manager.getTransaction().begin();

        a.setNomeprof(professor.getNomeprof());
        a.setCpf_prof(professor.getCpf_prof());
        a.setEmail_prof(professor.getEmail_prof());
        a.setIdade_prof(professor.getIdade_prof());
        a.setStatus_prof(professor.getStatus_prof());
        a.setTelefone_prof(professor.getTelefone_prof());

        manager.getTransaction().commit();

        JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
    }

    public void updateStatus(String codigo, String status) {
        TFAPRO professor = manager.find(TFAPRO.class, codigo + "");

        manager.getTransaction().begin();

        professor.setStatus_prof(status);

        manager.getTransaction().commit();

    }

    public void delete(String codigo) {
        TFAPRO professor = manager.find(TFAPRO.class, codigo + "");

        manager.getTransaction().begin();
        manager.remove(professor);
        manager.getTransaction().commit();

    }

    public TFAPRO listaPorCodigo(String codigo) {
        return buscaPorCodigo(codigo);
    }

    public TableModel listaPorNome(String nome) {
        String[] nomesColunas = {"Codigo", "nome", "CPF", "Email", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFAPRO professor : buscaPorNome(nome)) {
            lista.add(new String[]{professor.getCodprof(), professor.getNomeprof(), professor.getCpf_prof(),
                professor.getEmail_prof(), professor.getStatus_prof()});
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

        String[] nomesColunas = {"Codigo", "nome", "CPF", "Email", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFAPRO professor : buscaTodos()) {
            lista.add(new String[]{professor.getCodprof(), professor.getNomeprof(), professor.getCpf_prof(),
                professor.getEmail_prof(), professor.getStatus_prof()});
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
        for (TFAPRO professor : buscaPorNomeEStatus(nome, status)) {
            lista.add(new String[]{professor.getCodprof(), professor.getNomeprof(), professor.getCpf_prof(),
                professor.getEmail_prof(), professor.getStatus_prof()});
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

    private List<TFAPRO> buscaTodos() {
        return (List<TFAPRO>) manager.createQuery("FROM TFAPRO ORDER BY nomeprof").getResultList();
    }

    private List<TFAPRO> buscaPorNomeEStatus(String nome, String status) {
        return (List<TFAPRO>) manager.createQuery("FROM TFAPRO WHERE nomeprof LIKE '%" + nome + "%' AND status_prof = '" + status + "' ORDER BY nomeprof").getResultList();
    }

    private List<TFAPRO> buscaPorNome(String nome) {
        return (List<TFAPRO>) manager.createQuery("FROM TFAPRO WHERE nomeprof LIKE '%" + nome + "%' ORDER BY nomeprof").getResultList();
    }

    private TFAPRO buscaPorCodigo(String codigo) {
        return (TFAPRO) manager.createQuery("FROM TFAPRO WHERE codprof = '" + codigo + "'").getSingleResult();
    }
}
