package com.finance.tracket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;


public class TestServletTest {

    private TestServlet testServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        testServlet = new TestServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet() throws IOException {
        testServlet.doGet(request, response);

        verify(session, times(1)).setAttribute(eq("users"), anyList());
        verify(response, times(1)).sendRedirect("show_users.jsp");
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        when(request.getParameter("name")).thenReturn("Test Name");
        when(request.getParameter("position")).thenReturn("Test Position");

        testServlet.doPost(request, response);

        verify(request, times(1)).getParameter("name");
        verify(request, times(1)).getParameter("position");
    }
}