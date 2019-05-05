package application;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ZoomFunctions {
	
	public ScrollPane createZoomPane(final Group group) {
		final double SCALE_DELTA = 1.1;
		final StackPane zoomPane = new StackPane();
		
		zoomPane.getChildren().add(group);
		
		
		final ScrollPane scroller = new ScrollPane();
		final Group scrollContent = new Group(zoomPane);
		scroller.setContent(scrollContent);
		scroller.setPrefViewportWidth(860);
		scroller.setPrefViewportHeight(678);
		
		scroller.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				zoomPane.setMinSize(newValue.getWidth(), newValue.getHeight());
			}
		});

		zoomPane.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				event.consume();

				if (event.getDeltaY() == 0) {
					return;
				}

				double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

				// amount of scrolling in each direction in scrollContent coordinate
				// units
				Point2D scrollOffset = figureScrollOffset(scrollContent, scroller);

				group.setScaleX(group.getScaleX() * scaleFactor);
				group.setScaleY(group.getScaleY() * scaleFactor);

				// move viewport so that old center remains in the center after the
				// scaling
				repositionScroller(scrollContent, scroller, scaleFactor, scrollOffset);

			}
		});

		// Panning via drag
		final ObjectProperty<Point2D> lastMouseCoordinates = new SimpleObjectProperty<Point2D>();
		scrollContent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				lastMouseCoordinates.set(new Point2D(event.getX(), event.getY()));
			}
		});

		scrollContent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double deltaX = event.getX() - lastMouseCoordinates.get().getX();
				double extraWidth = scrollContent.getLayoutBounds().getWidth()
						- scroller.getViewportBounds().getWidth();
				double deltaH = deltaX * (scroller.getHmax() - scroller.getHmin()) / extraWidth;
				double desiredH = scroller.getHvalue() - deltaH;
				scroller.setHvalue(Math.max(0, Math.min(scroller.getHmax(), desiredH)));

				double deltaY = event.getY() - lastMouseCoordinates.get().getY();
				double extraHeight = scrollContent.getLayoutBounds().getHeight()
						- scroller.getViewportBounds().getHeight();
				double deltaV = deltaY * (scroller.getHmax() - scroller.getHmin()) / extraHeight;
				double desiredV = scroller.getVvalue() - deltaV;
				scroller.setVvalue(Math.max(0, Math.min(scroller.getVmax(), desiredV)));
			}
		});
		
		return scroller;
	}

	public Point2D figureScrollOffset(Node scrollContent, ScrollPane scroller) {
		double extraWidth = scrollContent.getLayoutBounds().getWidth() - scroller.getViewportBounds().getWidth();
		double hScrollProportion = (scroller.getHvalue() - scroller.getHmin())
				/ (scroller.getHmax() - scroller.getHmin());
		double scrollXOffset = hScrollProportion * Math.max(0, extraWidth);
		double extraHeight = scrollContent.getLayoutBounds().getHeight() - scroller.getViewportBounds().getHeight();
		double vScrollProportion = (scroller.getVvalue() - scroller.getVmin())
				/ (scroller.getVmax() - scroller.getVmin());
		double scrollYOffset = vScrollProportion * Math.max(0, extraHeight);
		return new Point2D(scrollXOffset, scrollYOffset);
	}

	public void repositionScroller(Node scrollContent, ScrollPane scroller, double scaleFactor, Point2D scrollOffset) {
		double scrollXOffset = scrollOffset.getX();
		double scrollYOffset = scrollOffset.getY();
		double extraWidth = scrollContent.getLayoutBounds().getWidth() - scroller.getViewportBounds().getWidth();
		if (extraWidth > 0) {
			double halfWidth = scroller.getViewportBounds().getWidth() / 2;
			double newScrollXOffset = (scaleFactor - 1) * halfWidth + scaleFactor * scrollXOffset;
			scroller.setHvalue(
					scroller.getHmin() + newScrollXOffset * (scroller.getHmax() - scroller.getHmin()) / extraWidth);
		} else {
			scroller.setHvalue(scroller.getHmin());
		}
		double extraHeight = scrollContent.getLayoutBounds().getHeight() - scroller.getViewportBounds().getHeight();
		if (extraHeight > 0) {
			double halfHeight = scroller.getViewportBounds().getHeight() / 2;
			double newScrollYOffset = (scaleFactor - 1) * halfHeight + scaleFactor * scrollYOffset;
			scroller.setVvalue(
					scroller.getVmin() + newScrollYOffset * (scroller.getVmax() - scroller.getVmin()) / extraHeight);
		} else {
			scroller.setHvalue(scroller.getHmin());
		}
	}
	
	  public Group createZoomButtonGroup(final Group group) {
			
		  Button zoomIn = new Button("+");
			zoomIn.setPrefSize(40,30);
			zoomIn.setFont(new Font(28));
			zoomIn.setTextFill(Color.web("726f6f"));
			zoomIn.setLayoutX(800);
			zoomIn.setLayoutY(580);
			zoomIn.setBlendMode(BlendMode.SRC_OVER);
			zoomIn.setPadding(new Insets(0, 0, 2, 0));
			zoomIn.setOnAction(new EventHandler<ActionEvent>() {
			      @Override
			      public void handle(ActionEvent event) {
			        group.setScaleX(group.getScaleX() * 1.5);
			        group.setScaleY(group.getScaleY() * 1.5);
			      }
			    });
			
			Button zoomOut = new Button("-");
			zoomOut.setPrefSize(40,30);
			zoomOut.setFont(new Font(28));
			zoomOut.setTextFill(Color.web("726f6f"));
			zoomOut.setLayoutX(800);
			zoomOut.setLayoutY(615);
			zoomOut.setBlendMode(BlendMode.SRC_OVER);
			zoomOut.setPadding(new Insets(0, 0, 2, 0));
		    zoomOut.setOnAction(new EventHandler<ActionEvent>() {
			      @Override
			      public void handle(ActionEvent event) {
			        group.setScaleX(group.getScaleX() * 1 / 1.5);
			        group.setScaleY(group.getScaleY() * 1 / 1.5);
			      }
			    });
			
		    Button reset = new Button("RESET");
		    reset.setPrefSize(40, 20);
		    reset.setFont(new Font(8));
			reset.setTextFill(Color.web("726f6f"));
			reset.setLayoutX(800);
			reset.setLayoutY(650);
			reset.setPadding(new Insets(0, 0, 0, 0));
			reset.setBlendMode(BlendMode.SRC_OVER);
			reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
		      public void handle(ActionEvent event) {
		        group.setScaleX(1);
		        group.setScaleY(1);
		      }
		    });
		    
		    Group buttonGroup = new Group(
		    		zoomIn,
		    		zoomOut,
		    		reset
		    		);
		    
		    return buttonGroup;

		  }
}
