import java.io.File;

public class MainFile {
    public static void printDeepDirectory(File dir, String brake) {

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(brake + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(brake + "Directory: " + file.getName());
                    printDeepDirectory(file, " " + brake);
                }
            }
        }
    }

    public static void main(String[] args) {
        File dir = new File(".");
        printDeepDirectory(dir, "");
    }
}
