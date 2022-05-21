package dam.db.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dam.db.AccesoDB;
import dam.model.Restaurante;
import dam.view.PConsultarRestaurante;

public class RestaurantePersistencia {
	private AccesoDB acceso;
	
	
	public RestaurantePersistencia() {
		acceso = new AccesoDB();
	}


	
	public ArrayList<Restaurante> seleccionarRestaurantes() {
		
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		
		String query = "SELECT " + RestauranteContract.COL_NOMBRE + "," + RestauranteContract.COL_CIUDAD + ", " + RestauranteContract.COL_DISTINCION + ", "
				+ RestauranteContract.COL_COCINA + ", " + RestauranteContract.COL_PRECIO_MIN + ", " + RestauranteContract.COL_PRECIO_MAX 
				+ " FROM " + RestauranteContract.NOM_TABLA;
		
		Connection conexion = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			stmt = conexion.createStatement();
			rst = stmt.executeQuery(query);
			
			Restaurante restaurante;
			String nombre;
			String ciudad;
			int distincion;
			String cocina;
			double precioMin;
			double precioMax;
			
			while (rst.next()) {
				nombre = rst.getString(1);
				ciudad = rst.getString(2);
				distincion = rst.getInt(3);
				cocina = rst.getString(4);
				precioMin = rst.getDouble(5);
				precioMax = rst.getDouble(6);
				
				restaurante = new Restaurante(0, nombre, null, ciudad, distincion, null, precioMin, precioMax, cocina, null, null);
				listaRestaurantes.add(restaurante);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (stmt != null) stmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return listaRestaurantes;
	}

	

	public ArrayList<Restaurante> filtrarRestaurante(String region, String distincion) {
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		
		String query = "SELECT " + RestauranteContract.COL_NOMBRE + "," + RestauranteContract.COL_CIUDAD + ", " + RestauranteContract.COL_DISTINCION + ", "
				+ RestauranteContract.COL_COCINA + ", " + RestauranteContract.COL_PRECIO_MIN + ", " + RestauranteContract.COL_PRECIO_MAX 
				+ " FROM " + RestauranteContract.NOM_TABLA;
		
		if (region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && !distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
			query += " WHERE " + RestauranteContract.COL_DISTINCION + "= ?";
			//select sin incluir where region y filtrar solo por distincion
		} else if (!region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
			//select sin incluir where distincion y filtrar solo por region
			query += " WHERE " + RestauranteContract.COL_REGION + "= ?";
		} else if (!region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && !distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
			query +=  " WHERE " + RestauranteContract.COL_REGION + "= ? AND " + RestauranteContract.COL_DISTINCION +
			"= ?";
			//Incluir en el where los dos filtros
		}

		Connection conexion = null;
		PreparedStatement pstmt = null; 
		ResultSet rslt = null; 
		
		try {
			conexion = acceso.hacerConexion();
			
			pstmt = conexion.prepareStatement(query);
			
			if (region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && !distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
				pstmt.setInt(1, Integer.parseInt(distincion));
			} else if (!region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
				pstmt.setString(1, region);
			} else if (!region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && !distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
				pstmt.setString(1, region);
				pstmt.setInt(2, Integer.parseInt(distincion));
			}
			
			pstmt.executeQuery(); 
			rslt = pstmt.executeQuery();
			
			Restaurante restaurante;
			String nombre;
			String ciudad;
			int dist;
			String cocina;
			double precioMin;
			double precioMax;
			
			
			while (rslt.next()) {
				nombre = rslt.getString(1);
				ciudad = rslt.getString(2);
				dist = rslt.getInt(3);
				cocina = rslt.getString(4);
				precioMin = rslt.getDouble(5);
				precioMax = rslt.getDouble(6); 
				
				restaurante = new Restaurante(0, nombre, null, ciudad, dist, null, precioMin, precioMax, cocina, null, null);
				listaRestaurantes.add(restaurante);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (pstmt != null) pstmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaRestaurantes;
	}

	

	public int eliminarRestaurante(String restauranteEliminar) {
		int resultado = 0;
		
		String query = "DELETE FROM " + RestauranteContract.NOM_TABLA + " WHERE " + RestauranteContract.COL_NOMBRE + "=?"; 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.hacerConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, restauranteEliminar);

			resultado = pstmt.executeUpdate(); 
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) { 
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { 
			try {
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return resultado;
	}


	
	public String consultarResNom(String nombre) {
		String cocina = null;
		
		String query =  "SELECT " +  RestauranteContract.COL_COCINA + " FROM " + RestauranteContract.NOM_TABLA + " WHERE " + RestauranteContract.COL_NOMBRE + " LIKE ?"; 
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nombre + "%");
			
			rst = pstmt.executeQuery(); //se usa este método porque es un SELECT
			
			if (rst.next()) { //con un if sirve porque solo va a recuperar un dato con la query
				cocina = rst.getString(1);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (pstmt != null) pstmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cocina;
	}

	

	public int registrarRestaurante(Restaurante nuevoRes) {
		int res = 0;
		
		String query = "INSERT INTO " + RestauranteContract.NOM_TABLA + " (" + RestauranteContract.COL_NOMBRE + ", "
				+ RestauranteContract.COL_REGION + ", " + RestauranteContract.COL_CIUDAD + ", " + RestauranteContract.COL_DISTINCION + ", " + RestauranteContract.COL_DIRECCION + ", "
				+ RestauranteContract.COL_PRECIO_MIN + ", " + RestauranteContract.COL_PRECIO_MAX + ", " + RestauranteContract.COL_COCINA + ", " + RestauranteContract.COL_TELEFONO
				+ ", " + RestauranteContract.COL_WEB + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			
			pstm.setString(1, nuevoRes.getNombre());
			pstm.setString(2, nuevoRes.getRegion());
			pstm.setString(3, nuevoRes.getCiudad());
			pstm.setInt(4, nuevoRes.getDistincion());
			pstm.setString(5, nuevoRes.getDireccion());
			pstm.setDouble(6, nuevoRes.getPrecioMin());
			pstm.setDouble(7, nuevoRes.getPrecioMax());
			pstm.setString(8, nuevoRes.getCocina());
			pstm.setString(9, nuevoRes.getTelefono());
			pstm.setString(10, nuevoRes.getWeb());
			
			res = pstm.executeUpdate();
						
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return res;
	}


	
	public ArrayList<String> seleccionarRegiones() {
		ArrayList<String> listaRegiones = new ArrayList<String>();
		
		String query = "SELECT DISTINCT " + RestauranteContract.COL_REGION + " FROM " + RestauranteContract.NOM_TABLA;
		
		Connection conexion = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		
		try {
			conexion = acceso.hacerConexion();
			stmt = conexion.createStatement();
			rst = stmt.executeQuery(query);
			
			String region;
			
			while (rst.next()) {
				region = rst.getString(1);
				
				listaRegiones.add(region);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (stmt != null) stmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return listaRegiones;
	}



	public Restaurante selecionarUnRestaurante(String nomRes) {
		Restaurante restaurante = null;
		
		String query = "SELECT * FROM " + RestauranteContract.NOM_TABLA + " WHERE " + RestauranteContract.COL_NOMBRE + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nomRes + "%");
			
			rst = pstmt.executeQuery();
			
			String nombre;
			String region;
			String ciudad;
			int distincion;
			String direccion;
			double precioMin;
			double precioMax;
			String cocina;
			String telefono;
			String web;
			
			if (rst.next()) {
				nombre = rst.getString(2);
				region = rst.getString(3);
				ciudad = rst.getString(4);
				distincion = rst.getInt(5);
				direccion = rst.getString(6);
				precioMin = rst.getDouble(7);
				precioMax = rst.getDouble(8);
				cocina = rst.getString(9);
				telefono = rst.getString(10);
				web = rst.getString(11);
				
				restaurante = new Restaurante(0, nombre, region, ciudad, distincion, direccion, precioMin, precioMax, cocina, telefono, web);
				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (pstmt != null) pstmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return restaurante;
	}



	public int modRestaurante(Restaurante restMod) {
		int resp = 0;
		
		String query = "UPDATE "  + RestauranteContract.NOM_TABLA + " SET " + RestauranteContract.COL_REGION + "= ?, " 
				+ RestauranteContract.COL_CIUDAD + "= ?, " + RestauranteContract.COL_DISTINCION + "= ?, " + RestauranteContract.COL_DIRECCION + "= ?, "
				+ RestauranteContract.COL_PRECIO_MIN + "= ?, " + RestauranteContract.COL_PRECIO_MAX + "= ?, " + RestauranteContract.COL_COCINA + "= ?, " + RestauranteContract.COL_TELEFONO
				+ "= ?, " + RestauranteContract.COL_WEB + "= ? WHERE " + RestauranteContract.COL_NOMBRE + "= ?";
		
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			
			pstm.setString(1, restMod.getRegion());
			pstm.setString(2, restMod.getCiudad());
			pstm.setInt(3, restMod.getDistincion());
			pstm.setString(4, restMod.getDireccion());
			pstm.setDouble(5, restMod.getPrecioMin());
			pstm.setDouble(6, restMod.getPrecioMax());
			pstm.setString(7, restMod.getCocina());
			pstm.setString(8, restMod.getTelefono());
			pstm.setString(9, restMod.getWeb());
			pstm.setString(10, restMod.getNombre());

	
			resp = pstm.executeUpdate();
						
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return resp;
	}
	
	
	
}
