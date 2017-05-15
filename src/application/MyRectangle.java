package application;

import javafx.scene.shape.Rectangle;

public class MyRectangle extends Rectangle {

	//Konstruktoren
	public MyRectangle() {
		super();
	}
	public MyRectangle(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	//Attribute und Methoden

	private String ID;
	public String getID() {
		return this.ID;
	}
	public void setID(String value) {
		this.ID = value;
	}

	private String Type;
	public String getType() {
		return this.Type;
	}
	public void setType(String value) {
		this.Type = value;
	}

	private String Label;
	public String getLabel() {
		return this.Label;
	}
	public void setLabel(String value) {
		this.Label = value;
	}

	private String Prefill;
	public String getPrefill() {
		return this.Prefill;
	}
	public void setPrefill(String value) {
		this.Prefill = value;
	}

	private boolean readOnly;
	public boolean getReadOnly() {
		return this.readOnly;
	}
	public void setReadOnly(boolean value) {
		this.readOnly = value;
	}

	private boolean warnIfEmpty;
	public boolean getWarnIfEmpty() {
		return this.warnIfEmpty;
	}
	public void setWarnIfEmpty(boolean value) {
		this.warnIfEmpty = value;
	}

	private boolean mandatory;
	public boolean getMandatory() {
		return this.mandatory;
	}
	public void setMandatory(boolean value) {
		this.mandatory = value;
	}

	private boolean prefillIfEditAsNew;
	public boolean getPrefillIfEditAsNew() {
		return this.prefillIfEditAsNew;
	}
	public void setPrefillIfEditAsNew(boolean value) {
		this.prefillIfEditAsNew = value;
	}

	private boolean showInOverview;
	public boolean getShowInOverview() {
		return this.showInOverview;
	}
	public void setShowInOverview(boolean value) {
		this.showInOverview = value;
	}


}
