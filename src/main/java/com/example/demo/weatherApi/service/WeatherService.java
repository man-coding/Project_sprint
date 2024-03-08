package com.example.demo.weatherApi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.weatherApi.dto.WeatherDTO;
import com.example.demo.weatherApi.entity.Weather;
import com.example.demo.weatherApi.repository.WeatherRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

	@Autowired
	WeatherRepository repository;

	String serviceKey = "SUGpdq3ufUPY4EA%2Fy6EDsonRTsw4bY%2F9ZInDHY304YJ1hxQYOh1TfWoWy4kVz1L%2Fm9P7ImHvkKGyD83FxWpSjQ%3D%3D";
	String dataType = "JSON";
	String code = "11B20201";

	public String getWeather() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("20", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
				+ URLEncoder.encode(dataType, "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
		urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(code,
				"UTF-8")); /* 11A00101(백령도), 11B10101 (서울), 11B20201(인천) 등... 별첨 엑셀자료 참조(‘육상’ 구분 값 참고) */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb.toString();
	}

	// api 호출 후 entity에 저장하는 메서드
	public List<Weather> jsonToEntity() throws IOException {

		// 매퍼 클래스 생성
		ObjectMapper mapper = new ObjectMapper();

		// 분석할 수 없는 구문을 무시하는 옵션 설정
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 날씨 데이터 가져오기
		String weather = getWeather();

		Root root = null;

		// JSON 문자열을 클래스로 변환
		root = mapper.readValue(weather, Root.class);

		List<Weather> list = new ArrayList<>();
		// Root 객체의 필드 값을 사용하여 Weather 엔티티 생성
		for (Item item : root.getResponse().getBody().getItems().getItem()) {
			Weather entity = Weather.builder().forecastTime(item.numEf).temperature(item.getTa()).weather(item.getWf())
					.rainPossi(item.getRnSt()).build();
			list.add(entity);
			repository.save(entity);
		}

		return list; // entity 리스트
	}

	// 매일 오전 5시 api 호출하여 미리 데이터 저장한다
	@Scheduled(cron = "* * 5 * * * ", zone = "Asia/Seoul")
	public void scheduledWeatherApiCall() throws IOException {
		jsonToEntity();
	}

	 //미리 저장된 날씨 최신 데이터를 dto로 변환
	public List<WeatherDTO> entityToDto() throws IOException {

		// DB에서 모든 Weather 엔티티를 가져옵니다.
		List<Weather> weatherList = repository.findAll();

		// 가장 최신의 regDate를 찾습니다.
		LocalDateTime latestRegDate = weatherList.stream().map(Weather::getRegDate).max(LocalDateTime::compareTo)
				.orElseThrow(() -> new RuntimeException("날씨 데이터가 없습니다."));

		// 최신 regDate에 해당하고, forecastTime이 0이거나 1인 데이터만 필터링하여 DTO로 변환합니다.
		List<WeatherDTO> list = weatherList.stream()
				.filter(weather -> weather.getRegDate().equals(latestRegDate)
						&& (weather.getForecastTime() == 0 || weather.getForecastTime() == 1))
				.map(weather -> WeatherDTO.builder().forecastTime(weather.getForecastTime())
						.temperature(weather.getTemperature()).weather(weather.getWeather())
						.rainPossi(weather.getRainPossi()).regDate(weather.getRegDate()).build())
				.collect(Collectors.toList());

		return list;
	}
}
//		for (Weather entity : weatherList) {
//
//			WeatherDTO dto = WeatherDTO.builder().forecastTime(entity.getForecastTime())
//					.temperature(entity.getTemperature()).weather(entity.getWeather()).rainPossi(entity.getRainPossi())
//					.build();
//			int serviceTime = entity.getForecastTime();
//			if (serviceTime == 0 || serviceTime == 1) {
//				list.add(dto);
//			}
//
//		}
//		return list;

//	@Component
//	public class ScheduledTasks {
//		@Scheduled(cron = "*/3 * * * * *")
//		public void printEveryThreeSeconds() {
//			System.out.println("3초마다 출력 테스트");
//		}
//	}
