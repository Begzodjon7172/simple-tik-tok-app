package uz.bnabiyev.simpletiktokapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.PopupMenu
import uz.bnabiyev.simpletiktokapp.database.MyDbHelper
import uz.bnabiyev.simpletiktokapp.databinding.ActivityAddBinding
import uz.bnabiyev.simpletiktokapp.models.MyReels
import java.io.File
import java.io.FileOutputStream

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private var path = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        myDbHelper = MyDbHelper(this)

        binding.videoView.setOnClickListener {

            val popupMenu = PopupMenu(this, binding.videoView)
            popupMenu.inflate(R.menu.my_menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_camera -> {
                        Toast.makeText(this, "Cameradan video olish toliq emas", Toast.LENGTH_SHORT)
                            .show()
                    }

                    R.id.menu_gallery -> {
                        getVideoContentByGallery.launch("video/*")
                    }
                }
                true
            }
            popupMenu.show()

        }

        binding.apply {

            btnSave.setOnClickListener {
                myDbHelper.addReels(
                    MyReels(
                        edtName.text.toString(),
                        path
                    )
                )
                finish()
            }


        }

    }


    private val getVideoContentByGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.videoView.setVideoURI(it)
            binding.videoView.start()
            val inputStream = contentResolver.openInputStream(it)
            val file = File(filesDir, "${System.currentTimeMillis()}.mp4")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            path = file.absolutePath
            inputStream?.close()
            fileOutputStream.close()
        }

    private val getVideoContentByCamera =
        registerForActivityResult(ActivityResultContracts.TakeVideo()) {

        }
}