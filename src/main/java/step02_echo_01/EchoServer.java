package step02_echo_01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端、客户端简单通讯【注意】未使用编解码，直接使用unpooled工具类解析字符串
 */
public class EchoServer {
    //绑定端口
    private int port = 10086;

    public EchoServer(int port) {
        this.port = port;
    }

    public void startServer() {
        //使用主从reactor线程模型
        //boss线程池 负责接收连接注册相关事件
        EventLoopGroup boss = new NioEventLoopGroup();
        //worker线程池 负责处理连接业务
        EventLoopGroup worker = new NioEventLoopGroup();

        //创建辅助类，作用是可自定义组装netty中各组件
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //1.绑定主从线程池
        serverBootstrap.group(boss, worker)
                //2.选择对应channel
                .channel(NioServerSocketChannel.class)
                //3.绑定初始化handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //添加自定义handler
                        socketChannel.pipeline().addLast(new EchoServerHandler());
                    }
                })
                //4.服务端进行相关设置, 指定等待连接队列长度
                .option(ChannelOption.SO_BACKLOG, 128)
                //5.对接受的连接进行相关设置， 长时间没响应会测试连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            //绑定端口开始监听
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //等待服务器通道关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new EchoServer(10086).startServer();
    }
}
