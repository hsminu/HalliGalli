package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HalliGalliDeck {
    private List<HalliGalliCard> deck;

    public HalliGalliDeck() {
        initializeDeck();
        shuffleDeck();
    }

    public void initializeDeck() {
        deck = new ArrayList<>();

        addCards(1, 5);
        addCards(2, 3);
        addCards(3, 3);
        addCards(4, 2);
        addCards(5, 1);
    }

    private void addCards(int numberOfFruits, int count) {
        for (int i = 1; i <= numberOfFruits; i++) {
            deck.add(new HalliGalliCard(HalliGalliCard.Fruit.getByIndex(i), numberOfFruits));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public HalliGalliCard drawCard() {
        if (!deck.isEmpty()) {
            return deck.remove(0); // 맨 위의 카드를 뽑아서 반환하고 덱에서 제거
        }
        return null; // 덱이 비어있으면 null 반환
    }

    // 사용자에게 카드를 나눠주는 메서드
    public void dealCardsToPlayer(Player player, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            HalliGalliCard drawnCard = drawCard();
            if (drawnCard != null) {
                player.addCardToHand(drawnCard);
            }
        }
    }
    public int remainingCards() {
        return deck.size();
    }

    public List<HalliGalliCard> getDeck(){
        return deck;
    }
}
