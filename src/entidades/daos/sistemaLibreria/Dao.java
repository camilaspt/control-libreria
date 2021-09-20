package entidades.daos.sistemaLibreria;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Dao {

    protected EntityManager entityManager = Persistence.createEntityManagerFactory("SistemaLibreria_PU").createEntityManager();
}
