package application;

public class MyForm {

	double Width;
	double Height;

	//Konstruktoren
	public MyForm() {

	}
	public MyForm(double Width, double Height) {
		this.Width = Width;
		this.Height = Height;
	}

	//Attribute und Methoden
	public double getWidth() {
		return Width;
	}
	public void setWidth(double Width) {
		this.Width = Width;
	}


	public double getHeight() {
		return Height;
	}
	public void setHeight(double Height) {
		this.Height = Height;
	}


	private String EditorSize;
	public String getEditorSize() {
		return EditorSize;
	}
	public void setEditorSize(String EditorSize) {
		this.EditorSize = EditorSize;
	}


	private String FormType;
	public String getFormType() {
		return FormType;
	}
	public void setFormType(String FormType) {
		this.FormType = FormType;
	}


	private String FormVersion;
	public String getFormVersion() {
		return FormVersion;
	}
	public void setFormVersion(String FormVersion) {
		this.FormVersion = FormVersion;
	}


	private String FormInfo;
	public String getFormInfo() {
		return FormInfo;
	}
	public void setFormInfo(String FormInfo) {
		this.FormInfo = FormInfo;
	}


	private String FormLabel;
	public String getFormLabel() {
		return FormLabel;
	}
	public void setFormLabel(String FormLabel) {
		this.FormLabel = FormLabel;
	}



}
