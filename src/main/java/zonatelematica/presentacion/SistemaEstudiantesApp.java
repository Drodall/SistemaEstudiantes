package zonatelematica.presentacion;

import zonatelematica.datos.EstudianteDAO;
import zonatelematica.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        boolean salir = false;
        var input = new Scanner(System.in);

        var estudianteDao = new EstudianteDAO();
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(input, estudianteDao);
            } catch (Exception e) {
                System.out.println("Error al realizar la opcion: "
                        + e.getMessage());
            }
            System.out.println();
        }// fin while

    }

    public static void mostrarMenu() {
        System.out.print("""
                *** Sistema de Estudiantes ***
                Seleccione una opcion
                1. Listar Estudiante
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                """);

    }

    public static boolean ejecutarOpciones(Scanner input,
                                              EstudianteDAO estudianteDAO) {
        int opcion = Integer.parseInt(input.nextLine());
        boolean salir = false;
        switch (opcion) {
            case 1: //Listar estudiantes
                System.out.println("Lista de Estudiantes...");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
                break;

            case 2: //Buscar estudiante por id
                System.out.println("Ingrese por favor el id_estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(input.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (encontrado)
                    System.out.println("Se encontro el estudiante exitosamente: "
                            + estudiante);
                else
                    System.out.println("No se pudo encontrar al estudiante: "
                            + estudiante);
                break;

            case 3: // Agregar estudiante
                System.out.println("Agregar Estudiante...");
                System.out.print("Nombre: ");
                var nombre = input.nextLine();
                System.out.print("Apellido: ");
                var apellido = input.nextLine();
                System.out.print("Telefono: ");
                var telefono = input.nextLine();
                System.out.print("Email: ");
                var email = input.nextLine();
                // Crear el objeto estudiante (sinid)
                estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Se agrega estudiante exitosamente: " + estudiante);
                else
                    System.out.println("No se pudo agregar estudiante: " + estudiante);
                break;

            case 4: //Modificar estudiante
                System.out.println("Modificar Estudiante...");
                System.out.println("Id Estudiante: ");
                idEstudiante = Integer.parseInt(input.nextLine());
                System.out.print("Nombre: ");
                nombre = input.nextLine();
                System.out.print("Apellido: ");
                apellido = input.nextLine();
                System.out.print("Telefono: ");
                telefono = input.nextLine();
                System.out.print("Email: ");
                email = input.nextLine();
                //Crear el objeto estudiante a modificar
                estudiante =
                        new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado)
                    System.out.println("Se modifica estudiante exitosamente: " + estudiante);
                else
                    System.out.println("No se pudo modificar estudiante: " + estudiante);
                break;

            case 5: //Eliminar Estudiante
                System.out.println("Eliminar Estudiante: ");
                System.out.println("Id Estudiante: ");
                idEstudiante = Integer.parseInt(input.nextLine());
                estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("Se eliminar estudiante exitosamente: " + estudiante);
                else
                    System.out.println("No se pudo eliminar estudiante: " + estudiante);
                break;

            case 6: //Salir de la opcion
                System.out.println("Adios...");
                salir = true;
                break;

            default:
                System.out.println("Opcion incorrecta... ");


        }
        return salir;
    }
}
