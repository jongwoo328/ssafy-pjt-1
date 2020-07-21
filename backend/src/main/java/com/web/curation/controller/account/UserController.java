package com.web.curation.controller.account;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.User;
import com.web.curation.service.user.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    UserService userService;
	
	@GetMapping("{uid}")
    @ApiOperation(value = "회원정보 관리")
    public ResponseEntity<User> readUser(@PathVariable String uid) {
		BasicResponse result = new BasicResponse();
		result.status = true;
        result.data = "success";
        return new ResponseEntity<User>(userService.getUserByUid(uid), HttpStatus.OK);
    }
	
	@PostMapping("{uid}")
	@ApiOperation(value = "비밀번호 확인")
	public Object checkUser(@PathVariable String uid, @RequestParam(required = true) final String password) {
		User user = new User();
//		user.setUid(uid);
		user.setPassword(password);
		
		User userinfo = userService.findUserByUidAndPassword(user);

        ResponseEntity response = null;

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
	
	@PutMapping("{uid}")
	@ApiOperation(value = "회원정보 변경")
	@Transactional
    public Object modifyUser(@Valid @RequestBody SignupRequest request) {
    	
    	String email = request.getEmail();
    	String uid = request.getNickname();
    	final BasicResponse result = new BasicResponse();
    	
    	String password = request.getPassword();
//    	User user = new User(uid, password, email);
//    	System.out.println(user);
//    	userService.update(user);
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
}
