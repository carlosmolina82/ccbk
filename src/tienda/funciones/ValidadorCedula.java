package tienda.funciones;

public class ValidadorCedula {
	private String cedula;
	private String resp_correcta;

	public String getResp_correcta() {
		return resp_correcta;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String VerificaCedula(String ced) {
		String verificado = "", va = "";
		resp_correcta = "";
		if (ced != null) {
			cedula = ced;
		}

		// datos verificadores
		int[] vectorverificador = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		// almacena el numero de cedula digito a digito en el vector
		int[] vectorcedula = new int[10];
		// almacena el valor calculado multiplicando los vectores
		// vectorverificador con vectorcedula uno a uno
		int[] valorcalculado = new int[9];
		// car almacena el valor individual al multiplicar los vectores
		// vectorverificador y vectorcedula
		int car = 0;
		// sum almacena los valores sumados del vector valorcalculado
		int sum = 0;
		// mod almacena el modulo
		int mod = 0, valverificado = 0;

		if (!cedula.equals("")) {
			if (cedula.length() == 10) {
				for (int i = 0; i < cedula.length(); i++) {
					vectorcedula[i] = RetornaNumeroDecimal(cedula.charAt(i));
				}
				for (int j = 0; j < 9; j++) {
					car = vectorverificador[j] * vectorcedula[j];
					if (car < 10) {
						valorcalculado[j] = car;
					} else if (car >= 10) {
						va = String.valueOf(car);
						for (int k = 0; k < va.length(); k++) {
							// solicita el valor numerico correspondiente
							sum = sum + RetornaNumeroDecimal(va.charAt(k));
						}
						// sum = sum + int.Parse(va[k].ToString());
						valorcalculado[j] = sum;
						sum = 0;
					}

				}
				// Suma los valores que se obtienen luego de multiplicar los dos
				// vectores
				for (int m = 0; m < 9; m++) {
					sum = sum + valorcalculado[m];
				}
				// Determino el modulo10 de la sumatoria del vector resultante
				mod = sum % 10;

				if (mod != 0) {
					valverificado = 10 - mod;
				} else {
					valverificado = mod;
				}
				// Compara el valor resultado del mod10 con el ultimo digito de
				// la cedula
				if (vectorcedula[9] == valverificado) {
					// verificado =
					// "N° de cedula correcto, Haz clic para continuar con el
					// registro..";//
					// Si los digitos son iguales
					verificado = "ok";
					resp_correcta = verificado;
				} else {
					// verificado = "error";
					verificado = "Documento incorrecto";// Si los digitos
					// son
					// distintos
				}
			} else {
				// verificado = "error";
				verificado = "El documento contiene 10 digitos";
			}
		} else {
			// verificado = "error";
			verificado = "Digite el documento";
		}
		// retorna el valor verificado
		return verificado;
	}

	private int RetornaNumeroDecimal(char c) {
		int num = -1;
		if (c == '0') {
			num = 0;
		} else if (c == '1') {
			num = 1;
		} else if (c == '2') {
			num = 2;
		} else if (c == '3') {
			num = 3;
		} else if (c == '4') {
			num = 4;
		} else if (c == '5') {
			num = 5;
		} else if (c == '6') {
			num = 6;
		} else if (c == '7') {
			num = 7;
		} else if (c == '8') {
			num = 8;
		} else if (c == '9') {
			num = 9;
		}
		return num;
	}
}
