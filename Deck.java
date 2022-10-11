import java.util.ArrayList;

public class Deck {

	public ArrayList<Card> cards = new ArrayList<Card>(52);

	public Deck() {
		reset();
	}

	public void reset() {
		cards.clear();

		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				String tFace = Card.FACESget(i);
				Card tCard = new Card(tFace);
				cards.add(tCard);
			}
		}
	}

	public Card dealCard() {

		int number = (int) (Math.random() * cards.size());
		Card tCard = cards.get(number);
		cards.remove(number);
		return tCard;

	}

	public ArrayList<Card> getCards() {
		return cards;
	}

}
