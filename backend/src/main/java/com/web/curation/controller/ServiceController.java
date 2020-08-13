package com.web.curation.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.web.curation.model.Review;
import com.web.curation.model.Search;
import com.web.curation.service.PayService;
import com.web.curation.service.ReviewService;
import com.web.curation.service.ServiceService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
@RequestMapping("/service")
public class ServiceController {
	private static String  SAVE_PATH = "/var/www/html/img/service/";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ServiceService svc;
	
	@Autowired
	PayService pay;
	
	@Autowired
	ReviewService rev;
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "서비스 정보 반환", response = ConnectorService.class)
	@GetMapping("/{userno}")
	public Object selectServiceByUserno(@PathVariable int userno) {
		List<ConnectorService> servList = svc.selectServiceByUserno(userno);
		
		if(servList != null) {
			List<Review> revList = rev.totalReview();
			for(ConnectorService s : servList) {
				double sum = 0.0;
				int sno = s.getServno();
				int count = 0;
				for(Review r : revList) {
					if(sno == r.getServno()) {
						sum += r.getPoint();
						count ++;
					}
				}
				
				if(count == 0) {
					sum = 0.0;
				} else {
					sum = sum / count;
				}
				s.setAvgpoint(Math.round((sum) * 10) / 10.0);
				s.setImgurl("img/service/" + s.getImgurl());
				s.setPayCount(pay.payCount(s.getServno()));
				
			}
			return new ResponseEntity<List<ConnectorService>>(servList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "각 서비스 정보 반환", response = ConnectorService.class)
	@GetMapping("/detail/servno={servno}&userno={userno}")
	public Object detailService(@PathVariable int servno, @PathVariable int userno) {
		
		ConnectorService serv = svc.detailService(servno);
		
		
		Pay p = pay.searchPayed(new Pay(servno, userno));
		
		if(serv != null) {
			List<Review> revList = rev.retrieveReview(servno);
			
			double sum = 0.0;
			int count = 0;
			for(Review r : revList) {
				sum += r.getPoint();
				count++;
			}
			if(count == 0) {
				serv.setAvgpoint(0.0);
			} else {
				serv.setAvgpoint(Math.round((sum/count) * 10) / 10.0);
			}
//			if(revList != null) {
//				serv.setRev(revList);				
//			}
			serv.setImgurl("img/service/"+serv.getImgurl());
			if(p != null) {
				serv.setRevcheck(true);
			} else {
				serv.setRevcheck(false);
			}
			serv.setPayCount(pay.payCount(serv.getServno()));
			serv.setProname(userService.getUserByUserno(serv.getUserno()).getName());
			if(rev.checkReview(new Review(userno ,serv.getServno())) != null) {
				serv.setRevwrite(true);
			} else {
				serv.setRevwrite(false);
			}
			
			return new ResponseEntity<ConnectorService>(serv, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}

	
	@ApiOperation(value = "서비스 초기화면에 6개 띄운다", response = List.class)
	@GetMapping("/main/{num}")
	public Object mainService(@PathVariable int num) {
		List<ConnectorService> servList = svc.selectServiceByDongcode(0, null, null);
		List<ConnectorService> resultList = new ArrayList<ConnectorService>();
		
		if(servList != null) {
			List<Review> revList = rev.totalReview();
			for(ConnectorService serv : servList) {
				serv.setImgurl("img/service/" + serv.getImgurl());
				int count = 0;
				double sum = 0.0;
				int sno = serv.getServno();
				for(Review r : revList) {
					if(r.getServno() == serv.getServno()) {
						sum += r.getPoint();
						count++;
					}
				}
				if(count == 0) {
					serv.setAvgpoint(0.0);
				} else {
					
					serv.setAvgpoint(Math.round((sum/count) * 10) / 10.0);
				}
			}
			
			Collections.sort(servList, new Comparator<ConnectorService>() {

				@Override
				public int compare(ConnectorService o1, ConnectorService o2) {
					return Double.compare(o2.getAvgpoint(), o1.getAvgpoint());
				}
			});
			
			
			int c = 0;
			for(ConnectorService s : servList) {
				if(c >= (num-1)*6 && c < num * 6) {
					s.setPayCount(pay.payCount(s.getServno()));
					resultList.add(s);					
				}
				c++;
				
				if(c == num * 6) {
					break;
				}
			}
			
		}
		return new ResponseEntity<List<ConnectorService>>(resultList, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "서비스 검색", response = List.class)
	@PostMapping("/search")
	public Object selectServiceByDongcode(@RequestBody Search s) {
		String dongcode = s.getSaddr5();
		String keyword = s.getKeyword();
		int num = s.getNum();
		
		
		List<ConnectorService> resultList = new ArrayList<ConnectorService>();
		if(keyword.equals("")) {
			keyword = null;
		}
		List<String> word = new ArrayList<String>();
		
		if(keyword != null) {
			keyword = keyword.trim();
			StringTokenizer st = new StringTokenizer(keyword);
			while(st.hasMoreTokens()) {
				word.add(st.nextToken());
			}	
		}
		
		
		int cateno = s.getCateno();
		List<ConnectorService> servList; 
		if(keyword != null) {
			servList = svc.selectServiceByDongcode(cateno, dongcode, word);
		} else {
			servList = svc.selectServiceByDongcode(cateno, dongcode, null);
		}
		if(servList != null) {
			List<Review> revList = rev.totalReview();
			
			int c = 0;
			
			for(ConnectorService serv : servList) {
				if(c >= (num-1)*6 && c < num * 6) {
					serv.setImgurl("img/service/" + serv.getImgurl());
					int count = 0;
					double sum = 0.0;
					int sno = serv.getServno();
					for(Review r : revList) {
						if(r.getServno() == serv.getServno()) {
							sum += r.getPoint();
							count++;
						}
					}
					if(count == 0) {
						serv.setAvgpoint(0.0);
					} else {
						
						serv.setAvgpoint(Math.round((sum/count) * 10) / 10.0);
					}
					serv.setPayCount(pay.payCount(serv.getServno()));
					resultList.add(serv);
				}
				c++;
				
				if(c == num * 6) {
					
				}
			}



			
			return new ResponseEntity<List<ConnectorService>>(resultList, HttpStatus.OK);
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
		
		serv.setPrice(request.getParameter("price"));
		
		serv.setDescription(request.getParameter("description"));
		serv.setSaddr1(request.getParameter("saddr1"));
		serv.setSaddr2(request.getParameter("saddr2"));
		serv.setSaddr3(request.getParameter("saddr3"));
		serv.setSaddr4(request.getParameter("saddr4"));
		serv.setSaddr5(request.getParameter("saddr5"));
		serv.setSaddr6(request.getParameter("saddr6"));
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
		MultipartFile imgFiles = mrequest.getFile("serviceImage");
		if(imgFiles != null) {
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
		ConnectorService pre =
        svc.detailService(Integer.parseInt(request.getParameter("servno")));
		
		ConnectorService service = new ConnectorService();
		
		service.setUserno(Integer.parseInt(request.getParameter("userno")));
		service.setCateno(Integer.parseInt(request.getParameter("cateno")));
		service.setServname(request.getParameter("servname"));
		service.setServno(Integer.parseInt(request.getParameter("servno")));
		
		
		service.setPrice(request.getParameter("price"));
		
		
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
	
	@ApiOperation(value = "서비스 정보 삭제", response = String.class)
	@DeleteMapping("/{servno}")
	public ResponseEntity<String> deleteService(@PathVariable int servno, HttpServletRequest request){
		
		
		ConnectorService service = svc.detailService(servno);
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
