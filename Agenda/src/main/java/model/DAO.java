package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;


public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	private String user = "root";
	private String password = "1580";

	// METODO DE CONEXAO

	@SuppressWarnings("unused")
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println("Erro não foi possivel conectar no banco de dados " + e);
			return null;

		}

	}
	
	//***CRUD CREATE*****
	
	 public void inserirContato(javaBeans contato) {
		 
		 String create = "insert into contato(nome,fone,email)values(?,?,?) ";
		 
		 try {
			Connection con = conectar();
			//prepara a QUERY para conexao no banco de dados            //MODELO					//METODO
			PreparedStatement pst = con.prepareStatement(create);   //	PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			pst.execute();
			//System.out.println("Dados cadastrado com sucesso");
			con.close();
			 
			 
		} catch (Exception e) {
			System.out.println("Erro ao gravar dados no banco"+e);
		}
		 
	 }
	 
	 public ArrayList<javaBeans>listarContatos(){
		
		 //Criando o objeto para acessar a classe javabeans
		 
		 ArrayList<javaBeans>contato = new ArrayList<>();
		 
		 
	String read = "select * from contato order by nome";
	
	try {
		Connection con = conectar();
		PreparedStatement pst = con.prepareStatement(read);
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {
			
			//variaveis de apoio que recebe os dados do banco
			String idcon = rs.getString(1);
			String nome = rs.getString(2);
			String fone = rs.getString(3);
			String email = rs.getString(4);
			
			//populando o arraylist
			contato.add(new javaBeans(idcon,nome,fone,email));
	
		}
		con.close();
		return contato;
	} catch (Exception e) {
		System.out.println("Erro ao retornar contato"+e);
	}
	return null;
	 }
	 /*CRUD UPDATE*/
	 
	 //SELECIONANDO CONTATO
	public void selecionarContato(javaBeans contato) {
		String read2 ="select * from contato where idcon=?";
		
		 try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
		    ResultSet rs = pst.executeQuery();
		    
		    while (rs.next()) {
		    	
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				
			}
		    con.close();
		} catch (Exception e) {
			System.out.println("Erro ao Selecionar Contato"+e);
		}
		
	}
}