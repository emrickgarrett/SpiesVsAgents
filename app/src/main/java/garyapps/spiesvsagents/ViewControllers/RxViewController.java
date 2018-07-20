package garyapps.spiesvsagents.ViewControllers;

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
    public void onViewControllerLoaded() {
        super.onViewControllerLoaded();
        bindObservables();
    }

    @Override
    public void onViewControllerResumed() {
        super.onViewControllerResumed();
        disposables = new ArrayList<>();
        bindObservables();
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

    abstract protected void bindObservables();
}
