package application;

import java.io.File;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;

public class MyController {

	@FXML Group root;
	@FXML ImageView imageView;
	@FXML AnchorPane anchorPane;
	@FXML StackPane stackPane;
	@FXML Canvas canvas;
	@FXML Pane pane;

	@FXML Button selectToolButton;
	@FXML Button createToolButton;
	@FXML Button loadImageButton;
	@FXML Button loadXMLButton;
	@FXML Button saveXMLButton;
	@FXML Button textfieldButton;
	@FXML Button textareaButton;
	@FXML Button checkboxButton;

	@FXML TextField IDText;
	@FXML TextField XText;
	@FXML TextField YText;
	@FXML TextField WidthText;
	@FXML TextField HeightText;
	@FXML TextField TypeText;
	@FXML TextField LabelText;
	@FXML TextField PrefillText;

	@FXML CheckBox ReadOnlyCheckbox;
	@FXML CheckBox WarnIfEmptyCheckbox;
	@FXML CheckBox MandatoryCheckbox;
	@FXML CheckBox PrefillIfEditAsNewCheckbox;
	@FXML CheckBox ShowInOverviewCheckbox;

	//Tab2
	@FXML CheckBox MissingPrefillCheckbox;
	@FXML CheckBox MissingIDCheckbox;
	@FXML CheckBox DuplicateIDCheckbox;
	@FXML CheckBox DuplicatePrefillCheckbox;

	//Tab3
	@FXML TextField FormTypeText;
	@FXML TextField FormLabelText;
	@FXML TextField FormInfoText;
	@FXML CheckBox xxxCheckbox;
	@FXML CheckBox yyyCheckbox;

	public MyForm form = new MyForm();

	//Standardwerte

	//Breite des Rechtecks
	private double rectW;
	//Höhe des Rechtecks
	private double rectH;
	//Farbe des Rechtecks
	private Color rectColor;
	//Transparenz des Rechtecks, gültiger Bereich: 0.0 - 1.0
	public final double rectStandardAlpha = 0.7;
	public final double rectSelectedAlpha = 1.0;
	//Typ des Rechtecks
	private String rectType;
	//ID-Counter der Rechtecke
	private int rectCounter;
	private String rectLabel = "x";
	private String rectPrefill = "";
	private boolean rectReadOnly = false;
	private boolean rectWarnIfEmpty = false;
	private boolean rectMandatory = false;
	private boolean rectPrefillIfEditAsNew = false;
	private boolean rectShowInOverview = false;

	public final Color rectStandardStrokeColor = Color.BLACK;
	public final Color rectAttentionStrokeColor = Color.RED;
	public final double rectStandardStrokeWidth = 1;
	public final StrokeType rectStandardStrokeType = StrokeType.INSIDE;

	private boolean isCreateMode;
	private boolean isMissingPrefillMode;
	private boolean isMissingIDMode;
	private boolean isDuplicateIDMode;
	private boolean isDuplicatePrefillMode;

	private boolean isListenerActive = true;

	private MyRectangle rect = null;
	private MyRectangle currRect = null;
	private MyRectangle lastRect = null;

	private ArrayList<MyRectangle> al = new ArrayList<MyRectangle>();

	RectangleTools rectTools = new RectangleTools();
	
