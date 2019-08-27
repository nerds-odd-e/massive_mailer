Feature:
  User can practice questions using Spaced Repetition.


  Background:
    And There are questions with dummy options:
      | description | correctOption | category |
      | Q1          | correctOption | Retro    |
    Given user "mary" has logged in successfully

  Scenario: User can practice question
    Given User is taking a practiceTest
    When User answered 1 questions correctly
    Then User should see "You have finished your practice for today"

  Scenario: No questions should be shown if questions have been answered today
    Given User is taking a practiceTest
    When User answered 1 question correctly
    Then User should see "You have finished your practice for today"
    When User is taking a practiceTest
    Then User should see "You have finished your practice for today"

  Scenario: If user answers wrongly, user should be shown advice page and be redirected to see completed practice page
    Given User is taking a practiceTest
    When User answered 1 question wrongly
    Then User should see Advice page
    When User clicks on Next on Advice page
    Then User should see "You have finished your practice for today"
    When User is taking a practiceTest
    Then User should see "You have finished your practice for today"

  @developing
  Scenario: ユーザがテストを開始できること
    Given ユーザがログインした状態である
    And 問題が15個存在する
    When プラクティスを開始
    Then 10個の問題が表示される

  @developing
  Scenario: 解答日時によってソートされること
    Given ユーザがログインした状態である
    And 問題1と問題2が存在する
    And 問題1の解答日時が2019年8月26日17時00分00秒である
    And 問題2の解答日時が2019年8月26日17時02分00秒である
    When プラクティスを開始
    Then 問題1が出題される
    When 問題1に正解する
    Then 問題2が出題される

  # space-based repetation
  @developing
  Scenario: 回答した問題は回答日から起算して仕様に従った間隔で再度出題されること
    Given ユーザがログインした状態である
    And 問題1と問題2が存在する
    And 問題1の解答日時が2019年8月26日17時00分00秒である
    And 問題2は未解答である
    And 現在は2019年8月27日である
    When "Start Practice"をする
    Then 問題1が出題される

  @developing
  Scenario: 再度出題された問題に回答しなかった場合翌日も出題される
    Given ユーザがログインした状態である
    And 問題1と問題2が存在する
    And 問題1の解答日時が2019年8月26日17時00分00秒である
    And 問題2には一度も解答していない
    And 2019年8月27日に問題1を回答していない
    And 今日は2019年8月28日である
    When テストを開始
    Then 問題1が出題される

  @developing
  Scenario: 延期された問題に解答した場合直近の解答日から起算して再出題される
    Given ユーザがログインした状態である
    And 問題1と問題2が存在する
    And 問題1の解答日時が2019年8月26日17時00分00秒である
    And 問題2には一度も解答していない
    And 2019年8月27日に問題1を回答していない
    And 問題1の解答日時が2019年8月28日17時00分00秒である
    When 今日は2019年8月29日である
    And テストを開始
    Then 問題2が出題される
    When 今日は2019年8月30日である
    And テストを開始
    Then 問題2が出題される
    When 今日は2019年8月31日である
    And テストを開始
    Then 問題1が出題される

  @developing
  Scenario: 解答したことのある問題と未解答の問題がそんざいする

  @developing
  Scenario: 登録済みの問題の数が１０未満

  @developing
  Scenario: 問題１が出題されて、解答しないで、またプラクティスを開始すると問題１が表示される