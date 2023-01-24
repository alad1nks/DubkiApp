package com.app.dubkiapp.ui.settings

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import com.app.dubkiapp.databinding.FragmentSettingsBinding
import com.app.dubkiapp.preferences.AppPreference
import javax.inject.Inject


class SettingsFragment : Fragment() {

    @Inject
    lateinit var appPreference: AppPreference

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as com.app.dubkiapp.DubkiApp).appComponent.settingsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val themeSwitch = binding.themeSwitch

        when (appPreference.getTheme()) {
            "light" -> themeSwitch.isChecked = false
            else -> themeSwitch.isChecked = true
        }

        themeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                appPreference.setTheme("dark")
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

            } else {
                appPreference.setTheme("light")
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }
        Log.d(TAG, "onViewCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}