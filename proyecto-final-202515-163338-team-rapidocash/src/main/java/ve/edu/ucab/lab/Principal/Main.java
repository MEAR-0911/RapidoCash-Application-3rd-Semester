package ve.edu.ucab.lab.Principal;

import ve.edu.ucab.lab.Vista.VentanaAccesoUsuario;

/**
 * Programa realizado por estudiantes de la facultad de ingeniería informática de la Universidad Católica Andrés Bello
 *
 * Programador y líder de equipo encargado del Backend :  Antias Rodríguez Manuel Enrique
 * Programador encargado del Backend: Agudelo Taborda Daniel Alejandro
 * Programadora encargada del Fronted: Best Burgos Camila Valeria

 * La clase Main es la clase principal de la aplicación que se encarga de iniciar la interfaz gráfica del usuario.
 * En el método main, se crea una instancia de la ventana de acceso al usuario y se configura para ser visible.
 * También se configura el cierre de la aplicación cuando se cierra la ventana principal.
 *
 * @author Daniel Agudelo
 * @author Manuel Antias
 * @author Camila Best
 * @version 2.32.6
 */

public class Main {
    /**
     * Método principal que arranca la aplicación.
     * Crea una instancia de la ventana de acceso al usuario, la hace visible,
     * y configura el comportamiento de cierre de la ventana.
     *
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Crear instancia de la ventana de acceso al usuario
        VentanaAccesoUsuario ventanaAcceso = new VentanaAccesoUsuario();

        // Hacer visible la ventana
        ventanaAcceso.setVisible(true);

        // Configurar cierre de la aplicación al cerrar la ventana (si no está configurado en la clase)
        ventanaAcceso.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
}