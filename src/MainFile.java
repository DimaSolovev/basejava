import java.io.File;

public class MainFile {
    public static void recursion(File file){
        if(file.isFile()){
            System.out.println(file.getName());
        }else if(file.isDirectory()) {
            File[] arr = file.listFiles();
            for (File fil: arr){
                recursion(fil);
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
        File dir = new File("C:\\D\\BaseJava\\basejava");
        recursion(dir);
    }
}
