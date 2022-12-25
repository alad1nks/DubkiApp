package com.example.dubkiapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dubkiapp.databinding.ActivityMainBinding
import com.example.dubkiapp.preferences.AppPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appPreference: AppPreference
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as DubkiApp).appComponent.mainActivityComponent().create().inject(this)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        if (auth.currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.hide()


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_schedule,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }



    override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }
    }
}


//db.reference.child("schedule").child("dubki").child("sunday").setValue(listOf(BusResponse(0, "Slavyanka", 300000, "00:05"),
//BusResponse(1, "Odintsovo", 3600000, "01:00"),
//BusResponse(2, "Odintsovo", 6000000, "01:40"),
//BusResponse(3, "Odintsovo", 30000000, "08:20"),
//BusResponse(4, "Odintsovo", 33900000, "09:25"),
//BusResponse(5, "Odintsovo", 36600000, "10:10"),
//BusResponse(6, "Odintsovo", 39000000, "10:50"),
//BusResponse(7, "Odintsovo", 43800000, "12:10"),
//BusResponse(8, "Odintsovo", 46320000, "12:52"),
//BusResponse(9, "Odintsovo", 49200000, "13:40"),
//BusResponse(10, "Odintsovo", 53640000, "14:54"),
//BusResponse(11, "Odintsovo", 60000000, "16:40"),
//BusResponse(12, "Odintsovo", 63600000, "17:40"),
//BusResponse(13, "Odintsovo", 67800000, "18:50"),
//BusResponse(14, "Odintsovo", 70200000, "19:30"),
//BusResponse(15, "Odintsovo", 72480000, "20:08"),
//BusResponse(16, "Odintsovo", 73800000, "20:30"),
//BusResponse(17, "Odintsovo", 75300000, "20:55"),
//BusResponse(18, "Odintsovo", 76860000, "21:21"),
//BusResponse(19, "Odintsovo", 79200000, "22:00"),
//BusResponse(20, "Odintsovo", 81600000, "22:40"),
//BusResponse(21, "Odintsovo", 85800000, "23:50"),
//BusResponse(22, "Odintsovo", 90000000, "01:00"),
//BusResponse(23, "Odintsovo", 92400000, "01:40")))


//db.reference.child("schedule").child("city").child("sunday").setValue(listOf(
//BusResponse(0, "Odintsovo", 4800000, "01:20"),
//BusResponse(1, "Odintsovo", 28200000, "07:50"),
//BusResponse(2, "Odintsovo", 32700000, "09:05"),
//BusResponse(3, "Odintsovo", 35700000, "09:55"),
//BusResponse(4, "Odintsovo", 37800000, "10:30"),
//BusResponse(5, "Odintsovo", 42000000, "11:40"),
//BusResponse(6, "Odintsovo", 44820000, "12:27"),
//BusResponse(7, "Odintsovo", 48000000, "13:20"),
//BusResponse(8, "Odintsovo", 52500000, "14:35"),
//BusResponse(9, "Odintsovo", 58800000, "16:20"),
//BusResponse(10, "Odintsovo", 62400000, "17:20"),
//BusResponse(11, "Odintsovo", 66600000, "18:30"),
//BusResponse(12, "Odintsovo", 68700000, "19:05"),
//BusResponse(13, "Odintsovo", 71400000, "19:50"),
//BusResponse(14, "Odintsovo", 72600000, "20:10"),
//BusResponse(15, "Odintsovo", 74100000, "20:35"),
//BusResponse(16, "Odintsovo", 75600000, "21:00"),
//BusResponse(17, "Odintsovo", 78000000, "21:40"),
//BusResponse(18, "Odintsovo", 80400000, "22:20"),
//BusResponse(19, "Odintsovo", 84000000, "23:20"),
//BusResponse(20, "Odintsovo", 91200000, "01:20")))


