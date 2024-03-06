package com.example.listycity5;

import static org.mockito.Mockito.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;


@RunWith(MockitoJUnitRunner.class)
public class CityControllerTest {
    @Mock
    private CollectionReference mockCollection;

    @Mock
    private DocumentReference mockDocumentReference;

    @Test
    public void testAddCity(){
        String testCityName = "Test city";
        String testProvCode = "XX";

        HashMap<String, String> city = new HashMap<>();
        city.put("city", testCityName);
        city.put("province", testProvCode);

        mockCollection = mock(CollectionReference.class);
        mockDocumentReference = mock(DocumentReference.class);

        Mockito.when(mockCollection.document()).thenReturn(mockDocumentReference);

        Task<Void> mockTask = mock(Task.class);
        Mockito.when(mockDocumentReference.set(any())).thenReturn(mockTask);

        CityController cityController = new CityController();
        cityController.addCity(testCityName, testProvCode, mockCollection);

        Mockito.verify(mockDocumentReference).set(city);

//        ArgumentCaptor<OnSuccessListener<Void>> successListenerCaptor = ArgumentCaptor.forClass(OnSuccessListener.class);
//        Mockito.verify(mockTask).addOnSuccessListener(successListenerCaptor.capture());
//
//        successListenerCaptor.getValue().onSuccess(null);
//
//        ArgumentCaptor<OnFailureListener> failureListenerCaptor = ArgumentCaptor.forClass(OnFailureListener.class);
//        Mockito.verify(mockTask).addOnFailureListener(failureListenerCaptor.capture());
//
//        Exception mockException = new Exception("Mock failure");
//        failureListenerCaptor.getValue().onFailure(mockException);
    }
}
