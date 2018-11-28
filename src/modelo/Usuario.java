package modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.toedter.calendar.JDateChooser;

public class Usuario {
	// persistencia
	private int codigo;
	// datos del Usuario
	private Boolean premium;
	private final String usuario;
	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	private JDateChooser fechaNacimiento;
	private LinkedList<Video> listaReciente;
	private Map<Integer, ListaVideos> listasVideos;
	private Filtro filtro;
	public final static int NUM_MAX_RECIENTES = 10;

	public Usuario(String login, String passwd, String nombre, String apellidos, String email, JDateChooser fecha,
			Boolean premiun) {
		// this.codigo = codigo;
		this.codigo = 0;
		this.usuario = login;
		this.password = passwd;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fecha;
		this.email = email;
		this.listaReciente = new LinkedList<>();
		this.listasVideos = new HashMap<>();

	}

	public Usuario(String login, String passwd, String nombre, String apellidos, String email, JDateChooser fecha) {
		this(login, passwd, nombre, apellidos, email, fecha, false);
	}

	public List<Video> getFiltro() {
		LinkedList<Video> lista = null;
		if (isPremium() == true) {
			filtro = new MisListas();
			lista = (LinkedList<Video>) filtro.filtarVideo();
			return lista;
		}

		filtro = new NoFiltro();
		lista = (LinkedList<Video>) filtro.filtarVideo();
		return lista;

	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombreUsuario() {
		return usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getEmail() {
		return email;
	}

	public JDateChooser getFechaNacimiento() {
		return fechaNacimiento;
	}

	public LinkedList<Video> getListaReciente() {
		return new LinkedList<>(this.listaReciente);
	}

	public Map<Integer, ListaVideos> mapListasVideos() {
		return new HashMap<>(this.listasVideos);
	}

	public LinkedList<ListaVideos> getListasVideos() {

		return new LinkedList<>(this.listasVideos.values());// (LinkedList<ListaVideos>)
															// this.listasVideos.values();
	}

	public void addListaAListaVideos(ListaVideos listaVideos) {
		// assert(listasCanciones.getID() != null);
		this.listasVideos.put(listaVideos.getCodigo(), listaVideos);

	}

	public void addRecienteVideos(Video video) {
		if (listaReciente.size() == NUM_MAX_RECIENTES)
			if (!listaReciente.stream().anyMatch(e -> e.getCodigo() == video.getCodigo())) {
				listaReciente.removeLast();
			} else if (!listaReciente.stream().anyMatch(e -> e.getCodigo() == video.getCodigo())) {
				listaReciente.add(video);
			}
	}

	public String getPassword() {
		return password;
	}

	public Boolean getPremium() {
		return premium;
	}

	public Boolean isPremium() {
		return premium;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFechaNacimiento(JDateChooser fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