//db.reference.child("schedule").child("city").child("saturday").setValue(listOf(
//BusResponse(0, "Odintsovo", 4800000, "01:20"),
//BusResponse(1, "Molodezhnaya", 24000000, "06:40"),
//BusResponse(2, "Odintsovo", 24600000, "06:50"),
//BusResponse(3, "Odintsovo", 26400000, "07:20"),
//BusResponse(4, "Molodezhnaya", 26700000, "07:25"),
//BusResponse(5, "Molodezhnaya", 28500000, "07:55"),
//BusResponse(6, "Molodezhnaya", 28500000, "07:55"),
//BusResponse(7, "Molodezhnaya", 28800000, "08:00"),
//BusResponse(8, "Molodezhnaya", 29100000, "08:05"),
//BusResponse(9, "Molodezhnaya", 34200000, "09:30"),
//BusResponse(10, "Molodezhnaya", 34500000, "09:35"),
//BusResponse(11, "Molodezhnaya", 34800000, "09:40"),
//BusResponse(12, "Odintsovo", 36900000, "10:15"),
//BusResponse(13, "Odintsovo", 38100000, "10:35"),
//BusResponse(14, "Slavyanka", 38700000, "10:45"),
//BusResponse(15, "Odintsovo", 39000000, "10:50"),
//BusResponse(16, "Molodezhnaya", 40500000, "11:15"),
//BusResponse(17, "Molodezhnaya", 41400000, "11:30"),
//BusResponse(18, "Molodezhnaya", 42300000, "11:45"),
//BusResponse(19, "Odintsovo", 42600000, "11:50"),
//BusResponse(20, "Slavyanka", 45000000, "12:30"),
//BusResponse(21, "Odintsovo", 46500000, "12:55"),
//BusResponse(22, "Slavyanka", 47700000, "13:15"),
//BusResponse(23, "Odintsovo", 48300000, "13:25"),
//BusResponse(24, "Odintsovo", 50400000, "14:00"),
//BusResponse(25, "Odintsovo", 51900000, "14:25"),
//BusResponse(26, "Molodezhnaya", 53700000, "14:55"),
//BusResponse(27, "Odintsovo", 54180000, "15:03"),
//BusResponse(28, "Odintsovo", 57900000, "16:05"),
//BusResponse(29, "Molodezhnaya", 59700000, "16:35"),
//BusResponse(30, "Odintsovo", 60000000, "16:40"),
//BusResponse(31, "Odintsovo", 62700000, "17:25"),
//BusResponse(32, "Odintsovo", 64800000, "18:00"),
//BusResponse(33, "Molodezhnaya", 66000000, "18:20"),
//BusResponse(34, "Odintsovo", 67500000, "18:45"),
//BusResponse(35, "Molodezhnaya", 69000000, "19:10"),
//BusResponse(36, "Odintsovo", 69900000, "19:25"),
//BusResponse(37, "Odintsovo", 72000000, "20:00"),
//BusResponse(38, "Molodezhnaya", 72600000, "20:10"),
//BusResponse(39, "Odintsovo", 74100000, "20:35"),
//BusResponse(40, "Molodezhnaya", 74100000, "20:35"),
//BusResponse(41, "Odintsovo", 75000000, "20:50"),
//BusResponse(42, "Odintsovo", 76500000, "21:15"),
//BusResponse(43, "Molodezhnaya", 77400000, "21:30"),
//BusResponse(44, "Slavyanka", 78000000, "21:40"),
//BusResponse(45, "Odintsovo", 78600000, "21:50"),
//BusResponse(46, "Odintsovo", 79800000, "22:10"),
//BusResponse(47, "Slavyanka", 80100000, "22:15"),
//BusResponse(48, "Odintsovo", 81600000, "22:40"),
//BusResponse(49, "Odintsovo", 82500000, "22:55"),
//BusResponse(50, "Odintsovo", 83700000, "23:15"),
//BusResponse(51, "Slavyanka", 84000000, "23:20"),
//BusResponse(52, "Odintsovo", 85200000, "23:40"),
//BusResponse(53, "Odintsovo", 91200000, "01:20")))


