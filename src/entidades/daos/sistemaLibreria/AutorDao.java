package entidades.daos.sistemaLibreria;

import entidades.sistemaLibreria.Autor;
import java.util.List;

public class AutorDao extends Dao {

    public Autor crearAutor(Autor autor) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(autor);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return autor;
    }

    public Autor modificarAutor(Autor autor) {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(autor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return autor;
    }

    public Autor eliminarAutor(Autor autor) {

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(autor);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return autor;
    }

    public Autor findByNombre(String nombre) {
        try {
            return (Autor) entityManager.createQuery("select a from Autor a where a.nombre =:nombre").setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

}
