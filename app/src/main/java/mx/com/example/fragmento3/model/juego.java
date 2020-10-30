package mx.com.example.fragmento3.model;

import java.util.Objects;

public class juego {
    private int idjuego;
    private String imagen;
    private String titulo;
    private int clasificacion;
    private String descripcion;

    public juego(int idjuego, String imagen, String titulo, int clasificacion, String descripcion) {
        this.idjuego = idjuego;
        this.imagen = imagen;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
    }

    public int getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        juego juego = (juego) o;
        return idjuego == juego.idjuego;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjuego);
    }
}
