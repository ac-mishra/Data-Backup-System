    package com.backup.main;

    import com.backup.model.BackupData;
    import com.backup.service.BackupManager;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Scanner;

    public class DataBackupSystem {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            BackupManager manager =
                    new BackupManager();

            int choice;


            do {

                System.out.println("\n=== DATA BACKUP SYSTEM ===");

                System.out.println("1. Create New Backup");
                System.out.println("2. Restore from Binary");
                System.out.println("3. Restore from Compressed");
                System.out.println("4. Import from CSV");
                System.out.println("5. List All Versions");
                System.out.println("6. Cleanup Old Backups");
                System.out.println("7. Backup Statistics");
                System.out.println("8. Exit");

                System.out.print("\nEnter Choice : ");

                choice = sc.nextInt();
                sc.nextLine();


                try {

                    switch (choice) {


                        case 1:

                            System.out.println("\n=== CREATE NEW BACKUP ===");

                            System.out.print("Enter backup description: ");

                            String description = sc.nextLine();

                            Map<String,Object> data = new HashMap<>();

                            String moreItems;

                            do{

                                System.out.println();

                                System.out.print("Enter key: ");
                                String key = sc.nextLine();

                                System.out.print("Enter value: ");
                                String value = sc.nextLine();

                                System.out.print(
                                        "Enter type (String/Integer/Double/Boolean): "
                                );

                                String type = sc.nextLine();

                                Object converted;

                                switch(type.toLowerCase()){

                                    case "integer":

                                        converted =
                                                Integer.parseInt(value);

                                        break;

                                    case "double":

                                        converted =
                                                Double.parseDouble(value);

                                        break;

                                    case "boolean":

                                        converted =
                                                Boolean.parseBoolean(value);

                                        break;

                                    default:

                                        converted = value;

                                }


                                data.put(

                                        key,

                                        converted

                                );


                                System.out.print(

                                        "Add more items? (y/n): "

                                );


                                moreItems = sc.nextLine();


                            }

                            while(

                                    moreItems.equalsIgnoreCase("y")

                            );


                            String id =

                                    manager.createBackup(

                                            description,

                                            data

                                    );


                            System.out.println(

                                    "\nBackup created successfully!"

                            );


                            System.out.println(

                                    "\nFiles created:"

                            );

                            System.out.println(

                                    "  • backups/"

                                            +

                                            id

                                            +

                                            ".dat"

                            );


                            System.out.println(

                                    "  • backups/"

                                            +

                                            id

                                            +

                                            ".gz"

                            );


                            System.out.println(

                                    "  • backups/"

                                            +

                                            id

                                            +

                                            ".csv"

                            );


                            System.out.println(

                                    "\nBackup ID: "

                                            +

                                            id

                            );


                            break;

                        case 2:


                            System.out.print(

                                    "\nEnter binary file path: "

                            );


                            String path =

                                    sc.nextLine();



                            BackupData backup =


                                    manager.restoreBinary(

                                            path

                                    );



                            System.out.println(

                                    "\n✅ RESTORED BACKUP:\n"

                            );


                            System.out.println(

                                    backup

                            );


                            System.out.println(

                                    "\nData Contents:\n"

                            );



                            backup.displayData();



                            break;



                        case 3:


                            System.out.print(

                                    "Compressed File Path : "

                            );



                            String compressed =


                                    sc.nextLine();



                            BackupData restore =



                                    manager.restoreCompressed(

                                            compressed

                                    );



                            System.out.println(

                                    restore

                            );


                            break;



                        case 4:



                            System.out.print(

                                    "CSV File Path : "

                            );


                            String csv =

                                    sc.nextLine();



                            BackupData csvBackup =



                                    manager.importCSV(

                                            csv

                                    );



                            System.out.println(

                                    csvBackup

                            );


                            break;



                        case 5:



                            manager.listVersions();



                            break;



                        case 6:



                            manager.cleanupOldBackups();



                            System.out.println(

                                    "Cleanup Completed"

                            );



                            break;



                        case 7:



                            System.out.println(

                                    "\n=== BACKUP STATISTICS ==="

                            );


                            manager.showStatistics();



                            break;



                        case 8:



                            manager.shutdownScheduler();



                            System.out.println(

                                    "Application Closed"

                            );



                            break;



                        default:



                            System.out.println(

                                    "Invalid Choice"

                            );


                    }


                }

                catch (Exception e) {


                    System.out.println(

                            "\nError : "

                                    +

                                    e.getMessage()

                    );


                }


            }

            while (choice != 8);


            sc.close();


        }


    }