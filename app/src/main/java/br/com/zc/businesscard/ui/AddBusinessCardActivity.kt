package br.com.zc.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.zc.businesscard.Application
import br.com.zc.businesscard.R
import br.com.zc.businesscard.data.BusinessCard
import br.com.zc.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as Application).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.txtlnName.editText?.text.toString(),
                empresa = binding.txtlnNameCompany.editText?.text.toString(),
                email = binding.txtlnEmail.editText?.text.toString(),
                telefone = binding.txtlnCellPhone.editText?.text.toString(),
                fundoPersonalizado = binding.txtlnColor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.show_sucess, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}