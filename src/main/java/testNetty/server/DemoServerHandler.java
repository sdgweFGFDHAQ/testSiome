package testNetty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class DemoServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("" + ctx.channel().remoteAddress() + "," + msg);

        String response = null;
        if ("stop".equals(msg)) {
            response = "停止连接";
        } else {
            response = "连接中";
        }
        ctx.channel().writeAndFlush("from server" + response);//1
//        ByteBuf byteBuf = Unpooled.copiedBuffer(response.getBytes());
//        ctx.writeAndFlush(byteBuf);//2
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
