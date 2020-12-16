import org.json.simple.JSONObject;
import org.json.simple.JSONArray; //JSON배열 사용
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class JSONAdd{
	String json; //파싱할 최종 데이터 담아주기 위한 문자열
	
	//생성자 통해 JSON생성
	public JSONAdd(){
		//JSON생성시 언제나 내부부터 채워나가기
		//JSONObject 3개에 각각 데이터 저장
		JSONObject data1 = new JSONObject();
		data1.put("professor", "김교수");
		data1.put("student", "이학생");

		JSONObject data2 = new JSONObject();
		data2.put("professor", "박교수");
		data2.put("student", "최학생");
		
		JSONObject data3 = new JSONObject();
		data3.put("professor", "한교수");
		data3.put("student", "황학생");
		
		//위에서 만든 3개의 오브젝트 데이터를 JSONArray에 순서대로 저장
		JSONArray arr = new JSONArray();
		arr.add(data1);//0번인덱스
		arr.add(data2);//1번인덱스
		arr.add(data3);//2번인덱스
		
		//최종적으로 univ오브젝트에 JSON배열 저장
		JSONObject univ = new JSONObject();
		univ.put("univ", arr);
		
		//파싱할 데이터 저장
		json = univ.toJSONString();
		
		//테스트용 출력
		//System.out.println(json);
	}
}

public class Main{
	public static void main(String[] args) throws ParseException {
		JSONAdd jsonAdd = new JSONAdd();
		//전체 파싱 정보가 저장된 jsonAdd의 json을 자체 String을 생성하여 옮겨 담음
		String json = jsonAdd.json; 
		
		JSONParser parser = new JSONParser();
		
		JSONObject univ = (JSONObject)parser.parse(json);
		
		//테스트출력
		//System.out.println(univ.toJSONString());
		
		JSONArray arr = (JSONArray)univ.get("univ");
		
		//테스트출력
		//System.out.println(arr.toJSONString());
		
		for(int i=0;i<arr.size();i++){
			JSONObject tmp = (JSONObject)arr.get(i);//인덱스 번호로 접근해서 가져온다.
			
			String professor = (String)tmp.get("professor");
			String student = (String)tmp.get("student");
			
			System.out.println("----- "+i+"번째 인덱스 값 -----");
			System.out.println("교수 : "+professor);
			System.out.println("학생 : "+student);
		}
	}
}