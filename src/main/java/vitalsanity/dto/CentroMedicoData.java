package vitalsanity.dto;

import java.util.Objects;

public class CentroMedicoData {

    private Long id;
    private String iban;
    private String direccion;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroMedicoData)) return false;
        CentroMedicoData centroMedicoData = (CentroMedicoData) o;
        return Objects.equals(id, centroMedicoData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
