package com.bungii.common.utilities;

import java.io.*;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class ManageDevices {
    static public String availableDeviceList;
    private static String fileName="Availabledevices.txt";
    public static void write(String content) {

        try {
            FileOutputStream file = new FileOutputStream(fileName);

            boolean written = false;
            do {
                try {
                    // Lock it!
                    FileLock lock = file.getChannel().lock();
                    try {
                        // Write the bytes.
                        byte[] bytes = content.getBytes();

                        file.write(bytes);
                        written = true;
                    } finally {
                        // Release the lock.
                        lock.release();
                    }
                } catch (OverlappingFileLockException ofle) {
                    try {
                        // Wait a bit
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        throw new InterruptedIOException("Interrupted waiting for a file lock.");
                    }
                }
            } while (!written);
        } catch (IOException ex) {
            System.out.println("Failed to lock " + ex);
        }
    }

    static public String readFile()
    {
        String content = null;
        File file = new File(fileName);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    static public void afterSuiteManageDevice(){
        String ClassName=System.getProperty("runner.class");
        String curentThreadNumber = ClassName.substring(2, 4);
       // System.out.println("***Thread Number : "+curentThreadNumber+" Available devices "+ManageDevices.readFile());
        /*if(curentThreadNumber.equals("01")){
            ManageDevices.write(System.getProperty("ALL_DEVICES"));
        }else*/ {
            try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
            if(ManageDevices.readFile().trim().equals("")) {
                ManageDevices.write(System.getProperty("DEVICE"));
            }else {
                ManageDevices.write(ManageDevices.readFile()+","+System.getProperty("DEVICE"));
            }
        }
       // System.out.println("***Thread Number : "+curentThreadNumber+" | Available devices after releasing current running device : "+ManageDevices.readFile());
    }

}
