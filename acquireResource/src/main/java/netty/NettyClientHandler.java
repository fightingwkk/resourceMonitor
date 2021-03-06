package netty;

import java.util.logging.Logger;

import acquireResource.AcquireCPU;
import acquireResource.AcquireDiskIO;
import acquireResource.AcquireMemory;
import acquireResource.AcquireNetIO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NettyClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger = Logger
			.getLogger(NettyClientHandler.class.getName());
	//private int counter;
//	private byte[] req;
//	
//	//private final ByteBuf firstMessage;
//	public NettyClientHandler() {
//		req = ("CPUSize"+System.getProperty("line.separator")).getBytes();
////		firstMessage = Unpooled.buffer(req.length);
////		firstMessage.writeBytes(req);
//	}
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		//ctx.writeAndFlush(firstMessage);
//		ByteBuf message = null;
//		for (int i = 0; i < 10; i++) {
//			message = Unpooled.buffer(req.length);
//			message.writeBytes(req);
//			ctx.writeAndFlush(message);
//		}
//	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf buf = (ByteBuf) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req,"UTF-8");
		String body = (String) msg;
		if(body.equalsIgnoreCase("acquireResource")){
			JSONObject jsonCPUUsageRate = new JSONObject();
			jsonCPUUsageRate = AcquireCPU.getCPUUsageRate();
			JSONArray jsonArrayDiskIORate = new JSONArray();
			jsonArrayDiskIORate = AcquireDiskIO.getDiskIORate();
			JSONObject jsonMemorySize = new JSONObject();
			jsonMemorySize = AcquireMemory.getMemorySize();
			JSONObject jsonMemoryUsageRate = new JSONObject();
			jsonMemoryUsageRate = AcquireMemory.geteMemoryUsageRate();
			JSONArray jsonArrayNetIO = new JSONArray();
			jsonArrayNetIO = AcquireNetIO.getNetIO();
			
			String jsonStr = jsonCPUUsageRate.toString() + System.getProperty("line.separator")
			+jsonArrayDiskIORate.toString() + System.getProperty("line.separator")
			+jsonMemorySize.toString() + System.getProperty("line.separator")
			+jsonMemoryUsageRate.toString() + System.getProperty("line.separator")
			+jsonArrayNetIO.toString() + System.getProperty("line.separator");
			ByteBuf resp = Unpooled.copiedBuffer(jsonStr.getBytes());
			ctx.writeAndFlush(resp);
		}
//		if(body.equalsIgnoreCase("DiskIORate")){
//			JSONArray jsonArray = new JSONArray();
//			jsonArray = AcquireDiskIO.getDiskIORate();
//			String jsonStr = jsonArray.toString()+System.getProperty("line.separator");
//			ByteBuf resp = Unpooled.copiedBuffer(jsonStr.getBytes());
//			ctx.writeAndFlush(resp);
//		}
//		if(body.equalsIgnoreCase("MemorySize")){
//			JSONObject json = new JSONObject();
//			json = AcquireMemory.getMemorySize();
//			String jsonStr = json.toString() + System.getProperty("line.separator");
//			ByteBuf resp = Unpooled.copiedBuffer(jsonStr.getBytes());
//			ctx.writeAndFlush(resp);
//		}
//		if(body.equalsIgnoreCase("MemoryUsageRate")){
//			JSONObject json = new JSONObject();
//			json = AcquireMemory.geteMemoryUsageRate();
//			String jsonStr = json.toString() + System.getProperty("line.separator");
//			ByteBuf resp = Unpooled.copiedBuffer(jsonStr.getBytes());
//			ctx.writeAndFlush(resp);
//		}
//		if(body.equalsIgnoreCase("NetIO")){
//			JSONArray jsonArray = new JSONArray();
//			jsonArray = AcquireNetIO.getNetIO();
//			String jsonStr = jsonArray.toString()+System.getProperty("line.separator");
//			ByteBuf resp = Unpooled.copiedBuffer(jsonStr.getBytes());
//			ctx.writeAndFlush(resp);
//		}
//		System.out.println("now is: "+body
//				+"; the counter is :"+ ++counter);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warning("Unexpected exception from downstream : "
				+cause.getMessage());
		ctx.close();
	}
}
