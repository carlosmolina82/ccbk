package tienda.funciones;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fechas {

	public String DiferenciaDiasEntre2Fechas(Date fechaanterior) {

		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; // Milisegundos al
															// día
		long diferencia;

		// int anioant, mesant, diaant;
		// int anioact, mesact, diaact;

		Calendar calendarfant = Calendar.getInstance();
		calendarfant.setTime(fechaanterior);
		/*
		 * diaant = calendarfant.get(Calendar.DAY_OF_MONTH); mesant =
		 * calendarfant.get(Calendar.MONTH) + 1; anioant =
		 * calendarfant.get(Calendar.YEAR);
		 */
		Date fechaact = new Date();
		Calendar calendarfact = Calendar.getInstance();
		calendarfact.setTime(fechaact);
		/*
		 * diaact = calendarfact.get(Calendar.DAY_OF_MONTH); mesact =
		 * calendarfact.get(Calendar.MONTH) + 1; anioact =
		 * calendarfact.get(Calendar.YEAR)
		 */
		diferencia = (fechaact.getTime() - fechaanterior.getTime())
				/ MILLSECS_PER_DAY;
		return String.valueOf(diferencia);
	}

	public int calcularEdad(String fecha) {
		try {
			Date fechaNac = null;
			/**
			 * Se puede cambiar la mascara por el formato de la fecha que se
			 * quiera recibir, por ejemplo año mes día "yyyy-MM-dd"
			 */

			fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
			Calendar fechaNacimiento = Calendar.getInstance();
			// Se crea un objeto con la fecha actual
			Calendar fechaActual = Calendar.getInstance();
			// Se asigna la fecha recibida a la fecha de nacimiento.
			fechaNacimiento.setTime(fechaNac);
			// Se restan la fecha actual y la fecha de nacimiento
			int anio = fechaActual.get(Calendar.YEAR)
					- fechaNacimiento.get(Calendar.YEAR);
			int mes = fechaActual.get(Calendar.MONTH)
					- fechaNacimiento.get(Calendar.MONTH);
			int dia = fechaActual.get(Calendar.DATE)
					- fechaNacimiento.get(Calendar.DATE);
			// Se ajusta el año dependiendo el mes y el día
			if (mes < 0 || (mes == 0 && dia < 0)) {
				anio--;
			}
			// Regresa la edad en base a la fecha de nacimiento
			return anio;
		} catch (Exception e) {
			return -1;
		}
	}

}
