package com.techelevator.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.techelevator.models.UserDAO;

public class AuthenticationControllerTest {

	private AuthenticationController controller;
	private UserDAO userDAO;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private HttpSession session;
	private ModelMap model;
	
	@Before
	public void setup() {
		userDAO = mock(UserDAO.class);
		controller = new AuthenticationController(userDAO);
		response = mock(HttpServletResponse.class);
		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		model = new ModelMap();
		when(request.getCookies()).thenReturn(new Cookie[]{});
	}
	
	@Test
	public void displays_login_view() {
		String viewName = controller.showLoginPage(model);
		assertThat(viewName, equalTo("login"));
	}
}
