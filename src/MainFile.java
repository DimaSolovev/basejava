import java.io.File;

public class MainFile {
    public static void printDeepDirectory(File dir) {
        int i = 0;
        String br = "";
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                i++;
                for (int j = 0; j < i; j++) {
                    br = br + " ";
                }
                if (file.isFile()) {
                    System.out.println("File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(br + "Directory: " + file.getName());
                    printDeepDirectory(file);
                }
            }
        }
    }

    public static void main(String[] args) {
//        String filePath = ".\\.gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File("./src/ru/javawebinar/basejava");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //File dir = new File("C:\\D\\BaseJava\\basejava");

        File dir = new File(".");
        printDeepDirectory(dir);

    }
}
