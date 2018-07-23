package garyapps.spiesvsagents.ViewControllers;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
import garyapps.spiesvsagents.Adapters.TextCardAdapter;
import garyapps.spiesvsagents.Enums.CardStatus;
import garyapps.spiesvsagents.Models.TextCard;
import garyapps.spiesvsagents.R;
import garyapps.spiesvsagents.UIElements.CardView;
import garyapps.spiesvsagents.UIElements.GameBoard;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class GameViewController extends RxViewController {

    @BindView(R.id.game_board)
    GameBoard board;

    private ArrayList<CardView> cardViews;
    private ArrayList<TextCard> cards;


    public GameViewController(SingleActivity context) {
        super(context);
    }

    @Override
    protected void inflateView() {
        super.inflateView();
        this.view = context.getLayoutInflater().inflate(R.layout.view_game_main, null);
        ButterKnife.bind(this, this.view);
        createGame();
    }

    @Override
    protected void bindObservables() {

    }

    private void createGame() {
        cardViews = new ArrayList<>();
        cards = new ArrayList<>();

        generateCards();
        board.cards.onNext(cardViews.toArray(new CardView[]{}));
    }

    private void generateCards() {
        cards.clear();
        CardView cardView;
        TextCard card;

        for(int i = 0; i < 25; i++) {
            cardView = new CardView(context);
            card = new TextCard("Card: " + i);

            cardView.labelText.onNext(card.getValue());
            cardView.status.onNext(card.getStatus());
            cardViews.add(cardView);

            final int index = i;
            this.addDisposable(
                cardView.click.subscribeOn(AndroidSchedulers.mainThread()).subscribe(_void -> {
                    cardClicked(index);
                })
            );
        }
    }

    private void cardClicked(int index) {
        //Do work
        Toast.makeText(context, "Touched " + index, Toast.LENGTH_SHORT).show();
    }
}
