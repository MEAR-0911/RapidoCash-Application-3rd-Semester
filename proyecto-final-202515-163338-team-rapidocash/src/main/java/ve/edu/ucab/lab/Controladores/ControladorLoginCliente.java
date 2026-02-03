package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * En esta clase Controlador se llevarán a cabo las validaciones pertinentes para el ingreso de los administradores
 * al programa. A su vez esta clase hace uso de la instancia de la clase {@linkplain ControladorBaseDatosClientes},
 * para poder acceder a la lista de objetos Administradores.
 *
 * @author Antias Manuel
 * @version 2.14.2
 */

public class ControladorLoginCliente {
    /**
     * Atributo controladorCliente, se creó un objeto de la clase {@linkplain ControladorBaseDatosClientes} para
     * llamar a la instancia de la clase sigleton para así luego hacer uso de sus métodos.
     */
    ControladorBaseDatosClientes controladorCliente= ControladorBaseDatosClientes.getInstanciaCliente();
    /**
     * Atributo clientes, en este atributo se guarda una lista de objetos {@linkplain Cliente}, dicha lista
     * de tipo Hashmap será llenada con la lista proveniente de los métodos de la clase
     *{@linkplain ControladorBaseDatosClientes}.
     */
    private Map<String, Cliente> clientes = new LinkedHashMap<>();
    /**
     * Atributo telefonoCliente, en este atributo se guarda el número de teléfono ingresado por el cliente.
     */
    private String telefonoCliente;
    /**
     * Atributo pinCliente, en este atributo se guarda el pin ingresado por el cliente.
     */
    private String pinCliente;
    /**
     * Atributo resultado, en este atributo se guarda si el usuario logra ingresar exitosamente o no (true/false).
     */
    private boolean resultado;
    /**
     * contadorIntentos, en este atributo se guarda la cantidad de intentos que le quedan al usuario para ingresar.
     */

    private int contadorIntentos;

    /**
     * Atributo cliente, en este atributo se guarda un objeto de la Clase {@linkplain Cliente}.
     */
    private Cliente cliente;

    private String rutaImagenes;

    private String rutaBaseDatos;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     *Controlador de la clase ControladorLoginCliente
     * @param telefonoCliente teléfono del cliente ingresado en {@linkplain ve.edu.ucab.lab.Vista.VentanaAccesoUsuario}.
     * @param pinCliente pin del clientre ingresado en {@linkplain ve.edu.ucab.lab.Vista.VentanaAccesoUsuario}.
     */
    public ControladorLoginCliente(String telefonoCliente, String pinCliente, int contadorIntentos) {
        this.telefonoCliente = telefonoCliente;
        this.pinCliente = pinCliente;
        resultado = false;
        clientes=controladorCliente.getClientes();
        this.contadorIntentos=contadorIntentos;
    }