//db.reference.child("schedule").child("city").child("weekday").setValue(listOf(
//BusResponse(0, "Odintsovo", 4800000, "01:20"),
//BusResponse(1, "Molodezhnaya", 23400000, "06:30"),
//BusResponse(2, "Odintsovo", 24300000, "06:45"),
//BusResponse(3, "Molodezhnaya", 25200000, "07:00"),
//BusResponse(4, "Odintsovo", 26400000, "07:20"),
//BusResponse(5, "Molodezhnaya", 27000000, "07:30"),
//BusResponse(6, "Odintsovo", 27300000, "07:35"),
//BusResponse(7, "Odintsovo", 27900000, "07:45"),
//BusResponse(8, "Molodezhnaya", 28500000, "07:55"),
//BusResponse(9, "Molodezhnaya", 28500000, "07:55"),
//BusResponse(10, "Molodezhnaya", 29100000, "08:05"),
//BusResponse(11, "Odintsovo", 29700000, "08:15"),
//BusResponse(12, "Molodezhnaya", 30000000, "08:20"),
//BusResponse(13, "Molodezhnaya", 30900000, "08:35"),
//BusResponse(14, "Odintsovo", 31500000, "08:45"),
//BusResponse(15, "Molodezhnaya", 32100000, "08:55"),
//BusResponse(16, "Molodezhnaya", 32400000, "09:00"),
//BusResponse(17, "Molodezhnaya", 33600000, "09:20"),
//BusResponse(18, "Odintsovo", 33900000, "09:25"),
//BusResponse(19, "Molodezhnaya", 34200000, "09:30"),
//BusResponse(20, "Molodezhnaya", 35100000, "09:45"),
//BusResponse(21, "Molodezhnaya", 35100000, "09:45"),
//BusResponse(22, "Odintsovo", 37500000, "10:25"),
//BusResponse(23, "Molodezhnaya", 38100000, "10:35"),
//BusResponse(24, "Odintsovo", 39300000, "10:55"),
//BusResponse(25, "Odintsovo", 40500000, "11:15"),
//BusResponse(26, "Molodezhnaya", 40500000, "11:15"),
//BusResponse(27, "Molodezhnaya", 41100000, "11:25"),
//BusResponse(28, "Molodezhnaya", 41700000, "11:35"),
//BusResponse(29, "Odintsovo", 42300000, "11:45"),
//BusResponse(30, "Slavyanka", 45600000, "12:40"),
//BusResponse(31, "Odintsovo", 45900000, "12:45"),
//BusResponse(32, "Molodezhnaya", 47700000, "13:15"),
//BusResponse(33, "Odintsovo", 48300000, "13:25"),
//BusResponse(34, "Odintsovo", 50400000, "14:00"),
//BusResponse(35, "Odintsovo", 52800000, "14:40"),
//BusResponse(36, "Molodezhnaya", 53700000, "14:55"),
//BusResponse(37, "Odintsovo", 55380000, "15:23"),
//BusResponse(38, "Odintsovo", 57600000, "16:00"),
//BusResponse(39, "Odintsovo", 59700000, "16:35"),
//BusResponse(40, "Molodezhnaya", 59700000, "16:35"),
//BusResponse(41, "Odintsovo", 61200000, "17:00"),
//BusResponse(42, "Odintsovo", 63000000, "17:30"),
//BusResponse(43, "Odintsovo", 64500000, "17:55"),
//BusResponse(44, "Molodezhnaya", 64800000, "18:00"),
//BusResponse(45, "Odintsovo", 65400000, "18:10"),
//BusResponse(46, "Molodezhnaya", 66900000, "18:35"),
//BusResponse(47, "Molodezhnaya", 66900000, "18:35"),
//BusResponse(48, "Odintsovo", 67200000, "18:40"),
//BusResponse(49, "Odintsovo", 69000000, "19:10"),
//BusResponse(50, "Odintsovo", 71100000, "19:45"),
//BusResponse(51, "Odintsovo", 71700000, "19:55"),
//BusResponse(52, "Molodezhnaya", 72300000, "20:05"),
//BusResponse(53, "Odintsovo", 72600000, "20:10"),
//BusResponse(54, "Odintsovo", 73500000, "20:25"),
//BusResponse(55, "Molodezhnaya", 74100000, "20:35"),
//BusResponse(56, "Odintsovo", 74400000, "20:40"),
//BusResponse(57, "Odintsovo", 75600000, "21:00"),
//BusResponse(58, "Odintsovo", 76800000, "21:20"),
//BusResponse(59, "Molodezhnaya", 77400000, "21:30"),
//BusResponse(60, "Slavyanka", 78000000, "21:40"),
//BusResponse(61, "Odintsovo", 78600000, "21:50"),
//BusResponse(62, "Odintsovo", 79500000, "22:05"),
//BusResponse(63, "Slavyanka", 80700000, "22:25"),
//BusResponse(64, "Odintsovo", 81600000, "22:40"),
//BusResponse(65, "Odintsovo", 82500000, "22:55"),
//BusResponse(66, "Odintsovo", 83700000, "23:15"),
//BusResponse(67, "Slavyanka", 84000000, "23:20"),
//BusResponse(68, "Odintsovo", 85200000, "23:40"),
//BusResponse(69, "Odintsovo", 91200000, "01:20")))


