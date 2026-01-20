package com.example.apps;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileImageOutputStreamExtended extends FileImageOutputStream {
    public FileImageOutputStreamExtended(File f) throws FileNotFoundException, IOException {
        super(f);
    }

    public void method() throws Throwable {
        super.finalize();
    }
}
