package br.com.cnf.testes;

import br.com.cnf.modelo.TFAALU;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteJPA {

    static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("cnf");

    static EntityManager manager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        TFAALU aluno = manager.find(TFAALU.class, "7");

        manager.getTransaction().begin();
        aluno.setNomealuno("TESTE JPA");
        manager.getTransaction().commit();
        manager.close();

    }
}
