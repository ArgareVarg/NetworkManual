package net.argvarg.NetworkManual;

import java.io.*;

/**
 * Created by fredrik on 12/24/14.
 */
public class ConfigFile {
    public File confFile;
    public int confNum;
    public String driver;

    ConfigFile(File file, int confNum) {
        this.confFile = file;
        this.confNum = confNum;
    }

    public void wpa_supplicant() {
        try {
            Runtime.getRuntime().exec("wpa_supplicant -D" + driver + " -I" + getInterface() + " -c" + confFile.getAbsolutePath());
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private String getInterface() {
        return "wlan0";
    }

    public static String pskGen(String ssid, String password) {
        String[] result = new String[5];
        try {
            Process proc = Runtime.getRuntime().exec("wpa_passphrase " + ssid + " " + password);
            BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            for (int i = 0; i < 5; i++) {
                result[i] = stdout.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result[3]);
        return result[3];
    }

    public static String[] createConfig(String ssid, String keyMgmt, String password) {
        String[] fileContents = new String[8];
        fileContents[0] = "network={";
        fileContents[1] = "\tssid=\"" + ssid + "\"";
        fileContents[2] = "\tproto=RSN";
        fileContents[3] = "\tkey_mgmt=" + keyMgmt;
        fileContents[4] = "\tpairwise=CCMP TKIP";
        fileContents[5] = "\tgroup=CCMP TKIP";
        fileContents[6] = pskGen(ssid, password);
        fileContents[7] = "}";
        return fileContents;
    }
}
