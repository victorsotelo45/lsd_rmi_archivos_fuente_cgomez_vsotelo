package servidorNotificaciones.dto;

import java.io.Serializable;
import servidorAlertas.dto.ClsAsintomaticoDTO;

public class ClsMensajeNotificacionDTO implements Serializable
{
        private ClsAsintomaticoDTO pacienteAsintomatico;
        private int frecuenciaCardiaca;
        private int frecuenciaRespiratoria;
	private float temperatura;
        private String fechaAlerta;
        private String horaAlerta;
        private String mensaje;
        
	public ClsMensajeNotificacionDTO(ClsAsintomaticoDTO pacienteAsintomatico, int frecuenciaCardiaca, int frecuenciaRespiratoria, float temperatura, String fechaAlerta, String horaAlerta, String mensaje)
	{
            this.pacienteAsintomatico = pacienteAsintomatico;
            this.frecuenciaCardiaca = frecuenciaCardiaca;
            this.frecuenciaRespiratoria = frecuenciaRespiratoria;
            this.temperatura = temperatura;
            this.fechaAlerta = fechaAlerta;
            this.horaAlerta = horaAlerta;
            this.mensaje = mensaje;
            
	}

        public ClsAsintomaticoDTO getPacienteAsintomatico() {return this.pacienteAsintomatico;}
        public void setPacienteAsintomatico(ClsAsintomaticoDTO pacienteAsintomatico){this.pacienteAsintomatico = pacienteAsintomatico;}
        public int getFrecuenciaCardiaca() {return frecuenciaCardiaca;}
        public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {this.frecuenciaCardiaca = frecuenciaCardiaca;}
        public int getFrecuenciaRespiratoria() {return frecuenciaRespiratoria;}
        public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {this.frecuenciaRespiratoria = frecuenciaRespiratoria;}
        public float getTemperatura() {return temperatura;}
        public void setTemperatura(float temperatura) {this.temperatura = temperatura;}
        public String getFechaAlerta(){return fechaAlerta;}
        public void setFechaAlerta(String fechaAlerta){this.fechaAlerta = fechaAlerta;}
        public String getHoraAlerta(){return horaAlerta;}
        public void setHoraAlerta(String horaAlerta){this.horaAlerta = horaAlerta;}
        public String getMensaje(){return mensaje;}
        public void setMensaje(String mensaje){this.mensaje = mensaje;}
		
}