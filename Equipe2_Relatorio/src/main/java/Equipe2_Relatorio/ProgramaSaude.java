package Equipe2_Relatorio;

import java.sql.SQLException;
import java.util.List;

public class ProgramaSaude {
	
	public List<String> ObterProgramas() throws SQLException, ClassNotFoundException
	{
    	RelatorioAcessoDados relatorioAcessoDados = new RelatorioAcessoDados();   
        return relatorioAcessoDados.ObterProgramas();
	}
	
	public int ObterParticipantesProgramas (String nomePrograma) throws SQLException, ClassNotFoundException
	{
    	RelatorioAcessoDados relatorioAcessoDados = new RelatorioAcessoDados();   
        return relatorioAcessoDados.ObterParticipantesProgramas(nomePrograma);
	}
}