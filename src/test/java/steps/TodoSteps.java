package steps;

import com.odde.massivemailer.model.Todo;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.site.MassiveMailerSite;

import static org.junit.Assert.assertEquals;


public class TodoSteps {

    private final MassiveMailerSite site = new MassiveMailerSite();

    @Given("^Todo一覧ページに遷移する$")
    public void todo一覧ページに遷移する() {
        site.visit("/index.html");
    }

    @Then("^Todo一覧ページが表示される$")
    public void todo一覧ページが表示される() {
        site.getDriver().pageShouldContain("Todos List");
    }


    @Given("^Todoが(\\d+)つある$")
    public void todoが_つある(int numberOfTodo) {
        Todo.createIt("title", "craft beer", "status", "new");
        Todo.createIt("title", "sake", "status", "new");
    }

    @Then("^Todoが複数表示されている$")
    public void todoが複数表示されている() throws InterruptedException {
        Thread.sleep(2000);
        site.getDriver().pageShouldContain("sake");
        site.getDriver().pageShouldContain("beer");
    }

    @When("^\"([^\"]*)\"を\"([^\"]*)\"に入力$")
    public void をに入力(String value, String name) {
        site.getDriver().setTextField(name, value);
    }


    @And("^\"([^\"]*)\"が表示されている$")
    public void が表示されている(String text) throws InterruptedException {
        Thread.sleep(2000);
        assertEquals(1, Todo.findAll().size());
        assertEquals("お風呂掃除", Todo.findAll().get(0).get("title"));
        site.getDriver().pageShouldContain(text);
    }

    @And("^\"([^\"]*)\"をクリック$")
    public void をクリック(String id) {
        site.getDriver().clickButton(id);
    }
}
