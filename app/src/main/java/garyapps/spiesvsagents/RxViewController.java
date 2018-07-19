package garyapps.spiesvsagents;

import java.util.ArrayList;

import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
import emrickgj.saa.ViewController;
import io.reactivex.disposables.Disposable;

public abstract class RxViewController extends ViewController {
    ArrayList<Disposable> disposables;

    public RxViewController(SingleActivity context) {
        super(context);
        disposables = new ArrayList<>();
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    protected void addDisposables(Disposable... disposable) {
        for(Disposable d: disposable) {
            disposables.add(d);
        }
    }

    @Override
    public void onViewControllerResumed() {
        super.onViewControllerResumed();
        disposables = new ArrayList<>();
    }

    @Override
    public void onViewControllerPaused() {
        super.onViewControllerPaused();
        destroyDisposables();
        disposables = null;
    }

    @Override
    public void onViewControllerDestroyed() {
        super.onViewControllerDestroyed();
        destroyDisposables();
        disposables = null;
    }

    private void destroyDisposables() {
        for(Disposable d : disposables) {
            d.dispose();
        }
    }
}
