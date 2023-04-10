
package entidad;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
   private String nombreCliente;

    public Cliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nombreCliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.nombreCliente, other.nombreCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombreCliente=" + nombreCliente + '}';
    }
   
}
