package com.odde.snowball.model.onlinetest;

import com.odde.snowball.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.odde.snowball.model.base.Repository.repo;

public class OnlinePractice extends OnlineTest {
    private OnlinePractice(List<Question> questions) {
        super(questions);
    }

    public static OnlineTest createOnlinePractice(User user) {
        List<Question> allQuestions = repo(Question.class).findAll();
        List<Question> dueQuestions = allQuestions.stream().filter(q-> q.isDueForUser(user)).collect(Collectors.toList());
        List<Question> questions = new QuestionCollection(dueQuestions).generateQuestionList(repo(Category.class).findAll(), dueQuestions.size());
        return new OnlinePractice(questions);
    }

    public static List<Question> findSpaceBasedRepetations(int count, User user, Date currentDate) {
        List<Question> questions = repo(Question.class).findAll();
        return Collections.emptyList();
    }

    public String endPageName() {
        return "/practice/completed_practice.jsp";
    }
}
