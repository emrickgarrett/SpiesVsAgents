package garyapps.spiesvsagents.UIElements;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import garyapps.spiesvsagents.Models.Card;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class GameBoard<T> extends GridLayout {

    private CompositeDisposable disposeBag = new CompositeDisposable();
    public BehaviorSubject<CardView[]> cards = BehaviorSubject.create();

    public GameBoard(Context context) {
        super(context);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void bindObservables() {
        disposeBag.add(
            cards
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(cards -> {
                        this.removeAllViewsInLayout();
                        for(CardView view : cards) {
                            this.addView(view);
                        }

            })
        );
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


}
