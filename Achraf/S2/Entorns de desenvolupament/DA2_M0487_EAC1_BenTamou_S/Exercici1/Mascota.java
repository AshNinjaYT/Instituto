package protectora;
public class Mascota {
 private String nom;
 private String especie;
 private Date dataEntrada;
 private int edat;
 private boolean disponibleAdopcio;
 public String getnom() { return nom; }
 public String getEspecie() { return especie; }
 public Date getDataEntrada() { return dataEntrada; }
 public int getEdat() { return edat; }
 public boolean isDisponibleAdopcio() { return disponibleAdopcio; }
 public void setNom(String nom) { this.nom = nom; }
 public void setEspecie(String especie) { this.especie = especie; }
 public void setDataEntrada(Date dataEntrada) { this.dataEntrada = dataEntrada; }
 public void setEdat(int edat) { this.edat = edat; }
 public void setDisponibleAdopcio(boolean disponibleAdopcio) { this.disponibleAdopcio =
disponibleAdopcio; }
 public boolean estaDisponibleAdopcio() { return this.disponibleAdopcio; }
}
