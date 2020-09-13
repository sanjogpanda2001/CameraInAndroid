package com.example.camera

import android.Manifest.permission
import java.util.jar.Manifest


import android.Manifest.permission.CAMERA
import android.Manifest.permission_group.CAMERA
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
  /*  companion object{
        private const val CAMERA_PERMISSION_CODE=1
        private  const val CAMERA_REQUEST_CODE=0
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                   android.Manifest.permission.CAMERA
                )==PackageManager.PERMISSION_GRANTED){
                val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 0)
            }
            else{ ActivityCompat.requestPermissions(
                this,
                arrayOf( android.Manifest.permission.CAMERA ),
                1
            )}
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== 1){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent,0)
            }
                else{
                Toast.makeText(this@MainActivity, "permission denied" , Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode== 0){
                val thumb: Bitmap= data!!.extras!!.get("data") as Bitmap
                 imageView.setImageBitmap(thumb)
            }
        }
    }
}