package hr.tvz.android.lukaburicproject.Details.View;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

import hr.tvz.android.lukaburicproject.Details.Presenter.DetailPresenter;
import hr.tvz.android.lukaburicproject.Main.Model.Hotel;
import hr.tvz.android.lukaburicproject.Main.View.MainActivity;
import hr.tvz.android.lukaburicproject.Miscellaneous.MapsActivity;
import hr.tvz.android.lukaburicproject.R;
import hr.tvz.android.lukaburicproject.databinding.ActivityMainBinding;
import hr.tvz.android.lukaburicproject.databinding.HotelDetailsBinding;

public class HotelDetails extends AppCompatActivity implements IHotelDetails{

    DetailPresenter detailPresenter;
    Hotel h;

    Integer pos;
    private HotelDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HotelDetailsBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(view);
        pos=getIntent().getIntExtra("pos",0);
        h = getIntent().getExtras().getParcelable("tag");
        Uri uri = Uri.parse(h.getImg());
        binding.imageView.setImageURI(uri);
        /*SimpleDraweeView draweeView = (SimpleDraweeView)findViewById(R.id.imageView);
        draweeView.setImageURI(uri);*/
        binding.Name.setText(h.getName());
        binding.Description.setText(h.getDescription());
        binding.Rating.setText(this.getString(R.string.rating)+" "+h.getRating());

        switch (pos){
            case 0:
                binding.getRoot().setBackgroundResource(R.color.brown);
                break;
            case 1:
                binding.getRoot().setBackgroundResource(R.color.dark);
                break;
        }

        /*
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                color(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */

        detailPresenter=new DetailPresenter(this);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void color(int position){
        switch (position){
            case 0:
                binding.getRoot().setBackgroundResource(R.color.brown);
                break;
            case 1:
                binding.getRoot().setBackgroundResource(R.color.dark);
                break;
        }
    }

    @Override
    public void akcija(View v){
        Intent intent=new Intent(this, MapsActivity.class);
        intent.putExtra("tag",h);
        startActivity(intent);
    }
    @Override
    public void openWeb(View view) {
        String url = h.getWeb();
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        String title = getResources().getString(R.string.contact);
        Intent chooser = Intent.createChooser(i, title);
        try {
            startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Unable to start acticity", Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        detailPresenter.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        detailPresenter.onDestroy();
    }
}
