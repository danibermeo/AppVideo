package modelo;

import java.util.LinkedList;

public class ListaVideos {
	private int codigo;
	private String nombreLista;
	private int numeroVideos;
	private LinkedList<Video> videos;

	public ListaVideos(String nombreLista, Video... videos) {
		this.nombreLista = nombreLista;
		for (Video video : videos) {
			this.videos.add(video);
		}
		this.numeroVideos = this.videos.size();
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public int getNumeroVideos() {
		return numeroVideos;
	}

	public LinkedList<Video> getVideos() {
		return new LinkedList<>(this.videos);
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public void setNumeroVideos(int numeroVideos) {
		this.numeroVideos = numeroVideos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
