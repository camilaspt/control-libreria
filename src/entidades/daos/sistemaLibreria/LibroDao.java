/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.daos.sistemaLibreria;

import entidades.sistemaLibreria.Autor;
import entidades.sistemaLibreria.Editorial;
import entidades.sistemaLibreria.Libro;
import java.util.List;

public class LibroDao extends Dao {

    public Libro crearLibro(Libro libro) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(libro);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return libro;
    }

    public Libro modificarLibro(Libro libro) {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(libro);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return libro;
    }

    public Libro eliminarLibro(Libro libro) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(libro);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return libro;
    }

    public Libro findByISBN(Long ISBN) {
        return (Libro) entityManager.createQuery("select L from Libro L where L.isbn =:ISBN").setParameter("ISBN", ISBN).getSingleResult();
    }

    public Libro findByTitulo(String nombre) {
        return (Libro) entityManager.createQuery("select L from Libro L where L.titulo =:nombre").setParameter("nombre", nombre).getSingleResult();
    }

    public List<Libro> findAllbyEditorial(Editorial editorial) {
        return entityManager.createQuery("select L from Libro L where L.editorial.nombre =:editorial").setParameter("editorial", editorial.getNombre()).getResultList();
    }

    public List<Libro> findAllbyAutor(Autor autor) {
        return entityManager.createQuery("select L from Libro L where L.autor.nombre =:autor").setParameter("autor", autor.getNombre()).getResultList();
    }
}
