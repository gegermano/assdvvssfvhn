/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cnf.controle;

import br.com.cnf.modelo.TFAALU;
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
public class AlunoDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("cnf");

    private final EntityManager manager = entityManagerFactory.createEntityManager();

    public void insert(String nome, String cpf, String email, String telefone, String idade, String status) {

        TFAALU aluno = new TFAALU();

        aluno.setNomealuno(nome);
        aluno.setCpf_aluno(cpf);
        aluno.setEmail_aluno(email);
        aluno.setTelefone_aluno(telefone);
        aluno.setIdade_aluno(idade);
        aluno.setStatus_aluno(status);

        manager.getTransaction().begin();
        manager.persist(aluno);
        manager.getTransaction().commit();
        

        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
    }

    public void update(TFAALU aluno) {

        TFAALU a = manager.find(TFAALU.class, aluno.getCodaluno() + "");

        manager.getTransaction().begin();

        a.setNomealuno(aluno.getNomealuno());
        a.setCpf_aluno(aluno.getCpf_aluno());
        a.setEmail_aluno(aluno.getEmail_aluno());
        a.setIdade_aluno(aluno.getIdade_aluno());
        a.setStatus_aluno(aluno.getStatus_aluno());
        a.setTelefone_aluno(aluno.getTelefone_aluno());

        manager.getTransaction().commit();
        

        JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
    }

    public void updateStatus(String codigo, String status) {
        TFAALU aluno = manager.find(TFAALU.class, codigo + "");

        manager.getTransaction().begin();

        aluno.setStatus_aluno(status);

        manager.getTransaction().commit();
        
    }
    
    public void delete(String codigo) {
        TFAALU aluno = manager.find(TFAALU.class, codigo + "");

        manager.getTransaction().begin();
        manager.remove(aluno);
        manager.getTransaction().commit();
        
    }

    public TFAALU listaPorCodigo(String codigo) {
        return buscaPorCodigo(codigo);
    }

    public TableModel listaPorNome(String nome) {
        String[] nomesColunas = {"Codigo", "nome", "CPF", "Email", "Status"};
        List<String[]> lista = new ArrayList();
        for (TFAALU aluno : buscaPorNome(nome)) {
            lista.add(new String[]{aluno.getCodaluno(), aluno.getNomealuno(), aluno.getCpf_aluno(),
                aluno.getEmail_aluno(), aluno.getStatus_aluno()});
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
        for (TFAALU aluno : buscaTodos()) {
            lista.add(new String[]{aluno.getCodaluno(), aluno.getNomealuno(), aluno.getCpf_aluno(),
                aluno.getEmail_aluno(), aluno.getStatus_aluno()});
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
        for (TFAALU aluno : buscaPorNomeEStatus(nome, status)) {
            lista.add(new String[]{aluno.getCodaluno(), aluno.getNomealuno(), aluno.getCpf_aluno(),
                aluno.getEmail_aluno(), aluno.getStatus_aluno()});
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
    
    private List<TFAALU> buscaTodos() {
        return (List<TFAALU>) manager.createQuery("FROM TFAALU ORDER BY nomealuno").getResultList();
    }

    private List<TFAALU> buscaPorNomeEStatus(String nome, String status) {
        return (List<TFAALU>) manager.createQuery("FROM TFAALU WHERE nomealuno LIKE '%" + nome + "%' AND status_aluno = '" + status + "' ORDER BY nomealuno").getResultList();
    }

    private List<TFAALU> buscaPorNome(String nome) {
        return (List<TFAALU>) manager.createQuery("FROM TFAALU WHERE nomealuno LIKE '%" + nome + "%' ORDER BY nomealuno").getResultList();
    }
    
    private TFAALU buscaPorCodigo(String codigo) {
        return (TFAALU) manager.createQuery("FROM TFAALU WHERE codaluno = '" + codigo + "'").getSingleResult();
    }
}
