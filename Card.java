import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class Card extends ImageView {

	private static String[] FACES = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q" };
	private static int HEIGHT = 130;

	private String face;

	public Card(String face) {
		this.face = face;
		Image cards = new Image("file:" +face+".png");
		setImage(cards);
		setFitHeight(HEIGHT);
		setPreserveRatio(true);
	}

	public String getface() {
		return face;
	}

	public int valueOf() {
		int index = 0;

		for (int i = 0; i < 13; i++) {
			if (face.equals(FACES[i])) {
				index = i;
			}
		}
		if (index == 0) {
			return 11;
		} else if (index <= 9) {
			return index + 1;
		} else {
			return 10;
		}
	}

	public static String FACESget(int i) {

		return FACES[i];

	}
}
