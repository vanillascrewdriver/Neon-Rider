package tdv;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Button {
	Image image;
	Shape shape;
	Point position;
	Point dimensions;
	
	public Button(Image image, Point position){
		this.image = image;
		this.position = position;
		this.dimensions = new Point(image.getWidth(), image.getHeight());
		this.shape = new Rectangle(this.position.x, this.position.y, this.dimensions.x, this.dimensions.y);
	}
	
	public Image getImage(){
		return this.image;
	}
	public Shape getShape(){
		return this.shape;
	}
	public Point getPosition(){
		return this.position;
	}
	public Point getDimensions(){
		return this.dimensions;
	}
	public void setSelected(boolean selected){
		if(selected){
			
		}
	}
}
