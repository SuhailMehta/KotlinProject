package com.tala.assignment.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.tala.assignment.R
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.ui.venuelist.VenueListActivity
import com.tala.assignment.ui.venuelist.VenueListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, IActivityCommunication {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragment = CategoryListFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
        }
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onClick(data: VenueListModel.VenueRow) {
        val intent = Intent(this, VenueListActivity::class.java)
        intent.putExtra(VenueListFragment.title(), data.name)
        intent.putExtra(VenueListFragment.id(), data.id)
        startActivity(intent)
    }
}

interface IActivityCommunication {
    fun setTitle(title: String)
    fun onClick(data: VenueListModel.VenueRow)
}
