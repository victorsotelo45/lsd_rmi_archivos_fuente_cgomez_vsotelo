package servidorNotificaciones.dto;

import java.io.Serializable;
import servidorAlertas.dto.ClsAsintomaticoDTO;

public class ClsMensajeNotificacionDTO implements Serializable
{
        private ClsAsintomaticoDTO pacienteAsintomatico;
        private int frecuenciaCardiaca;
        private int frecuenciaRespiratoria;
	private float temperatura;
	
        
	public ClsMensajeNotificacionDTO(ClsAsintomaticoDTO pacienteAsintomatico, int frecuenciaCardiaca, int frecuenciaRespiratoria, float temperatura)
	{
            this.pacienteAsintomatico = pacienteAsintomatico;
            this.frecuenciaCardiaca = frecuenciaCardiaca;
            this.frecuenciaRespiratoria = frecuenciaRespiratoria;
            this.temperatura = temperatura;
            
	}

        public ClsAsintomaticoDTO getPacienteAsintomatico() {return this.pacienteAsintomatico;}
        public void setId(ClsAsintomaticoDTO pacienteAsintomatico) {this.pacienteAsintomatico = pacienteAsintomatico;}
        public int getFrecuenciaCardiaca() {return frecuenciaCardiaca;}
        public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {this.frecuenciaCardiaca = frecuenciaCardiaca;}
        public int getFrecuenciaRespiratoria() {return frecuenciaRespiratoria;}
        public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {this.frecuenciaRespiratoria = frecuenciaRespiratoria;}
        public float getTemperatura() {return temperatura;}
        public void setTemperatura(float temperatura) {this.temperatura = temperatura;}
		
}