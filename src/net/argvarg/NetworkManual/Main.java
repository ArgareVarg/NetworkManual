package net.argvarg.NetworkManual;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Main start = new Main();
        start.run();
    }

    public static final String absolutePathToDir = System.getProperty("user.home") + "/.NetworkManual";
    public static final String absolutePathToConfsDir = absolutePathToDir + "/confs/";
    public static final String absolutePathToTempDir = absolutePathToDir + "/temp";
    public static final String[] myDirs = {absolutePathToDir, absolutePathToConfsDir, absolutePathToTempDir};
    public static File confsDir = new File(absolutePathToConfsDir);
    public static File tempDir = new File(absolutePathToTempDir);
    public static File[] confsDirContents = confsDir.listFiles();

    public static String selectedConf = null;

    private void mkdirs() {
        for (int i = 0; i < myDirs.length; i++) {
            boolean booleanExists = new File(myDirs[i]).mkdir();
        }
    }
    public void run() {
        mkdirs();
        SelectConfDialog myConfDialog = new SelectConfDialog();
        myConfDialog.run();
        System.out.println(confsDirContents.length);
    }

    public static void setSelectedConf(String absolutePathToConf) {
        selectedConf = absolutePathToConf;
    }
}
