package hr.tvz.android.lukaburicproject.Main.Presenter;


import hr.tvz.android.lukaburicproject.Main.View.MainActivity;

public class MainPresenter implements IMainPresenter{
    private MainActivity mainView;

    public MainPresenter(MainActivity mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onDestroy(){
        mainView=null;
    }

    @Override
    public void onResume() {
    }
}
