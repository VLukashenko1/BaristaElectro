package com.example.baristaelectro.ReportMaker;

import android.content.Context;
import android.net.Uri;

import com.example.baristaelectro.DataCollector.Data;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ReportMaker {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public void getDataFromFirebase(Context context) {
        String docName = Data.dateFormat.format(new Date());
        db.collection(Data.CollectionName).document(docName).get().addOnSuccessListener(documentSnapshot -> {
            writeToFile(documentSnapshot, context);
        }).addOnFailureListener(e -> {
            System.out.println("ERROR" + e);
            /// TODO: 04.02.2023 File don`t download
        });
    }

    private void writeToFile(DocumentSnapshot documentSnapshot, Context context) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reports");

        Row row;
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("Напій");
        row.createCell(1).setCellValue("Кількість");

        int rowNumber = 2;
        for (String key : documentSnapshot.getData().keySet()) {
            row = sheet.createRow(rowNumber);

            Cell name = row.createCell(0);
            Cell count = row.createCell(1);
            name.setCellValue(key);
            count.setCellValue(documentSnapshot.get(key).toString());
            rowNumber++;
        }
        File path = context.getFilesDir();
        File file = new File(path, Data.dateFormat.format(new Date()) + ".xls");

        try {
            workbook.write(new FileOutputStream(file));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            pushToFBStorage(file);
        }
    }

    private void pushToFBStorage(File file){
        Uri fileUri = Uri.fromFile(file);

        StorageReference storageRef = storage.getReference("ReportsDB");
        StorageReference uploadRef = storageRef.child(Data.dateFormat.format(new Date()) + ".xls");
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentEncoding("UTF8")
                .setCustomMetadata("DayReport", "ElectronicBarista")
                .build();

        UploadTask uploadTask = uploadRef.putFile(fileUri, metadata);
        uploadTask.addOnCompleteListener(task -> System.out.println("_____________\n" + "SUCCESS" + "\n________"))
                .addOnFailureListener(e -> System.out.println("_____________\n" + "FAILED" + "\n________"));
    }
}
