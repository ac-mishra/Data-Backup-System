package com.backup.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BackupData implements Serializable {

    private String backupId;

    private String backupTime;

    private String description;

    private HashMap<String, Object> data;


    public BackupData() {

    }


    public BackupData(

            String description,

            Map<String, Object> data

    ) {


        this.backupId =

                UUID.randomUUID()

                        .toString();



        this.backupTime =

                LocalDateTime.now()

                        .format(

                                DateTimeFormatter

                                        .ofPattern(

                                                "yyyy-MM-dd HH:mm:ss"

                                        )

                        );



        this.description =

                description;



        this.data =

                new HashMap<>(

                        data

                );


    }


    public String getBackupId() {

        return backupId;

    }


    public String getBackupTime() {

        return backupTime;

    }


    public String getDescription() {

        return description;

    }


    public HashMap<String, Object> getData() {

        return data;

    }


    public int getDataCount() {

        return data.size();

    }


    public void displayData() {


        int i = 1;


        for (

                Map.Entry<String, Object>

                        entry

                :

                data.entrySet()

        ) {


            System.out.println(

                    i++

                            +

                            ". "

                            +

                            entry.getKey()

                            +

                            " = "

                            +

                            entry.getValue()

                            +

                            " ("

                            +

                            entry.getValue()

                                    .getClass()

                                    .getSimpleName()

                            +

                            ")"

            );


        }


    }


    @Override
    public String toString() {


        return


                "Backup ID : "

                        +

                        backupId


                        +


                        "\nTime : "


                        +

                        backupTime



                        +


                        "\nDescription : "


                        +

                        description



                        +


                        "\nData Items : "


                        +

                        data.size();


    }


}