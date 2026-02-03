package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Administrador;
import ve.edu.ucab.lab.modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Este controlador se encarga de hacer las operaciones lógicas pertinentes para poder realizar el cambio de pin del
 * cliente. Se enfoca en asegurarse de que el correo ingresado para elc ambio de pin esté registrado, que se ingrese
 * correctamente el pin viejo, y el pin nuevo además de que se asegura de comprobar que los mismos no sean iguales.
 * En esta clase se hace uso de la instancia del {@linkplain ControladorBaseDatosClientes}.
 *
 * @version 2.28.3
 * @author Manuel Antias.
 */
public class ControladorCambiarPinCliente {
    /**
     * Atributo numeroTelfCliente, se guarda el número de teléfono ingresado por el cliente.
     */
    private String numeroTelfCliente;
    /**
     * Atributo correoCliente, se guarda el correo del cliente ingresado por el cliente.
     */
    private String correoCliente;
    /**
     * Atributo nuevoPin, se guarda el nuevo pin ingresado por el cliente.
     */
    private String nuevoPin;
    /**
     * Atributo pinViejo, se guarda el pin antiguo ingresado por el cliente.
     */
    private String pinViejo;
    /**
     * Atributo cliente, se guarda un objeto del tipo {@linkplain Cliente}.
     */
    private Cliente cliente;
    /**
     * Atributo clienteCambio, se guarda otro objeto del tipo {@linkplain Cliente}, que será usado para poder cambiar el
     * valor del pin del cliente.
     */
    private Cliente clienteCambio;
    /**
     * Atributo clientes, guarda una lista Map de objetos del tipo {@linkplain Cliente}.
     */
    private Map<String,Cliente> clientes=new LinkedHashMap<>();
    /**
     * Atributo controladorClientes, se guarda un objeto de la instancia de la clase
     * {@linkplain ControladorBaseDatosClientes}.
     */
    private ControladorBaseDatosClientes controladorClientes=ControladorBaseDatosClientes.getInstanciaCliente();
    /**
     * Atributo correcto, se guarda un booleano para luego verificar que la operación se llevó a cabo de manera
     * satisfactoria.
     */
    private boolean correcto;

    private String rutaImagenes;

    private String rutaBaseDatos;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;


    /**
     *Constructor del ControladorCambiarPinCliente.
     *
     * @param numeroTelfCliente Número de teléfono a buscar.
     * @param correoCliente Correo del cliente a buscar.
     * @param nuevoPin Nuevo pin a registrar.
     * @param pinViejo Viejo pin a confirmar.
     * @param cliente Cliente a recibir para las operaciones de datos.
     */
    public ControladorCambiarPinCliente(String numeroTelfCliente, String correoCliente, String nuevoPin, String
            pinViejo, Cliente cliente) {
        this.numeroTelfCliente = numeroTelfCliente;
        this.correoCliente = correoCliente;
        this.nuevoPin = nuevoPin;
        this.pinViejo = pinViejo;
        this.cliente = cliente;
        clientes=controladorClientes.getClientes();
        this.clienteCambio=cliente;
        correcto=false;
    }

    /**
     * Este método se enfoca en tomar el número de teléfono de cliente y confirmar si está correcto buscándolo en
     * la base de datos mediante el Map. En dado caso de que esté correcto verificamos que el correo se encuentre
     * en la base de datos, asociado al atributo de correo del cliente. En dado caso de que el correo se lleve a cabo
     * de manera espléndida, entonces se verifica que el pin nuevo no sea igual al pin que está a punto de ser cambiado.
     * Cabe acotar que también se verifica que el pin a cambiar ingresado coincida con el registrado en el atributo del
     * cliente.
     *
     * @return correcto, se retorna el valor de la variable asociada al método para verificar que toda la operación
     * se haya llevado a cabo de manera fructífera.
     */

