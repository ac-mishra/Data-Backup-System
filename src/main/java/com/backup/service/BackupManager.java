package com.backup.service;

import com.backup.compression.CompressionUtils;
import com.backup.io.BinaryHandler;
import com.backup.io.CSVHandler;
import com.backup.io.JsonHandler;
import com.backup.model.BackupData;
import com.backup.model.BackupStatistics;
import com.backup.utils.FilesUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackupManager {

    private static final String BACKUP_DIR = "backups";

    private static final String VERSION_DIR = "versions";


    private VersionManager versionManager;

    private RecoveryManager recoveryManager;

    private ScheduledExecutorService scheduler;

    private BackupStatistics statistics;


    public BackupManager() {

        initializeDirectories();

        versionManager = new VersionManager();

        recoveryManager = new RecoveryManager();

        scheduler = Executors.newScheduledThreadPool(1);

        statistics = new BackupStatistics();

        updateStatistics();

    }


    private void initializeDirectories() {

        FilesUtils.createDirectory(BACKUP_DIR);

        FilesUtils.createDirectory(VERSION_DIR);

    }


    private void updateStatistics() {

        statistics.setTotalBackups(

                FilesUtils.countFiles(

                        BACKUP_DIR

                )

        );


        double binarySize = 0;


        File folder = new File(BACKUP_DIR);

        File[] files = folder.listFiles();


        if (files != null) {


            for (File file : files) {


                if (file.getName().endsWith(".dat")) {


                    binarySize +=

                            FilesUtils.getFileSizeMB(

                                    file.getPath()

                            );


                }


            }


        }


        statistics.setBinarySize(

                binarySize

        );


        statistics.setTotalSize(

                statistics.getBinarySize()

                        +

                        statistics.getCompressedSize()

                        +

                        statistics.getCsvSize()

        );


    }


    public String createBackup(

            String description,

            Map<String, Object> data

    )

            throws Exception {


        BackupData backup =

                new BackupData(

                        description,

                        data

                );


        String id = backup.getBackupId();


        String binary =

                BACKUP_DIR

                        +

                        File.separator

                        +

                        id

                        +

                        ".dat";


        String csv =

                BACKUP_DIR

                        +

                        File.separator

                        +

                        id

                        +

                        ".csv";


        String json =

                BACKUP_DIR

                        +

                        File.separator

                        +

                        id

                        +

                        ".json";


        String compressed =

                BACKUP_DIR

                        +

                        File.separator

                        +

                        id

                        +

                        ".gz";


        BinaryHandler.save(

                backup,

                binary

        );


        CSVHandler.export(

                backup,

                csv

        );


        JsonHandler.export(

                backup,

                json

        );


        CompressionUtils.compress(

                binary,

                compressed

        );


        versionManager.createVersion(

                backup

        );


        statistics.setCompressedSize(

                statistics.getCompressedSize()

                        +

                        FilesUtils.getFileSizeMB(

                                compressed

                        )

        );


        statistics.setCsvSize(

                statistics.getCsvSize()

                        +

                        FilesUtils.getFileSizeMB(

                                csv

                        )

        );


        updateStatistics();


        return id;


    }


    public BackupData restoreBinary(

            String path

    )

            throws Exception {


        return recoveryManager.restore(

                path

        );


    }


    public BackupData restoreCompressed(

            String source

    )

            throws Exception {


        String temp =

                BACKUP_DIR

                        +

                        File.separator

                        +

                        "temp.dat";


        CompressionUtils.decompress(

                source,

                temp

        );


        return recoveryManager.restore(

                temp

        );


    }


    public BackupData restoreJson(

            String path

    )

            throws Exception {


        return JsonHandler.importJSON(

                path

        );


    }


    public BackupData importCSV(

            String path

    )

            throws Exception {


        Map<String, Object> data =

                CSVHandler.importCSV(

                        path

                );


        return new BackupData(

                "Imported CSV",

                data

        );


    }


    public BackupData undoRestore() {


        return recoveryManager.undoRestore();


    }


    public void showStatistics() {


        System.out.println(

                statistics

        );


    }


    public void listVersions()

            throws Exception {


        int i = 1;


        for(

                BackupData backup

                :

                versionManager.getVersions()

        ){


            if(i==1){


                System.out.println(

                        "\nVersion 1 (Most Recent)\n"

                );


            }


            else{


                System.out.println(

                        "\nVersion "

                                +

                                i

                                +

                                "\n"

                );


            }



            System.out.println(

                    backup

            );


            i++;

        }


    }


    public void cleanupOldBackups() {

        File dir = new File(BACKUP_DIR);

        File[] files = dir.listFiles();

        if (files == null) {

            return;

        }

        for (File file : files) {

            if (file.getName().endsWith(".dat")) {

                file.delete();

            }

        }

        updateStatistics();

    }


    public void scheduleBackup(

            Runnable task,

            int seconds

    ) {


        scheduler.scheduleAtFixedRate(

                task,

                0,

                seconds,

                TimeUnit.SECONDS

        );


    }


    public void shutdownScheduler() {


        scheduler.shutdown();


    }


}