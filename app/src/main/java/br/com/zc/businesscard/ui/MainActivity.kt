package br.com.zc.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.zc.businesscard.Application
import br.com.zc.businesscard.databinding.ActivityMainBinding
import br.com.zc.businesscard.util.Image

class MainActivity : AppCompatActivity() {
    
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as Application).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insetListener()
    }

    private fun insetListener() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }
    
    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this, {businessCard ->
            adapter.submitList(businessCard)
        } )
    }
}