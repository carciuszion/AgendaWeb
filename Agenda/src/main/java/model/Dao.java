package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Dao.
 */
public class Dao {
	/* Modulo de conexão */
	/** The driver. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3307/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "carcius";
	
	/** The password. */
	private String password = "zion05";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String sql = "insert into contatos(nome, fone, email) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String sql = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Remover contato.
	 *
	 * @param contato the contato
	 */
	public void removerContato(JavaBeans contato) {
		String sql = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contato.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContato() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String sql = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				String idcon = rst.getString(1);
				String nome = rst.getString(2);
				String fone = rst.getString(3);
				String email = rst.getString(4);
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String sql = "Select * from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contato.getIdcon());
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				contato.setIdcon(rst.getString(1));
				contato.setNome(rst.getString(2));
				contato.setFone(rst.getString(3));
				contato.setEmail(rst.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// teste de conexão
	/*
	 * public void testeConexao() { try { Connection con = conectar();
	 * System.out.println(con.getSchema()); con.close(); } catch (Exception e) {
	 * System.out.println(e); } }
	 */

}
