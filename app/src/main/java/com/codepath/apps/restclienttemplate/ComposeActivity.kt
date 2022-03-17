package com.codepath.apps.restclienttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.codepath.apps.restclienttemplate.models.Tweet
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class ComposeActivity : AppCompatActivity() {

    lateinit var etCompose:EditText
    lateinit var btnTweet: Button

    lateinit var client: TwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        etCompose = findViewById(R.id.etTweetCompose)
        btnTweet = findViewById(R.id.btnTweet)

        client = TwitterApplication.getRestClient(this)

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
                //Toast.makeText(this, tweetContent, Toast.LENGTH_SHORT).show()

                client.publishTweet(tweetContent, object : JsonHttpResponseHandler(){

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                       // TODO send the tweet back to timelineActivity
                        Log.i(TAG, "Successfully published tweets")
                        // TODO send the tweet back to timelineActivity
                     val tweet = Tweet.fromJson(json.jsonObject)

                    val intent = Intent()
                        intent.putExtra("tweet", tweet)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Log.e(TAG, "Failed to publish tweet", throwable)
                    }



                })


            }
        }
    }
    companion object{
        val TAG = "composeActivity"
    }
}