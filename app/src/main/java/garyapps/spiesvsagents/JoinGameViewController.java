package garyapps.spiesvsagents;

import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class JoinGameViewController extends RxViewController {

    @BindView(R.id.start_game)
    Button startGame;

    public JoinGameViewController(SingleActivity context) {
        super(context);
    }

    @Override
    protected void inflateView() {
        this.view = context.getLayoutInflater().inflate(R.layout.view_join_game, null);
        ButterKnife.bind(this, this.view);
    }

    @Override
    public void onViewControllerLoaded() {
        super.onViewControllerLoaded();
        bindObservables();
    }

    private void bindObservables() {
        addDisposable(
                RxView.clicks(startGame)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(n -> {
                        Toast.makeText(context, "Start!", Toast.LENGTH_SHORT);
                        context.popViewController();
                    })
        );
    }
}
