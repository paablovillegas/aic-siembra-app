package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormDescuentoBinding

class FormDescuento : Fragment() {
    lateinit var binding: FormDescuentoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormDescuentoBinding.inflate(inflater, container, false)
        return binding.root
    }

}