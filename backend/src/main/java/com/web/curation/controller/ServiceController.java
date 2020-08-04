package com.web.curation.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.curation.model.ConnectorService;
import com.web.curation.model.Pay;
import com.web.curation.model.Profile;
import com.web.curation.service.PayService;
import com.web.curation.service.ServiceService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/service")
public class ServiceController {
	private static final String  SAVE_PATH = "C:\\Users\\multicampus\\Desktop\\git\\s03p13d106\\backend\\src\\main\\resources\\static\\img\\service/";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ServiceService svc;
	
	@Autowired
	PayService pay;
	
	@ApiOperation(value = "서비스 정보 반환", response = ConnectorService.class)
	@GetMapping("{userno}")
	public Object selectServiceByUserno(@PathVariable int userno) {
		List<ConnectorService> servList = svc.selectServiceByUserno(userno);
		if(servList != null) {
			return new ResponseEntity<List<ConnectorService>>(servList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "각 서비스 정보 반환", response = ConnectorService.class)
	@GetMapping("/detail/{servno}")
	public Object detailService(@PathVariable int userno, @PathVariable int servno) {
		ConnectorService serv = svc.detailService(servno);
		
		Pay p = pay.searchPayed(userno, servno);
		
		
		
		if(serv != null) {
			serv.setImgurl("img/service/"+serv.getImgurl());
			if(p != null) {
				serv.setRevcheck(true);
				System.out.println("리뷰 작성 가능");
			} else {
				serv.setRevcheck(false);
				System.out.println("결제 내역 없음");
			}
			
			return new ResponseEntity<ConnectorService>(serv, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "서비스 검색", response = ConnectorService.class)
	@PostMapping("/{word}")
	public Object selectServiceByDongcode(HttpServletRequest request) {
		String dongcode = request.getParameter("dongcode");
		String keyword = request.getParameter("word");
		keyword = keyword.trim();
		StringTokenizer st = new StringTokenizer(keyword);
		List<String> word = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			word.add(st.nextToken());
		}
		
		
		int cateno = Integer.parseInt(request.getParameter("cateno"));
		List<ConnectorService> servList = svc.selectServiceByDongcode(cateno, dongcode, word);
		if(servList != null) {
			for(ConnectorService serv : servList) {
				serv.setImgurl("img/service/" + serv.getImgurl());
			}
			return new ResponseEntity<List<ConnectorService>>(servList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}

	
	@ApiOperation(value = "새로운 서비스를 등록한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeProfile(HttpServletRequest request) {
		
		ConnectorService serv = new ConnectorService();
		serv.setUserno(Integer.parseInt(request.getParameter("userno")));
		serv.setCateno(Integer.parseInt(request.getParameter("cateno")));
		serv.setServname(request.getParameter("servname"));
		serv.setPrice(Integer.parseInt(request.getParameter("price")));
		serv.setDescription(request.getParameter("description"));
		serv.setSaddr1(request.getParameter("saddr1"));
		serv.setSaddr2(request.getParameter("saddr2"));
		serv.setSaddr3(request.getParameter("saddr3"));
		serv.setSaddr4(request.getParameter("saddr4"));
		serv.setSaddr5(request.getParameter("saddr5"));
		serv.setSaddr6(request.getParameter("saddr6"));
		System.out.println(serv);
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
		MultipartFile imgFiles = mrequest.getFile("serviceImage");
		if(imgFiles != null) {
			System.out.println(mrequest.getFileNames());
			System.out.println(imgFiles.getOriginalFilename());			
			String originalFileName = imgFiles.getOriginalFilename();
			String time = new String();
			time += System.currentTimeMillis();
			String saveFile = SAVE_PATH + time + originalFileName;
			try {
				imgFiles.transferTo(new File(saveFile));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			serv.setImgurl(time + originalFileName);
			
		} else {
			serv.setImgurl("null.png");
		}
		
		if(svc.resistService(serv)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "servno에 해당하는 서비스 정보를 수정한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateProfile(HttpServletRequest request) throws JsonMappingException, JsonProcessingException{
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
		MultipartFile imgFiles = mrequest.getFile("serviceImage");
		ConnectorService pre = svc.detailService(Integer.parseInt(request.getParameter("servno")));
		
		ConnectorService service = new ConnectorService();
		
		service.setUserno(Integer.parseInt(request.getParameter("userno")));
		service.setCateno(Integer.parseInt(request.getParameter("cateno")));
		service.setServname(request.getParameter("servname"));
		service.setPrice(Integer.parseInt(request.getParameter("price")));
		service.setDescription(request.getParameter("description"));
		service.setSaddr1(request.getParameter("saddr1"));
		service.setSaddr2(request.getParameter("saddr2"));
		service.setSaddr3(request.getParameter("saddr3"));
		service.setSaddr4(request.getParameter("saddr4"));
		service.setSaddr5(request.getParameter("saddr5"));
		service.setSaddr6(request.getParameter("saddr6"));
		
		if(imgFiles != null) {
			if(pre.getImgurl().equals("null.png")) {
				
			} else {
				File file = new File(SAVE_PATH + pre.getImgurl());
				if(file.exists() == true) {
					file.delete();
				}				
			}
			String originalFileName = imgFiles.getOriginalFilename();
			String time = new String("");
			time += System.currentTimeMillis();
			String saveFile = SAVE_PATH + time + originalFileName;
			
			try {
				imgFiles.transferTo(new File(saveFile));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			service.setImgurl(time + originalFileName);
		} else {
			service.setImgurl(pre.getImgurl());
		}
		
		
		
		if(svc.updateService(service)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
				
	}
	
	@ApiOperation(value = "서비스 정보 삭제")
	@DeleteMapping("{servno}")
	public ResponseEntity<String> deleteService(@RequestBody ConnectorService service){
		System.out.println("삭제");
		
		if(service.getImgurl().equals("null.png")) {
			
		} else {
			File file = new File(SAVE_PATH + service.getImgurl());
			if(file.exists() == true) {
				file.delete();
			}			
		}
		
		
		if(svc.deleteService(service.getServno())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

}
