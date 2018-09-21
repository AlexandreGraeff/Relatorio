package Equipe2_Relatorio;

import java.sql.SQLException;
import java.util.List;

public class UsuarioOperador {

	private String id;
	private String nome;
	private String senha;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<UsuarioOperador> ObterUsuarios() throws SQLException, ClassNotFoundException{
    	ConsultaBancoDados conexao = new ConsultaBancoDados();       
        return (List<UsuarioOperador>)conexao.ObterUsuarios();            
	}
	
}