    public void archivito(String ruta,String rutaimg,String archivo, String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable(ruta,rutaimg,archivo,png);
        rutasDeEjecutable.rutas();
        rutaBaseDatos=rutasDeEjecutable.getRutaBaseDatos();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    public boolean cambiarPin(){
        clienteCambio=clientes.get(numeroTelfCliente);
        if(clienteCambio!=null){
            if (!clienteCambio.getCorreoCliente().equals(correoCliente)) {

                archivito("","src/main/java/Imagenes/Contrasena_erronea.png","","Contrasena_erronea.png");

                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\"><b>¡Ups parece que cometió un error!</b>" +
                                "<br><br>el correo electrónico ingresado <b>no coincide</b> con el registrado<br>" +
                                "en la base de datos.Por favor ingrese nuevamente el correo.</p></html>",
                        "ERROR FATAL",
                        JOptionPane.INFORMATION_MESSAGE, icono);
                correoCliente="";

            } else if (!clienteCambio.getPin().equals(pinViejo)) {

                archivito("","src/main/java/Imagenes/Contrasena_erronea.png","","Contrasena_erronea.png");

                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\"><b>¡Ups parece que cometió un error!</b>" +
                                "<br><br>el pin anterior ingresado <b>no coincide</b> con el registrado en la base" +
                                "de datos.Por favor ingrese nuevamente el pin.</p></html>", "ERROR FATAL",
                        JOptionPane.INFORMATION_MESSAGE, icono);
                pinViejo="";

            } else if (pinViejo.equals(nuevoPin)) {

                archivito("","src/main/java/Imagenes/Contrasena_erronea.png","","Contrasena_erronea.png");

                ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\"><b>¡Ups parece que cometió un error!</b>" +
                                "<br><br>el <b>nuevo pin no puede ser igual</b> al pin anterior registrado.<br>" +
                                "Por favor ingrese un nuevo pin.</p></html>",
                        "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                nuevoPin="";
            }else{
                clienteCambio.setPin(nuevoPin);
                try {
                    archivito("src/main/java/ve/edu/ucab/lab/baseDeDatos/DatosClientes",
                            "src/main/java/Imagenes/cambiar-la-contrasena.png","DatosCliente",
                            "cambiar-la-contrasena.png");

                    FileWriter archivo = new FileWriter(rutaBaseDatos);
                    /**
                     * Se crea este StringBuilder como recipiente de toda los datos que se ingresarán en la BD.
                     */
                    StringBuilder linea = new StringBuilder();
                    String datos;
                    for (Cliente clienteCambio : clientes.values()) {
                        datos = clienteCambio.getPin()+ "," + clienteCambio.getNombreCliente()+","+
                                clienteCambio.getApellidoCliente()+","+clienteCambio.getCiCliente()+","+
                        clienteCambio.getTelefonoCliente()+","+clienteCambio.getCorreoCliente()+","+
                        clienteCambio.getCuenta().getTipoCuentaCliente()+","+clienteCambio.getCuenta().getCuentaCliente()+
                        ","+clienteCambio.getCuenta().getSaldoCliente();
                        linea.append(datos + "\n");
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
                    nuevoPin="";
                    pinViejo="";
                    correoCliente="";
                    correcto=true;
                    ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\"><b>¡Su pin se ha cambiado con éxito!</b>" +
                                    "<br><br>ya puede acceder al sistema RapidoCash</p></html>",
                            "PIN CAMBIADO", JOptionPane.INFORMATION_MESSAGE, icono);
                }catch (IOException e) {

                    archivito("","src/main/java/Imagenes/ArchivoCorrupto.png","","ArchivoCorrupto.png");

                    ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\">¡Archivo no encontrado o Corrupto!</p></html>",
                            "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                }
            }
        }
        else {
            archivito("","src/main/java/Imagenes/error-de-base-de-datos.png","",
                    "error-de-base-de-datos.png");
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\"<b>¡Ups parece que cometió un error!</b><br><br>" +
                            "el teléfono ingresado <b>no se encuentra en la base de datos</b>, por favor ingréselo<br>" +
                            "nuevamente</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);
        }
        return correcto;
    }

    /**
     * Getter correcto.
     * @return correcto, se retorna si la operació se llevó a cabo con éxito.
     */
    public boolean esCorrecto() {
        return correcto;
    }

    /**
     * Getter numeroTelfCliente.
     * @return numeroTelfCliente, se retorna el valor del número de teléfono, en este caso se hace uso para borrar
     * aquellos campos en los que haya error.
     */
    public String getNumeroTelfCliente() {
        return numeroTelfCliente;
    }

    /**
     * Getter correoCliente.
     * @return correoCliente, se retorna el valor del correo. En este caso se hace uso para borrar
     * aquel campo en el que haya error.
     */
    public String getCorreoCliente() {
        return correoCliente;
    }

    /**
     * Getter nuevoPin.
     * @return nuevoPin, se retorna el valor del nuevo pin. En este caso se hace uso para borrar
     * aquel campo en el que haya error.
     */
    public String getNuevoPin() {
        return nuevoPin;
    }

    /**
     * Getter pinViejo.
     * @return nuevoPin, se retorna el valor del pin viejo En este caso se hace uso para borrar
     * aquel campo en el que haya error.
     */
    public String getPinViejo() {
        return pinViejo;
    }
}
