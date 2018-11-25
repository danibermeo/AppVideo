package persistencia;

import java.util.List;

import modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	public int registrarUsuario(Usuario cliente);
	public void borrarUsuario(Usuario cliente);
	public void modificarUsuario(Usuario cliente);
	public Usuario recuperarCliente(int codigo);
	public List<Usuario> recuperarTodosUsuarios();
}
