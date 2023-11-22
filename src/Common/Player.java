package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<HalliGalliCard> hand;
    private List<HalliGalliCard> put_floor;

    public boolean isHeadPlayer = false;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public HalliGalliCard drawCard() {
        if (!hand.isEmpty()) {
            return hand.remove(0); // 맨 위의 카드를 뽑아서 반환하고 덱에서 제거
        }
        return null; // 덱이 비어있으면 null 반환
    }

    public void addCardToHand(HalliGalliCard card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }

    public List<HalliGalliCard> getHand() {
        return hand;
    }
}