//db.reference.child("schedule").child("dubki").child("weekday").setValue(listOf(
//BusResponse(0, "Odintsovo", 3600000, "01:00"),
//BusResponse(1, "Odintsovo", 6000000, "01:40"),
//BusResponse(2, "Odintsovo", 25200000, "Примерно 07:00"),
//BusResponse(3, "Odintsovo", 27300000, "Примерно 07:35"),
//BusResponse(4, "Odintsovo", 28200000, "Примерно 07:50"),
//BusResponse(5, "Odintsovo", 28800000, "Примерно 08:00"),
//BusResponse(6, "Molodezhnaya", 29100000, "08:05"),
//BusResponse(7, "Odintsovo", 30600000, "Примерно 08:30"),
//BusResponse(8, "Odintsovo", 32400000, "Примерно 09:00"),
//BusResponse(9, "Odintsovo", 34800000, "Примерно 09:40"),
//BusResponse(10, "Odintsovo", 38400000, "Примерно 10:40"),
//BusResponse(11, "Odintsovo", 40200000, "Примерно 11:10"),
//BusResponse(12, "Odintsovo", 41700000, "11:35"),
//BusResponse(13, "Molodezhnaya", 43500000, "12:05"),
//BusResponse(14, "Odintsovo", 44100000, "12:15"),
//BusResponse(15, "Odintsovo", 47100000, "13:05"),
//BusResponse(16, "Odintsovo", 49500000, "13:45"),
//BusResponse(17, "Slavyanka", 51300000, "14:15"),
//BusResponse(18, "Odintsovo", 51600000, "14:20"),
//BusResponse(19, "Molodezhnaya", 52200000, "14:30"),
//BusResponse(20, "Odintsovo", 54300000, "15:05"),
//BusResponse(21, "Molodezhnaya", 55800000, "15:30"),
//BusResponse(22, "Odintsovo", 56580000, "15:43"),
//BusResponse(23, "Odintsovo", 59100000, "16:25"),
//BusResponse(24, "Odintsovo", 61200000, "17:00"),
//BusResponse(25, "Molodezhnaya", 61800000, "17:10"),
//BusResponse(26, "Odintsovo", 62520000, "17:22"),
//BusResponse(27, "Odintsovo", 64500000, "17:55"),
//BusResponse(28, "Odintsovo", 65700000, "18:15"),
//BusResponse(29, "Odintsovo", 67020000, "18:37"),
//BusResponse(30, "Molodezhnaya", 67500000, "18:45"),
//BusResponse(31, "Odintsovo", 69000000, "19:10"),
//BusResponse(32, "Molodezhnaya", 69000000, "19:10"),
//BusResponse(33, "Molodezhnaya", 70200000, "19:30"),
//BusResponse(34, "Odintsovo", 70500000, "19:35"),
//BusResponse(35, "Odintsovo", 72300000, "20:05"),
//BusResponse(36, "Odintsovo", 73080000, "20:18"),
//BusResponse(37, "Odintsovo", 73920000, "20:32"),
//BusResponse(38, "Molodezhnaya", 74100000, "20:35"),
//BusResponse(39, "Odintsovo", 74700000, "20:45"),
//BusResponse(40, "Odintsovo", 75540000, "20:59"),
//BusResponse(41, "Molodezhnaya", 76200000, "21:10"),
//BusResponse(42, "Odintsovo", 76560000, "21:16"),
//BusResponse(43, "Odintsovo", 78000000, "21:40"),
//BusResponse(44, "Molodezhnaya", 79500000, "22:05"),
//BusResponse(45, "Odintsovo", 80100000, "22:15"),
//BusResponse(46, "Odintsovo", 81300000, "22:35"),
//BusResponse(47, "Slavyanka", 81300000, "22:35"),
//BusResponse(48, "Odintsovo", 82800000, "23:00"),
//BusResponse(49, "Odintsovo", 83340000, "23:09"),
//BusResponse(50, "Slavyanka", 83700000, "23:15"),
//BusResponse(51, "Odintsovo", 84900000, "23:35"),
//BusResponse(52, "Odintsovo", 86100000, "23:55"),
//BusResponse(53, "Slavyanka", 86700000, "00:05"),
//BusResponse(54, "Odintsovo", 90000000, "01:00"),
//BusResponse(55, "Odintsovo", 92400000, "01:40")))


