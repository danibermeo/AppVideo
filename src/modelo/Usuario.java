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
	private Usuario login;
	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	private JDateChooser fechaNacimiento;
	private LinkedList<Video> listaReciente;
	private Map<Integer, ListaVideos> listasVideos;
	private Filtro filtro;

	public Usuario(int codigo, Usuario login, String passwd, String nombre, String apellidos, String email,
			JDateChooser fecha, Boolean premiun) {
		this.codigo = codigo;
		this.login = login;
		this.password = passwd;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fecha;
		this.email = email;
		this.listaReciente = new LinkedList<>();
		this.listasVideos = new HashMap<>();

	}

	public Usuario(int codigo, Usuario login, String passwd, String nombre, String apellidos, String email,
			JDateChooser fecha) {
		this(codigo, login, passwd, nombre, apellidos, email, fecha, false);
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

	public Usuario getLogin() {
		return login;
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

	public void setLogin(Usuario login) {
		this.login = login;
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
