package com.muharram.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextImageButton: ImageButton
    private lateinit var questionTextview : TextView
    private lateinit var previuosImageButton: ImageButton

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0
    var score =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton=findViewById(R.id.t_button)
        falseButton=findViewById(R.id.f_button)
        nextImageButton=findViewById(R.id.next_button)
        questionTextview=findViewById(R.id.q_text)
        previuosImageButton=findViewById(R.id.prev_button)


        trueButton.setOnClickListener{
            checkAnswer(true)
            trueButton.isEnabled=false
            falseButton.isEnabled=false
        }

        falseButton.setOnClickListener{
            checkAnswer(false)
            disableButtons()
        }

        updteQustion()
        nextImageButton.setOnClickListener{
            enableButtons()
            currentIndex=currentIndex+1
            if(currentIndex > questionBank.lastIndex)
                youAreDone()
                else
            updteQustion()

        }
        questionTextview.setOnClickListener{
            currentIndex=(currentIndex+1)%questionBank.size
            updteQustion()
            enableButtons()
        }

        previuosImageButton.setOnClickListener{
            currentIndex=if (currentIndex-1 <= 0)
                questionBank.lastIndex
            else
                currentIndex-1
            updteQustion()
            disableButtons()
        }


    }

    fun youAreDone (){
        //val yourScorePercentage = (score/6)*100
        val t = Toast.makeText(this,"SCORE: $score true of 6",Toast.LENGTH_LONG)
        t.show()
    }

    fun enableButtons(){
        trueButton.isEnabled=true
        falseButton.isEnabled=true
    }

    fun disableButtons(){
        trueButton.isEnabled=false
        falseButton.isEnabled=false
    }
    fun updteQustion(){
        val questionText = questionBank[currentIndex].restTextId
        questionTextview.setText(questionText)
    }

    fun checkAnswer(userAnswer:Boolean){

        val correctAnswer=questionBank[currentIndex].answer
        val messageResId =if(correctAnswer==userAnswer){
            score=score+1
            R.string.correct_toast

        }
        else{
            R.string.incorrect_toast
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show()
    }
}
