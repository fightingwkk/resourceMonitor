package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NettyServerHandler extends ChannelHandlerAdapter {
	// private int counter;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// ByteBuf buf = (ByteBuf) msg;
		// byte[] req = new byte[buf.readableBytes()];
		// buf.readBytes(req);
		String body = (String) msg;
		System.out.println(body);
		
//		if(body.startsWith("{\"CPUUsageRate\":")){
//			JSONObject json =  JSONObject.fromObject(body);
//		}
//		if(body.startsWith("[{\"diskName\":")){
//			JSONArray json =  JSONArray.fromObject(body);
//		}
//		if(body.startsWith("{\"memorySize\":")){
//			JSONObject json =  JSONObject.fromObject(body);
//		}
//		if(body.startsWith("{\"memoryUsageRate\":")){
//			JSONObject json =  JSONObject.fromObject(body);
//		}
//		if(body.startsWith("[{\"Interface\":")){
//			JSONArray json =  JSONArray.fromObject(body);
//		}
		
		// System.out.println("The time server receive order:" + body
		// +"; the counter is : "+ ++counter);
		// String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
		// ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD
		// ORDER";
		// currentTime = currentTime + System.getProperty("line.separator");
		// ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		// ctx.writeAndFlush(resp);
	}


	public void acquireResource(String message){
		System.out.println("massage:"+message);
	}
	@Override
	public void channelActive( ChannelHandlerContext ctx) throws Exception {
		String message = null;
		message = "acquireResource";
		message += System.getProperty("line.separator");
		ByteBuf resp = null;
		for (int i = 0; i < 3; i++) {
			
		
		resp = Unpooled.copiedBuffer(message.getBytes());
		ctx.writeAndFlush(resp);
		//resp.release();
		//Thread.sleep(5000);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
