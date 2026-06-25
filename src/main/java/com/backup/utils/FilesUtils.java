package com.backup.utils;

import java.io.File;

public class FilesUtils {


    public static void createDirectory(

            String path

    ){


        File file =

                new File(path);


        if(

                !file.exists()

        ){


            file.mkdirs();


        }


    }




    public static double getFileSizeMB(

            String path

    ){



        File file =

                new File(path);



        return


                file.length()


                        /


                        1024.0


                        /


                        1024.0;



    }


    public static int countFiles(

            String folder

    ){

        File dir =

                new File(folder);


        File[] files =

                dir.listFiles();


        int count = 0;


        for(

                File file

                :

                files

        ){


            if(

                    file.getName()

                            .endsWith(

                                    ".dat"

                            )

            ){


                count++;

            }


        }


        return count;


    }


}