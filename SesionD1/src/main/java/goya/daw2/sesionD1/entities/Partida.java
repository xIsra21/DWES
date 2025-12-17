package goya.daw2.sesionD1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Partida {

	@Id
	private String fecha;
	private Integer puntuacion;
	
	@ManyToOne
	Participantes participantes;
	
	public Partida() {}
	
	public Partida(String fecha, Integer puntuacion) {
		super();
		this.fecha = fecha;
		this.puntuacion = puntuacion;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public Integer getPuntuacion() {
		return puntuacion;
	}
	
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public Participantes getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Participantes participantes) {
		this.participantes = participantes;
	}

	@Override
	public String toString() {
		return "Partida [fecha=" + fecha + ", puntuacion=" + puntuacion + "]";
	}
	
	
}
