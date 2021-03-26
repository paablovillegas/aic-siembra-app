package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.extras.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormExtrasBinding

class FormExtra : Fragment() {
    lateinit var binding: FormExtrasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormExtrasBinding.inflate(inflater, container, false)
        return binding.root
    }

}