//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(0, "Odintsovo", 25500000, "Примерно 07:05"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(1, "Odintsovo", 27300000, "Примерно 07:35"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(2, "Molodezhnaya", 29700000, "Примерно 08:15"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(3, "Molodezhnaya", 35700000, "Примерно 09:55"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(4, "Molodezhnaya", 36000000, "10:00"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(5, "Odintsovo", 37800000, "Примерно 10:30"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(6, "Odintsovo", 39000000, "Примерно 10:50"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(7, "Odintsovo", 39900000, "Примерно 11:05"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(8, "Molodezhnaya", 42300000, "11:45"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(9, "Molodezhnaya", 43500000, "12:05"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(10, "Odintsovo", 44100000, "12:15"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(11, "Molodezhnaya", 44100000, "12:15"))


//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(12, "Slavyanka", 47400000, "13:10"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(13, "Odintsovo", 47700000, "13:15"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(14, "Odintsovo", 49200000, "13:40"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(15, "Slavyanka", 51000000, "14:10"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(16, "Odintsovo", 51840000, "14:24"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(17, "Odintsovo", 53100000, "14:45"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(18, "Slavyanka", 54300000, "15:05"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(19, "Odintsovo", 55440000, "15:24"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(20, "Molodezhnaya", 55800000, "15:30"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(21, "Odintsovo", 59100000, "16:25"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(22, "Odintsovo", 61800000, "17:10"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(23, "Molodezhnaya", 61800000, "17:10"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(24, "Odintsovo", 64500000, "17:55"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(25, "Odintsovo", 66000000, "18:20"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(26, "Molodezhnaya", 67800000, "18:50"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(27, "Odintsovo", 69300000, "19:15"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(28, "Molodezhnaya", 70800000, "19:40"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(29, "Odintsovo", 71100000, "19:45"))



//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(30, "Odintsovo", 73500000, "20:25"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(31, "Molodezhnaya", 74100000, "20:35"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(32, "Odintsovo", 75240000, "20:54"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(33, "Odintsovo", 76080000, "21:08"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(34, "Molodezhnaya", 76200000, "21:10"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(35, "Odintsovo", 77700000, "21:35"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(36, "Molodezhnaya", 79200000, "22:00"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(37, "Odintsovo", 79500000, "22:05"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(38, "Odintsovo", 81000000, "22:30"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(39, "Slavyanka", 81300000, "22:35"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(40, "Odintsovo", 82620000, "22:57"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(41, "Odintsovo", 83340000, "23:09"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(42, "Slavyanka", 83700000, "23:15"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(43, "Odintsovo", 84900000, "23:35"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(44, "Odintsovo", 86100000, "23:55"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(45, "Slavyanka", 86700000, "00:05"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(46, "Odintsovo", 90000000, "01:00"))
//db.reference.child("schedule").child("dubki").child("saturday").push().setValue(BusResponse(47, "Odintsovo", 92400000, "01:40"))


