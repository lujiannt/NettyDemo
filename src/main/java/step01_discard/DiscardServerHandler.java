package step01_discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务器具体handler
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 监听新连接加入
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有一个新的连接");
    }

    /**
     * 监听通道是否有新消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("有一个新的消息");
    }

    /**
     * 监听连接断开
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("有一个连接断开了");
    }
}
