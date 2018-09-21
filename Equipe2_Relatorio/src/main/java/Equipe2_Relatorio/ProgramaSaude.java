package Equipe2_Relatorio;

import java.sql.SQLException;
import java.util.List;

public class ProgramaSaude {
	
	public List<String> ObterProgramas() throws SQLException, ClassNotFoundException
	{
    	ConsultaBancoDados conexao = new ConsultaBancoDados();   
        return conexao.ObterProgramas();
	}
	
	public int ObterParticipantesProgramas (String nomePrograma) throws SQLException, ClassNotFoundException
	{
    	ConsultaBancoDados conexao = new ConsultaBancoDados();
        return conexao.ObterParticipantesProgramas(nomePrograma);
	}
}