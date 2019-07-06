package step02_echo_02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 服务端发送之前，将string编码成bytebuf 【使用泛型】
 */
public class ServerEncoderByT extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String msg, ByteBuf byteBuf) throws Exception {
        System.out.println("encoder : 我是编码器");
        byteBuf.writeBytes(msg.getBytes());
    }
}