//db.reference.child("schedule").child("dubki").child("saturday").setValue(listOf(
//BusResponse(0, "Slavyanka", 86700000, "00:05"),
//BusResponse(1, "Odintsovo", 90000000, "01:00"),
//BusResponse(2, "Odintsovo", 92400000, "01:40"),
//BusResponse(3, "Odintsovo", 25500000, "Примерно 07:05"),
//BusResponse(4, "Odintsovo", 27300000, "Примерно 07:35"),
//BusResponse(5, "Molodezhnaya", 29700000, "Примерно 08:15"),
//BusResponse(6, "Molodezhnaya", 35700000, "Примерно 09:55"),
//BusResponse(7, "Molodezhnaya", 36000000, "10:00"),
//BusResponse(8, "Odintsovo", 37800000, "Примерно 10:30"),
//BusResponse(9, "Odintsovo", 39000000, "Примерно 10:50"),
//BusResponse(10, "Odintsovo", 39900000, "Примерно 11:05"),
//BusResponse(11, "Molodezhnaya", 42300000, "11:45"),
//BusResponse(12, "Molodezhnaya", 43500000, "12:05"),
//BusResponse(13, "Odintsovo", 44100000, "12:15"),
//BusResponse(14, "Molodezhnaya", 44100000, "12:15"),
//BusResponse(15, "Slavyanka", 47400000, "13:10"),
//BusResponse(16, "Odintsovo", 47700000, "13:15"),
//BusResponse(17, "Odintsovo", 49200000, "13:40"),
//BusResponse(18, "Slavyanka", 51000000, "14:10"),
//BusResponse(19, "Odintsovo", 51840000, "14:24"),
//BusResponse(20, "Odintsovo", 53100000, "14:45"),
//BusResponse(21, "Slavyanka", 54300000, "15:05"),
//BusResponse(22, "Odintsovo", 55440000, "15:24"),
//BusResponse(23, "Molodezhnaya", 55800000, "15:30"),
//BusResponse(24, "Odintsovo", 59100000, "16:25"),
//BusResponse(25, "Odintsovo", 61800000, "17:10"),
//BusResponse(26, "Molodezhnaya", 61800000, "17:10"),
//BusResponse(27, "Odintsovo", 64500000, "17:55"),
//BusResponse(28, "Odintsovo", 66000000, "18:20"),
//BusResponse(29, "Molodezhnaya", 67800000, "18:50"),
//BusResponse(30, "Odintsovo", 69300000, "19:15"),
//BusResponse(31, "Molodezhnaya", 70800000, "19:40"),
//BusResponse(32, "Odintsovo", 71100000, "19:45"),
//BusResponse(33, "Odintsovo", 73500000, "20:25"),
//BusResponse(34, "Molodezhnaya", 74100000, "20:35"),
//BusResponse(35, "Odintsovo", 75240000, "20:54"),
//BusResponse(36, "Odintsovo", 76080000, "21:08"),
//BusResponse(37, "Molodezhnaya", 76200000, "21:10"),
//BusResponse(38, "Odintsovo", 77700000, "21:35"),
//BusResponse(39, "Molodezhnaya", 79200000, "22:00"),
//BusResponse(40, "Odintsovo", 79500000, "22:05"),
//BusResponse(41, "Odintsovo", 81000000, "22:30"),
//BusResponse(42, "Slavyanka", 81300000, "22:35"),
//BusResponse(43, "Odintsovo", 82620000, "22:57"),
//BusResponse(44, "Odintsovo", 83340000, "23:09"),
//BusResponse(45, "Slavyanka", 83700000, "23:15"),
//BusResponse(46, "Odintsovo", 84900000, "23:35"),
//BusResponse(47, "Odintsovo", 86100000, "23:55"),
//BusResponse(48, "Slavyanka", 86700000, "00:05"),
//BusResponse(49, "Odintsovo", 90000000, "01:00"),
//BusResponse(50, "Odintsovo", 92400000, "01:40")))