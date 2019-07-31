package com.firstproject.basicecommercewebsite;

import com.firstproject.basicecommercewebsite.model.Learn;
import com.firstproject.basicecommercewebsite.service.UserService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  private Gson g = new Gson();


//	@Test
//	public void getAllUsers() throws Exception {
//		mockMvc.perform(//getRequest);

//		User user = userService.getUser();
//		validateNewUser(user);
//		User user=new User("raghav1","raghav1@gmail.com","1234567891","Delhi");
//		List <User> al = Arrays.asList(user);
//		Mockito.when(userService.getAllUsersService()).thenReturn(al);

//		mockMvc.perform(MockMvcRequestBuilders.get("/users/getAllUsers")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].name", is("raghav1")))
//				.andExpect(jsonPath("$[0].email", is("raghav1@gmail.com")))
//				.andExpect(jsonPath("$[0].phoneno", is("1234567891")))
//				.andExpect(jsonPath("$[0].address", is("Delhi")));

//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/users/getAllUsers")
//				.accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//
//		Type listType = new TypeToken<List<User>>() {}.getType();
//
//		List<User> u = new Gson().fromJson(response.getContentAsString(), listType);
//		String s1,s2;
//		s1=g.toJson(user);
//		s2=g.toJson(u.get(0));
//
//		System.out.println(u);
//		JSONAssert.assertEquals(s1,s2,true);
//	}

//	@Test
//	public void validateUpdateUser() throws Exception {
//		// db.addUser();
//		// updateAPI
//		// validateUser();
//		//Setup
//		User user=new User("raghav10","raghav10@gmail.com","3434567891","Delhi");
//		System.out.println(userService.addUserService(user));
//		String s=g.toJson(user);
//		System.out.println(s);
//
//		//Perform
//		user.setEmail("raghav12@gmail.com");
//		System.out.println(g.toJson(user));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/updateUser")
//				.content(g.toJson(user))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//
//
//		//Validate
//		Assert.assertEquals("Updated Successfully!!",response.getContentAsString());
//		User ob = userService.getSingleUserService(user.getUser_id());
//		s=g.toJson(user);
//		String s1=g.toJson(ob);
//		JSONAssert.assertEquals(s,s1,true);
//	}
//
//	@Test
//	public void validateAddUser() throws Exception {
//
//		//Setup
//
//		User user=new User("raghav28","raghav28@gmail.com","2234567481","Delhi");
//		String s=g.toJson(user);
//
//		//Perform
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/addUser")
//				.content(g.toJson(user))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//
//
//		//Validate
//
//		User u1=userService.getSingleUserService(user.getEmail());
////		Assert.assertEquals("User Added!!",response.getContentAsString());
//		user.setUser_id(u1.getUser_id());
//		s=g.toJson(user);
//		String s1=g.toJson(u1);
//		System.out.println(s);
//		System.out.println(s1);
//		JSONAssert.assertEquals(s,s1,true);
//	}

//	@Test
//	public void validateGetUser() throws Exception{
//
//		//Setup
//
//		User user=new User("raghav90","raghav90@gmail.com","6234567891","Delhi");
//		userService.addUserService(user);
//		System.out.println(g.toJson(user)+" **** ");
//		System.out.println();
//		String s=g.toJson(user);
//		System.out.println(s);
//
//		//Perform
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/getSingleUser")
//		.accept(MediaType.APPLICATION_JSON).param("userId", String.valueOf(user.getUser_id()));
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//
//		User u = g.fromJson(response.getContentAsString(),User.class);
//
//		//Validate
//		String s1=g.toJson(u);
//		System.out.println(s1);
//		JSONAssert.assertEquals(s,s1,false);
//	}

  @Test
  public void validateLearn() throws Exception {

    //Setup
    Learn ob=new Learn();
    ob.setAge(10);
    ob.setName("raghav");
    Gson g=new Gson();

    //Perform

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/getLearn")
      .content(g.toJson(ob))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    System.out.println(response.getContentAsString());

  }


}