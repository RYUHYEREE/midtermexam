package ku.opensrcsw._MidTerm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
static String clientId = "NFba8mGsUkVdP5IkKCyP";//애플리케이션 클라이언트 아이디값";
static String clientSecret = "_FgGe8E365";//애플리케이션 클라이언트 시크릿값";

public static void main(String[] args) throws IOException, ParseException{
Scanner sc = new Scanner(System.in);
System.out.print("검색어를 입력하세요 : ");
String search = sc.nextLine();
String text = URLEncoder.encode(search, "UTF-8");
String apiURL = "https://openapi.naver.com/v1/search/movie?query=$" + text; //json결과
URL url = new URL(apiURL);
HttpURLConnection con = (HttpURLConnection)url.openConnection();
con.setRequestMethod("GET");
con.setRequestProperty("X-Naver-Client-Id", clientId);
con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
int responseCode = con.getResponseCode();
BufferedReader br;
if(responseCode==200) {
br=new BufferedReader(new InputStreamReader(con.getInputStream()));
}
else {
br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
}
String inputLine;
StringBuffer response = new StringBuffer();
while((inputLine = br.readLine())!=null) {
response.append(inputLine);
}
JSONParser jsonParser = new JSONParser();
JSONObject jsonObject = (JSONObject)jsonParser.parse(response.toString());
JSONArray infoArray = (JSONArray) jsonObject.get("items");
for(int i=0; i<infoArray.size();i++) {
System.out.println("=item_"+i+"===================================");
JSONObject itemObject = (JSONObject)infoArray.get(i);
System.out.println("title:\t"+itemObject.get("title"));
//System.out.println("link:\t"+itemObject.get("link"));
System.out.println("subtitle:\t"+itemObject.get("subtitle"));
//System.out.println("pubDate:\t"+itemObject.get("pubDate"));
System.out.println("director:\t"+itemObject.get("director"));
System.out.println("actor:\t"+itemObject.get("actor"));
System.out.println("userRating:\t"+itemObject.get("userRating"));
}
br.close();
}
}