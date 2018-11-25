package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
	}

	@Override
	public IAdaptadorVideo getVideoDAO() {
		// TODO Auto-generated method stub
		return AdaptadorVideoTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaVideosDAO getListaVideosDAO() {
		// TODO Auto-generated method stub
		return AdaptadorListaVideosTDS.getUnicaInstancia();

	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return AdaptadorUsuarioTDS.getUnicaInstancia();

	}

}
