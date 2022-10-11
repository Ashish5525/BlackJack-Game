import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BlackjackTableGUI extends Application {
	Player dealer = new Player();
	Player user = new Player();
	int initialPScore = 0;
	int initialDScore = 0;

	Label dealerh = new Label("Dealer Hand");
	Label dlScore = new Label("Dealer Score: 0");
	Label dValueScore = new Label("Value: 0");

	Label playerh = new Label("Player Hand");
	Label plScore = new Label("Player Score: 0");
	Label pValueScore = new Label("Value: 0");

	Label winner = new Label("");

	HBox dealerC = new HBox();
	HBox playerC = new HBox();

	Button hit = new Button("Hit");
	Button stand = new Button("Stand");
	Button start = new Button("Start");

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		primaryStage.setTitle("BlackJack");

//Making the styling of the words
		
		dealerh.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
		dlScore.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
		dValueScore.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

		playerh.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
		plScore.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
		pValueScore.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

		winner.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

		dealerC.setPrefSize(600, 150);
		playerC.setPrefSize(600, 150);

		hit.setDisable(true);
		stand.setDisable(true);
		start.setDisable(false);

//Button Action Listeners
		start.setOnAction((ActionEvent event) -> {
			startGame();

		});

		hit.setOnAction((ActionEvent event) -> {
			user.hit();
			updateHand(user, playerC, pValueScore);

			if (user.bust()) {
				pValueScore.setText("Value: " + user.valueOfHand() + " Bust");
				endGame();
			} else if (user.valueOfHand() == 21) {
				endGame();
			}

		});

		stand.setOnAction((ActionEvent event) -> {
			boolean cHit = true;

			while (cHit) {
				dealer.hit();
				updateHand(dealer, dealerC, dValueScore);

				if (dealer.bust()) {
					cHit = false;
					dValueScore.setText("Value: " + dealer.valueOfHand() + " Bust");
					endGame();
				} else if (dealer.stand(dealer.valueOfHand())) {
					cHit = false;
					endGame();
				}
			}
		});

//Arranging the screen and GridPane
		HBox deal = new HBox(dealerh, dValueScore);
		deal.setSpacing(100);
		deal.setAlignment(Pos.CENTER);
	


		HBox play = new HBox(playerh, pValueScore);
		play.setSpacing(100);
		play.setAlignment(Pos.CENTER);


		VBox pnds = new VBox(plScore, dlScore);
		pnds.setAlignment(Pos.BASELINE_LEFT);
		pnds.setPrefWidth(150);
		
		
		HBox bottom = new HBox(pnds, winner);
		bottom.setSpacing(330);
		bottom.setAlignment(Pos.CENTER);
		
		
		HBox but = new HBox(10, start, hit, stand);
		but.setSpacing(10);
		but.setAlignment(Pos.CENTER);
		
		VBox finalV = new VBox(deal, dealerC, play, playerC, but, pnds, winner);
		finalV.setSpacing(10);
	
		
		

		finalV.setPadding(new Insets(10));
		finalV.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, null, null)));
		Scene s = new Scene(finalV);
		primaryStage.setScene(s);
		primaryStage.show();

	}

//New Classes
	public void startGame() {

		winner.setText("");
		dealerC.getChildren().clear();
		playerC.getChildren().clear();
		user.getDeck().reset();
		user.clearHand();
		dealer.clearHand();
		dealer.hit();
		pValueScore.setText("Value: 0");

		updateHand(dealer, dealerC, dValueScore);

		start.setDisable(true);
		hit.setDisable(false);
		stand.setDisable(false);
	}

	public void updateHand(Player p, HBox box, Label handValue) {
		
		Card tCard = p.getHand().get(p.getHand().size()-1);
		box.getChildren().add(tCard);
		handValue.setText("Value: " + String.valueOf(p.valueOfHand()));
//		handValue.setText("Value: " + p.valueOfHand());
//		box.getChildren().add(p.returnCard());

	}

	public void endGame() {

		boolean playerWins = false;

		if (user.bust()) {
			playerWins = false;
		} else if (dealer.bust()) {
			playerWins = true;
		} else if (user.valueOfHand() > dealer.valueOfHand()) {
			playerWins = true;
		} else if (user.valueOfHand() < dealer.valueOfHand()) {
			playerWins = false;
		} else {
			winner.setText("Push! No one Wins!");
			start.setDisable(false);
			hit.setDisable(true);
			stand.setDisable(true);
		}

		if (playerWins) {

			initialPScore++;
			plScore.setText("Player Score: " + initialPScore);
			winner.setText("Player Wins!");

		} else {

			initialDScore++;
			dlScore.setText("Dealer Score: " + initialDScore);
			winner.setText("Dealer Wins!");

		}
		start.setDisable(false);
		hit.setDisable(true);
		stand.setDisable(true);

	}
}
