/*The MIT License (MIT)

Copyright (c) 2015 Ivy Development Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/
package org.ivy.connect;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import org.ivy.engine.thread.NetworkAcceptorThread;
import org.ivy.engine.thread.NetworkClientThread;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolReactor;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 16, 2015
 */
public class GameConnector extends ChannelInitializer<SocketChannel> {

	/**
	 * Binds the port and the {@code GameConnector}.
	 * 
	 * @throws InterruptedException If the port is already in use.
	 */
	public static void connectGame() throws InterruptedException {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(new NioEventLoopGroup(1, new NetworkAcceptorThread()), new NioEventLoopGroup(7, new NetworkClientThread()));
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		bootstrap.childHandler(new GameConnector());
		bootstrap.bind(new InetSocketAddress(RuneScape.getGameContext().getHost(), RuneScape.getGameContext().getPort())).sync();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("reactor", new ProtocolReactor());
		ch.pipeline().addLast("handler", new GameReactor());
	}

}
