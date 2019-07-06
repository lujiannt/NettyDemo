package step03_pojo01_default;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class PojoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 监听新连接加入
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有一个新的连接");

        //此处写入对象发送给客户端
        User user = new User("张三", 21);
        Pet pet = new Pet("小黑1");
        user.setPet(pet);
        ChannelFuture channelFuture = ctx.writeAndFlush(user);

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
