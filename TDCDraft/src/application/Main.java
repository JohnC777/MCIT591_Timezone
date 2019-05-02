package application;
	
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	Stage window;
	private static final String IMAGE_URL = "Map_Export500.png";
	private static final int MIN_PIXELS = 10;
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Time Zone Planner");
		Group root = new Group();
		Scene scene = new Scene(root, 1150, 750);
		
		//parentBorderPane is the main window.
		BorderPane parentBorderPane = new BorderPane();
		parentBorderPane.setPrefSize(1150.0, 750.0);
		
		//Below is code for the left section of the parentBorderPane
		BorderPane leftBorderPane = new BorderPane();
		leftBorderPane.setPrefSize(271.0, 900);
		leftBorderPane.setStyle("-fx-background-color: #1e2e4a;");
		AnchorPane titlePane = new AnchorPane();
		titlePane.setPrefSize(271.0, 53.0);
		titlePane.setStyle("-fx-background-color: #1f3559;");
		leftBorderPane.setTop(titlePane);	
		Label title = new Label("TIME ZONE PLANNER");
		title.setTextFill(Color.web("#d3d3d3"));
		title.setFont(new Font(20.0));
		title.setLayoutX(28.0);
		title.setLayoutY(-2.0);
		title.setPrefSize(238.0, 57.0);
		titlePane.getChildren().add(title);
		parentBorderPane.setLeft(leftBorderPane);
		
		//Below is code for the right/central section of the parentBorderPane
		BorderPane centerBorderPane = new BorderPane();
		parentBorderPane.setCenter(centerBorderPane);
		centerBorderPane.setPrefSize(1129.0, 79.0);
		//Below is the AnchorPane for the white top section and its contents. 
		AnchorPane searchPane = new AnchorPane();
		searchPane.setPrefSize(1129.0, 53.0);
		centerBorderPane.setTop(searchPane);
		
		Label searchPrompt = new Label("SEARCH LOCATION");
		searchPrompt.setTextFill(Color.web("#656565"));
		searchPrompt.setLayoutX(21.0);
		searchPrompt.setLayoutY(19.0);
		searchPrompt.setFont(new Font(16.0));
		AnchorPane.setLeftAnchor(searchPrompt, 21.0);
		AnchorPane.setTopAnchor(searchPrompt, 19.0);
		searchPane.getChildren().add(searchPrompt);
		
		//Text field for future search bar integration.
		TextField citySearch = new TextField();
		citySearch.setLayoutX(191.0);
		citySearch.setLayoutY(16.0);
		AnchorPane.setLeftAnchor(citySearch, 191.0);
		AnchorPane.setTopAnchor(citySearch, 16.0);
		citySearch.setPadding(new Insets(5.0, 20.0, 5.0, 0));
		searchPane.getChildren().add(citySearch);
		
		Button addCity = new Button("ADD");
		addCity.setLayoutX(386.0);
		addCity.setLayoutY(17.0);
		addCity.setMnemonicParsing(false);
		addCity.setPrefSize(52.0, 25.0);
		addCity.setTextFill(Color.web("#e8e8e8"));
		addCity.setFont(new Font(12.0));
		addCity.setStyle("-fx-background-color: rgba(0, 204, 190, 1);");
		searchPane.getChildren().add(addCity);
		
		//Below is code for the center/lower map section.
		ScrollPane mapScrollPane = new ScrollPane();
		mapScrollPane.setPrefSize(1129.0, 745.0);
		centerBorderPane.setCenter(mapScrollPane);
		
		//Adding pane to hold map image.
		ImageView mapImage = new ImageView();
		mapImage.setFitHeight(745.0);
		mapImage.setFitWidth(1330);
		
		//Adding map image.
		Image map = new Image(IMAGE_URL);
		double width = map.getWidth();
        double height = map.getHeight();
		
		mapImage.setImage(map);
		mapImage.setPickOnBounds(true);
		mapImage.setPreserveRatio(true);
		mapScrollPane.setContent(mapImage);
		
		//Start of zoom function code. 
		//First adding a way to reset.
		reset(mapImage, width, height);

        ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
        
        //registering a click as a starting point for a map movement.
        mapImage.setOnMousePressed(e -> {
            
            Point2D mousePress = imageViewToImage(mapImage, new Point2D(e.getX(), e.getY()));
            mouseDown.set(mousePress);
        });
        
        //Registering the movement of the clicked and dragged mouse to shift the map image.
        mapImage.setOnMouseDragged(e -> {
            Point2D dragPoint = imageViewToImage(mapImage, new Point2D(e.getX(), e.getY()));
            shift(mapImage, dragPoint.subtract(mouseDown.get()));
            mouseDown.set(imageViewToImage(mapImage, new Point2D(e.getX(), e.getY())));
        });

        //Function for zooming upon a scroll action.
        mapImage.setOnScroll(e -> {
            double delta = e.getDeltaY();
            Rectangle2D viewport = mapImage.getViewport();

            double scale = clamp(Math.pow(1.005, delta),  // altered the value from 1.01to zoom slower
                    // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
                    Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
                    // don't scale so that we're bigger than image dimensions:
                    Math.max(width / viewport.getWidth(), height / viewport.getHeight())
            );
            if (scale != 1.0) {
                Point2D mouse = imageViewToImage(mapImage, new Point2D(e.getX(), e.getY()));

                double newWidth = viewport.getWidth();
                double newHeight = viewport.getHeight();
                double imageViewRatio = (mapImage.getFitWidth() / mapImage.getFitHeight());
                double viewportRatio = (newWidth / newHeight);
                if (viewportRatio < imageViewRatio) {
                    // adjust width to be proportional with height
                    newHeight = newHeight * scale;
                    newWidth = newHeight * imageViewRatio;
                    if (newWidth > map.getWidth()) {
                        newWidth = map.getWidth();
                    }
                } else {
                    // adjust height to be proportional with width
                    newWidth = newWidth * scale;
                    newHeight = newWidth / imageViewRatio;
                    if (newHeight > map.getHeight()) {
                        newHeight = map.getHeight();
                    }
                }

                // To keep the visual point under the mouse from moving, we need
                // (x - newViewportMinX) / (x - currentViewportMinX) = scale
                // where x is the mouse X coordinate in the image
                // solving this for newViewportMinX gives
                // newViewportMinX = x - (x - currentViewportMinX) * scale
                // we then clamp this value so the image never scrolls out
                // of the imageview:
                double newMinX = 0;
                if (newWidth < map.getWidth()) {
                    newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
                            0, width - newWidth);
                }
                double newMinY = 0;
                if (newHeight < map.getHeight()) {
                    newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
                            0, height - newHeight);
                }

                mapImage.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
            }
        });

        //Clicking twice on the map resets the view.
        mapImage.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                reset(mapImage, width, height);
            }
        });

		//Compiling the nested GUI features and adding to root and setting scene.
		root.getChildren().add(parentBorderPane);
		window.setScene(scene);
		window.show();
		
	}
	
    // reset to the top left:
    private void reset(ImageView imageView, double width, double height) {
        imageView.setViewport(new Rectangle2D(0, 0, width, height));
    }

    // shift the viewport of the imageView by the specified delta, clamping so
    // the viewport does not move off the actual image:
    private void shift(ImageView imageView, Point2D delta) {
		Rectangle2D viewport = imageView.getViewport();
		double width = imageView.getImage().getWidth();
		double height = imageView.getImage().getHeight();
		double maxX = width - viewport.getWidth();
		double maxY = height - viewport.getHeight();
		double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
		double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);
		if (minX < 0.0) {
			minX = 0.0;
		}
		if (minY < 0.0) {
			minY = 0.0;
		}
		imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
	}

    private double clamp(double value, double min, double max) {

        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    // convert mouse coordinates in the imageView to coordinates in the actual image:
    private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

        Rectangle2D viewport = imageView.getViewport();
        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(), 
                viewport.getMinY() + yProportion * viewport.getHeight());
    }
	
	public static void main(String[] args) {
		//SvgImageLoaderFactory.install();
		//SvgImageLoaderFactory.install(new PrimitiveDimensionProvider());
		launch(args);
	}
}
