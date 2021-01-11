package com.onramp.android.takehome.explore

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.onramp.android.takehome.*
import com.onramp.android.takehome.imageData.Image
import com.onramp.android.takehome.imageData.source.local.FavoriteImage
import com.onramp.android.takehome.imageData.source.remote.ImageRemoteDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.w3c.dom.Text
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Exception
import kotlin.collections.ArrayList

class ExploreActivity : AppCompatActivity(), ExploreContract.View {

    private var adapter: ImageAdapter? = null
    private lateinit var presenter: ExploreContract.Presenter

    var downloadMap = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        // load topAppBar
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(topAppBar)

        setPresenter(ExplorePresenter(this, DependencyInjectorImpl()))
        // onViewCreated is blank
        presenter.onViewCreated()

        val activityContext = this
        // async function to retrieve data
        CoroutineScope(IO).launch { presenter.getRandomImageData(activityContext) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // onOptionsItemSelected is trigger when appbar menu item is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // item is the menu item clicked
        return when (item.itemId) {
            R.id.action_download -> {
                val downloadButton = findViewById<MaterialButton>(R.id.downloadButton)
                downloadButton.visibility = View.VISIBLE

                // TODO: get unique instance
//                val switchMaterial = findViewById<SwitchMaterial>(R.id.downloadSwitchMaterial)
//                switchMaterial.visibility = View.VISIBLE


                true
            }
            R.id.action_about -> {
                // TODO: opens about activity
                true
            }
            R.id.action_settings -> {
                // TODO: opens settings activity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setPresenter(presenter: ExploreContract.Presenter) {
        this.presenter = presenter
    }

    override suspend fun setBlank() {
        withContext(Main) {
            Toast.makeText(applicationContext, "Problem with connection", Toast.LENGTH_SHORT).show()
        }
    }

    override suspend fun setImagesOnMainThread(activityContext: Context, imageList: ArrayList<Image>) {
        withContext(Main) {
            setImagesToGridView(activityContext, imageList)
        }
    }

    // bind adapter to gridView
    private fun setImagesToGridView(activityContext: Context, imageList: ArrayList<Image>) {
        adapter = ImageAdapter(activityContext, imageList)
        val gridView = findViewById<GridView>(R.id.imageGrid)
        gridView.adapter = adapter
    }

    private var focusedView: View? = null

    // OnClick function for CardViews
    fun showPhotoDetail(view: View) {
        try {
            val imageView = view.findViewById<ImageView>(R.id.cardImageView)
            val textView = view.findViewById<TextView>(R.id.cardTextView)
            val imageButton = view.findViewById<ImageButton>(R.id.favoriteImageButton)

            when (focusedView) {
                view -> {
                    textView.visibility = View.INVISIBLE
                    imageButton.visibility = View.INVISIBLE
                    imageView.alpha = 1f
                    focusedView = null
                }
                null -> {
                    textView.visibility = View.VISIBLE
                    imageButton.visibility = View.VISIBLE
                    imageView.alpha = 0.5f
                    focusedView = view
                }
                else -> {
                    // deselect previous card
                    val prevImageView = focusedView!!.findViewById<ImageView>(R.id.cardImageView)
                    val prevTextView = focusedView!!.findViewById<TextView>(R.id.cardTextView)
                    val prevImageButton = focusedView!!.findViewById<ImageButton>(R.id.favoriteImageButton)
                    prevTextView.visibility = View.INVISIBLE
                    prevImageButton.visibility = View.INVISIBLE
                    prevImageView.alpha = 1f

                    // select the current card
                    textView.visibility = View.VISIBLE
                    imageButton.visibility = View.VISIBLE
                    imageView.alpha = 0.3f
                    focusedView = view
                }
            }
        } catch (e: Exception) {
            throw Exception("MaterialCardView OnClick: $e")
        }
    }

    fun saveToFavorites(view: View) {
        val url = focusedView?.findViewById<ImageView>(R.id.cardImageView)?.tag
        val imageData = FavoriteImage(0, "", "",
                url.toString(),"")
        CoroutineScope(IO).launch { presenter.saveFavoriteImage(this@ExploreActivity, imageData) }
    }

    fun startDownload(view: View) {
        // view refers to download button

        // TODO: get unique switchMaterial Instance, below call only refers to first one
//        val switchMaterial = findViewById<SwitchMaterial>(R.id.downloadSwitchMaterial)
//        switchMaterial.visibility = View.GONE
        view.visibility = View.GONE
        val downloadList: MutableCollection<String> = downloadMap.values
        // TODO: start service here
    }

    fun saveForDownload(view: View) {
        // view refers to switchMaterial
        val switchMaterial = view.findViewById<SwitchMaterial>(R.id.downloadSwitchMaterial)
        val tag = switchMaterial.tag as List<*>
        val key = tag[0].toString()
        val value = tag[1].toString()

        if (switchMaterial.isChecked) {
            downloadMap[key] = value
        } else {
            downloadMap.remove(key)
        }

    }

}