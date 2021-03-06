package acquireResource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AcquireNetIO {
	private static String CMD = "cat /proc/net/dev";

	public static JSONArray getNetIO() {
		String info1 = null;
		String info2 = null;
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			info1 = Runcommand.runCommand(CMD);
			Thread.sleep(1000);//隔一秒再执行一次
			info2 = Runcommand.runCommand(CMD);
			//System.out.println(info1);
			//System.out.println(info2);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String[] data1 = info1.split("\n");
		String[] data2 = info2.split("\n");
		int receiveBytes1 = 0;
		int transmitBytes1 = 0;
		int receiveBytes2 = 0;
		int transmitBytes2 = 0;
		for (int i = 2; i < data1.length; i++) {
			String[] numdata1 = data1[i].trim().split(" +");
			String[] numdata2 = data2[i].trim().split(" +");
			receiveBytes1 = Integer.parseInt(numdata1[1]);
			transmitBytes1 = Integer.parseInt(numdata1[9]);
			receiveBytes2 = Integer.parseInt(numdata2[1]);
			transmitBytes2 = Integer.parseInt(numdata2[9]);

			int receiveBytes = receiveBytes2 - receiveBytes1;
			int transmitBytes = transmitBytes2 - transmitBytes1;
			numdata1[0] = numdata1[0].substring(0, numdata1[0].length()-1);
			json.put("Interface", numdata1[0]);//端口名称
			json.put("receiveBytes", receiveBytes);//端口一秒内接收的字节数
			json.put("transmitBytes", transmitBytes);//端口一秒内发送的字节数
			jsonArray.add(json);
			// System.out.println(numdata1[0]+" receiveBytes:" + receiveBytes +
			// " bytes transmitBytes:" + transmitBytes + " bytes");
		}
		return jsonArray;//返回json数组
	}

//	public static void main(String[] args) {
//		System.out.println(getNetIO());
//	}
}
