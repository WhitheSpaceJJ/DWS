
package principal;

import pantallas.Ingreso;

public class Principal {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Ingreso.getInstance().setVisible(true);
            }
        });
    }
    
}
