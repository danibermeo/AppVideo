package modelo;

import java.util.LinkedList;
import java.util.List;

public class Video {
	private int codigo;
	private String url;
	private String titulo;
	private int numeroReproducciones;
	private List<Etiqueta> etiquetas;

	public Video(/* int codigo, */ String url, String titulo, Etiqueta... etiquetas) {
		this.codigo = 0;
		this.url = url;
		this.titulo = titulo;
		for (Etiqueta etiqueta : etiquetas) {
			this.etiquetas.add(etiqueta);
		}
		this.numeroReproducciones = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public List<Etiqueta> getEtiquetas() {
		return new LinkedList<>(this.etiquetas);
	}

	public int getNumeroReproducciones() {
		return numeroReproducciones;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrl() {
		return url;
	}

	public void incrementarNumeroReproducciones() {
		this.numeroReproducciones += 1;

	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setNumeroReproducciones(Integer numeroReproducciones) {
		this.numeroReproducciones = numeroReproducciones;
	}

	public void addEtiquetas(Etiqueta etiqueta) {
		this.etiquetas.add(etiqueta);
	}
}
