package com.dasho.android.kotlinSample

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var GenActBtn: Button
    private lateinit var FibActBtn: Button

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GenActBtn = findViewById(R.id.GenActBtn)
        FibActBtn = findViewById(R.id.FibActBtn)

        GenActBtn.setOnClickListener(this)
        FibActBtn.setOnClickListener(this)

        //ApplicationLogic(applicationContext).someApplicationLogic()

        val zebra: MyStringUtilZebra = MyStringUtilZebra()
        val aa = zebra.getZebra();
        Log.i("MyString", "Zebra=" + aa);

        val elephants: MyStringUtilElephants = MyStringUtilElephants()
        val bb = elephants.getElephants();
        Log.i("MyString", "Elephants=" + bb);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    /**
     * Handles the button clicks
     * @param v The view clicked
     */
    override fun onClick(v: View) {
//        if (!ApplicationLogic.wasDashOUsed()) {
//            toast("DashO was not used.")
//        } else if (!ApplicationLogic.wasRenamingApplied()) {
//            toast("DashO was used, but R8 was not used.")
//        }

//        when (v) {
//            GenActBtn -> startActivity(Intent(this, RandomGenActivity::class.java))
//            FibActBtn -> startActivity(Intent(this, FibActivity::class.java))
//        }
    }

    /**
     * Shows the about dialog
     */
    private fun showAboutDialog() {
        val dlgAlert = AlertDialog.Builder(this)
        dlgAlert.setTitle(R.string.abtTitle)
        dlgAlert.setMessage(R.string.abtMsg)
        dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null)
        dlgAlert.setCancelable(true)
        dlgAlert.create().show()
    }

    /**
     * Shows a long toast
     *
     * @param message The toast message
     */
    private fun toast(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}