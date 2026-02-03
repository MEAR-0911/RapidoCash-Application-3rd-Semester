package ve.edu.ucab.lab.Controladores;

import ve.edu.ucab.lab.modelo.Administrador;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Este controlador se encarga de implementar los métodos pertinentes para que se pueda llevar a cabo una recuperación
 * (Cambio) de contraseña por parte del administrador. En este controlador la funcionalidad principal es recibir
 * correctamente el nombre de usuario y el correo para poder enviar el correo al destinatario.
 *
 * @version 2.30.6
 * @author Manuel Antias.
 */

public class ControladorOlvidarContraAdmin {
    /**
     * En este atributo se guarda un objeto controladorAdmis del tipo {@linkplain ControladorBaseDatosAdministrador},
     * la cual será una instancia del mismo.
     */
    private ControladorBaseDatosAdministrador controladorAdmis=ControladorBaseDatosAdministrador.getInstanciaAdministrador();
    /**
     * Este atributo guarda una lista del tipo Map con una compilación de objetos del tipo {@linkplain Administrador}.
     */
    private Map<String, Administrador> administrador = new LinkedHashMap<>();
    /**
     * Este atributo guarda un string nombreUsuario para poder guardar el nombre de usuario
     * ingresado por el administrador y así hacer las comparaciones pertinentes.
     */
    private String nombreUsuario;
    /**
     * Este atributo guarda un string correoElectronico para poder guardar el correo ingresado por el administrador y
     * así hacer las comparaciones pertinentes
     */
    private String correoElectronico;
    /**
     * Este atributo guarda un booleano resultado para confirmar si la operación se hizo de manera exitósa.
     */
    private boolean resultado;
    /**
     * Se guarda un objeto admi del tipo {@linkplain Administrador}.
     */
    private Administrador admin;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor de la clase.
     *
     * @param nombreUsuario recibe el nombre de usuario ingresado por el administrador.
     * @param correoElectronico recibe el correco Electrónico ingresado por el administrador.
     */
    public ControladorOlvidarContraAdmin(String nombreUsuario, String correoElectronico) {
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        resultado=false;
        administrador=controladorAdmis.getAdministradores();
    }

    public void archivito(String rutaimg,String png){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",rutaimg,"",png);
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();
    }

    /**
     * Este método se encarga de recibir el nombre de usuario buscarlo en la lista del objeto administrador que se
     * está implementado para así verificar si este existe y confirmar que esté ingresando los datos correctamente
     * en dado caso de que la persona ingrese el nombre de usuario correctamente se empieza a comparar si el correo
     * ingresado coincide con el correo registrado en el atribuo del administrador con el que se está trabajando. Si
     * El correo y el username fueron correctamente ingresados, se procede a enviar un correo gracias a la clase
     * {@linkplain ControladorEnviarCorreo}, usando justamente el correo ingresado y ya verificado. Luego se toma el
     * tokken generado por la clase para poder pasarlo a la siguiente ventana y clase encargada de verificar y hacer
     * efectiva la operación.
     *
     * @return resultado, retornamos si la ejecución de la operación se realizó exitósamente.
     */
    public boolean cambioContrasenia(){
        admin=administrador.get(nombreUsuario);
        if (admin==null){

            archivito("src/main/java/Imagenes/error-de-base-de-datos.png","error-de-base-de-datos.png");

            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">Tal parece que el nombre de usuario ingresado " +
                            "no se encuentra en la base de datos, por favor ingréselo nuevamente</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);
            nombreUsuario="";
        }
        else {
            if (!admin.getCorreoAdministrador().equals(correoElectronico)){

                archivito("src/main/java/Imagenes/error-de-base-de-datos.png","error-de-base-de-datos.png");

                ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
                Image img = icono.getImage();
                Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(scaledImage);
                JOptionPane.showMessageDialog(null,
                        "<html><p style=\"color:black;font-size:15px;\">Tal parece que el correo ingresado " +
                                "no se encuentra en la base de datos, por favor ingréselo nuevamente</p></html>",
                        "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE,icono);
                correoElectronico="";
            }
            else {
                if (!admin.getEstadoCuentaAdministrador()){

                    archivito("src/main/java/Imagenes/CuentaBloqueada.png","CuentaBloqueada.png");

                    ImageIcon icono = new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\">Estimado usuario, lamentamos informarle "+
                                    "que actualmente su su cuenta<br> se <b>encuentra bloqueada</b>. Por favor recupere su cuenta con su" +
                                    "superior</p></html>",
                            "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
                    nombreUsuario="";
                    correoElectronico="";
                }
                else {

                    archivito("src/main/java/Imagenes/Verificacion_correcta.png","Verificacion_correcta.png");

                    ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
                    Image img = icono.getImage();
                    Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(scaledImage);
                    JOptionPane.showMessageDialog(null,
                            "<html><p style=\"color:black;font-size:15px;\">¡Los datos se han verificado exitósamente! " +
                                    "<br><br>a continuación a su correo electrónico registrado se le hará llegar un tokken<br>" +
                                    "de verificación de información, por favor se le agredece ingresarlo.</p></html>",
                            "ENTRADA EXITÓSA", JOptionPane.INFORMATION_MESSAGE,icono);
                    resultado=true;
                }
            }
        }
        return resultado;
    }

    /**
     * Getter resultado.
     * @return resultado, retornamos el valor de la variable que indica si el método se llevó a cabo efectivamente.
     */
    public boolean esResultado() {
        return resultado;
    }

    /**
     * Getter admin.
     * @return admin, retornamos el objeto Administrador que se validó correctamente en el método para así poder
     * hacer efectivo el cambio de la contraseña en la clase pertinente.
     */
    public Administrador getAdmin() {
        return admin;
    }

    /**
     * Getter nombreUsuario.
     * @return nombreUsuario, retornamos nombre de usuario correcto,
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Getter correoElectronico.
     * @return correoElectronico, retornamos el correo electrónico correcto ingresado, ya validado claramente.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }
}
