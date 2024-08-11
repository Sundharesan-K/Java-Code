package com.secure.spring_secure_6.controller;

import com.secure.spring_secure_6.model.Family;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {

  //These all are test the security config on postman
  List<Family> families = List.of(
      new Family(1,"Krishnan"),
      new Family(2,"Suguna")
  );

  @GetMapping("/csrf-token")
  public CsrfToken getCsrfToken(HttpServletRequest request){
    return (CsrfToken) request.getAttribute("_csrf");
  }

  @GetMapping("/family")
  public List<Family> getFamilies(){
    return families;
  }

  @PostMapping("/add")
  public Family addF(@RequestBody Family family){
    if (families == null) {
      families.add(family);
    }
    return family;
  }
}
