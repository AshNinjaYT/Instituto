/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

/**
 *
 * @author joan
 */
public class GestorExcepcio extends Exception {

    public GestorExcepcio() {
    }

    public GestorExcepcio(String message) {
        super(message);
    }

    public GestorExcepcio(String message, Throwable cause) {
        super(message, cause);
    }

    public GestorExcepcio(Throwable cause) {
        super(cause);
    }

    public GestorExcepcio(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
