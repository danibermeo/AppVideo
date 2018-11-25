package persistencia;

import java.text.SimpleDateFormat;
import java.util.List;

import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	@SuppressWarnings("unused")
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	@SuppressWarnings("unused")
	private SimpleDateFormat dateFormat;

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}

	@Override
	public void registrarUsuario(Usuario cliente) {
		System.out.println("Estamos probando que esto  funcione bien ");

	}

	@Override
	public void borrarUsuario(Usuario cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarUsuario(Usuario cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario recuperarCliente(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}
