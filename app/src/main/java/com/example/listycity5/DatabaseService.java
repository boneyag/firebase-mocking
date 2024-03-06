package com.example.listycity5;

import com.google.firebase.firestore.CollectionReference;

public interface DatabaseService {
    public CollectionReference getCollectionReference(String collectionName);
}
