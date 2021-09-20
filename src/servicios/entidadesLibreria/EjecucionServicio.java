package servicios.entidadesLibreria;

import java.util.Scanner;

public class EjecucionServicio {

    LibroServicio libroServicio = new LibroServicio();
    AutorServicio autorServicio = new AutorServicio();
    EditorialServicio editorialServicio = new EditorialServicio();

    private final Scanner read = new Scanner(System.in).useDelimiter("\n");

    private void menuPrincipal() {
        System.out.println("----------- MENU -----------");
        System.out.println("1) Ingresar datos\n"
                + "2) Modificar datos\n"
                + "3) Eliminar datos\n"
                + "4) Opciones de busqueda\n"
                + "5) Salir\n");
    }

    private void menuIngresar() {
        System.out.println("1) Ingresar un libro\n"
                + "2) Ingresar un autor\n"
                + "3) Ingresar una editorial\n"
                + "4) Volver al menu principal");
    }

    private void ejecutarIngreso() {
        int op;
        boolean state = true;
        do {
            menuIngresar();
            op = verificaOpcion(4);
            switch (op) {
                case 1:
                    libroServicio.llenaLibro();
                    break;
                case 2:
                    autorServicio.llenarAutor();
                    break;
                case 3:
                    editorialServicio.llenarEditorial();
                    break;
                case 4:
                    state = false;
                    break;
            }
        } while (state);
    }

    private void menuModificar() {
        System.out.println("1) Modificar un libro\n"
                + "2) Modificar un autor\n"
                + "3) Modificar una editorial\n"
                + "4) Volver al menu principal");
    }

    private void ejecutarModificar() throws Exception {
        int op;
        boolean state = true;
        do {
            menuModificar();
            op = verificaOpcion(4);

            switch (op) {
                case 1:
                    libroServicio.modificarLibro();
                    break;
                case 2:
                    autorServicio.modificarAutor();
                    break;
                case 3:
                    editorialServicio.modificarEditorial();
                    break;
                case 4:
                    state = false;
                    break;
            }
        } while (state);
    }

    private void menuEliminar() {
        System.out.println("1) Eliminar un libro\n"
                + "2) Eliminar un autor\n"
                + "3) Eliminar una editorial\n"
                + "4) Volver al menu principal");
    }

    private void ejecutaEliminar() {
        int op;
        boolean state = true;
        do {
            menuEliminar();
            op = verificaOpcion(4);

            switch (op) {
                case 1:
                    libroServicio.eliminarLibro();
                    break;
                case 2:
                    autorServicio.eliminarAutor();
                    break;
                case 3:
                    editorialServicio.eliminarEditorial();
                    break;
                case 4:
                    state = false;
                    break;
            }
        } while (state);
    }

    private void menuBusqueda() {
        System.out.println("1) Buscar un Autor por nombre\n"
                + "2) Buscar un libro por ISBN\n"
                + "3) Buscar un libro por titulo\n"
                + "4) Busqueda por autor\n"
                + "5) Busqueda por editorial\n"
                + "6) Volver al menu principal");
    }

    private void ejecutarBusqueda() {
        int op;
        boolean state = true;
        do {
            menuBusqueda();
            op = verificaOpcion(6);

            switch (op) {
                case 1:
                    autorServicio.busquedaPorNombre();
                    break;
                case 2:
                    libroServicio.busquedaPorISBN();
                    break;
                case 3:
                    libroServicio.busquedaPorTitulo();
                    break;
                case 4:
                    libroServicio.busquedaPorAutor();
                    break;
                case 5:
                    libroServicio.busquedaPorEditorial();
                    break;
                case 6:
                    state = false;
                    break;
            }
        } while (state);
    }

    public void ejecutar() throws Exception {
        int op;
        boolean state = true;

        do {
            menuPrincipal();
            System.out.println("Ingrese una opcion");
            op = verificaOpcion(6);
            switch (op) {
                case 1:
                    ejecutarIngreso();
                    break;
                case 2:
                    ejecutarModificar();
                    break;
                case 3:
                    ejecutaEliminar();
                    break;
                case 4:
                    ejecutarBusqueda();
                    break;
                case 5:
                    System.out.println("Esta seguro que desea salir del programa? S/N");
                    String val = read.next().substring(0);
                    if (val.toUpperCase().equals("S")) {
                        state = false;
                    }
                    break;
            }
        } while (state);
    }

    private int verificaOpcion(int max) {
        int op = read.nextInt();
        while (op < 1 || op > max) {
            System.out.println("Opcion incorrecta. Ingrese una nuevamente");
            op = read.nextInt();
        }
        return op;
    }
}
