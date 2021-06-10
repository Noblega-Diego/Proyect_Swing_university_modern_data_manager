
package com.uni.model;
import java.util.List;
import com.uni.dao.DaoCarrera;
/**
 *
 * @author diego
 */
public class Carrera {
    private int codigo;
    private String nombre;
    private String duracion;

    public Carrera() {
    }
    
    public Carrera(int codigo, String nombre, String duracion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracion = duracion;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    
    
    
    //CRUD
    
    public List<Carrera> listarCarreras(){
        return DaoCarrera.read();
    }
    
    public void updateCarrera(Carrera carrera){
        DaoCarrera.update(carrera);
    }
    
    public void createCarrera(Carrera carrera){
        DaoCarrera.agregar(carrera);
    }
    
    public void delateCarrera(int codigo){
        DaoCarrera.delete(codigo);
    }
    
}
