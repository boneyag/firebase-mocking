package com.example.listycity5;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseController implements DatabaseService{

    private FirebaseFirestore db;
    public DatabaseController(){
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public CollectionReference getCollectionReference(String collectionName){
        return db.collection(collectionName);
    }
}
