package java.awt.color;

import javax.management.remote.rmi.RMIIIOPServerImpl;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.io.IOException;

public class TestClass {

    public static void main(String[] args) throws IOException {
        ICC_Profile profile = ICC_Profile.getInstance(1);
        profile.finalize();

        ColorModel colorModel = ColorModel.getRGBdefault();
        colorModel.finalize();

        IndexColorModel indexColorModel = new IndexColorModel(0, 0, null, null, null);
        indexColorModel.finalize();

        ThreadGroup threadGroup = new ThreadGroup("");
        threadGroup.allowThreadSuspension(false);

        RMIIIOPServerImpl server = new RMIIIOPServerImpl(null);
        Compiler compiler = null;
    }


}
