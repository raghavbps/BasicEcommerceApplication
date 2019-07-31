package com.firstproject.basicecommercewebsite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class BasicecommercewebsiteApplication {

  class TestClass {
    private Boolean active;
    private String scriptBody;

    public TestClass(Boolean active, String scriptBody) {
      this.active = active;
      this.scriptBody = scriptBody;
    }

    public Boolean getActive() {
      return active;
    }

    public void setActive(Boolean active) {
      this.active = active;
    }

    public String getScriptBody() {
      return scriptBody;
    }

    public void setScriptBody(String scriptBody) {
      this.scriptBody = scriptBody;
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(BasicecommercewebsiteApplication.class);


  public static void main(String[] args) throws ParseException {
    SpringApplication.run(BasicecommercewebsiteApplication.class, args);
  }
}
//		String pattern = "yyyy-MM-dd HH:mm:ss";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		String date = simpleDateFormat.format(new Date().getTime());
//		Date date1=new SimpleDateFormat(pattern).parse(date);
//		System.out.println(new java.sql.Timestamp(new Date().getTime())+" "+new java.sql.Timestamp(date1.getTime()));
//        java.util.Date date = new Date();
//        Object param = new java.sql.Timestamp(date.getTime());
//        System.out.println(param);
//    RestTemplate restTemplate = new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("x-session-token", "1:idLXOeLxYelcAGLwk6toJkQluwYxJ8N0");
//    HttpEntity<String> entity = new HttpEntity<>("body", headers);
//    String url = "http://api.ops.flock.com/ss/sieve/getAllScripts?T-MAIL-ID={T-MAIL-ID}&T-CLUSTER-ID={T-CLUSTER-ID}";
//    String url1 = "http://api.ops.flock.com/ss/sieve/getAllScripts";
//    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url1)
//      .queryParam("T-MAIL-ID", 484423059213312L)
//      .queryParam("T-CLUSTER-ID", 1001);
//    url = builder.toUriString();
//    ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class,
//      "484423059213312", "1001");
//    System.out.println(response.getBody().toString());
////    User ob = restTemplate.getForObject("http://localhost:8080/users/getSingleUser?userId=1", User.class);
////    System.out.println(ob.getUser_id() + " " + ob.getAddress());
////
//    String s1="{\n" +
//      "    \"flockmail\": {\n" +
//      "        \"active\": true,\n" +
//      "        \"scriptBody\": \"#{\\\"ruleNames\\\":[],\\\"version\\\":1}\\nrequire [\\\"copy\\\"] ;\\nif allof (true ){redirect :copy \\\"gandotra.deepak@gmail.com\\\" ;}\\n\"\n" +
//      "    }\n" +
//      "}";
//    if(s1.equalsIgnoreCase(response.getBody().toString())) {
//      System.out.println("eq");
//    }
//    else {
//      System.out.println("not eq");
//    }
//    System.out.println(s1);
//    System.out.println();
//    System.out.println(response.getBody().toString());
//    Gson gson=new Gson();
//    System.out.println();
//    System.out.println();
//    System.out.println(gson.toJson(response.getBody()));
//    System.out.println();
//    System.out.println();
//    Type eMapType = new TypeToken<Map<String,TestClass>>() {}.getType();
//    Map<String, TestClass > resp = gson.fromJson(gson.toJson(response.getBody()), eMapType);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(resp);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    for(String s:resp.keySet()) {
//      System.out.println(s+" ** "+resp.get(s).getActive()+" "+resp.get(s).getScriptBody());
//      System.out.println();
//      System.out.println();
//      System.out.println();
//      Map<String,TestClass> innerresp=resp.get(s);
//      for(String s2:innerresp.keySet()) {
//        System.out.println(s2+" "+innerresp.get(s2));
//      }
//    }