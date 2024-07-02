package com.test.security.controller;

import com.test.security.entity.RequirementInfo;
import com.test.security.model.AuthRequest;
import com.test.security.model.CommonResponse;
import com.test.security.entity.UserInfo;
import com.test.security.model.TokenResponse;
import com.test.security.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private RequirementInfoJsonDataService requirementInfoJsonDataService;

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/auth-service/user/new")
    public ResponseEntity<CommonResponse> addNewUser(@RequestBody UserInfo userInfo) {
        CommonResponse res = new CommonResponse();
        res.setMessage(authService.addUser(userInfo));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/auth-service/user/authenticate")
    public ResponseEntity<TokenResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        String token = authService.authenticateAndGetToken(authRequest);
        TokenResponse res = new TokenResponse();
        res.setToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //Update if existed, insert if not existed and this is allowed only to Admin user
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/vehicle-service/data/{id}")
    public ResponseEntity<CommonResponse> updateData(@PathVariable String id, @RequestBody String requirementInfoJson) throws Exception {
        requirementInfoJsonDataService.updateJson(id, requirementInfoJson);
        CommonResponse res = new CommonResponse();
        res.setMessage("RequirementInfo saved/updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/vehicle-service/data/{id}")
    public ResponseEntity<CommonResponse> getDataById(@PathVariable String id) {
        RequirementInfo requirementInfo = requirementInfoJsonDataService.getRequirementInfoById(id);
        CommonResponse res = new CommonResponse();
        if (requirementInfo == null) {
            res.setMessage("data not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        res.setResult(requirementInfo);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
