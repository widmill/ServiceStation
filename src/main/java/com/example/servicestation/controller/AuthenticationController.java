//package com.example.servicestation.controller;
//
//import com.example.servicestation.model.AuthenticationRequest;
//import com.example.servicestation.service.MyUserDetailService;
//import com.example.servicestation.util.JwtUtil;
//import com.example.servicestation.model.AuthenticationResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationController {
//    @Autowired
//    private MyUserDetailService userDetailService;
//    @Autowired
//    private JwtUtil jwtTokenUtil;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//        @PostMapping("/authenticate")
//    public ResponseEntity createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//            try {
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
//                                authenticationRequest.getPassword()));
//            } catch (BadCredentialsException e) {
//                throw new Exception("Incorrect username or password", e);
//            }
//            final UserDetails userDetails = userDetailService
//                    .loadUserByUsername(authenticationRequest.getUsername());
//            final String jwt = jwtTokenUtil.generateToken(userDetails);
//            return ResponseEntity.ok(new AuthenticationResponse(jwt));
//        }
//}
//
