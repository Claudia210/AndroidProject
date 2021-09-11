package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_intent.*
import java.io.ByteArrayOutputStream

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent
        val aTitle = intent.getStringExtra("iTitle")
        val aDescription = intent.getStringExtra("iDescription")
        val aImageView = intent.getIntExtra("iImageView",0)

        actionBar.setTitle(aTitle)
        titleView.text = aTitle
        descriptionView.text = aDescription
        imageView.setImageResource(aImageView)

        //buton share si trimiterea imaginii pe email
        share_button.setOnClickListener {
            val image: Bitmap? = getBitmapFromView(imageView)

            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/*"
            share.putExtra(Intent.EXTRA_STREAM,getImageUri(this,image!!))
            startActivity(Intent.createChooser(share,"Share via"))
        }
    }
    private fun getBitmapFromView(view: ImageView): Bitmap?{
        val bitmap = Bitmap.createBitmap(view.width,view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri?{
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG,100,bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver,inImage,"Title",null)
        return Uri.parse(path)
    }
}