package co.ueb.matriculas.tests;

import java.math.BigDecimal;

import co.ueb.matriculas.logical.EstudianteLogical;

public class TestPromedio {
	
	public static void main(String[] args) {
		
		EstudianteLogical el = new EstudianteLogical();
		
		String promedio = el.obtenerPromedio(new BigDecimal(1016072428));
		
		System.out.println(promedio);		
		
	}
	
}