package javax.swing.plaf.basic;

import com.example.apps.Deprecation;

import javax.security.auth.Subject;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.text.ParseException;

public class NewClass extends BasicMenuItemUI {
    public void method() throws IOException, ParseException {
        MouseInputHandler mouseInputHandler = new MouseInputHandler();

        BasicDirectoryModel basicDirectoryModel = new BasicDirectoryModel(null);
        basicDirectoryModel.intervalAdded(null);
        basicDirectoryModel.intervalRemoved(null);
        basicDirectoryModel.lt(null, null);

        BasicToolBarUI basicToolBarUI = new BasicToolBarUI();
        basicToolBarUI.createFloatingFrame(null);

        Subject.doAs(new Subject(), new PrivilegedActionImpl());

        SynthLookAndFeel synthLookAndFeel = new SynthLookAndFeel();
        URL url = new URL("hey");
        synthLookAndFeel.load(url);

        Thread thread = new Thread();
        thread.stop();
    }

    public class PrivilegedActionImpl implements PrivilegedAction<String> {
        @Override
        public String run() {
            return "";
        }
    }
}
