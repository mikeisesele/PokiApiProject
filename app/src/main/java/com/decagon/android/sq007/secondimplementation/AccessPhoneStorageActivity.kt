package com.decagon.android.sq007.secondimplementation

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.loader.content.CursorLoader
import com.decagon.android.sq007.databinding.ActivityAccessPhoneStorageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class AccessPhoneStorageActivity : AppCompatActivity() {

        lateinit var binding: ActivityAccessPhoneStorageBinding
        lateinit var picture: Unit

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityAccessPhoneStorageBinding.inflate(LayoutInflater.from(this))

            setContentView(binding.root)

            binding.btnSelectImageButton.setOnClickListener {
                if(isPermissionsAllowed()){
                    selectImage(this)
                } else {
                    askForPermissions()
                }
            }

            binding.btnUploadButton.setOnClickListener {
                //uploadImage()
            }

            binding.btnDownloadButton.setOnClickListener {
                downloadPhoto()
            }
        }

        private fun downloadPhoto() {

            // set dispatchers
            GlobalScope.launch(context = Dispatchers.IO) {
                // save the reference in a variable
                val imageLink = URL("https://darot-upload-image-service-darothub.vercel.app/docs/")

                // open connection to the website
                val connection = imageLink.openConnection() as HttpURLConnection

                connection.doInput = true
                connection.connect()

                // save data coming from internet
                val inputStream: InputStream? = connection.inputStream

                // decode input to bitmap
                val bitmap = BitmapFactory.decodeStream(inputStream)

                launch(context = Main) {
                    binding.receivedImage.setImageBitmap(bitmap)
                }

            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isPermissionsAllowed(): Boolean {
            return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun askForPermissions(): Boolean {
            if (!isPermissionsAllowed()) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this as Activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )) {
                    showPermissionDeniedDialog()
                } else {
                    ActivityCompat.requestPermissions(
                        this as Activity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_CODE
                    )
                }
                return false
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            when (requestCode) {
                REQUEST_CODE -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // permission is granted, you can perform your operation here
                        selectImage(this)
                    } else {
                        // permission is denied, you can ask for permission again, if you want
                        askForPermissions()
                    }
                    return
                }
            }
        }

        private fun showPermissionDeniedDialog() {
            AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("Permission is denied, Please allow permissions from App Settings.")
                .setPositiveButton("App Settings",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        // send to app settings if permission is denied permanently
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    })
                .setNegativeButton("Cancel", null)
                .show()
        }

        private fun selectImage(
            storageAccessActivity: AccessPhoneStorageActivity,
        ) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }

//    private fun uploadImage( fileUri: Uri, desc: String){

/*
        GlobalScope.launch(context = Dispatchers.IO) {
  //          try {
                //creating a file
                val file: File = File(getRealPathFromURI(fileUri));

                //creating request body for file
                val requestFile: RequestBody = RequestBody.create(
                    MediaType.parse(getContentResolver().getType(fileUri)), file
                );
                val descBody: RequestBody = RequestBody.create(
                    MediaType.parse("text/plain"),
                    desc
                );

                //The gson builder
                val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create();


 */


//                    //creating retrofit object
//                    val retrofit: Retrofit = Retrofit.Builder()
//                        .baseUrl(Api.BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .build();
//
//                    //creating our api
//                    val api: Api  = retrofit.create(Api.class);
//
//                    //creating a call and calling the upload image method
//                    val call: Call<MyResponse>  = api.uploadImage(requestFile, descBody);
//
//                    //finally performing the call
//                    call.enqueue(Callback:<MyResponse>() {
//                        @Override
//                        fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
//                            if (!response.body().error) {
//                                Toast.makeText(
//                                    getApplicationContext(),
//                                    "File Uploaded Successfully...",
//                                    Toast.LENGTH_LONG
//                                ).show();
//                            } else {
//                                Toast.makeText(
//                                    getApplicationContext(),
//                                    "Some error occurred...",
//                                    Toast.LENGTH_LONG
//                                ).show();
//                            }
//                        }
//
//                        @Override
//                        fun onFailure(call: Call<MyResponse>, t: Throwable) {
//                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//
        //           } catch(e: Error){

        //           }
        //       }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
                binding.receivedImage.setImageURI(data?.data) // handle chosen image
            }
        }

//        private fun getRealPathFromURI(contentUri: Uri): String {
//            val proj = arrayOf(MediaStore.Images.Media.DATA)
//            val loader = CursorLoader(this, contentUri, proj, null, null, null)
//            val cursor: Cursor? = loader.loadInBackground()
//            val column_index: Int = (cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: cursor?.moveToFirst()) as Int
//            val result: String = (cursor?.getString(column_index) ?: cursor?.close()) as String
//            return result
//        }

        companion object {
            private var REQUEST_CODE = 100
        }
    }
