package com.backup.compression;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionUtils {


    public static void compress(

            String source,

            String target

    )

            throws IOException {


        try(

                FileInputStream fis =
                        new FileInputStream(source);

                GZIPOutputStream gzip =

                        new GZIPOutputStream(

                                new FileOutputStream(target)

                        )

        ){


            byte[] buffer =

                    new byte[1024];


            int length;


            while(

                    (length=fis.read(buffer))

                            >0

            ){


                gzip.write(

                        buffer,

                        0,

                        length

                );


            }


        }


    }



    public static void decompress(

            String source,

            String target

    )

            throws IOException {


        try(

                GZIPInputStream gzip =


                        new GZIPInputStream(

                                new FileInputStream(source)

                        );


                FileOutputStream fos =

                        new FileOutputStream(target)

        ){


            byte[] buffer =

                    new byte[1024];


            int length;



            while(

                    (length=gzip.read(buffer))

                            >0

            ){


                fos.write(

                        buffer,

                        0,

                        length

                );


            }


        }


    }



}