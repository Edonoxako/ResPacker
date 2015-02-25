import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EugeneM on 24.02.2015.
 */
public class Packer {
    public String HDPI = "hdpi";
    public String MDPI = "mdpi";
    public String XHDPI = "xhdpi";
    public String XXHDPI = "xxhdpi";

    private String BASE_PATH = "D:\\iWork\\resources\\";

    private String PROJECT_HDPI_SUBDIR = "drawable\\";
    private String HDPI_SUBDIR = "drawable-hdpi\\";
    private String MDPI_SUBDIR = "drawable-mdpi\\";
    private String XHDPI_SUBDIR = "drawable-xhdpi\\";
    private String XXHDPI_SUBDIR = "drawable-xxhdpi\\";

    private String DEST_PATH = "D:\\iWork\\CalConverter1\\CalConverter\\res\\";

    public Map<String, List<File>> fileList;


    public Packer() {
        fileList = new HashMap<String, List<File>>();
    }

    public void extractFiles() {
        File root = new File(BASE_PATH);
        String subDirectories[] = root.list();

        List<File> mdpiFiles = new ArrayList<File>();
        List<File> hdpiFiles = new ArrayList<File>();
        List<File> xhdpiFiles = new ArrayList<File>();
        List<File> xxhdpiFiles = new ArrayList<File>();

        for (String subDir : subDirectories) {
            mdpiFiles.add(new File(BASE_PATH + subDir + "\\" + MDPI_SUBDIR).listFiles()[0]);
            hdpiFiles.add(new File(BASE_PATH + subDir + "\\" + HDPI_SUBDIR).listFiles()[0]);
            xhdpiFiles.add(new File(BASE_PATH + subDir + "\\" + XHDPI_SUBDIR).listFiles()[0]);
            xxhdpiFiles.add(new File(BASE_PATH + subDir + "\\" + XXHDPI_SUBDIR).listFiles()[0]);
        }

        fileList.put(MDPI, mdpiFiles);
        fileList.put(HDPI, hdpiFiles);
        fileList.put(XHDPI, xhdpiFiles);
        fileList.put(XXHDPI, xxhdpiFiles);
    }

    public void pasteFiles() throws IOException {
        File dest;

        dest = new File(DEST_PATH + MDPI_SUBDIR);
        if (!dest.exists()) dest.mkdir();

        for (File mdpiRes : fileList.get(MDPI)) {
            dest = new File(DEST_PATH + MDPI_SUBDIR + mdpiRes.getName());
            if ((dest.exists())) dest.delete();
            Files.copy(mdpiRes.toPath(), dest.toPath());
        }


        dest = new File(DEST_PATH + PROJECT_HDPI_SUBDIR);
        if (!dest.exists()) dest.mkdir();

        for (File hdpiRes : fileList.get(HDPI)) {
            dest = new File(DEST_PATH + PROJECT_HDPI_SUBDIR + hdpiRes.getName());
            if ((dest.exists())) dest.delete();
            Files.copy(hdpiRes.toPath(), dest.toPath());
        }


        dest = new File(DEST_PATH + XHDPI_SUBDIR);
        if (!dest.exists()) dest.mkdir();

        for (File xhdpiRes : fileList.get(XHDPI)) {
            dest = new File(DEST_PATH + XHDPI_SUBDIR + xhdpiRes.getName());
            if ((dest.exists())) dest.delete();
            Files.copy(xhdpiRes.toPath(), dest.toPath());
        }


        dest = new File(DEST_PATH + XXHDPI_SUBDIR);
        if (!dest.exists()) dest.mkdir();

        for (File xxhdpiRes : fileList.get(XXHDPI)) {
            dest = new File(DEST_PATH + XXHDPI_SUBDIR + xxhdpiRes.getName());
            if ((dest.exists())) dest.delete();
            Files.copy(xxhdpiRes.toPath(), dest.toPath());
        }
    }

    public static void main(String args[]) {
        try {
            echo("starting...");
            Packer p = new Packer();
            p.extractFiles();
            p.pasteFiles();
            echo("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void echo(String txt){
        System.out.println(txt);
    }
}
