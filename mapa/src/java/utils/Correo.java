/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author miguel.duran
 */
public class Correo {
    private String mensaje;
    private String correoFrom;
    private String correoTo;
    private String servidor;
    private String puerto;
    private String asunto;
    private String password;
    
    public Correo() {
    }

    public void send() {
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", this.servidor);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", this.puerto);
        props.setProperty("mail.smtp.user", this.correoFrom);
        props.setProperty("mail.smtp.auth", "true");
        
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);
        //session.setDebug(true);
        
        try {
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correoFrom));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(this.correoTo));
            message.setSubject(this.asunto);
            message.setText(this.mensaje);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(this.correoFrom, this.password);
            t.sendMessage(message, message.getAllRecipients());
            
            // Cierre.
            t.close();
            
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCorreoFrom() {
        return correoFrom;
    }

    public void setCorreoFrom(String correoFrom) {
        this.correoFrom = correoFrom;
    }

    public String getCorreoTo() {
        return correoTo;
    }

    public void setCorreoTo(String correoTo) {
        this.correoTo = correoTo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }


}