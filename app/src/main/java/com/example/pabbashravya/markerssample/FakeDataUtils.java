package com.example.pabbashravya.markerssample;

import java.util.ArrayList;
import java.util.List;

public class FakeDataUtils {

    public static List<Datum> getFakeData(){
        List <Datum> locations=new ArrayList<>();

        locations.add(new Datum("https://picsum.photos/200/300/?random","12.929327618","77.684214897","Yulu 1"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.929163598","77.685027434","Yulu 2"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.92542","77.68684","Yulu 3"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.92236","77.68505","Yulu 4"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.92083","77.68542","Yulu 5"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.91949","77.68555","Yulu 6"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.91942","77.68516","Yulu 7"));
        locations.add(new Datum("https://picsum.photos/200/300/?random","12.942466005","77.69511102","Yulu 8"));

        return locations;
    }
}
