package garyapps.spiesvsagents.ViewControllers;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
import garyapps.spiesvsagents.Adapters.TextCardAdapter;
import garyapps.spiesvsagents.Models.TextCard;
import garyapps.spiesvsagents.R;

public class GameViewController extends RxViewController {

    @BindView(R.id.lv_game_cards)
    ListView cardsView;

    public GameViewController(SingleActivity context) {
        super(context);
    }

    @Override
    protected void inflateView() {
        this.view = context.getLayoutInflater().inflate(R.layout.view_game_main, null);
        ButterKnife.bind(this, this.view);
        createGame();
    }

    @Override
    protected void bindObservables() {

    }

    private void createGame() {
        ArrayList<TextCard> cards = createCards();
        createBoard(cards);
    }

    private ArrayList<TextCard> createCards() {
        ArrayList<TextCard> cards = new ArrayList<TextCard>();
        for(int i = 0; i < 25; i++) {
            cards.add(new TextCard("Test " + i));
        }
        return cards;
    }

    private void createBoard(ArrayList<TextCard> cards) {
        TextCardAdapter adapter = new TextCardAdapter(context, cards);

        cardsView.setAdapter(adapter);
    }
}
