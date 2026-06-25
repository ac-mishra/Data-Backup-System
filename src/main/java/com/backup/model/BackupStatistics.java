package com.backup.model;

public class BackupStatistics {


    private int totalBackups;

    private double binarySize;

    private double compressedSize;

    private double csvSize;

    private double totalSize;


    public BackupStatistics() {

        totalBackups = 0;

        binarySize = 0;

        compressedSize = 0;

        csvSize = 0;

        totalSize = 0;

    }


    public BackupStatistics(

            int totalBackups,

            double binarySize,

            double compressedSize,

            double csvSize

    ) {


        this.totalBackups = totalBackups;

        this.binarySize = binarySize;

        this.compressedSize = compressedSize;

        this.csvSize = csvSize;


        this.totalSize =

                binarySize

                        +

                        compressedSize

                        +

                        csvSize;

    }


    public int getTotalBackups() {

        return totalBackups;
    }


    public double getBinarySize() {

        return binarySize;
    }


    public double getCompressedSize() {

        return compressedSize;
    }


    public double getCsvSize() {

        return csvSize;
    }


    public double getTotalSize() {

        return totalSize;
    }


    public void setTotalBackups(

            int totalBackups

    ) {

        this.totalBackups = totalBackups;

    }


    public void setBinarySize(

            double binarySize

    ) {

        this.binarySize = binarySize;

    }


    public void setCompressedSize(

            double compressedSize

    ) {

        this.compressedSize = compressedSize;

    }


    public void setCsvSize(

            double csvSize

    ) {

        this.csvSize = csvSize;

    }


    public void setTotalSize(

            double totalSize

    ) {

        this.totalSize = totalSize;

    }


    @Override
    public String toString() {


        return


                "\n===== BACKUP STATISTICS ====="

                        +

                        "\n📊 Storage Usage"

                        +

                        "\nTotal Backups : "

                        +

                        totalBackups


                        +

                        "\nBinary Files : "

                        +

                        String.format("%.4f MB", binarySize)


                        +

                        "\nCompressed Files : "

                        +

                        String.format("%.4f MB", compressedSize)


                        +

                        "\nCSV Files : "

                        +

                        String.format("%.4f MB", csvSize)


                        +

                        "\nTotal Size : "

                        +

                        String.format("%.4f MB", totalSize) ;


    }


}