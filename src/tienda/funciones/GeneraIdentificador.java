package tienda.funciones;

import tienda.dao.UsuarioDAOImpl;

public class GeneraIdentificador {

	// genera identificador de 6 digitos
	public String generaId6(String idmor) {
		String idm;
		int val;
		if (idmor == null) {
			idm = "1";
			val = 1;
		} else {
			idm = idmor;
			val = Integer.parseInt(idm) + 1;
		}

		if (val > 0 && val < 10) {
			idm = "00000" + String.valueOf(val);
		} else if (val > 9 && val < 100) {
			idm = "0000" + String.valueOf(val);
		} else if (val > 99 && val < 1000) {
			idm = "000" + String.valueOf(val);
		} else if (val > 999 && val < 10000) {
			idm = "00" + String.valueOf(val);
		} else if (val > 9999 && val < 100000) {
			idm = "0" + String.valueOf(val);
		} else {
			idm = String.valueOf(val);
		}
		return idm;
	}

	// Genera identificador de 5 digitos
	public String generaId5(String idvariable) {
		String idv;
		int val;
		if (idvariable == null) {
			idv = "1";
			val = 1;
		} else {
			idv = idvariable;
			val = Integer.parseInt(idv) + 1;
		}

		if (val > 0 && val < 10) {
			idv = "0000" + String.valueOf(val);
		} else if (val > 9 && val < 100) {
			idv = "000" + String.valueOf(val);
		} else if (val > 99 && val < 1000) {
			idv = "00" + String.valueOf(val);
		} else if (val > 999 && val < 10000) {
			idv = "0" + String.valueOf(val);
		} else {
			idv = String.valueOf(val);
		}
		return idv;
	}

	// Genera identificador de 3 digitos
	public String generaId3(String idvariable) {
		String idv;
		int val;
		if (idvariable == null) {
			idv = "1";
			val = 1;
		} else {
			idv = idvariable;
			val = Integer.parseInt(idv) + 1;
		}

		if (val > 0 && val < 10) {
			idv = "00" + String.valueOf(val);
		} else if (val > 9 && val < 100) {
			idv = "0" + String.valueOf(val);
		} else {
			idv = String.valueOf(val);
		}
		return idv;
	}

	// Genera identificador de 2 digitos
	public String generaId2(String idvariable) {
		String idv;
		int val;
		if (idvariable == null) {
			idv = "1";
			val = 1;
		} else {
			idv = idvariable;
			val = Integer.parseInt(idv) + 1;
		}

		if (val > 0 && val < 10) {
			idv = "0" + String.valueOf(val);
		} else if (val > 9 && val < 100) {
			idv = String.valueOf(val);
		}
		return idv;
	}

	// Genera identificador de 10 caracteres
	public String generaId10(String idvariable) {
		String var;
		int val;
		if (idvariable == null) {
			var = "1";
			val = 1;
		} else {
			var = idvariable;
			val = Integer.parseInt(var) + 1;
		}

		if (val > 0 && val < 10) {
			var = "000000000" + String.valueOf(val);
		} else if (val > 9 && val < 100) {
			var = "00000000" + String.valueOf(val);
		} else if (val > 99 && val < 1000) {
			var = "0000000" + String.valueOf(val);
		} else if (val > 999 && val < 10000) {
			var = "000000" + String.valueOf(val);
		} else if (val > 9999 && val < 100000) {
			var = "00000" + String.valueOf(val);
		} else if (val > 99999 && val < 1000000) {
			var = "0000" + String.valueOf(val);
		} else if (val > 999999 && val < 10000000) {
			var = "000" + String.valueOf(val);
		} else if (val > 9999999 && val < 100000000) {
			var = "00" + String.valueOf(val);
		} else if (val > 99999999 && val < 1000000000) {
			var = "0" + String.valueOf(val);
		} else {
			var = String.valueOf(val);
		}
		return var;
	}

	public String generarAlias(String nombres, String apellidos, int n) {
		UsuarioDAOImpl us = new UsuarioDAOImpl();
		String alias, resp = "";
		if (n == 0) {
			alias = nombres.substring(0, 1) + apellidos;
			System.err.println("Usuario: " + alias);
		} else {
			alias = nombres.substring(0, 1) + apellidos + String.valueOf(n);
			System.err.println("Usuario: " + alias);
		}
		resp = us.comprobarExistenciaUsuario(alias);
		if (resp.equals("noexiste"))
			return alias;
		else {
			return generarAlias(nombres, apellidos, n + 1);
		}
	}

}
