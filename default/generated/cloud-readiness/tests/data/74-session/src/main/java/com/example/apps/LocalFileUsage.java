package com.example.apps;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.LinkOption;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.zip.ZipFile;

import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.net.URL;

public class LocalFileUsage {

    final static String filename = "C:/some.tmp";

    final static String filename1 = "/tmp/output/123.txt";

    public static void main(String[] args) {
	try {
	    RandomAccessFile randomFile1 = new RandomAccessFile("/tmp/some.tmp", "rwd");
	    RandomAccessFile randomFile2 = new RandomAccessFile(new File("/tmp/some.tmp"), "rwd");
		PrintWriter printWriter = new PrintWriter(filename1);

		PrintStream printStream = new PrintStream(filename);

		FileWriter fileWriter = new FileWriter("file.txt");

		FileReader fileReader = new FileReader(filename);
		FileReader fileReader1 = new FileReader("/var/opt/log");


		ZipFile zipFile = new ZipFile("/tmp/compressesd.zip");

		File tempFile = File.createTempFile("test", "tmp");

		AsynchronousFileChannel afc = new MyAsynchronousFileChannel();
	    afc.size();
	    FileChannel fc = new MyFileChannel();
	    fc.size();
	    FileLock fl = new MyFileLock(null, 0, 0, false);
	    fl.isValid();
	    DirectoryStream ds = new MyDirectoryStream();
	    ds.iterator();
	    FileSystem fsy = FileSystems.getDefault();
	    fsy.close();
	    Path p = Paths.get("foo", "path");
	    p.endsWith("foo");
	    FileStore fs = Files.getFileStore(p);
	    fs.isReadOnly();
	    
	    // the next 2 lines test that rule 'local-storage-00002' is only fired from the 2nd line
	    URL url1 = new URL("url");
	    URL url2 = new URL("file://");
	    new File("D:/file.ext");
		doStuff(null, "c:\foo");
		String[] paths = new String[]{"C:/s","D:/e","E:/f"};
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

	private static void doStuff(Object o, String s) {

	}

	private void nioPaths() {
	Paths.get("/tmp/lock.id");
    }
    
    private static class MyAsynchronousFileChannel extends AsynchronousFileChannel {
		@Override
		public long size() throws IOException {
			return 0;
		}

		@Override
		public AsynchronousFileChannel truncate(long size) throws IOException {
			return null;
		}

		@Override
		public void force(boolean metaData) throws IOException {

		}

		@Override
		public <A> void lock(long position, long size, boolean shared, A attachment, CompletionHandler<FileLock, ? super A> handler) {

		}

		@Override
		public Future<FileLock> lock(long position, long size, boolean shared) {
			return null;
		}

		@Override
		public FileLock tryLock(long position, long size, boolean shared) throws IOException {
			return null;
		}

		@Override
		public <A> void read(ByteBuffer dst, long position, A attachment, CompletionHandler<Integer, ? super A> handler) {

		}

		@Override
		public Future<Integer> read(ByteBuffer dst, long position) {
			return null;
		}

		@Override
		public <A> void write(ByteBuffer src, long position, A attachment, CompletionHandler<Integer, ? super A> handler) {

		}

		@Override
		public Future<Integer> write(ByteBuffer src, long position) {
			return null;
		}

		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public void close() throws IOException {

		}
	}
    private static class MyFileChannel extends FileChannel {
		@Override
		public int read(ByteBuffer dst) throws IOException {
			return 0;
		}

		@Override
		public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
			return 0;
		}

		@Override
		public int write(ByteBuffer src) throws IOException {
			return 0;
		}

		@Override
		public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
			return 0;
		}

		@Override
		public long position() throws IOException {
			return 0;
		}

		@Override
		public FileChannel position(long newPosition) throws IOException {
			return null;
		}

		@Override
		public long size() throws IOException {
			return 0;
		}

		@Override
		public FileChannel truncate(long size) throws IOException {
			return null;
		}

		@Override
		public void force(boolean metaData) throws IOException {

		}

		@Override
		public long transferTo(long position, long count, WritableByteChannel target) throws IOException {
			return 0;
		}

		@Override
		public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException {
			return 0;
		}

		@Override
		public int read(ByteBuffer dst, long position) throws IOException {
			return 0;
		}

		@Override
		public int write(ByteBuffer src, long position) throws IOException {
			return 0;
		}

		@Override
		public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException {
			return null;
		}

		@Override
		public FileLock lock(long position, long size, boolean shared) throws IOException {
			return null;
		}

		@Override
		public FileLock tryLock(long position, long size, boolean shared) throws IOException {
			return null;
		}

		@Override
		protected void implCloseChannel() throws IOException {

		}
	}
    private static class MyFileLock extends FileLock {
		protected MyFileLock(FileChannel channel, long position, long size, boolean shared) {
			super(channel, position, size, shared);
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public void release() throws IOException {

		}
	}
    private static class MyDirectoryStream implements DirectoryStream {
		@Override
		public Iterator iterator() {
			return null;
		}

