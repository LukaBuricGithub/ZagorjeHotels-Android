package hr.tvz.android.lukaburicproject.Main.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import hr.tvz.android.lukaburicproject.Main.Model.Hotel;
import retrofit2.Call;

public interface IMainActivity {
    void Enque(ListView listView, ArrayAdapter<String> adapter, Call<List<Hotel>> slike);
    void Music();
    void color(int position);
}
