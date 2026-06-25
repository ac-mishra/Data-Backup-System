package com.backup.io;

import com.backup.model.BackupData;

import java.io.*;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.HashMap;

import java.util.Map;


/*

CSV Export Import


*/


public class CSVHandler {


    public static void export(

            BackupData backup,

            String path

    )

            throws IOException {



        try (

                BufferedWriter writer =

                        Files.newBufferedWriter(

                                Paths.get(path)

                        )

        ) {


            writer.write(

                    "Key,Value,Type"

            );


            writer.newLine();



            for (

                    Map.Entry<String, Object>


                            entry


                    :


                    backup.getData()

                            .entrySet()

            ) {


                writer.write(

                        String.format(

                                "\"%s\",\"%s\",\"%s\"",

                                entry.getKey(),


                                entry.getValue(),


                                entry.getValue()

                                        .getClass()

                                        .getSimpleName()

                        )

                );


                writer.newLine();


            }


        }


    }



    public static Map<String,Object>

    importCSV(

            String path

    )

            throws IOException {


        Map<String,Object>

                data


                =


                new HashMap<>();



        try (

                BufferedReader reader =


                        Files.newBufferedReader(

                                Paths.get(path)

                        )

        ) {


            String line;


            boolean first = true;



            while (

                    (line = reader.readLine())

                            != null

            ) {



                if (first) {

                    first = false;

                    continue;

                }


                String[] parts =

                        line.replace("\"","")

                                .split(",");



                if(parts.length>=2){


                    if(parts.length>=3){


                        String key = parts[0];


                        String value = parts[1];


                        String type = parts[2];


                        Object converted;



                        switch(type){


                            case "Integer":


                                converted =

                                        Integer.parseInt(value);

                                break;



                            case "Double":


                                converted =

                                        Double.parseDouble(value);

                                break;



                            case "Boolean":


                                converted =

                                        Boolean.parseBoolean(value);

                                break;



                            default:


                                converted=value;


                        }



                        data.put(

                                key,

                                converted

                        );


                    }


                }


            }


        }



        return data;


    }


}