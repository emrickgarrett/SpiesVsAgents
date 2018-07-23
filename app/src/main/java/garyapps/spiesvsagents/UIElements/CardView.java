package garyapps.spiesvsagents.UIElements;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import garyapps.spiesvsagents.Enums.CardStatus;
import garyapps.spiesvsagents.UtilityFunctions;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class CardView extends RelativeLayout implements View.OnClickListener {

    private TextView label;
    private CompositeDisposable disposeBag = new CompositeDisposable();

    private int standardWidth = 200;
    private int standardHeight = (int)(standardWidth * .75);

    public BehaviorSubject<String> labelText = BehaviorSubject.create();
    public BehaviorSubject<CardStatus> status = BehaviorSubject.create();
    public BehaviorSubject<Object> click = BehaviorSubject.create();

    public CardView(Context context) {
        super(context);
        createLabel();
        calculateNewDimensions();
        this.setOnClickListener(this);
    }

    protected void bindObservables() {
        disposeBag.addAll(
                labelText.subscribeOn(AndroidSchedulers.mainThread()).subscribe(this.label::setText),
                status.subscribeOn(AndroidSchedulers.mainThread()).subscribe(this::handleStatusChange)
        );
    }

    protected void handleStatusChange(CardStatus status) {
        switch(status) {
            case Neutral:
                updateCard(Color.BLACK, Color.WHITE);
                break;
            case Spy:
                updateCard(Color.WHITE, Color.BLUE);
                break;
            case Agent:
                updateCard(Color.WHITE, Color.RED);
                break;
            case Nerve_Gas:
                updateCard(Color.RED, Color.GREEN);
                break;
            case Psychopath:
                updateCard(Color.RED, Color.BLACK);
                break;
        }
    }

    protected void updateCard(int textColor, int backgroundColor) {
        this.label.setTextColor(textColor);
        this.setBackgroundColor(backgroundColor); // Prob switch to a drawable later!
    }

    private void createLabel() {
        label = new TextView(getContext());

        LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        label.setLayoutParams(params);
        this.addView(label);
    }

    private void calculateNewDimensions() {
        Pair<Integer, Integer> dimensions = UtilityFunctions.getScreenDimensions(getContext());
        double width = standardWidth;
        double height = standardHeight;
        if(dimensions.first != null) {

            width = dimensions.first * .18;
            height = width * .75;
        }

        if(width > standardWidth) {
            width = standardWidth;
            height = standardHeight;
        }

        LayoutParams params = new RelativeLayout.LayoutParams((int) width, (int) height);
        this.setLayoutParams(params);
        this.invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        disposeBag.dispose();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bindObservables();
    }

    @Override
    public void onClick(View view) {
        click.onNext(new Object());
    }
}
