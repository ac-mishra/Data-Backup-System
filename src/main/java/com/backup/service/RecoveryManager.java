package com.backup.service;

import com.backup.io.BinaryHandler;
import com.backup.model.BackupData;

import java.util.Stack;


/**
 *
 * Restore backup snapshots
 *
 */


public class RecoveryManager {


    private Stack<BackupData>

            history


            =


            new Stack<>();



    public BackupData restore(

            String path

    )

            throws Exception {



        BackupData backup =


                BinaryHandler.restore(

                        path

                );



        history.push(

                backup

        );



        return backup;



    }



    public BackupData undoRestore(){


        if(

                history.isEmpty()

        ){


            return null;


        }



        history.pop();



        if(

                history.isEmpty()

        ){


            return null;


        }



        return history.peek();



    }



}