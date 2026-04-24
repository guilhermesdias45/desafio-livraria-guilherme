package vc.com.programar.acessoBanco;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO {

    public EntityManagerFactory emf;
    public EntityManager em;

    public DAO() {
        this.emf = Persistence.createEntityManagerFactory("desafio_livraria");
        this.em = emf.createEntityManager();
    }
}
