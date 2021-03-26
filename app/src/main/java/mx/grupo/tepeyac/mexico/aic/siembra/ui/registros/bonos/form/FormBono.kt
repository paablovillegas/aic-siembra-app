package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.bonos.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormBonosBinding

class FormBono : Fragment() {
    lateinit var binding: FormBonosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormBonosBinding.inflate(inflater, container, false)
        return binding.root
    }

}