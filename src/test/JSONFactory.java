package test;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFactory {

	private String fileName;

	public JSONFactory(String fileName) {
		this.fileName = fileName;
	}

	public void createFile() {
		try {
			FileWriter fileWriter = new FileWriter(this.fileName);
			fileWriter.write("[\r\n" + "   {\r\n" + "      \"pol\":\"H\",\r\n" + "      \"sat\":\"BulgariaSat-1\",\r\n"
					+ "      \"orbital\":\"1.9° E\",\r\n" + "      \"freq\":\"12072\",\r\n"
					+ "      \"sym\":\"30000\",\r\n" + "      \"channels\":[\r\n" + "         {\r\n"
					+ "            \"sid\":\"606\",\r\n" + "            \"type\":\"TV\",\r\n"
					+ "            \"name\":\"Fuel TV HD\",\r\n" + "            \"v_pid\":\"2030\",\r\n"
					+ "            \"a_pid\":\"2031 eng\",\r\n" + "            \"compression\":\"MPEG-4\",\r\n"
					+ "            \"url\":\"http:\\/\\/fuel.tv\\/\",\r\n" + "            \"enc\":\"Bulcrypt\",\r\n"
					+ "            \"package\":\" \",\r\n" + "            \"res\":\"HD\"\r\n" + "         },\r\n"
					+ "         {\r\n" + "            \"compression\": \" \",\r\n" + "            \"enc\": \"FTA\",\r\n"
					+ "            \"package\": \" \",\r\n" + "            \"sid\": \"28468\",\r\n"
					+ "            \"a_pid\": \"1031 ger\",\r\n" + "            \"res\": \" \",\r\n"
					+ "            \"url\": \"http:\\/\\/www.swr3.de\\/\",\r\n" + "            \"name\": \"SWR3\",\r\n"
					+ "            \"type\": \"R\",\r\n" + "            \"v_pid\": \"0\"\r\n" + "         },\r\n"
					+ "         {\r\n" + "            \"enc\":\"Bulcrypt\",\r\n" + "            \"type\":\"TV\",\r\n"
					+ "            \"name\":\"Gametoon HD\",\r\n" + "            \"package\":\" \",\r\n"
					+ "            \"a_pid\":\"2041 eng\",\r\n" + "            \"compression\":\"MPEG-4\",\r\n"
					+ "            \"v_pid\":\"2040\",\r\n" + "            \"sid\":\"608\",\r\n"
					+ "            \"url\":\"http:\\/\\/www.gametoonbox.com\\/\",\r\n"
					+ "            \"res\":\"HD\"\r\n" + "         }\r\n" + "      ]\r\n" + "   },\r\n" + "   {\r\n"
					+ "      \"pol\":\"H\",\r\n" + "      \"orbital\":\"1.9° E\",\r\n"
					+ "      \"sat\":\"BulgariaSat-1\",\r\n" + "      \"freq\":\"12188\",\r\n"
					+ "      \"channels\":[\r\n" + "         {\r\n" + "            \"a_pid\":\"1701 bul\",\r\n"
					+ "            \"name\":\"Test HD\",\r\n" + "            \"res\":\"HD\",\r\n"
					+ "            \"url\":\"\",\r\n" + "            \"sid\":\"540\",\r\n"
					+ "            \"v_pid\":\"1700\",\r\n" + "            \"package\":\" \",\r\n"
					+ "            \"type\":\"TV\",\r\n" + "            \"enc\":\"BulcryptConax\",\r\n"
					+ "            \"compression\":\"HEVC (H.265)\"\r\n" + "         },\r\n" + "         {\r\n"
					+ "            \"compression\":\"HEVC (H.265)\",\r\n"
					+ "            \"name\":\"Auto Motor und Sport HD\",\r\n" + "            \"sid\":\"541\",\r\n"
					+ "            \"type\":\"TV\",\r\n" + "            \"a_pid\":\"1706 eng\",\r\n"
					+ "            \"url\":\"http:\\/\\/www.auto-motor-und-sport.de\\/\",\r\n"
					+ "            \"v_pid\":\"1705\",\r\n" + "            \"package\":\" \",\r\n"
					+ "            \"res\":\"HD\",\r\n" + "            \"enc\":\"BulcryptConax\"\r\n"
					+ "         },\r\n" + "         {\r\n" + "            \"enc\":\"BulcryptConax\",\r\n"
					+ "            \"res\":\"HD\",\r\n" + "            \"type\":\"TV\",\r\n"
					+ "            \"package\":\" \",\r\n"
					+ "            \"url\":\"http:\\/\\/www.chasseetpechetv.fr\\/\",\r\n"
					+ "            \"sid\":\"542\",\r\n" + "            \"a_pid\":\"1711 eng\",\r\n"
					+ "            \"v_pid\":\"1710\",\r\n" + "            \"name\":\"Chasse & Peche HD\",\r\n"
					+ "            \"compression\":\"HEVC (H.265)\"\r\n" + "         },\r\n" + "         {\r\n"
					+ "            \"v_pid\":\"1715\",\r\n" + "            \"res\":\"HD\",\r\n"
					+ "            \"type\":\"TV\",\r\n" + "            \"sid\":\"543\",\r\n"
					+ "            \"package\":\" \",\r\n" + "            \"url\":\"\",\r\n"
					+ "            \"name\":\"Wness TV HD\",\r\n" + "            \"compression\":\"HEVC (H.265)\",\r\n"
					+ "            \"enc\":\"BulcryptConax\",\r\n" + "            \"a_pid\":\"1716 bul\"\r\n"
					+ "         }\r\n" + "      ]\r\n" + "   },\r\n" + "   {\r\n" + "      \"sym\":\"28000\",\r\n"
					+ "      \"orbital\":\"4.8° E\",\r\n" + "      \"pol\":\"H\",\r\n" + "      \"freq\":\"11881\",\r\n"
					+ "      \"sat\":\"Astra 4A\",\r\n" + "      \"channels\":[\r\n" + "         {\r\n"
					+ "            \"url\":\"http:\\/\\/www.cmore.se\\/\",\r\n"
					+ "            \"name\":\"C More Fotboll HD\",\r\n" + "            \"compression\":\"MPEG-4\",\r\n"
					+ "            \"v_pid\":\"5001\",\r\n" + "            \"sid\":\"5000\",\r\n"
					+ "            \"res\":\"HD\",\r\n" + "            \"enc\":\"Videoguard\",\r\n"
					+ "            \"a_pid\":\"5002 swe\",\r\n" + "            \"package\":\"Viasat\",\r\n"
					+ "            \"type\":\"TV\"\r\n" + "         },\r\n" + "         {\r\n"
					+ "            \"package\":\"HD+\",\r\n" + "            \"name\":\"RTL HD\",\r\n"
					+ "            \"res\":\"HD\",\r\n" + "            \"type\":\"TV\",\r\n"
					+ "            \"compression\":\"MPEG-4\",\r\n" + "            \"a_pid\":\"259 ger\",\r\n"
					+ "            \"sid\":\"61200\",\r\n" + "            \"v_pid\":\"255\",\r\n"
					+ "            \"url\":\"http:\\/\\/www.rtl.de\\/\",\r\n"
					+ "            \"enc\":\"VideoguardIrdeto 2Nagravision 3\"\r\n" + "         },\r\n"
					+ "         {\r\n" + "            \"compression\":\"MPEG-2\",\r\n"
					+ "            \"enc\":\"Videoguard\",\r\n" + "            \"a_pid\":\"5012 eng\",\r\n"
					+ "            \"sid\":\"5010\",\r\n" + "            \"package\":\"Viasat\",\r\n"
					+ "            \"url\":\"http:\\/\\/www.nhk.or.jp\\/nhkworld\\/english\\/tv\\/\",\r\n"
					+ "            \"type\":\"TV\",\r\n" + "            \"v_pid\":\"5011\",\r\n"
					+ "            \"name\":\"NHK World TV\",\r\n" + "            \"res\":\"SD\"\r\n" + "         }\r\n"
					+ "      ]\r\n" + "   }\r\n" + "]");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
