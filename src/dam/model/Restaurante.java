package dam.model;

import dam.view.PConsultarRestaurante;

public class Restaurante {

	public static final String[] DISTINCION = {PConsultarRestaurante.TODOS_ELEMENTOS, "1", "2", "3"};
	public static final String[] COCINA = {"Creativa", "Moderna", "Tradicional", "Regional", "Fusión"};
	public static final String[] TODAS_CCAA = {"Andalucia", "Aragón", "Asturias", "Canarias", "Cantabria", "Castilla y León", "Castilla - La Mancha", "Cataluña", "Ceuta", "Comunidad Valenciana"
												,"Extremadura", "Galicia", "Islas Baleares", "La Rioja", "Madrid", "Melilla", "Murcia", "Navarra", "País Vasco"};
	
	private int id;
	private String nombre;
	private String region;
	private String ciudad;
	private int distincion;
	private String direccion;
	private double precioMin;
	private double precioMax;
	private String cocina;
	private String telefono;
	private String web;
	
	
	public Restaurante(int id, String nombre, String region, String ciudad, int distincion, String direccion,
			double precioMin, double precioMax, String cocina, String telefono, String web) {
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.ciudad = ciudad;
		this.distincion = distincion;
		this.direccion = direccion;
		this.precioMin = precioMin;
		this.precioMax = precioMax;
		this.cocina = cocina;
		this.telefono = telefono;
		this.web = web;
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getRegion() {
		return region;
	}


	public String getCiudad() {
		return ciudad;
	}


	public int getDistincion() {
		return distincion;
	}


	public String getDireccion() {
		return direccion;
	}


	public double getPrecioMin() {
		return precioMin;
	}


	public double getPrecioMax() {
		return precioMax;
	}


	public String getCocina() {
		return cocina;
	}


	public String getTelefono() {
		return telefono;
	}


	public String getWeb() {
		return web;
	}
	
	
}
