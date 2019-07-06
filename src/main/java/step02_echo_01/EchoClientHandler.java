package step02_echo_01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 客户端具体handler
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client: 连上了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client: 有服务端的消息");
        ByteBuf in = (ByteBuf) msg;
        System.out.println("msg :" + in.toString(CharsetUtil.UTF_8));

        ByteBuf out = Unpooled.copiedBuffer("我收到了哟",
                CharsetUtil.UTF_8);
        ChannelFuture cf = ctx.writeAndFlush(out);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client: 和服务端断开了");
    }
}
