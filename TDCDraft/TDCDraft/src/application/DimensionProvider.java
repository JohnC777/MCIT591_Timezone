package application;

import org.w3c.dom.Document;

public interface DimensionProvider {
	Dimension getDimension(Document document);
}
