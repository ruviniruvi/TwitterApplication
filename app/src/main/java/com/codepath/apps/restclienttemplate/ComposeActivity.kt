package com.codepath.apps.restclienttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ComposeActivity : AppCompatActivity() {

    lateinit var etCompose:EditText
    lateinit var btnTweet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        etCompose = findViewById(R.id.etTweetCompose)
        btnTweet = findViewById(R.id.btnTweet)

        //handle tweets
        btnTweet.setOnClickListener{
            //grab the content
   val tweetContent = etCompose.text.toString()
            //1. make sure tweet is not empty
            if(tweetContent.isEmpty()){
                Toast.makeText(this, "Empty tweets not allowed", Toast.LENGTH_SHORT).show()

            }
            //2.make sure the tweet is under character count
            if(tweetContent.length > 140){
                Toast.makeText(this, "Tweet is too long! Limit is 140 characters", Toast.LENGTH_SHORT).show()
            }else {
                //make API call to twitter to publish tweet
                Toast.makeText(this, tweetContent, Toast.LENGTH_SHORT).show()


            }
        }
    }
}