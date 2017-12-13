package chiri.tienda;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import chiri.tienda.Car;
import chiri.tienda.CarService;

@SuppressWarnings("serial")
public class EditView implements Serializable {

	private List<Car> cars1;
	private List<Car> cars2;

	private CarService service;

	@PostConstruct
	public void init() {
		try {
			// cars1 = service.createCars(10);
			// cars2 = service.createCars(10);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("INICIANDO ------------------------------------------ " + e.getMessage());
		}
	}

	public List<Car> getCars1() {
		try {
			cars1 = service.createCars(10);

			return cars1;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("DESDE GET ------------------------------------------ " + e.getMessage());
			return null;
		}
	}

	public List<Car> getCars2() {
		return cars2;
	}

	public List<String> getBrands() {
		return service.getBrands();
	}

	public List<String> getColors() {
		return service.getColors();
	}

	public void setService(CarService service) {
		this.service = service;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}