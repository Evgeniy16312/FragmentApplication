package com.example.fragmentapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.fragment.app.activityViewModels
import com.example.fragmentapplication.ContactList.contactList
import com.example.fragmentapplication.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding

    private val data: ModelData by activityViewModels()

    private var newId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        putIdEdit()
        savedChanges()
    }

    private fun putIdEdit() {
        data.putId.observe(activity as LifecycleOwner, {
            newId = it
        })
        with(binding) {
            etName.setText(contactList[newId].name)
            etSurname.setText(contactList[newId].surname)
            etPhoneNumber.setText(contactList[newId].number)
        }
    }

    private fun savedChanges() {
        with(binding) {
            btSave.setOnClickListener {
                contactList[newId].name = etName.text.toString()
                contactList[newId].surname = etSurname.text.toString()
                contactList[newId].number = etPhoneNumber.text.toString()
                exitAndSave()
            }
        }
        if (context?.resources?.configuration?.smallestScreenWidthDp!! >= 600) {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, ContactFragment())
                ?.addToBackStack(null)
                ?.commit()
            exitAndSave()
        }
    }

    private fun exitAndSave() {
        binding.btSave.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, ContactsListFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    companion object {
        fun newInstance() = ContactFragment()
    }
}