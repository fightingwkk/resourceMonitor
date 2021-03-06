package acquireResource;

import net.sf.json.JSONObject;

public class AcquireCPU {
	private static String CMD = "cat /proc/stat";

	public static JSONObject getCPUUsageRate() {
		String info1 = null;
		String info2 = null;
		try {
			info1 = Runcommand.runCommand(CMD);
			Thread.sleep(1000);//隔一秒再运行一次
			info2 = Runcommand.runCommand(CMD);
		} catch (Exception e) {
		}

		double total1 = 0.0;// 第一次获取的CPU使用总时间
		double total2 = 0.0;// 第二次获取的CPU使用总时间
		double CPUUsageRate = 0.0;// CPU使用率
		String[] data1 = info1.split("\n");
		String[] data2 = info2.split("\n");
		String[] strdata1 = data1[0].split(" +");
		String[] strdata2 = data2[0].split(" +");
		int[] numdata1 = new int[strdata1.length - 1];
		int[] numdata2 = new int[strdata2.length - 1];
		for (int i = 1; i < strdata1.length; i++) {
			numdata1[i - 1] = Integer.parseInt(strdata1[i]);
			total1 += numdata1[i - 1];
			numdata2[i - 1] = Integer.parseInt(strdata2[i]);
			total2 += numdata2[i - 1];
		}
		double idle1 = numdata1[3];// CPU未使用的时间
		double idle2 = numdata2[3];// CPU未使用的时间
		//System.out.println(info1);
		//System.out.println(info2);
		CPUUsageRate = 1.0 - (idle2 - idle1) / (total2 - total1);//CPU使用率
		JSONObject json = new JSONObject();
		json.put("CPUUsageRate", CPUUsageRate);
		return json;

	}
}
