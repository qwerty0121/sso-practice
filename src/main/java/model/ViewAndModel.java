package model;

public class ViewAndModel {

	private String view;

	private Object model;

	public ViewAndModel(String view, Object model) {
		this.view = view;
		this.model = model;
	}

	public String getView() {
		return view;
	}

	public Object getModel() {
		return model;
	}

}