		@Override
		public void close() throws IOException {

		}
	}
    private class MyFileStore extends FileStore {
		@Override
		public String name() {
			return "";
		}

		@Override
		public String type() {
			return "";
		}

		@Override
		public boolean isReadOnly() {
			return false;
		}

		@Override
		public long getTotalSpace() throws IOException {
			return 0;
		}

		@Override
		public long getUsableSpace() throws IOException {
			return 0;
		}

		@Override
		public long getUnallocatedSpace() throws IOException {
			return 0;
		}

		@Override
		public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
			return false;
		}

		@Override
		public boolean supportsFileAttributeView(String name) {
			return false;
		}

		@Override
		public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> type) {
			return null;
		}

		@Override
		public Object getAttribute(String attribute) throws IOException {
			return null;
		}
	}
    private class MyFileSystem extends FileSystem {
		@Override
		public FileSystemProvider provider() {
			return null;
		}

		@Override
		public void close() throws IOException {

		}

		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public boolean isReadOnly() {
			return false;
		}

		@Override
		public String getSeparator() {
			return "";
		}

		@Override
		public Iterable<Path> getRootDirectories() {
			return null;
		}

		@Override
		public Iterable<FileStore> getFileStores() {
			return null;
		}

		@Override
		public Set<String> supportedFileAttributeViews() {
			return new HashSet<>();
		}

		@Override
		public Path getPath(String first, String... more) {
			return null;
		}

		@Override
		public PathMatcher getPathMatcher(String syntaxAndPattern) {
			return null;
		}

		@Override
		public UserPrincipalLookupService getUserPrincipalLookupService() {
			return null;
		}

		@Override
		public WatchService newWatchService() throws IOException {
			return null;
		}
	}
    private class MyPath implements Path {
		@Override
		public FileSystem getFileSystem() {
			return null;
		}

		@Override
		public boolean isAbsolute() {
			return false;
		}

		@Override
		public Path getRoot() {
			return null;
		}

		@Override
		public Path getFileName() {
			return null;
		}

		@Override
		public Path getParent() {
			return null;
		}

		@Override
		public int getNameCount() {
			return 0;
		}

		@Override
		public Path getName(int index) {
			return null;
		}

		@Override
		public Path subpath(int beginIndex, int endIndex) {
			return null;
		}

		@Override
		public boolean startsWith(Path other) {
			return false;
		}

		@Override
		public boolean startsWith(String other) {
			return false;
		}

		@Override
		public boolean endsWith(Path other) {
			return false;
		}

		@Override
		public boolean endsWith(String other) {
			return false;
		}

		@Override
		public Path normalize() {
			return null;
		}

		@Override
		public Path resolve(Path other) {
			return null;
		}

		@Override
		public Path resolve(String other) {
			return null;
		}

		@Override
		public Path resolveSibling(Path other) {
			return null;
		}

		@Override
		public Path resolveSibling(String other) {
			return null;
		}

		@Override
		public Path relativize(Path other) {
			return null;
		}

		@Override
		public URI toUri() {
			return null;
		}

		@Override
		public Path toAbsolutePath() {
			return null;
		}

		@Override
		public Path toRealPath(LinkOption... options) throws IOException {
			return null;
		}

		@Override
		public File toFile() {
			return null;
		}

		@Override
		public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
			return null;
		}

		@Override
		public WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException {
			return null;
		}

		@Override
		public Iterator<Path> iterator() {
			return null;
		}

		@Override
		public int compareTo(Path other) {
			return 0;
		}
	}
}
