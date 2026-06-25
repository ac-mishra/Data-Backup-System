package com.backup.service;

import com.backup.io.BinaryHandler;
import com.backup.model.BackupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Handles backup versions
 */

public class VersionManager {

    private static final String VERSION_DIR =
            "versions";


    public VersionManager() {

        File dir = new File(VERSION_DIR);

        if (!dir.exists()) {

            dir.mkdirs();

        }

    }


    public void createVersion(

            BackupData backup

    )

            throws IOException {


        String path =

                VERSION_DIR


                        + File.separator


                        +


                        "version_"


                        +


                        backup.getBackupId()


                        +


                        ".dat";


        BinaryHandler.save(

                backup,

                path

        );


    }


    public List<BackupData>

    getVersions()

            throws Exception {


        List<BackupData>

                list


                =


                new ArrayList<>();


        File folder =

                new File(VERSION_DIR);


        File[] files =

                folder.listFiles();


        if (

                files != null

        ) {


            for (

                    File file


                    :


                    files

            ) {


                if (

                        file.getName()

                                .endsWith(

                                        ".dat"

                                )

                ) {


                    list.add(

                            BinaryHandler.restore(

                                    file.getPath()

                            )

                    );


                }


            }


        }



        list.sort(

                Comparator.comparing(

                                BackupData::getBackupTime

                        )

                        .reversed()

        );


        return list;


    }



}