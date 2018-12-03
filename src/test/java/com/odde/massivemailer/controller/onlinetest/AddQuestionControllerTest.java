package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import com.odde.massivemailer.controller.onlinetest.AddQuestionController;
import com.odde.massivemailer.model.onlinetest.AnswerOption;
import com.odde.massivemailer.model.onlinetest.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(TestWithDB.class)
public class AddQuestionControllerTest {
    private AddQuestionController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUpMockService() {
        controller = new AddQuestionController();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    private void setupValidRequest() {
        request.setParameter("description", "aaaaaaaaaaaaaaaa");
        request.setParameter("option1", "option1");
        request.setParameter("option2", "option2");
        request.setParameter("option3", "option3");
        request.setParameter("option4", "option4");
        request.setParameter("option5", "option5");
        request.setParameter("option6", "option6");
        request.setParameter("check", String.valueOf(1));
    }

    @Test
    public void doPostAddQuestion() throws Exception {
        setupValidRequest();
        controller.doPost(request, response);
        Question question = Question.findFirst("");

        String description = request.getParameter("description");
        assertEquals(description, question.getDescription());

        for (int i = 0; i < 6; i++) {
            String option = request.getParameter("option" + (i + 1));
            boolean hasOption = question.getOptions().stream().anyMatch(opt -> opt.getDescription().equals(option));
            assertTrue(hasOption);
        }

        String rightOptionDescription = request.getParameter("option1");

        Optional<AnswerOption> rightAnswer = question.getOptions().stream().filter(AnswerOption::isCorrect).findFirst();
        assertEquals(rightAnswer.get().getDescription(), rightOptionDescription);
    }

    @Test
    public void redirectToQuestionListPage() throws Exception {
        setupValidRequest();
        controller.doPost(request, response);
        assertEquals("/onlinetest/question_list.jsp", response.getRedirectedUrl());
    }

    @Test
    public void descriptionIsEmpty() throws Exception {
        setupValidRequest();
        request.setParameter("description", "");
        controller.doPost(request, response);
        String errorMessage = String.valueOf(request.getAttribute("errorMessage"));
        assertEquals(errorMessage, "Invalid inputs found!");
    }

    @Test
    public void option1stIsEmpty() throws Exception {
        setupValidRequest();
        request.setParameter("option1", "");
        controller.doPost(request, response);

        String errorMessage = String.valueOf(request.getAttribute("errorMessage"));
        assertEquals(errorMessage, "Invalid inputs found!");
    }

    @Test
    public void option2ndIsEmpty() throws Exception {
        setupValidRequest();
        request.setParameter("option2", "");
        controller.doPost(request, response);

        String errorMessage = String.valueOf(request.getAttribute("errorMessage"));
        assertEquals(errorMessage, "Invalid inputs found!");
    }

    @Test
    public void correctAnswerIsNotSelected() throws Exception {
        setupValidRequest();
        request.setParameter("check", (String)null);
        controller.doPost(request, response);

        String errorMessage = String.valueOf(request.getAttribute("errorMessage"));
        assertEquals(errorMessage, "Right answer is not selected!");
    }


    @Test
    public void doSelectedAnswerIsEmpty() throws Exception{
        setupValidRequest();
        request.setParameter("option3", "");
        request.setParameter("check", String.valueOf(3));
        controller.doPost(request, response);

        String errorMessage = String.valueOf(request.getAttribute("errorMessage"));
        assertEquals(errorMessage, "Invalid inputs found!");
    }
}
