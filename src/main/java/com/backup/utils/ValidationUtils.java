package com.backup.utils;

public class ValidationUtils {


    public static boolean isValidInteger(

            String value

    ){

        try{

            Integer.parseInt(

                    value

            );

            return true;

        }

        catch(Exception e){

            return false;

        }

    }



    public static boolean isValidDouble(

            String value

    ){

        try{

            Double.parseDouble(

                    value

            );

            return true;

        }

        catch(Exception e){

            return false;

        }

    }



}