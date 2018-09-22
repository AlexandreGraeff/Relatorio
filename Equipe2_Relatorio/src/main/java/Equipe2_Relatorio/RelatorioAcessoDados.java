package Equipe2_Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioAcessoDados {
	public List<String> ObterProgramas() throws SQLException, ClassNotFoundException{
		ConexaoAcessoDados conexao = new ConexaoAcessoDados();
    	Connection novaConexao = conexao.ObterConexao();
        String sql = "SELECT ID, NOME_DO_PROGRAMA, QTD_PARTICIPANTES FROM PROGRAMAS";	
        try(PreparedStatement stm = novaConexao.prepareStatement(sql);
            ResultSet retornoConsultaProgramas = stm.executeQuery()){
            List<String> listaProgramas = new ArrayList<>();
            while(retornoConsultaProgramas.next()){
            	listaProgramas.add(retornoConsultaProgramas.getString(2));
            }
            return listaProgramas;
        }
	}
	
	public int ObterParticipantesProgramas(String nomePrograma) throws SQLException, ClassNotFoundException{
		ConexaoAcessoDados conexao = new ConexaoAcessoDados();
    	Connection novaConexao = conexao.ObterConexao();
        String sql = "SELECT NOME_DO_PROGRAMA, QTD_PARTICIPANTES FROM PROGRAMAS";	
        try(PreparedStatement stm = novaConexao.prepareStatement(sql);
            ResultSet retornoConsultaParticipantes = stm.executeQuery()){
    		int quantidadeParticipantes = 0;
            while(retornoConsultaParticipantes.next()){
            	if(retornoConsultaParticipantes.getString(1).equals(nomePrograma))
            	{
            		quantidadeParticipantes = retornoConsultaParticipantes.getInt(2);
            	}
            }
            return quantidadeParticipantes;
        }
	}
}