    public void archivito(String ruta,String rutaimg,String archivo, String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(ruta,rutaimg,archivo,png);
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Este método se encarga de verificar si el usuario ingresa los datos correctos de entrada. Tomando en
     * cuenta que solamente se trabaja con varios clientes, la Key del HashMap para poder buscar en nuestra lista
     * es el número de teléfono del usuario, el cual es único. Si el número de teléfono existe en la lista entonces se
     * pasa a verificar si la cuenta no está bloqueada. En dado caso de que la cuenta no esté bloqueada se verifica que
     * el pin ingresado exista en nuestra lista. En dado caso de que el dato ingresado sea erróneo entonces le damos dos
     * intentos más al usuario, si se acaban las oportunidades entonces se cambia el estado de "activo" a "bloqueado" y
     * luego se reescribe toda la base de datos con esta modifcación. Por último se vuelve a cargar en automático la BD.
     * En dado caso de que la clave se ingrese correctamente entonces se ingresa exitósamente.
     * @return resultado, valor que indicará la entrada exitosa del usuario.
     */
    public boolean loginCliente() {
        cliente = clientes.get(telefonoCliente);
        if (cliente == null) {

            archivito("","src/main/java/Imagenes/error-de-base-de-datos.png","",
                    "error-de-base-de-datos.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">Tal parece que el teléfono ingresado " +
                            "no se encuentra en la base de datos, por favor ingréselo nuevamente</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);
        }
        else if (cliente!=null){
            if (cliente.getCuenta().getEstadoCuentaCliente()){
                if (!cliente.getPin().equals(pinCliente)){
                    if (contadorIntentos!=0){

                        archivito("","src/main/java/Imagenes/ClaveInvalida.png","",
                                "ClaveInvalida.png");

                        ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
                        Image img = icono.getImage();
                        Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        icono = new ImageIcon(scaledImage);
                        JOptionPane.showMessageDialog(null,
                                "<html><p style=\"color:black;font-size:15px;\">Clave inválida verifíquela nuevamente " +
                                        "le quedan "+contadorIntentos+" intentos</p></html>",
                                "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);
                        pinCliente="";
                    } else if (contadorIntentos==0) {
                        cliente.getCuenta().setCuentaCliente("bloqueado");
                        try {

                            archivito("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes",
                                    "src/main/java/Imagenes/bloquear-usuario.png","DatosClientes",
                                    "bloquear-usuario.png");

                            FileWriter archivo = new FileWriter(rutaBaseDatos);
                            /**
                             * Se crea este StringBuilder como recipiente de toda los datos que se ingresarán en la BD.
                             */
                            StringBuilder linea = new StringBuilder();
                            String datos;
                            for (Cliente cliente2: clientes.values()){
                                datos=cliente2.getPin()+","+cliente2.getNombreCliente()+","+cliente2.getApellidoCliente()+","+
                                        cliente2.getCiCliente()+","+cliente2.getTelefonoCliente()+","+cliente2.getCorreoCliente()+
                                        ","+ cliente2.getCuenta().getTipoCuentaCliente()+","+cliente2.getCuenta().getCuentaCliente()+","+
                                        cliente2.getCuenta().getSaldoCliente();
                                linea.append(datos+"\n");
                                /**
                                 * Convertimos el StringBuilder en una línea de texto aceptable para poder ser escrita
                                 * en la BD.
                                 */
                                archivo.write(linea.toString());
                                /**
                                 * Sirve para vaciar nuestro recipiente de datos (StringBuilder) y no concatenar
                                 * erróneamente cosas de más.
                                 */
                                linea.setLength(0);
                            }
                            archivo.close();
                            controladorCliente.cargarClientes();
                            ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                            Image img = icono.getImage();
                            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icono = new ImageIcon(scaledImage);
                            JOptionPane.showMessageDialog(null,
                                    "<html><p style=\"color:black;font-size:15px;\">¡La clave que ha ingresado es errónea " +
                                            "por medidas de seguridad, la cuenta ha sido bloqueada, por favor contacte al gerente bancario" +
                                            "!</p></html>",
                                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                            pinCliente="";
                            telefonoCliente="";
                        } catch (IOException e) {

                            archivito("", "src/main/java/Imagenes/ArchivoCorrupto.png",
                                    "", "ArchivoCorrupto.png");

                            ImageIcon icono = new javax.swing.ImageIcon();
                            Image img = icono.getImage();
                            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            icono = new ImageIcon(scaledImage);
                            JOptionPane.showMessageDialog(null,
                                    "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);

                        }
                    }
                } else if (cliente.getPin().equals(pinCliente)) {

                    archivito("", "src/main/java/Imagenes/entradaExitosa.png",
                            "", "entradaExitosa.png");

                    ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\">¡INGRESO EXITOSO, BIENVENIDO: "+
                                    cliente.getNombreCliente()+" "+cliente.getApellidoCliente()+"</p></html>",
                            "BIENVENIDO A RAPIDOCASH", JOptionPane.INFORMATION_MESSAGE, icono);
                    resultado=true;
                    pinCliente="";
                    telefonoCliente="";
                }
            }
            else {

                archivito("", "src/main/java/Imagenes/CuentaBloqueada.png", "",
                        "CuentaBloqueada.png");

                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\">Estimado usuario, lamentamos informarle "+
                                "que actualmente su cuenta se encuentra bloqueada. Por favor recupere su cuenta</p></html>",
                        "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                pinCliente="";
                telefonoCliente="";
            }
        }
        return resultado;
    }

    /**
     * Getter resultado.
     * @return resultado, valor del atributo resultado de la clase Controladora del Login del Cliente.
     */
    public boolean resultado() {
        return resultado;
    }

    /**
     * Getter cliente.
     * @return cliente, valor del objeto Cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Getter pinCliente.
     * @return pinCliente, valor del pin del cliente.
     */
    public String getPinCliente() {
        return pinCliente;
    }

    /**
     * Getter telefonoCliente.
     * @return telefonoCliente, valor del teléfono del cliente.
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }
}
