package servidorAlertas.dto;

import java.io.Serializable;

public class ClsAsintomaticoDTO implements Serializable
{
	private String nombres;
	private String apellidos;
	private String tipo_id;
	private int id;
	private String direccion;

        public ClsAsintomaticoDTO() {
         }
	
	public ClsAsintomaticoDTO(String nombres, String apellidos, String tipo_id, int id, String direccion)
	{
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipo_id = tipo_id;
		this.id = id;
		this.direccion = direccion;
		
	}
	
	public String getNombres(){return this.nombres;}
	public void setNombres(String nombres){this.nombres = nombres;}
	public String getApellidos(){return this.apellidos;}
	public void setApellidos(String apellidos){this.apellidos = apellidos;}
	public String getTipo_id(){return this.tipo_id;}
	public void setTipo_id(String tipo_id){this.tipo_id = tipo_id;}
	public int getId(){return this.id;}
	public void setId(int id){this.id = id;}
	public String getDireccion(){return this.direccion;}
	public void setDireccion(String direccion){this.direccion = direccion;}

    
}