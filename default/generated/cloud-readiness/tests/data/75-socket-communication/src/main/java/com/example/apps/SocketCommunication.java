package com.example.apps;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.MulticastSocket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.channels.MembershipKey;
import java.nio.channels.NetworkChannel;
import java.nio.channels.MulticastChannel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.nio.channels.spi.SelectorProvider;

import java.net.ServerSocket;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SocketCommunication {
	
	private static class MySocket extends Socket {}
	private static class MyMulticastSocket extends MulticastSocket {
		public MyMulticastSocket() throws IOException {
		}
	}
	private static class MyDatagramSocket extends DatagramSocket {
		public MyDatagramSocket() throws SocketException {
		}
	}
	private static class MyInetSocketAddress extends InetSocketAddress {
		public MyInetSocketAddress(int port) {
			super(port);
		}
	}
	private static class MyNetworkChannel implements NetworkChannel {
		@Override
		public NetworkChannel bind(SocketAddress local) throws IOException {
			return null;
		}

		@Override
		public SocketAddress getLocalAddress() throws IOException {
			return null;
		}

		@Override
		public <T> NetworkChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}

		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public void close() throws IOException {

		}
	}
	private static class MyMulticastChannel implements MulticastChannel {
		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public void close() throws IOException {

		}

		@Override
		public MembershipKey join(InetAddress group, NetworkInterface interf) throws IOException {
			return null;
		}

		@Override
		public MembershipKey join(InetAddress group, NetworkInterface interf, InetAddress source) throws IOException {
			return null;
		}

		@Override
		public NetworkChannel bind(SocketAddress local) throws IOException {
			return null;
		}

		@Override
		public SocketAddress getLocalAddress() throws IOException {
			return null;
		}

		@Override
		public <T> NetworkChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}
	}
	private static class MyDatagramChannel extends DatagramChannel {
		/**
		 * Initializes a new instance of this class.
		 *
		 * @param provider The provider that created this channel
		 */
		protected MyDatagramChannel(SelectorProvider provider) {
			super(provider);
		}

		@Override
		public DatagramChannel bind(SocketAddress local) throws IOException {
			return null;
		}

		@Override
		public <T> DatagramChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}

		@Override
		public DatagramSocket socket() {
			return null;
		}

		@Override
		public boolean isConnected() {
			return false;
		}

		@Override
		public DatagramChannel connect(SocketAddress remote) throws IOException {
			return null;
		}

		@Override
		public DatagramChannel disconnect() throws IOException {
			return null;
		}

		@Override
		public SocketAddress getRemoteAddress() throws IOException {
			return null;
		}

		@Override
		public SocketAddress receive(ByteBuffer dst) throws IOException {
			return null;
		}

		@Override
		public int send(ByteBuffer src, SocketAddress target) throws IOException {
			return 0;
		}

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
		public SocketAddress getLocalAddress() throws IOException {
			return null;
		}

		@Override
		public MembershipKey join(InetAddress group, NetworkInterface interf) throws IOException {
			return null;
		}

		@Override
		public MembershipKey join(InetAddress group, NetworkInterface interf, InetAddress source) throws IOException {
			return null;
		}

		@Override
		protected void implCloseSelectableChannel() throws IOException {

		}

		@Override
		protected void implConfigureBlocking(boolean block) throws IOException {

		}
	}
	private static class MyAsynchronousSocketChannel extends AsynchronousSocketChannel {
		/**
		 * Initializes a new instance of this class.
		 *
		 * @param provider The provider that created this channel
		 */
		protected MyAsynchronousSocketChannel(AsynchronousChannelProvider provider) {
			super(provider);
		}

		@Override
		public AsynchronousSocketChannel bind(SocketAddress local) throws IOException {
			return null;
		}

		@Override
		public <T> AsynchronousSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}

		@Override
		public AsynchronousSocketChannel shutdownInput() throws IOException {
			return null;
		}

		@Override
		public AsynchronousSocketChannel shutdownOutput() throws IOException {
			return null;
		}

		@Override
		public SocketAddress getRemoteAddress() throws IOException {
			return null;
		}

		@Override
		public <A> void connect(SocketAddress remote, A attachment, CompletionHandler<Void, ? super A> handler) {

		}

		@Override
		public Future<Void> connect(SocketAddress remote) {
			return null;
		}

		@Override
		public <A> void read(ByteBuffer dst, long timeout, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> handler) {

		}

		@Override
		public Future<Integer> read(ByteBuffer dst) {
			return null;
		}

		@Override
		public <A> void read(ByteBuffer[] dsts, int offset, int length, long timeout, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> handler) {

		}

		@Override
		public <A> void write(ByteBuffer src, long timeout, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> handler) {

		}

		@Override
		public Future<Integer> write(ByteBuffer src) {
			return null;
		}

		@Override
		public <A> void write(ByteBuffer[] srcs, int offset, int length, long timeout, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> handler) {

		}

		@Override
		public SocketAddress getLocalAddress() throws IOException {
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
	private static class MySocketChannel extends SocketChannel {
		/**
		 * Initializes a new instance of this class.
		 *
		 * @param provider The provider that created this channel
		 */
		protected MySocketChannel(SelectorProvider provider) {
			super(provider);
		}

		@Override
		public SocketChannel bind(SocketAddress local) throws IOException {
			return null;
		}

		@Override
		public <T> SocketChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}

		@Override
		public SocketChannel shutdownInput() throws IOException {
			return null;
		}

		@Override
		public SocketChannel shutdownOutput() throws IOException {
			return null;
		}

		@Override
		public Socket socket() {
			return null;
		}

		@Override
		public boolean isConnected() {
			return false;
		}

		@Override
		public boolean isConnectionPending() {
			return false;
		}

		@Override
		public boolean connect(SocketAddress remote) throws IOException {
			return false;
		}

		@Override
		public boolean finishConnect() throws IOException {
			return false;
		}

		@Override
		public SocketAddress getRemoteAddress() throws IOException {
			return null;
		}

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
		public SocketAddress getLocalAddress() throws IOException {
			return null;
		}

		@Override
		protected void implCloseSelectableChannel() throws IOException {

		}

		@Override
		protected void implConfigureBlocking(boolean block) throws IOException {

		}
	}
	
	private static class MyServerSocket extends ServerSocket {
		public MyServerSocket() throws IOException {
		}
	}
	private static class MyAsynchronousServerSocketChannel extends AsynchronousServerSocketChannel {
		/**
		 * Initializes a new instance of this class.
		 *
		 * @param provider The provider that created this channel
		 */
		protected MyAsynchronousServerSocketChannel(AsynchronousChannelProvider provider) {
			super(provider);
		}

		@Override
		public AsynchronousServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {
			return null;
		}

		@Override
		public <T> AsynchronousServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}

		@Override
		public <A> void accept(A attachment, CompletionHandler<AsynchronousSocketChannel, ? super A> handler) {

		}

		@Override
		public Future<AsynchronousSocketChannel> accept() {
			return null;
		}

		@Override
		public SocketAddress getLocalAddress() throws IOException {
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
	private static class MyServerSocketChannel extends ServerSocketChannel {
		/**
		 * Initializes a new instance of this class.
		 *
		 * @param provider The provider that created this channel
		 */
		protected MyServerSocketChannel(SelectorProvider provider) {
			super(provider);
		}

		@Override
		public ServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {
			return null;
		}

		@Override
		public <T> ServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
			return null;
		}

		@Override
		public <T> T getOption(SocketOption<T> name) throws IOException {
			return null;
		}

		@Override
		public Set<SocketOption<?>> supportedOptions() {
			return Collections.emptySet();
		}

		@Override
		public ServerSocket socket() {
			return null;
		}

		@Override
		public SocketChannel accept() throws IOException {
			return null;
		}

		@Override
		public SocketAddress getLocalAddress() throws IOException {
			return null;
		}

		@Override
		protected void implCloseSelectableChannel() throws IOException {

		}

		@Override
		protected void implConfigureBlocking(boolean block) throws IOException {

		}
	}
	
	public static void main(String argv[]) throws Exception {
		Socket s = new Socket();
		s.getInputStream();
		MulticastSocket ms = new MulticastSocket();
		ms.close();
		DatagramSocket ds = new DatagramSocket();
		ds.close();
		InetSocketAddress isa = new MyInetSocketAddress(10);
		isa.isUnresolved();
		NetworkChannel nc = new MyNetworkChannel();
		nc.getLocalAddress();
		MulticastChannel mc = new MyMulticastChannel();
		mc.close();
		DatagramChannel dc =  new MyDatagramChannel(null);
		dc.getLocalAddress();
		AsynchronousSocketChannel asc = new MyAsynchronousSocketChannel(null);
		asc.getLocalAddress();
		SocketChannel sc = new MySocketChannel(null);
		sc.isConnected();
		
		ServerSocket ss = new ServerSocket();
		ss.close();
		AsynchronousServerSocketChannel assd = new MyAsynchronousServerSocketChannel(null);
		assd.getLocalAddress();
		ServerSocketChannel ssc = new MyServerSocketChannel(null);
		ssc.getLocalAddress();
	}
}