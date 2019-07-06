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
        System.out.println("bytes size : " + byteBuf.readableBytes());
        if (byteBuf.readableBytes() < 8) {
            return;
        }
        System.out.println("Decoder : 我要解码了哟");

        //【注意.方法一】这里要用readBytes先读出来，然后转string添加到out
        ByteBuf buf = byteBuf.readBytes(8);
        out.add(buf.toString(CharsetUtil.UTF_8));

        //【注意.方法二】如果不想像上面那样先读出来，就必须在每次添加到out后手动指定bytebuf中指针位置
//        out.add(byteBuf.toString(CharsetUtil.UTF_8));
        //手动指定缓存跳过当前已读的部分
//        byteBuf.skipBytes(byteBuf.readableBytes());
        //https://www.orchome.com/915

    }
}
