package com.exam.controller;


import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;


    // add quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){

        return  ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public  ResponseEntity<Quiz> updateQuiz(@RequestBody  Quiz quiz){
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // get Quiz by Id
    @GetMapping("/{quizId}")
    public  Quiz getQuiz(@PathVariable("quizId") Long quizId){
        return this.quizService.getQuiz(quizId);
    }

    // get all Quiz
    @GetMapping("/")
    public  ResponseEntity<?> getAllQuiz(){
        return  ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //delete quiz

    @DeleteMapping("/{quizId}")
    public  void deleteQuizById(@PathVariable("quizId") Long quiId){

        this.quizService.deleteQuiz(quiId);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizOfCategory(@PathVariable("cid") Long cid ){
       Category category=new Category();
       category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    // get active quizzes
    @GetMapping("/active")
    public  List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }

    // get active quizzes
    @GetMapping("/category/active/{cid}")
    public  List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }

}
