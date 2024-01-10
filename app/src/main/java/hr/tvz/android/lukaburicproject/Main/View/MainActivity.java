package hr.tvz.android.lukaburicproject.Main.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hr.tvz.android.lukaburicproject.Details.View.HotelDetails;
import hr.tvz.android.lukaburicproject.Miscellaneous.InterfacePremaServisu;
import hr.tvz.android.lukaburicproject.Main.Model.Hotel;
import hr.tvz.android.lukaburicproject.Main.Presenter.MainPresenter;
import hr.tvz.android.lukaburicproject.Miscellaneous.ServiceGenerator;
import hr.tvz.android.lukaburicproject.R;
import hr.tvz.android.lukaburicproject.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IMainActivity {


    private static final String API_URL = "https://api.npoint.io/";
    private List<Hotel> modeli;
    private ListView listView;
    public List<Hotel> listHotels;
    private MediaPlayer mediaPlayer;
    private Integer number;

    public  Integer pos;
    MainPresenter presenter;
    private ActivityMainBinding binding;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        pos=0;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(view);
        Music();



        ListView listView= (ListView) findViewById(R.id.RecList);

        InterfacePremaServisu client=
                ServiceGenerator.createService(InterfacePremaServisu.class,API_URL);
        final ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        Call<List<Hotel>> slike = client.dohvatiModele();

        Enque(listView, adapter, slike);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), HotelDetails.class);
                intent.putExtra("tag", modeli.get(position));
                intent.putExtra("pos", returnPos());
                startActivity(intent);
            }
        });

        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                color(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        presenter = new MainPresenter(this);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void color(int position){
        switch (position){
            case 0:
                binding.getRoot().setBackgroundResource(R.color.brown);
                pos=0;
                break;
            case 1:
                binding.getRoot().setBackgroundResource(R.color.dark);
                pos=1;
                break;
        }
    }

    public Integer returnPos()
    {
        return pos;
    }


    @Override
    public void Enque(ListView listView, ArrayAdapter<String> adapter, Call<List<Hotel>> slike) {
        slike.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                // Poziva se kada se dobije response od servera - bilo koji (čak i 404)
                if(response.isSuccessful()) {
                    // Napuni podacima
                    modeli = response.body();
                    for (Hotel model : modeli) {
                        adapter.add(model.getName());
                    }
                    listView.setAdapter(adapter);
                    binding.NumberOfItems.setText("Hotels found: "+String.valueOf(modeli.size()));
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void Music() {
        mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.disco);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        presenter.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        presenter.onDestroy();

    }
}