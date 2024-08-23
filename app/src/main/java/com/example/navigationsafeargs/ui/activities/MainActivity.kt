package com.example.navigationsafeargs.ui.activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.navigationsafeargs.R
import com.example.navigationsafeargs.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(Toolbar(this))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottom_navigation) as NavHostFragment

        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.home_fragment,
            R.id.addNoteFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}



//    private lateinit var drawerLayout: DrawerLayout
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
//    private lateinit var btnBottomSheetPersistent:AppCompatButton
//        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheet))
//        btnBottomSheetPersistent=findViewById(R.id.btnBottomSheetPersistent)
//        bottomSheetBehavior.addBottomSheetCallback(object :

//        BottomSheetBehavior.BottomSheetCallback() {
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                // handle onSlide
//            }
//
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(
//                        this@MainActivity,
//                        "STATE_COLLAPSED",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(
//                        this@MainActivity,
//                        "STATE_EXPANDED",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(
//                        this@MainActivity,
//                        "STATE_DRAGGING",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(
//                        this@MainActivity,
//                        "STATE_SETTLING",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(
//                        this@MainActivity,
//                        "STATE_HIDDEN",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    else -> Toast.makeText(this@MainActivity, "OTHER_STATE", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//
//
//
//        })
//
//        btnBottomSheetPersistent.setOnClickListener {
//            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            else
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        }
