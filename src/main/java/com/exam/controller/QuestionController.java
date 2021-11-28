package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }



    //update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // get all question

    @GetMapping("/")
    public ResponseEntity<?> getQuestion(){
        return ResponseEntity.ok(this.questionService.getQuestion());
    }

    // get question by id
    @GetMapping("/{quesId}")
    public  Question getQuestion(@PathVariable  Long quesId){
        return this.questionService.getQuestion(quesId);
    }




    // get all question of any quid j
    @GetMapping("/quiz/{quesId}")
    public  ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quesId") Long quesId ){

        Quiz quiz = this.quizService.getQuiz(quesId);
        Set<Question> questions= quiz.getQuestions();

        List<Question> list = new ArrayList(questions);
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }


    // get all question of any quid j
    @GetMapping("/quiz/all/{quesId}")
    public  ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("quesId") Long quesId ){

        Quiz quiz= new Quiz();
        quiz.setqId(quesId);
        Set<Question> questionsOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
    }





    //delete question
    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){

        this.questionService.deleteQuestion(quesId);
    }

    //eval quiz
    @PostMapping("/eval-quiz")
    public  ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
       double marksGot=0;
        int correctAnswers=0;
        int attempted=0;
        for (Question q:questions) {
            //single questions
            Question question = this.questionService.get(q.getQuesId());
            if (question.getAnswer().equals(q.getGivenAnswer())) {
                // correct
                correctAnswers++;
                double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot+=marksSingle;
            }
            if (q.getGivenAnswer() != null ) {
                attempted++;
            }
        }

        Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
        return  ResponseEntity.ok(map);
    }

}










