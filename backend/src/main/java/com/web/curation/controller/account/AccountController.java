package com.web.curation.controller.account;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.User;
import com.web.curation.service.user.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {

    @Autowired
    UserService userService;

    
    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestBody SignupRequest test) {

    System.out.println(test.getEmail());
    System.out.println(test.getPassword());
    User user = new User();
    user.setEmail(test.getEmail());
    user.setPassword(test.getPassword());
    ResponseEntity response = null;
    User userinfo = userService.login(user);
    if (userinfo != null) {
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = userinfo;
        response = new ResponseEntity<>(result, HttpStatus.OK);
    } else {
        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    return response;
}
    
    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    @Transactional
    public Object signup(@Valid @RequestBody SignupRequest request) {
    	
    	String email = request.getEmail();
    	System.out.println(email);
    	User user = userService.getUserByEmail(email);
    	final BasicResponse result = new BasicResponse();
    	if (user != null) {
    		result.status = false;
    		result.data = "email";
    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    	}
    	String uid = request.getNickname();
    	user = userService.getUserByUid(uid);
    	if (user != null) {
    		result.status = false;
    		result.data = "nickname";
    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    	}
    	
    	String password = request.getPassword();
    	System.out.println(uid);
    	System.out.println(password);
    	user = new User(uid, password, email);
    	System.out.println(user);
    	userService.insert(user);
    	result.status = true;
    	result.data = "success";
    	
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
//    @PostMapping("/account/login")
//    @ApiOperation(value = "로그인")
//    public Object login(@RequestParam(required = true) final String email,
//            @RequestParam(required = true) final String password) {
//
//    	System.out.println(email);
//    	System.out.println(password);
//        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
//
//        ResponseEntity response = null;
//
//        if (userOpt.isPresent()) {
//            final BasicResponse result = new BasicResponse();
//            result.status = true;
//            result.data = "success";
//            response = new ResponseEntity<>(result, HttpStatus.OK);
//        } else {
//            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return response;
//    }
//
//    @PostMapping("/account/signup")
//    @ApiOperation(value = "가입하기")
//    @Transactional
//    public Object signup(@Valid @RequestBody SignupRequest request) {
//    	
//    	String email = request.getEmail();
//    	System.out.println(email);
//    	User user = userDao.getUserByEmail(email);
//    	final BasicResponse result = new BasicResponse();
//    	if (user != null) {
//    		result.status = false;
//    		result.data = "email";
//    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//    	}
//    	String uid = request.getNickname();
//    	user = userDao.getUserByUid(uid);
//    	if (user != null) {
//    		result.status = false;
//    		result.data = "nickname";
//    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//    	}
//    	
//    	String password = request.getPassword();
//    	System.out.println(uid);
//    	System.out.println(password);
//    	user = new User(uid, password, email);
//    	System.out.println(user);
//    	userDao.save(user);
//        result.status = true;
//        result.data = "success";
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}