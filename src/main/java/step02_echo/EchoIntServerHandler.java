package step02_echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 服务器具体handler
 */
public class EchoIntServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 监听新连接加入
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有一个新的连接");

        ByteBuf byteBuf = Unpooled.copiedBuffer("1314",
                CharsetUtil.UTF_8);
        ChannelFuture channelFuture = ctx.writeAndFlush(byteBuf);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("发送成功");
                } else {
                    System.out.println("发送失败");
                }
            }
        });
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
        ByteBuf in = (ByteBuf) msg;
        System.out.println("msg :" + in.toString(CharsetUtil.UTF_8));
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
