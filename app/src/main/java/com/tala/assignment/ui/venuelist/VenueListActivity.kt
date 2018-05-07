package com.tala.assignment.ui.venuelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.tala.assignment.R
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.ui.main.IActivityCommunication
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.tala.assignment.R.id.toolbar




class VenueListActivity : AppCompatActivity(), HasSupportFragmentInjector, IActivityCommunication {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val title: String? = intent.getStringExtra(VenueListFragment.title())
        val id: String? = intent.getStringExtra(VenueListFragment.id())

        val fragment = VenueListFragment()

        if (savedInstanceState == null) {

            val args = Bundle()

            args.putString(VenueListFragment.title(), title)
            args.putString(VenueListFragment.id(), id)
            fragment.arguments = args

            supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
        }
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onClick(data: VenueListModel.VenueRow) {
    }
}
