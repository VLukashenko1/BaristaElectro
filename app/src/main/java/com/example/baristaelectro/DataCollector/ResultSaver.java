package com.example.baristaelectro.DataCollector;

import com.example.baristaelectro.App;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;
import java.util.HashMap;

public class ResultSaver {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void saveCheckedDrinks() {
        HashMap<String, FieldValue> drinkForSave = new HashMap<>();
        if (TempResultHolder.getInstance().getListMutableLiveData().getValue() == null) return;

        for (DrinkModel drink : TempResultHolder.getInstance().getListMutableLiveData().getValue()) {
            if (drink.checked) {
                drinkForSave.put(drink.name, FieldValue.increment(1));
            }
        }

        if (!drinkForSave.isEmpty()) {
            saveToFB(drinkForSave);
        }
        TempResultHolder.getInstance().getListMutableLiveData().setValue(null);
    }

    private void saveToFB(HashMap drinks) {
        String docName = Data.dateFormat.format(new Date());
        db.collection(Data.CollectionName).document(docName).set(drinks, SetOptions.merge()).addOnSuccessListener(unused -> {
                    App.makeText("Success uploaded");
                })
                .addOnFailureListener(e -> {
                    App.makeText("Fail to upload file. Try again");
                    //todo save checkedDrinks local
                });
    }


}
