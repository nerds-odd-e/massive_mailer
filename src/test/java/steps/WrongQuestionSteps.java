package steps;

import com.odde.massivemailer.model.User;
import com.odde.massivemailer.model.onlinetest.Question;
import com.odde.massivemailer.model.tokkun.QuestionResponseForTokkun;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

import java.time.LocalDateTime;

public class WrongQuestionSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();

    @Given("^ユーザが登録されている$")
    public void ユーザが登録されている() throws Throwable {
        User.createIt("id",1, "email","mizukami@gmail.com", "hashed_password", "0", "token", "0", "name", "mizukami", "is_admin", 0);
    }

    @Given("^スクラムとは何ですか？の問題が(\\d+)時間前に不正解になっている$")
    public void スクラムとは何ですか_の問題が_時間前に不正解になっている(int elapsed_time){
        LocalDateTime date = LocalDateTime.now().minusHours(elapsed_time);
        QuestionResponseForTokkun.createIt("answered_at", date.toString(),"counter",0 , "question_id", Question.findAll().get(0).getId(), "user_id", User.findAll().get(0).getId());
    }

    @Then("^問題が表示される$")
    public void 問題が表示される() {
        site.visit("tokkun/question");
        Assert.assertEquals(driver.findElementById("title").getText(), "Question");
        Assert.assertEquals(driver.findElementById("question").getText(), Question.findAll().get(0).getString("description"));
    }

    @Then("^問題が非表示される$")
    public void 問題が非表示される() {
        site.visit("tokkun/question");
        Assert.assertEquals("Tokkun List", driver.getCurrentTitle());
    }
}