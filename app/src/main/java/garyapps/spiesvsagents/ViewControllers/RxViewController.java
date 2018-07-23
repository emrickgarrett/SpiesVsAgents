package garyapps.spiesvsagents.ViewControllers;

import android.support.annotation.CallSuper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import emrickgj.saa.SingleActivity;
import emrickgj.saa.ViewController;
import io.reactivex.disposables.Disposable;

public abstract class RxViewController extends ViewController {
    ArrayList<Disposable> disposables;

    public RxViewController(SingleActivity context) {
        super(context);
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
    @CallSuper
    protected void inflateView() {
        disposables = new ArrayList<>();
    }

    @Override
    @CallSuper
    public void onViewControllerLoaded() {
        super.onViewControllerLoaded();
        bindObservables();
    }

    @Override
    @CallSuper
    public void onViewControllerResumed() {
        super.onViewControllerResumed();
        disposables = new ArrayList<>();
        bindObservables();
    }

    @Override
    @CallSuper
    public void onViewControllerPaused() {
        super.onViewControllerPaused();
        destroyDisposables();
        disposables = null;
    }

    @Override
    @CallSuper
    public void onViewControllerDestroyed() {
        super.onViewControllerDestroyed();
        destroyDisposables();
        disposables = null;
    }

    private void destroyDisposables() {
        if(disposables == null) { return; }

        for(Disposable d : disposables) {
            d.dispose();
        }
    }

    abstract protected void bindObservables();
}