	/**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// force the field to be numeric only
    	IDText.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			if (isListenerActive) {
    				writeTextfieldToRectangle();
    			}
            }
        });
    	XText.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			if (isListenerActive) {
    				if (!newValue.matches("\\d*")) {
						XText.setText(newValue.replaceAll("[^\\d]", ""));
					}
    				writeTextfieldToRectangle();
    			}
            }
        });
    	YText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isListenerActive) {
                	if (!newValue.matches("\\d*")) {
						YText.setText(newValue.replaceAll("[^\\d]", ""));
					}
    				writeTextfieldToRectangle();
                }
            }
        });
    	WidthText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (isListenerActive) {
            		if (!newValue.matches("\\d*")) {
						WidthText.setText(newValue.replaceAll("[^\\d]", ""));
					}
            		writeTextfieldToRectangle();
            	}
            }
        });
    	HeightText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (isListenerActive) {
            		if (!newValue.matches("\\d*")) {
						HeightText.setText(newValue.replaceAll("[^\\d]", ""));
					}
            		writeTextfieldToRectangle();
            	}
            }
        });
    	TypeText.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			if (isListenerActive) {
    				writeTextfieldToRectangle();
    			}
            }
        });
    	LabelText.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			if (isListenerActive) {
    				writeTextfieldToRectangle();
    			}
            }
        });
    	PrefillText.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			if (isListenerActive) {
    				writeTextfieldToRectangle();
    			}
            }
        });

    	//Tab2

    	MissingIDCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                	isMissingIDMode = true;

                	isMissingPrefillMode = false;
                	MissingPrefillCheckbox.setSelected(false);
                	isDuplicateIDMode = false;
                	DuplicateIDCheckbox.setSelected(false);
                	isDuplicatePrefillMode = false;
                	DuplicatePrefillCheckbox.setSelected(false);

                	MarkRects();
                }
                else {
                	isMissingIDMode = false;

                	MarkRects();
                }
            }
        });
    	MissingPrefillCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
            	if (new_val) {
                	isMissingPrefillMode = true;

                	isMissingIDMode = false;
                	MissingIDCheckbox.setSelected(false);
                	isDuplicateIDMode = false;
                	DuplicateIDCheckbox.setSelected(false);
                	isDuplicatePrefillMode = false;
                	DuplicatePrefillCheckbox.setSelected(false);

                	MarkRects();
                }
                else {
                	isMissingPrefillMode = false;

                	MarkRects();
                }
        	}
        });
    	DuplicateIDCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
            	if (new_val) {
                	isDuplicateIDMode = true;

                	isMissingIDMode = false;
                	MissingIDCheckbox.setSelected(false);
                	isMissingPrefillMode = false;
                	MissingPrefillCheckbox.setSelected(false);
                	isDuplicatePrefillMode = false;
                	DuplicatePrefillCheckbox.setSelected(false);

                	MarkRects();
                }
                else {
                	isDuplicateIDMode = false;

                	MarkRects();
                }
            }
        });
    	DuplicatePrefillCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
            	if (new_val) {
                	isDuplicatePrefillMode = true;

                	isMissingIDMode = false;
                	MissingIDCheckbox.setSelected(false);
                	isMissingPrefillMode = false;
                	MissingPrefillCheckbox.setSelected(false);
                	isDuplicateIDMode = false;
                	DuplicateIDCheckbox.setSelected(false);

                	MarkRects();
                }
                else {
                	isDuplicatePrefillMode = false;

                	MarkRects();
                }
            }
        });

    	canvas.setWidth(826);
    	canvas.setHeight(1169);

    	form.setWidth(826);
    	form.setHeight(1169);

    	GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		al.clear();

		rectW = 100;
		rectH = 20;
		rectColor = Color.LIGHTSKYBLUE;
		rectType = "textfield";
		rectCounter = 0;

		isCreateMode = false;
		isMissingPrefillMode = false;
		isMissingIDMode = false;
		isDuplicateIDMode = false;
		isDuplicatePrefillMode = false;
    }

    private void MarkRects() {

    	for (int i = al.size() - 1; i >= 0; i--) {
	    	al.get(i).setStroke(rectStandardStrokeColor);
		}

    	if (isMissingIDMode) {
			for (int i = al.size() - 1; i >= 0; i--) {
		    	if (al.get(i).getID() == null || al.get(i).getID().equals("")) {
		    		al.get(i).setStroke(rectAttentionStrokeColor);
		    	}
			}
		} else if (isMissingPrefillMode) {
			for (int i = al.size() - 1; i >= 0; i--) {
		    	//if (al.get(i).getPrefill().length() == 0) {
        		if (al.get(i).getPrefill().equals("")) {
		    		al.get(i).setStroke(rectAttentionStrokeColor);
		    	}
			}
		} else if (isDuplicateIDMode) {
			for (int j = al.size() -1; j >= 0; j --) {
        		for (int i = al.size() - 1; i >= 0; i--) {
        			if (al.get(j) != al.get(i) && al.get(i).getID().equals(al.get(j).getID())) {
		    			al.get(j).setStroke(rectAttentionStrokeColor);
		    			al.get(i).setStroke(rectAttentionStrokeColor);
		    		}
        		}
        	}
		} else if (isDuplicatePrefillMode) {
			for (int j = al.size() -1; j >= 0; j --) {
        		for (int i = al.size() - 1; i >= 0; i--) {
        			if (al.get(j) != al.get(i) && al.get(i).getPrefill().equals(al.get(j).getPrefill())) {
		    			al.get(j).setStroke(rectAttentionStrokeColor);
		    			al.get(i).setStroke(rectAttentionStrokeColor);
		    		}
        		}
        	}
		}
	}

    @FXML
    protected void loadXMLButtonPressed(){
    	try {
    		FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
			fileChooser.getExtensionFilters().add(extFilter);
			fileChooser.setTitle("Load config.xml...");
			File selectedFile = fileChooser.showOpenDialog(null);

			if (selectedFile != null) {
				form = null;
				al.clear();
				pane.getChildren().clear();
	    		
	    		XMLTools xml = new XMLTools();
				form = xml.read("file:" + selectedFile.getAbsolutePath());			
				for (int i = xml.getList().size() - 1; i >= 0; i--) {
		    		pane.getChildren().add(xml.getList().get(i));
		    		al.add(xml.getList().get(i));
				}			
				canvas.setWidth(form.getWidth());
				canvas.setHeight(form.getHeight());
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			}
			else {
				//keine Datei ausgewählt
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	@FXML
	protected void loadImageButtonPressed(){
		try {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
			fileChooser.getExtensionFilters().add(extFilter);
			fileChooser.setTitle("Load background.png...");
			File selectedFile = fileChooser.showOpenDialog(null);

			if (selectedFile != null) {
				Image image = new Image("file:" + selectedFile.getAbsolutePath());

				anchorPane.maxHeight(1000);
				anchorPane.maxWidth(1000);

				GraphicsContext gc = canvas.getGraphicsContext2D();
				canvas.setHeight(image.getHeight());
				canvas.setWidth(image.getWidth());
				gc.drawImage(image, 0, 0);
			}
			else {
				//keine Datei ausgewählt
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	boolean foundSomething = false;
	@FXML protected void canvasMouseClick(MouseEvent event){
		try {
			if (isCreateMode) {
				if (event.getX() < canvas.getWidth() && event.getY() < canvas.getHeight()) {
					rect = new MyRectangle(event.getX(), event.getY(), rectW, rectH);
					rect.setFill(rectColor);
					rect.setOpacity(rectStandardAlpha);
					al.add(rect);

					rectTools.switchCurrRect(rect, currRect, lastRect);
					rect = rectTools.getRect();
					currRect = rectTools.getCurrRect();
					lastRect = rectTools.getLastRect();					
					
					rect.setID(String.valueOf(rectCounter));
					rectCounter += 1;
					rect.setType(rectType);
					rect.setLabel(rectLabel);
					rect.setPrefill(rectPrefill);
					rect.setReadOnly(rectReadOnly);
					rect.setWarnIfEmpty(rectWarnIfEmpty);
					rect.setMandatory(rectMandatory);
					rect.setPrefillIfEditAsNew(rectPrefillIfEditAsNew);
					rect.setShowInOverview(rectShowInOverview);

					if (isMissingPrefillMode) {
						rect.setStroke(rectAttentionStrokeColor);
					} else {
						rect.setStroke(rectStandardStrokeColor);
					}
					rect.setStrokeWidth(rectStandardStrokeWidth);
					rect.setStrokeType(rectStandardStrokeType);

					pane.getChildren().add(rect);
				}
			} else {

				foundSomething = false;
			    Point2D mousePoint2D = new Point2D(event.getX(), event.getY());

			    for (int i = al.size() - 1; i >= 0; i--) {
			    	if(al.get(i).contains(mousePoint2D) && !foundSomething) {
			    		foundSomething = true;
			    		rectTools.switchCurrRect(al.get(i), currRect, lastRect);
			    		al.set(i, rectTools.getRect());
						currRect = rectTools.getCurrRect();
						lastRect = rectTools.getLastRect();	
			    	}
				}

			    if (!foundSomething) {
		    		rectTools.switchCurrRect(null, currRect, lastRect);					
					currRect = rectTools.getCurrRect();
					lastRect = rectTools.getLastRect();	
		    	}
			}
			writeRectangleToTextfield();
			findAlignedRects(currRect);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML protected void paneKeyPress(KeyEvent keyEvent){
		if (currRect != null) {
			switch (keyEvent.getCode()) {
		        //Löschen
				case DELETE:
	        		al.remove(currRect);
	        		pane.getChildren().remove(currRect);
	        		currRect = null;
		        	keyEvent.consume();
		            break;
		        //Verschieben
		        case LEFT:
		        	if (currRect.getX() > 0) {
						currRect.setX(currRect.getX() - 1);
					}
		            keyEvent.consume();
		            break;
		        case RIGHT:
		        	if (currRect.getX() + currRect.getWidth() < pane.getWidth()) {
						currRect.setX(currRect.getX() + 1);
					}
		        	keyEvent.consume();
		        	break;
		        case UP:
		        	if (currRect.getY() > 0) {
						currRect.setY(currRect.getY() - 1);
					}
		        	keyEvent.consume();
		        	break;
		        case DOWN:
		        	if (currRect.getY() + currRect.getHeight() < pane.getHeight()) {
						currRect.setY(currRect.getY() + 1);
					}
		        	keyEvent.consume();
		        	break;
		        //Skalieren
		        case A:
		        	if(currRect.getWidth() > 1) {
						currRect.setWidth(currRect.getWidth() - 1);
					}
		            keyEvent.consume();
		            break;
		        case D:
		        	if(currRect.getX() + currRect.getWidth() < pane.getWidth()) {
						currRect.setWidth(currRect.getWidth() + 1);
					}
		        	keyEvent.consume();
		        	break;
		        case W:
		        	if(currRect.getHeight() > 1) {
						currRect.setHeight(currRect.getHeight() - 1);
					}
		        	keyEvent.consume();
		        	break;
		        case S:
		        	if(currRect.getY() + currRect.getHeight() < pane.getHeight()) {
						currRect.setHeight(currRect.getHeight() + 1);
					}
		        	keyEvent.consume();
		        	break;
		        default:
		        	return;
		    }
			writeRectangleToTextfield();
			findAlignedRects(currRect);
		}
		else {
	    	keyEvent.consume();
	    }
	}

	private void findAlignedRects(MyRectangle currRect) {
		try {
			if (currRect != null) {
				for (int i = al.size() - 1; i >= 0; i--) {
					al.get(i).setFill(rectTools.setRectangleColor(al.get(i).getType()));
	        		if (al.get(i).getID() != currRect.getID() && (al.get(i).getX() == currRect.getX() || al.get(i).getY() == currRect.getY())) {
			    		al.get(i).setFill(Color.YELLOW);
			    	} else if (al.get(i).getFill() == Color.YELLOW && al.get(i).getX() != currRect.getX() && al.get(i).getY() != currRect.getY()) {
			    		al.get(i).setFill(rectTools.setRectangleColor(al.get(i).getType()));
			    	}
				}
			} else {
				for (int i = al.size() - 1; i >= 0; i--) {
	        		if (al.get(i).getFill() == Color.YELLOW) {
			    		al.get(i).setFill(rectTools.setRectangleColor(al.get(i).getType()));
			    	}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void writeRectangleToTextfield() {
		isListenerActive = false;
		if (currRect != null) {
			IDText.setText(currRect.getID());
			XText.setText(String.valueOf((int) currRect.getX()));
			YText.setText(String.valueOf((int) currRect.getY()));
			WidthText.setText(String.valueOf((int) currRect.getWidth()));
			HeightText.setText(String.valueOf((int) currRect.getHeight()));
			TypeText.setText(currRect.getType());
			LabelText.setText(currRect.getLabel());
			PrefillText.setText(currRect.getPrefill());
			ReadOnlyCheckbox.setSelected(currRect.getReadOnly());
			WarnIfEmptyCheckbox.setSelected(currRect.getWarnIfEmpty());
			MandatoryCheckbox.setSelected(currRect.getMandatory());
			PrefillIfEditAsNewCheckbox.setSelected(currRect.getPrefillIfEditAsNew());
			ShowInOverviewCheckbox.setSelected(currRect.getShowInOverview());
		} else {
			IDText.clear();
			XText.clear();
			YText.clear();
			WidthText.clear();
			HeightText.clear();
			TypeText.clear();
			LabelText.clear();
			PrefillText.clear();
			ReadOnlyCheckbox.setSelected(false);
			WarnIfEmptyCheckbox.setSelected(false);
			MandatoryCheckbox.setSelected(false);
			PrefillIfEditAsNewCheckbox.setSelected(false);
			ShowInOverviewCheckbox.setSelected(false);
		}
		isListenerActive = true;
	}

	private void writeTextfieldToRectangle() {
		try {
			if (currRect != null) {
				currRect.setID(IDText.getText());
				if (XText.getText().length() > 0) {
					currRect.setX(Double.parseDouble(XText.getText()));
				}
				if (YText.getText().length() > 0) {
					currRect.setY(Double.parseDouble(YText.getText()));
				}
				if (WidthText.getText().length() > 0) {
					currRect.setWidth(Double.parseDouble(WidthText.getText()));
				}
				if (HeightText.getText().length() > 0) {
					currRect.setHeight(Double.parseDouble(HeightText.getText()));
				}
				currRect.setType(TypeText.getText());
				currRect.setLabel(LabelText.getText());
				currRect.setPrefill(PrefillText.getText());
				if (PrefillText.getText() == null) {
					currRect.setStroke(rectAttentionStrokeColor);
				}
				else {
					currRect.setStroke(rectStandardStrokeColor);
				}
				currRect.setReadOnly(ReadOnlyCheckbox.isSelected());
				currRect.setWarnIfEmpty(WarnIfEmptyCheckbox.isSelected());
				currRect.setMandatory(MandatoryCheckbox.isSelected());
				currRect.setPrefillIfEditAsNew(PrefillIfEditAsNewCheckbox.isSelected());
				currRect.setShowInOverview(ShowInOverviewCheckbox.isSelected());
				MarkRects();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void writeFormToTextfield() {
		isListenerActive = false;
		FormTypeText.setText(form.getFormType());
		FormLabelText.setText(form.getFormLabel());
		FormInfoText.setText(form.getFormInfo());
		isListenerActive = true;
	}

	private void writeTextfieldToForm() {
		try {
			form.setFormType(FormTypeText.getText());
			form.setFormLabel(FormLabelText.getText());
			form.setFormInfo(FormInfoText.getText());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML protected void textfieldButtonPressed(){
		try {
			textfieldButton.setStyle("-fx-font-weight: bold; -fx-base: #b3ffcc;");
			textareaButton.setStyle("");
			checkboxButton.setStyle("");

			rectW = 100;
			rectH = 20;
			rectType = "textfield";
			rectColor = rectTools.setRectangleColor(rectType);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML protected void textareaButtonPressed(){
		try {
			textareaButton.setStyle("-fx-font-weight: bold; -fx-base: #b3ffcc;");
			textfieldButton.setStyle("");
			checkboxButton.setStyle("");

			rectW = 100;
			rectH = 20;
			rectType = "textarea";
			rectColor = rectTools.setRectangleColor(rectType);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML protected void checkboxButtonPressed(){
		try {
			checkboxButton.setStyle("-fx-font-weight: bold; -fx-base: #b3ffcc;");
			textfieldButton.setStyle("");
			textareaButton.setStyle("");

			rectW = 15;
			rectH = 15;
			rectType = "checkbox";
			rectColor = rectTools.setRectangleColor(rectType);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML protected void saveXMLButtonPressed(){
		try {

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML protected void selectToolButtonPressed(){
		try {
			selectToolButton.setStyle("-fx-font-weight: bold; -fx-base: #99ddff;");
			createToolButton.setStyle("");

			isCreateMode = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML protected void createToolButtonPressed(){
		try {
			isCreateMode = true;
			System.out.println(createToolButton.getStyle());
			selectToolButton.setStyle("");
			createToolButton.setStyle("-fx-font-weight: bold; -fx-base: #99ddff;");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
