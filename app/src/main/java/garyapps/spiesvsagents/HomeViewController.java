package garyapps.spiesvsagents;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
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
    public void onViewControllerLoaded() {
        super.onViewControllerLoaded();
        bindObservables();
    }

    @Override
    public void onViewControllerResumed() {
        super.onViewControllerResumed();
        bindObservables();
    }

    private void bindObservables() {
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
