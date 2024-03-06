package com.example.listycity5;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;

import java.util.HashMap;

public class CityController{
    private static String TAG = "CityCtrl";


    public void addCity(String cityName, String provinceCode, CollectionReference cityRef){
        HashMap<String, String> city = new HashMap<>();
        city.put("city", cityName);
        city.put("province", provinceCode);

        cityRef.document()
                .set(city);
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d(TAG, "Document successfully written");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "Error writing document ", e);
//                    }
//                });
    }

    public void deleteCity(String docId, CollectionReference cityRef){
        cityRef.document(docId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Deletion successful");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error deleting document ", e);
                    }
                });
    }
}
