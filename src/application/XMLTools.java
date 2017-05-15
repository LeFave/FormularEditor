package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLTools {
	
	private List<MyRectangle> rectList = new ArrayList<>();
	public List<MyRectangle> getList() {
		return this.rectList;
	}
	
	MyController controller = new MyController();
	RectangleTools rectTools = new RectangleTools();
	MyForm form = new MyForm();
	
	public MyForm read(String FileURL) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler()
			{
				//List<MyRectangle> rectList = new ArrayList<>();
			    MyRectangle rect = null;


			    boolean bEditorSize = false;
			    boolean bFields = false;
			    boolean bFormType = false;
			    boolean bFormVersion = false;
			    boolean bFormInfo = false;
			    boolean bFormLabel = false;
			    String sEditorSize = "";
			    String sFields = "";
			    String sFormType = "";
			    String sFormVersion = "";
			    String sFormInfo = "";
			    String sFormLabel = "";

			    boolean isInsideFields = false;

			    boolean bX = false;
				boolean bY = false;
				boolean bWidth = false;
				boolean bHeight = false;
				boolean bID = false;
				boolean bEditorFieldBounds = false;
				boolean bType = false;
				boolean bLabel = false;
				boolean bPrefill = false;
				boolean bReadOnly = false;
				boolean bWarnIfEmpty = false;
				boolean bMandatory = false;
				boolean bPrefillIfEditAsNew = false;
				boolean bShowInOverview = false;
				boolean bSomethingElse = false;

				String Output = "";

				boolean bDict = false;
				boolean bKey = false;
				boolean bString = false;
				boolean bInteger = false;
				boolean bBoolean = false;
				String bValue = "";

				@Override
				public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException
				{
					if (qName.equalsIgnoreCase("dict")) {
						bDict = true;
						rect = new MyRectangle(0, 0, 0, 0);
					}

					if (qName.equalsIgnoreCase("key")) {
						bKey = true;
					}

					if (qName.equalsIgnoreCase("string")) {
						bString = true;
					}

					if (qName.equalsIgnoreCase("integer")) {
						bInteger = true;
					}

					if (qName.equalsIgnoreCase("true")) {
						bBoolean = true;
						bValue = "true";
					}

					if (qName.equalsIgnoreCase("false")) {
						bBoolean = true;
						bValue = "false";
					}
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException
				{
					if (qName == "true" || qName == "false") {

					} else {
						System.out.println(Output);
						Output = "";
					}

					if (isInsideFields && qName.equalsIgnoreCase("dict")) {
						bDict = false;
						rect.setOpacity(controller.rectStandardAlpha);
						rect.setStroke(controller.rectStandardStrokeColor);
						rect.setStrokeWidth(controller.rectStandardStrokeWidth);
						rect.setStrokeType(controller.rectStandardStrokeType);
						rectList.add(rect);
						rect = null;
					}

					if (qName.equalsIgnoreCase("array")) {
						System.out.println("STOP");
						isInsideFields = false;
					}
				}

				@Override
				public void characters(char ch[], int start, int length) throws SAXException
				{
					if (bKey) {
						Output = Output + "key : " + new String(ch, start, length) + "      ---      ";
						
						if (new String(ch, start, length).toLowerCase().equals("fields")) {
							isInsideFields = true;
						}
						if (isInsideFields) {
							switch (new String(ch, start, length).toLowerCase()) {
							//Formularfelder
							case "id":
								bID = true;
								break;
							case "editorfieldbounds":
								bEditorFieldBounds = true;
								break;
							case "type":
								bType = true;
								break;
							case "label":
								bLabel = true;
								break;
							case "prefill":
								bPrefill = true;
								break;
							case "readonly":
								bReadOnly = true;
								break;
							case "warnifempty":
								bWarnIfEmpty = true;
								break;
							case "mandatory":
								bMandatory = true;
								break;
							case "prefillifeditasnew":
								bPrefillIfEditAsNew = true;
								break;
							case "showinoverview":
								bShowInOverview = true;
								break;
							default:
								bSomethingElse = true;
								break;
							}
						}
						else {
							switch (new String(ch, start, length).toLowerCase()) {
							//Formular-Infos
							case "editorsize":
								bEditorSize = true;
								break;
							case "formtype":
								bFormType = true;
								break;
							case "formversion":
								bFormVersion = true;
								break;
							case "info":
								bFormInfo = true;
								break;
							case "label":
								bFormLabel = true;
								break;
							default:
								bSomethingElse = true;
								break;
							}
						}
						bKey = false;
					}
					else if (bString) {						
						Output = Output + "string : " + new String(ch, start, length) + "      ---      ";

						if (bEditorSize) {
							sEditorSize = new String(ch, start, length);
							int[] numbers = Arrays.asList(sEditorSize.split(","))
				                      .stream()
				                      .map(String::trim)
				                      .mapToInt(Integer::parseInt).toArray();
							if (numbers.length == 2) {
								form.setWidth(numbers[0]);
								form.setHeight(numbers[1]);
								System.out.println(form.getWidth());
								System.out.println(form.getHeight());
							}
							bEditorSize = false;
						} else if (bFormType) {
							sFormType = new String(ch, start, length);
							bFormType = false;
						} else if (bFormVersion) {
							sFormVersion = new String(ch, start, length);
							bFormVersion = false;
						} else if (bFormInfo) {
							sFormInfo = new String(ch, start, length);
							bFormInfo = false;
						} else if (bFormLabel) {
							sFormLabel = new String(ch, start, length);
							bFormLabel = false;
						} else if (bID) {
							rect.setID(new String(ch, start, length));							
							bID = false;
						} else if (bEditorFieldBounds) {
							int[] numbers = Arrays.asList(new String(ch, start, length).split(","))
				                      .stream()
				                      .map(String::trim)
				                      .mapToInt(Integer::parseInt).toArray();
							if (numbers.length == 2) {
								rect.setX(numbers[0]);
								rect.setY(numbers[1]);
							} else if (numbers.length == 4) {
								rect.setX(numbers[0]);
								rect.setY(numbers[1]);
								rect.setWidth(numbers[2]);
								rect.setHeight(numbers[3]);
							}
							bEditorFieldBounds = false;
						} else if (bType) {
							rect.setType(new String(ch, start, length));
							rect.setFill(rectTools.setRectangleColor(rect.getType()));
							bType = false;
						} else if (bLabel) {
							rect.setLabel(new String(ch, start, length));
							bLabel = false;
						} else if (bPrefill) {
							rect.setPrefill(new String(ch, start, length));
							bPrefill = false;
						} else if (bSomethingElse) {
							System.out.println("!!!!!!!!!!!!!!!!!!!!ACHTUNG : " + new String(ch, start, length));
							bShowInOverview = false;
						}

						bString = false;
					}
					else if (bInteger) {
						Output = Output + "integer : " + new String(ch, start, length) + "      ---      ";
						bInteger = false;
					}
					else if (bBoolean) {
						Output = Output + "boolean : " + bValue + "      ---      ";
						System.out.println(Output);
						Output = "";

						if (bReadOnly) {
							rect.setReadOnly(Boolean.parseBoolean(bValue));
							bReadOnly = false;
						} else if (bWarnIfEmpty) {
							rect.setWarnIfEmpty(Boolean.parseBoolean(bValue));
							bWarnIfEmpty = false;
						} else if (bMandatory) {
							rect.setMandatory(Boolean.parseBoolean(bValue));
							bMandatory = false;
						} else if (bPrefillIfEditAsNew) {
							rect.setPrefillIfEditAsNew(Boolean.parseBoolean(bValue));
							bPrefillIfEditAsNew = false;
						} else if (bShowInOverview) {
							rect.setShowInOverview(Boolean.parseBoolean(bValue));
							bShowInOverview = false;
						}

						bBoolean = false;
						bValue = "";
					}
					else {

					}
				}
			};

			saxParser.parse(FileURL, handler);
    	}
    	catch (Exception e)
		{
    		e.printStackTrace();
    	}
		
		return form;
	}
}