package garyapps.spiesvsagents.ViewControllers;

import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
import garyapps.spiesvsagents.R;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomeViewController extends RxViewController {

    @BindView(R.id.host_a_game)
    Button hostAGame;

    @BindView(R.id.join_a_game)
    Button joinAGame;

    public HomeViewController(SingleActivity context) {
        super(context);
    }

    @Override
    protected void inflateView() {
        this.view = context.getLayoutInflater().inflate(R.layout.view_home, null);
        ButterKnife.bind(this, this.view);
    }

    @Override
    protected void bindObservables() {
        addDisposables(
                RxView.clicks(hostAGame)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(n -> {
                            //Host A Game
                            this.navigate(new HostGameViewController(context));
                        }),

                RxView.clicks(joinAGame)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(n -> {
                            //Join A Game
                            this.navigate(new JoinGameViewController(context));
                        })
        );
    }
}
