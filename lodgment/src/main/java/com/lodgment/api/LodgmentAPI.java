package com.lodgment.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class LodgmentAPI {
	//서비스키를 전역변수로 선언하여 사용할 것이다.
	final String serviceKey="i78uTwdXPPgqdYiKZ6HLsD9TvQcb0SPTGZjcUly%2BYIZHlfKwrKa7SDDbBitBH5TSmWJqM45XhnPsdbFQO74U3w%3D%3D";
	
	//tag값의 정보를 가져오는 메소드
	public static String getTagValue(String tag, Element eElement) {
		try {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);
			if(nValue == null) {
				System.out.println("null");
				return null;
			}
			System.out.println(nValue.getNodeValue());
			return nValue.getNodeValue();
		} catch(NullPointerException e) {
			System.out.println("catch");
			return null;
		}
	}
	//장소정보
	public ArrayList<NodeList> getSpotInfo(ArrayList<String> contentIdList) throws Exception {
		ArrayList<NodeList> infoList = new ArrayList<NodeList>();
		for (int i=0;i<contentIdList.size();i++) {
			//파싱할 url 지정
			String url = "http://apis.data.go.kr/B551011/KorService/detailCommon?"
					+ "serviceKey="+serviceKey
					+ "&numOfRows=1"
					+ "&pageNo=1"
					+ "&MobileOS=ETC"
					+ "&areacodeYN=Y"
					+ "&catcodeYN=Y"
					+ "&addrinfoYN=Y"
					+ "&MobileApp=AppTest"
					+ "&contentId="+contentIdList.get(i)
					+ "&contentTypeId="
					+ "&defaultYN=Y"
					+ "&firstImageYN=Y"
					+ "&mapinfoYN=Y"
					+ "&overviewYN=Y&";
			/**페이지에 접근해줄 Document 객체 생성
			 * 생성한 document 객체를 통해 파싱할 url 요소를 읽어 들임.
				doc.getDocument().getNodeName()메서드를 출력하면 위 XML의 최상위 tag 값을 가져온다.
				response가 최상위 tag값
			 */

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// root tag 
			doc.getDocumentElement().normalize();
			System.out.println(url);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			// item tag
			NodeList nList = doc.getElementsByTagName("item");
			infoList.add(nList);
			Thread.sleep(100);
		}
		return infoList;
	}
	//위치기반관광정보조회
	public ArrayList<String> getLocationBasedList(String pageNo, String sigunguCode,String contentTypeId,String numOfRow) throws Exception {
		String url = "http://apis.data.go.kr/B551011/KorService/locationBasedList?"
				+ "serviceKey="+serviceKey
				+ "&pageNo="+pageNo
				+ "&numOfRows="+numOfRow
				+ "&MobileApp=AppTest"
				+ "&MobileOS=ETC"
				+ "&arrange=S"
				+ "&radius=20000"
				+ "&contentTypeId="+contentTypeId
				+ "&mapX="
				+ "&mapY="
				+ "&listYN=Y"
				+ "&modifiedtime=&";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		/** item tag
		 *  파싱할 정보가 있는 tag에 접근하는 부분
		 *  파싱할 데이터는 item이라는 tag안에 있기때문에 이쪽으로 접근해야함
		 *  그러면 nList에 item이라는 태그가 하나씩 담기게 되고 getLength 를 통해 리스트의 수를 확인 
		 */
		NodeList nList = doc.getElementsByTagName("item");
		//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		ArrayList<String> contentIdList = new ArrayList<String>();

		/**
		 * NodeList에 담긴 데이터를 출력하는부분
		 * 위에 담긴 item을 반복문을 통해 출력할 것이다
		 * getTextContent()는 전체 정보를 의미
		 * getTagValue("tag",element) : 입력한 tag 정보를 의미
		 * getTagValue("tag")<-실질적인 tag의 변수값을 넣어줘야 함
		 * getTagValue는 따로 메소드를 만들어 준것
		 */
		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				contentIdList.add(getTagValue("contentid", element));
				
				//System.out.println("콘텐츠id:"+getTagValue("contentid", element));
			}
		}
		return contentIdList;
	}
	
	
	// 토탈 카운트
	public int getTotalCount(String contentTypeId, String sigunguCode) throws Exception {
		String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "serviceKey="+serviceKey
				+ "&MobileApp=AppTest"
				+ "&MobileOS=ETC"
				+ "&contentTypeId="+contentTypeId
				+ "&areaCode=39"
				+ "&sigunguCode="+sigunguCode
				+ "&listYN=N";

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		System.out.println("Root element3 :" + doc.getDocumentElement().getNodeName());

		/** item tag
		 *  파싱할 정보가 있는 tag에 접근하는 부분
		 *  파싱할 데이터는 item이라는 tag안에 있기때문에 이쪽으로 접근해야함
		 *  그러면 nList에 item이라는 태그가 하나씩 담기게 되고 getLength 를 통해 리스트의 수를 확인 
		 */
		NodeList nList = doc.getElementsByTagName("item");

		//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		ArrayList<Integer> contentIdList = new ArrayList<Integer>();

		/**
		 * NodeList에 담긴 데이터를 출력하는부분
		 * 위에 담긴 item을 반복문을 통해 출력할 것이다
		 * getTextContent()는 전체 정보를 의미
		 * getTagValue("tag",element) : 입력한 tag 정보를 의미
		 * getTagValue("tag")<-실질적인 tag의 변수값을 넣어줘야 함
		 * getTagValue는 따로 메소드를 만들어 준것
		 */
		for(int i=0;i<nList.getLength();i++) {
			Node node = nList.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element element = (Element) node;
				contentIdList.add(Integer.parseInt(getTagValue("totalCnt", element)));
			}
		}

		return contentIdList.get(0);
	}

}
