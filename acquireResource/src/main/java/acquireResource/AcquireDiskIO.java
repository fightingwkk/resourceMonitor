package acquireResource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AcquireDiskIO {
	public static String CMD = "iostat -d -x 1 2";

	public static JSONArray getDiskIORate() {
		String info = Runcommand.runCommand(CMD);
		String diskName = null;
		double rkb = 0.0;
		double wkb = 0.0;
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		String[] data = info.split("\n");
		for (int i = 7; i < data.length; i++) {
			String[] numdata = data[i].split(" +");
			diskName = numdata[0];//磁盘名称
			rkb = Double.parseDouble(numdata[5]);//磁盘读数据速率
			wkb = Double.parseDouble(numdata[6]);//磁盘写数据速率
			json.put("diskName", diskName);
			json.put("rkb", rkb);
			json.put("wkb", wkb);
			jsonArray.add(json);
		}
		return jsonArray;//返回json数组
	}

//	public static void main(String[] args) {
//		String info = Runcommand.runCommand(CMD);
//		System.out.println(info);
//		System.out.println(getDiskIORate(info));
//	}
}
