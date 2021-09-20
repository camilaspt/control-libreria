package servicios.entidadesLibreria;

import entidades.daos.sistemaLibreria.EditorialDao;

import entidades.sistemaLibreria.Editorial;
import java.util.Scanner;

public class EditorialServicio {

    EditorialDao editorialDao = new EditorialDao();
    private final Scanner read = new Scanner(System.in).useDelimiter("\n");

    public Editorial crearEditorial(Editorial editorial) {
        return editorialDao.crearEditorial(editorial);
    }

    public void llenarEditorial() {

        System.out.println("Ingrese el nombre de la editorial");
        String nombre = read.next();
        crearEditorial(new Editorial(Integer.SIZE, nombre, true));

    }

    public void modificarEditorial() {
        try {
            System.out.println("Ingrese el nombre de la editoral a modificar");
            Editorial editorial = editorialDao.findByNombre(read.next());
            System.out.println("Ingrese el nuevo nombre");
            editorial.setNombre(read.next());
            editorialDao.modificarEditorial(editorial);
            System.out.println("Modificacion exitosa");
        } catch (Exception e) {
            System.out.println("Ocurrio un error al realizar la modificacion");
        }
    }

    public void eliminarEditorial() {
        try {
            System.out.println("Ingrese el nombre de la editorial a eliminar");
            editorialDao.eliminarEditorial(editorialDao.findByNombre(read.next()));
        } catch (Exception e) {
            System.out.println("Ocurrio un error al realizar la eliminacion");
        }
    }
}
