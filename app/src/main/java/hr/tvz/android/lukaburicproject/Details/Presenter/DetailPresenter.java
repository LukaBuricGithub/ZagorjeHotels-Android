package hr.tvz.android.lukaburicproject.Details.Presenter;

import hr.tvz.android.lukaburicproject.Details.View.HotelDetails;

public class DetailPresenter implements IDetailsPresenter {
    private HotelDetails detailsView;

    public DetailPresenter(HotelDetails detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public void onDestroy(){
        detailsView=null;
    }

    @Override
    public void onResume() {
    }
}
