package hr.tvz.android.lukaburicproject.Miscellaneous;

import java.util.List;

import hr.tvz.android.lukaburicproject.Main.Model.Hotel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePremaServisu {
    //@GET("9af64f92c03ac4261f9c") d70b8ef7c87a25a77560
    //@GET("80d07882078d792eb29f") 3140623177595d81ab15

    //radi 3140623177595d81ab15

    //@GET("3140623177595d81ab15")
    //@GET("b890f7cba6e57a0995c1")
    //@GET("13d523fbd6d2cd593827")

    @GET("53507ac583f5411a18f4")


    Call<List<Hotel>> dohvatiModele();
}
