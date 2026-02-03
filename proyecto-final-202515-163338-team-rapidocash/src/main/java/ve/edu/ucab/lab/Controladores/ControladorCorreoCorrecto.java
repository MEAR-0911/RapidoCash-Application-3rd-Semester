package ve.edu.ucab.lab.Controladores;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * En esta clase controladora de correo, se busca asegurar que el correo ingresado por el usario o administrador
 * cumpla con la estructura coherente y necesaria para que pueda existir, así se puede evitar algún error en la
 * aplicación y base de datos.
 *
 * @author Antias Manuel
 * @version 2.16.1
 */

public class ControladorCorreoCorrecto {
    /**
     * Atributo estructuraRegular, en este se guarda el patrón de estructura regular utilizada para una
     * correcta entrada.
     */
    private String estructuraRegular;
    /**
     * Atributo entradaNumerica, en este se guarda la entrada numérica recibida en la ventana.
     */
    private String correo;
    /**
     * Atributo cantChar, en este se guarda la cantidad de dígitos que debería tener la entrada, especificada en
     * el código de las ventanas (más específicamente como parámetro en el constructor).
     */
    private boolean datoCorrecto;

    private String rutaImagenes;

    private ControladorCreadorDeRutasDeEjecutable rutasDeEjecutable;

    /**
     * Constructor del ControladorCorreoCorrecto.
     * @param correo correo ingresado por el usuario o administrador.
     */
    public ControladorCorreoCorrecto(String correo) {
        this.correo = correo;
        /** Esta estructura regular nos narra: la cadena ingresada puede contener cualquier tipo de letra o carácter
         * siempre que están concatenadas; la cadena puede contener números; es obligatorio que la cadena contenga
         * "@"; a su vez la cadena obligatoriamente debe llevar el dominio del correo, gmail, hotmail, outlook.*/
        estructuraRegular = "^[\\w\\.-]+@(?:[A-Za-z0-9-]+\\.)+(?:com|doc|est|ucab|edu|ve|es|net|org|mx|gmail|hotmail|" +
                "outlook)$";
    }

    /**
     * En este método se crea el código que se encargará de verificar que la entrada se ingrese correctamente.
     * Se creó un objeto Pattern para convertir la expresión regular en un patrón. Luego se utiliza un objeto
     * Matcher para poder comparar la entrada con el patrón creado. Por último, si la cadena ingresada es errónea
     * entonces se enviará un mensaje de error.
     *
     * @return datoCorrecto, valor para saber si el dato está correctamente ingresado (true/false).
     */
    public boolean confirmarCorreo(){
        rutasDeEjecutable=new ControladorCreadorDeRutasDeEjecutable("",
                "src/main/java/Imagenes/errorCorreo.png","","errorCorreo.png");
        rutasDeEjecutable.rutas();
        rutaImagenes=rutasDeEjecutable.getRutaImagenes();

        Pattern patron = Pattern.compile(estructuraRegular);
        Matcher coincidencia = patron.matcher(correo);
        datoCorrecto = coincidencia.matches();
        if (datoCorrecto==false) {
            ImageIcon icono=new javax.swing.ImageIcon(rutaImagenes);
            Image img = icono.getImage();
            Image scaledImage = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icono = new ImageIcon(scaledImage);
            JOptionPane.showMessageDialog(null,
                    "<html><p style=\"color:black;font-size:15px;\">¡Uups parece que cometió un error!<br><br>, " +
                            "el correo ingresado <b>no contiene una estructura permitida</b>,<br> por favor ingréselo nuevamente" +
                            "</p></html>",
                    "ERROR FATAL", JOptionPane.INFORMATION_MESSAGE, icono);
        }
        return datoCorrecto;
    }

    /**
     * Getter correoCorrecto.
     * @return datoCorrecto, valor del atributo datoCorrecto de la clase Controladora de correo ingresado.
     */
    public boolean correoCorrecto() {
        return datoCorrecto;
    }
}
