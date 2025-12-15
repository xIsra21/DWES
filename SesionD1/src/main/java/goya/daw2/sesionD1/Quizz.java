package goya.daw2.sesionD1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Quizz {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer puntos;
	
	public Quizz() {}

	public Quizz(Integer puntos) {
		super();
		this.puntos = puntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos += puntos;
	}

	@Override
	public String toString() {
		return "Tu puntuación es de " + puntos + " puntos";
	}
	
	 
}
