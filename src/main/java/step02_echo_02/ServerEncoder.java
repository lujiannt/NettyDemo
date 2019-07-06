package step02_echo_02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 服务端发送之前，将string编码成bytebuf 【不适用泛型】
 */
public class ServerEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        System.out.println("encoder : 我是编码器");
        byteBuf.writeBytes(((String) msg).getBytes());
    }
}
