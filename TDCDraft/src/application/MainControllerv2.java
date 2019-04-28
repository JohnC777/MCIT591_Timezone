package application;

import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class MainControllerv2 {
//	private static final int MIN_PIXELS = 10;
//	
//	@FXML
//	private ImageView mapPane;
//    Image image = mapPane.getImage();
//    double width = image.getWidth();
//    double height = image.getHeight();
//    
//	ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
//	
//    // convert mouse coordinates in the imageView to coordinates in the actual image:
//    private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
//        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
//        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();
//
//        Rectangle2D viewport = imageView.getViewport();
//        return new Point2D(
//                viewport.getMinX() + xProportion * viewport.getWidth(), 
//                viewport.getMinY() + yProportion * viewport.getHeight());
//    }	
//   
//    // reset to the top left:
//    private void reset(ImageView imageView, double width, double height) {
//        imageView.setViewport(new Rectangle2D(0, 0, width / 3 , height / 3));
//    }
//    
//    // shift the viewport of the imageView by the specified delta, clamping so
//    // the viewport does not move off the actual image:
//    private void shift(ImageView imageView, Point2D delta) {
//		Rectangle2D viewport = imageView.getViewport();
//		double width = imageView.getImage().getWidth();
//		double height = imageView.getImage().getHeight();
//		double maxX = width - viewport.getWidth();
//		double maxY = height - viewport.getHeight();
//		double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
//		double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);
//		if (minX < 0.0) {
//			minX = 0.0;
//		}
//		if (minY < 0.0) {
//			minY = 0.0;
//		}
//		imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
//	}
//
//    private double clamp(double value, double min, double max) {
//
//        if (value < min)
//            return min;
//        if (value > max)
//            return max;
//        return value;
//    }
//    
//	public void zoomView(ScrollEvent event) {
//		mapPane.setPreserveRatio(true);
//        mapPane.setOnScroll(e -> {
//            double delta = e.getDeltaY();
//            Rectangle2D viewport = mapPane.getViewport();
//
//            double scale = clamp(Math.pow(1.005, delta),  // altered the value from 1.01to zoom slower
//                    // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
//                    Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
//                    // don't scale so that we're bigger than image dimensions:
//                    Math.max(width / viewport.getWidth(), height / viewport.getHeight())
//            );
//            if (scale != 1.0) {
//                Point2D mouse = imageViewToImage(mapPane, new Point2D(e.getX(), e.getY()));
//
//                double newWidth = viewport.getWidth();
//                double newHeight = viewport.getHeight();
//                double imageViewRatio = (mapPane.getFitWidth() / mapPane.getFitHeight());
//                double viewportRatio = (newWidth / newHeight);
//                if (viewportRatio < imageViewRatio) {
//                    // adjust width to be proportional with height
//                    newHeight = newHeight * scale;
//                    newWidth = newHeight * imageViewRatio;
//                    if (newWidth > image.getWidth()) {
//                        newWidth = image.getWidth();
//                    }
//                } else {
//                    // adjust height to be proportional with width
//                    newWidth = newWidth * scale;
//                    newHeight = newWidth / imageViewRatio;
//                    if (newHeight > image.getHeight()) {
//                        newHeight = image.getHeight();
//                    }
//                }
//
//                // To keep the visual point under the mouse from moving, we need
//                // (x - newViewportMinX) / (x - currentViewportMinX) = scale
//                // where x is the mouse X coordinate in the image
//                // solving this for newViewportMinX gives
//                // newViewportMinX = x - (x - currentViewportMinX) * scale
//                // we then clamp this value so the image never scrolls out
//                // of the imageview:
//                double newMinX = 0;
//                if (newWidth < image.getWidth()) {
//                    newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
//                            0, width - newWidth);
//                }
//                double newMinY = 0;
//                if (newHeight < image.getHeight()) {
//                    newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
//                            0, height - newHeight);
//                }
//
//                mapPane.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
//            }
//        });
//	}
//	
//	public void mousePress(MouseEvent event) {
//		mapPane.setPreserveRatio(true);
//		mapPane.setOnMousePressed(e -> {
//            
//            Point2D mousePress = imageViewToImage(mapPane, new Point2D(e.getX(), e.getY()));
//            mouseDown.set(mousePress);
//        });
//	}
//	
//	public void mouseDrag(MouseEvent event) {
//		 mapPane.setPreserveRatio(true);
//		 mapPane.setOnMouseDragged(e -> {
//	            Point2D dragPoint = imageViewToImage(mapPane, new Point2D(e.getX(), e.getY()));
//	            shift(mapPane, dragPoint.subtract(mouseDown.get()));
//	            mouseDown.set(imageViewToImage(mapPane, new Point2D(e.getX(), e.getY())));
//	        });
//		
//	}
//	
//	public void mouseClick(MouseEvent event) {
//		mapPane.setPreserveRatio(true);
//		mapPane.setOnMouseClicked(e -> {
//            if (e.getClickCount() == 2) {
//            reset(mapPane, width, height);
//        }
//    });
//
//	}
//	
	
}
