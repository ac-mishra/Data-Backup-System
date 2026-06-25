package com.backup.io;


import com.backup.model.BackupData;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import java.io.*;


/*

JSON Export Import


*/


public class JsonHandler {



    public static void export(

            BackupData backup,

            String path

    )

            throws IOException {



        Gson gson =


                new GsonBuilder()

                        .setPrettyPrinting()

                        .create();



        try (

                Writer writer =


                        new FileWriter(path)

        ) {



            gson.toJson(

                    backup,

                    writer

            );



        }


    }




    public static BackupData importJSON(

            String path

    )

            throws IOException {



        Gson gson =


                new Gson();



        try (

                Reader reader =


                        new FileReader(path)

        ) {



            return gson.fromJson(

                    reader,

                    BackupData.class

            );


        }


    }



}