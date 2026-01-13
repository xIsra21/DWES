package goya.daw2.sesionD1.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Participantes {
	
	@Id
	private String nombre;
	
	@OneToMany(
			mappedBy = "participantes",
			cascade = CascadeType.ALL)
	Set<Partida> partidas = new HashSet<Partida>();
	
	public Participantes() {
		super();
	}
	
	public Participantes(String nombre) {
		super();
		this.nombre = nombre;
	}

	public void addPartida(Partida p) {
		partidas.add(p);
		p.setParticipantes(this);
	}
	
	public void delPartida(Partida p) {
		partidas.remove(p);
		p.setParticipantes(this);
	}
	
	public Set<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(Set<Partida> partidas) {
		this.partidas = partidas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	
}
