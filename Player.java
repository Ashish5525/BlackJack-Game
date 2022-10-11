import java.util.ArrayList;

import javafx.scene.Node;

public class Player {

	ArrayList<Card> hand;
	static Deck deck = new Deck();

	public Player() {
		hand = new ArrayList<Card>(0);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int valueOfHand() {
		boolean ace = false;

		int value = 0;

		for (int i = 0; i < hand.size(); i++) {
			value += hand.get(i).valueOf();

			if (hand.get(i).getface().equals("A")) {
				ace = true;
			}
		}
		if (value > 21 && ace) {
			value -= 10;
		}

		return value;

	}

	public void clearHand() {
		hand.clear();
	}

	public boolean stand(int otherPlayerValue) {

		if (valueOfHand() > otherPlayerValue) {
			return true;
		}
		if (valueOfHand() >= 19) {
			return true;
		} else if (valueOfHand() >= 16) {

			if (Math.random() > 0.5) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public void hit() {
		hand.add(deck.dealCard());
	}

	public boolean bust() {

		if (valueOfHand() > 21) {
			return true;
		} else {
			return false;
		}
	}

	public Card returnCard() {
		Card card = hand.get(hand.size() - 1);

		return card;
	}

	public Deck getDeck() {
		return deck;
	}

}