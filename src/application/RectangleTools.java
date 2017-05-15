package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class RectangleTools {
	
	private MyRectangle rect = null;
	private MyRectangle currRect = null;
	private MyRectangle lastRect = null;
	
	public MyRectangle getRect() {
		return rect;
	}
	public void setRect(MyRectangle rect) {
		this.rect = rect;
	}
	
	public MyRectangle getCurrRect() {
		return currRect;
	}
	public void setCurrRect(MyRectangle currRect) {
		this.currRect = currRect;
	}
	
	public MyRectangle getLastRect() {
		return lastRect;
	}
	public void setLastRect(MyRectangle lastRect) {
		this.lastRect = lastRect;
	}
	
	//Transparenz des Rechtecks, gültiger Bereich: 0.0 - 1.0
	public final double rectStandardAlpha = 0.7;
	public final double rectSelectedAlpha = 1.0;
	
	public Color setRectangleColor(String Type)
	{	
		switch (Type.toLowerCase()) {
		case "textfield":
			return Color.LIGHTSKYBLUE;
		case "textarea":
			return Color.STEELBLUE;
		case "checkbox":
			return Color.DARKRED;
		case "med_textfield":
			return Color.CADETBLUE;
		case "datefield":
			return Color.ORANGE;
		case "timefield":
			return Color.ORANGERED;
		default:
			return Color.BLACK;
		}
	}
	
	public void switchCurrRect(MyRectangle rect, MyRectangle currRect, MyRectangle lastRect) {
		setRect(rect);
		setCurrRect(currRect);
		setLastRect(lastRect);
		
		//Klick hat kein Rechteck getroffen
		//Zuvor war ein Rechteck selektiert
		//--> Selektion wird entfernt
		if (rect == null && currRect != null) {
				getCurrRect().setOpacity(rectStandardAlpha);
				setCurrRect(null);
		}
		//Klick hat ein Rechteck getroffen
		//Zuvor war kein Reckteck selektiert
		//--> Klick wird selektiert
		else if (getRect() != null) {
			if (getCurrRect() == null) {
				setCurrRect(rect);
				getCurrRect().setOpacity(rectSelectedAlpha);
			}
			//Klick hat ein Rechteck getroffen
			//Zuvor war ein Rechteck selektiert
			//--> Klick wird selektiert, die alte Selektion wird enfernt
			else {
				setLastRect(currRect);
				getLastRect().setOpacity(rectStandardAlpha);
				setCurrRect(rect);
				getCurrRect().setOpacity(rectSelectedAlpha);
			}
		}
		//Klick hat kein Rechteck getroffen
		//Zuvor war kein Rechteck selektiert
		//--> nichts passiert
		else {
		}
	}
	
}
