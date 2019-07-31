package com.firstproject.basicecommercewebsite.resolver;

import com.firstproject.basicecommercewebsite.annotation.learn;
import com.firstproject.basicecommercewebsite.model.Learn;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LearnResolver implements HandlerMethodArgumentResolver {

  private Gson GSON = new Gson();

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterType().equals(Learn.class);
  }

  JsonElement getRequestBody(NativeWebRequest nativeWebRequest) throws IOException {
    HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
    String jsonString = IOUtils.toString(servletRequest.getInputStream());
    return GSON.fromJson(jsonString,JsonElement.class);
  }

  String getParameterValue(NativeWebRequest nativeWebRequest, JsonElement jsonElement, String parameterName) {
    String parameterValue = nativeWebRequest.getParameter(parameterName);
    if (parameterValue == null) {
      parameterValue = jsonElement.getAsJsonObject().get(parameterName).getAsString();
    }
    return parameterValue;
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
    String nameParameterName = methodParameter.getParameterAnnotation(learn.class).name();
    String ageParameterName = methodParameter.getParameterAnnotation(learn.class).age();
//    System.out.println(nameParameterName+" "+ageParameterName+" **** ");
    JsonElement requestBody = getRequestBody(nativeWebRequest);
    String name = getParameterValue(nativeWebRequest, requestBody, nameParameterName);
    int age = Integer.parseInt(getParameterValue(nativeWebRequest, requestBody, ageParameterName));
//    System.out.println(name+" "+age+" **** ");
//    System.out.println(requestBody);
    Learn ob = new Learn();
    ob.setAge(age);
    ob.setName(name);
    String ageGroup = "";
    if (age <= 10) {
      ageGroup = "Kid";
    } else if (age >= 25) {
      ageGroup = "Adult";
    } else {
      ageGroup = "Teenager";
    }
    ob.setAge(age);
    ob.setAgeGroup(ageGroup);
    return ob;
  }
}