package step02_echo_02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * 客户端对服务端发来的信息解码成String
 */
public class ClientDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        //解码成String
        System.out.println("Decoder : 我要解码了哟");
        out.add(byteBuf.toString(CharsetUtil.UTF_8));
    }
}
