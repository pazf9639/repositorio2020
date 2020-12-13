package mx.com.example.fragmento3.model;

import java.util.Objects;

public class Juego {
    private String idjuego;
    private String titulo;
    private String descripcion;
    private int clasificacion;
    private String imagen;

    public Juego() {
    }

    public Juego(String idjuego, String titulo, String descripcion, int clasificacion, String imagen) {
        this.idjuego = idjuego;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.clasificacion = clasificacion;
        this.imagen = imagen;
    }

    public String getIdJuego() {
        return idjuego;
    }

    public void setIdJuego(String idJuego) {
        this.idjuego = idJuego;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juego juego = (Juego) o;
        return Objects.equals(idjuego, juego.idjuego);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjuego);
    }
}
