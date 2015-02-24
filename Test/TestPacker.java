import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EugeneM on 24.02.2015.
 */
public class TestPacker extends TestCase {
    String basePath = "D:\\iWork\\resources\\";

    String closedSubDir = "res_closed\\";
    String openedSubDir = "res_opened\\";

    String project_hdpi = "drawable\\";
    String hdpi = "drawable-hdpi\\";
    String mdpi = "drawable-mdpi\\";
    String xhdpi = "drawable-xhdpi\\";
    String xxhdpi = "drawable-xxhdpi\\";

    String closed = "ic_closed.png";
    String opened = "ic_opened.png";

    Packer packer;

    @Override
    protected void setUp() throws Exception {
        packer = new Packer();
    }

    @Override
    protected void tearDown() throws Exception {
        packer.fileList.clear();
        packer = null;
    }

    public void test_extractFiles() {
        packer.extractFiles();

        String closedPath = basePath + closedSubDir;
        String openedPath = basePath + openedSubDir;

        List<File> list = packer.fileList.get(packer.MDPI);

        File actualClosed = list.get(0);
        File actualOpened = list.get(1);

        File expectedClosed = new File(closedPath + mdpi + closed);
        File expectedOpened = new File(openedPath + mdpi + opened);

        assertEquals(expectedClosed, actualClosed);
        assertEquals(expectedOpened, actualOpened);


        list = packer.fileList.get(packer.HDPI);

        actualClosed = list.get(0);
        actualOpened = list.get(1);

        expectedClosed = new File(closedPath + hdpi + closed);
        expectedOpened = new File(openedPath + hdpi + opened);

        assertEquals(expectedClosed, actualClosed);
        assertEquals(expectedOpened, actualOpened);


        list = packer.fileList.get(packer.XHDPI);

        actualClosed = list.get(0);
        actualOpened = list.get(1);

        expectedClosed = new File(closedPath + xhdpi + closed);
        expectedOpened = new File(openedPath + xhdpi + opened);

        assertEquals(expectedClosed, actualClosed);
        assertEquals(expectedOpened, actualOpened);


        list = packer.fileList.get(packer.XXHDPI);

        actualClosed = list.get(0);
        actualOpened = list.get(1);

        expectedClosed = new File(closedPath + xxhdpi + closed);
        expectedOpened = new File(openedPath + xxhdpi + opened);

        assertEquals(expectedClosed, actualClosed);
        assertEquals(expectedOpened, actualOpened);
    }

    public void test_pasteFiles() {
        try {
            packer.extractFiles();
            packer.pasteFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dest = "D:\\TestPacker\\";
        boolean closedExist;
        boolean openedExist;

        closedExist = new File(dest + mdpi + closed).exists();
        openedExist = new File(dest + mdpi + opened).exists();

        assertTrue(closedExist);
        assertTrue(openedExist);


        closedExist = new File(dest + project_hdpi + closed).exists();
        openedExist = new File(dest + project_hdpi + opened).exists();

        assertTrue(closedExist);
        assertTrue(openedExist);


        closedExist = new File(dest + xhdpi + closed).exists();
        openedExist = new File(dest + xhdpi + opened).exists();

        assertTrue(closedExist);
        assertTrue(openedExist);


        closedExist = new File(dest + xxhdpi + closed).exists();
        openedExist = new File(dest + xxhdpi + opened).exists();

        assertTrue(closedExist);
        assertTrue(openedExist);
    }
}
