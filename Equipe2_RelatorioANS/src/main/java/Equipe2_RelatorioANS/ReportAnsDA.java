package Equipe2_RelatorioANS;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportAnsDA {
	
	private Connection connection;
	public ReportAnsDA()
	{	
		try 
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } 
		catch (ClassNotFoundException excecao) 
		{
            System.out.println("Não encontrou o driver de conexão ao banco");
            excecao.printStackTrace();
            return;
        }
        connection = null;
        try 
        {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "abc1234");
        } 
        catch (SQLException excecao) 
        {
            System.out.println("Falha na conexão");
            excecao.printStackTrace();
            return;

        }
        if (connection != null) 
        {
            System.out.println("Sucesso na conexão");
        } 
        else 
        {
            System.out.println("Falha na conexão");
            return;
        }
	}

	public int getQtyPrograms( FederativeUnitEnum state )
	{
		int qtyPrograms = 0;
		if ( state == FederativeUnitEnum.RS ) qtyPrograms = 10;
		if ( state == FederativeUnitEnum.SC ) qtyPrograms = 5;
		if ( state == FederativeUnitEnum.PR ) qtyPrograms = 2;
		return qtyPrograms;
	}

	public int getQtyProgramsClients( FederativeUnitEnum state )
	{
		int qtyProgramsClients = 0;
		if ( state == FederativeUnitEnum.RS ) qtyProgramsClients = 1000;
		if ( state == FederativeUnitEnum.SC ) qtyProgramsClients = 500;
		if ( state == FederativeUnitEnum.PR ) qtyProgramsClients = 200;
		return qtyProgramsClients;
	}
	
	public List<String> getPrograms()
	{
		List<String> programList = new ArrayList<String>();
		String sql = "SELECT ID, NOME_DO_PROGRAMA, QTD_PARTICIPANTES FROM PROGRAMAS";
        try 
        {
            try(PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet retornoConsulta = stm.executeQuery())
            {
	            while(retornoConsulta.next())
	            {
	            	programList.add(retornoConsulta.getString(2));
	              }    
	            }
        } catch (SQLException excecao) {
            System.out.println("Falha na conexão");
            excecao.printStackTrace();
            return null;	
        }
        return programList;
	}
	
	public int getQtyByProgram (String ProgramName)
	{
		String sql = "SELECT NOME_DO_PROGRAMA, QTD_PARTICIPANTES FROM PROGRAMAS";
		int qty = 0;
        try 
        {
            try(PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet retornoConsulta = stm.executeQuery())
            {
	            while(retornoConsulta.next())
	            {
	            	if(retornoConsulta.getString(1).equals(ProgramName))
	            	{
	            		qty = retornoConsulta.getInt(2);
	            	}
	              }    
	            }
        } catch (SQLException excecao) {
            System.out.println("Falha na conexão");
            excecao.printStackTrace();
            return 0;	
        }
        return qty;
	}

}
