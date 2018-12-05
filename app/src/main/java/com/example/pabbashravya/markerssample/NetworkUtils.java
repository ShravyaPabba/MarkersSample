package com.example.pabbashravya.markerssample;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUtils {

    public static List<Datum> getLocationsFromNetwork(){

        final List <Datum> locations=new ArrayList<>();

        APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<NetworkResponse> call=apiService.getLocationsList();
        call.enqueue(new Callback<NetworkResponse>() {
            @Override
            public void onResponse(Call<NetworkResponse> call, Response<NetworkResponse> response) {
                if(response.body()==null)
                    return;

                for(Datum loc:response.body().getData())
                    locations.add(new Datum(loc.getImage(),loc.getLatitude(),loc.getLongitude(),loc.getTitle()));


            }

            @Override
            public void onFailure(Call<NetworkResponse> call, Throwable t) {
                call.cancel();
            }
        });

        return locations;
    }
}
