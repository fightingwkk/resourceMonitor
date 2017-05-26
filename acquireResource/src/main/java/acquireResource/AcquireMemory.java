package acquireResource;

import net.sf.json.JSONObject;

public class AcquireMemory {
	private static int memorySize;//内存大小
	private static double memoryUsageRate;//内存使用率
	private static String CMD = "free";

	

public static JSONObject getMemorySize(){
	String info = Runcommand.runCommand(CMD);
	JSONObject json = new JSONObject();
	String[] data=info.split(" +");
    memorySize=Integer.parseInt(data[7]);
    json.put("memorySize", memorySize);
	return json;
}
public static JSONObject geteMemoryUsageRate(){
	String info = Runcommand.runCommand(CMD);
	JSONObject json = new JSONObject();
	String[] data=info.split(" +");
	double total=Double.parseDouble(data[7]);//内存总大小
	double used=Double.parseDouble(data[8]);//已使用内存大小
	memoryUsageRate = used/total;
	json.put("memoryUsageRate", memoryUsageRate);
	return json;
}

//	public static void main(String[] args) {
//		String info = Runcommand.runCommand(CMD);
//		System.out.println(info);
//		System.out.println(getMemorySize(info));
//		System.out.println(geteMemoryUsageRate(info));
//	}

}
