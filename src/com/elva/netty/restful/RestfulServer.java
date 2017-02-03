package com.elva.netty.restful;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class RestfulServer {
	public static void main(String args[]) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .childHandler(new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpRequestDecoder());
                ch.pipeline().addLast(new HttpResponseEncoder());
                ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpObject>(){
                    protected void channelRead0(ChannelHandlerContext ctx,HttpObject msg) throws Exception {
                        if(msg instanceof HttpRequest){
                        	HttpRequest request = (HttpRequest)msg;
//                        	URI uri = new URI(request.getUri());
                        	System.out.println(request.getUri());
                        	StringBuilder builder = new StringBuilder();
                        	builder.append(request.getUri()).append("\n");
                        	
                        	response(ctx,"success");
                        }
                    }
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                    }
                });
            }
        });
        
        ChannelFuture f = b.bind(8080).sync();
        
        f.channel().closeFuture().sync();
        
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    
    }
	
	public static void response(ChannelHandlerContext ctx,String msg){
		ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
		FullHttpResponse res = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK,
				buf
				);
		res.headers().set("Content-Type", "text/html; charset=UTF-8");
		res.headers().set("Content-Length", buf.readableBytes());
		ctx.channel().writeAndFlush(res);
	}
}
