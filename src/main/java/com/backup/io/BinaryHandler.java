package com.backup.io;

import com.backup.model.BackupData;

import java.io.*;


/**
 *
 * Handles Binary Serialization
 *
 */

public class BinaryHandler {


    public static void save(

            BackupData backup,

            String path

    )

            throws IOException {


        try (

                ObjectOutputStream out =

                        new ObjectOutputStream(

                                new FileOutputStream(path)

                        )

        ) {


            out.writeObject(backup);


        }


    }


    public static BackupData restore(

            String path

    )

            throws IOException,

            ClassNotFoundException {


        try (

                ObjectInputStream in =

                        new ObjectInputStream(

                                new FileInputStream(path)

                        )

        ) {


            return (BackupData)

                    in.readObject();


        }


    }


}