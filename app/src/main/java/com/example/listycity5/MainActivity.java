package com.example.listycity5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "FB";
    private ListView cityList;
    private ArrayList<City> cityDataList;
    private CityArrayAdapter cityArrayAdapter;
    private CityController cityController;
    private DatabaseController databaseController;
    private CollectionReference citiesRef;
    private EditText cityText;
    private EditText provinceText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = findViewById(R.id.editText_city);
        provinceText = findViewById(R.id.editText_province);
        addButton = findViewById(R.id.button_add);

        databaseController = new DatabaseController();
        citiesRef = databaseController.getCollectionReference("cities");

        cityDataList = new ArrayList<>();

        cityList = findViewById(R.id.city_list);
        cityDataList = new ArrayList<>();

        cityArrayAdapter = new CityArrayAdapter(this, cityDataList);
        cityList.setAdapter(cityArrayAdapter);

        addButton.setOnClickListener(view -> {
            String cityName = cityText.getText().toString();
            String provinceCode = provinceText.getText().toString();

            if ((cityName.length() != 0) && (provinceCode.length() != 0)){
                cityController = new CityController();
                cityController.addCity(cityName, provinceCode, citiesRef);
                cityText.setText("");
                provinceText.setText("");
            }
        });

        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                City city = cityDataList.get(i);
                cityController = new CityController();
                cityController.deleteCity(city.getCityDocId(), citiesRef);
                return false;
            }
        });

        updateListView();
    }

    public void updateListView(){
        citiesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.e(TAG, error.toString());
                }
                if (value != null){
                    cityDataList.clear();
                    for (QueryDocumentSnapshot doc: value){
                        String docId = doc.getId();
                        String city = doc.getString("city");
                        String province = doc.getString("province");
                        Log.d(TAG, String.format("City(%s, %s) fetched", city, province));
                        cityDataList.add(new City(city, province, docId));
                    }
                    cityArrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}