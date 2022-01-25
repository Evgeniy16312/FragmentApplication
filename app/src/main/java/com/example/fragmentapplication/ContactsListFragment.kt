package com.example.fragmentapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fragmentapplication.ContactList.contactList
import com.example.fragmentapplication.databinding.FragmentContactsListBinding


class ContactsListFragment : Fragment() {

    private lateinit var binding: FragmentContactsListBinding

    private val data: ModelData by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContactsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            contact1.text = contactList[0].name
            contact2.text = contactList[1].name
            contact3.text = contactList[2].name
            contact4.text = contactList[3].name
            contact5.text = contactList[4].name
            pushToFragment()
        }
    }

    private fun pushToFragment() {
        binding.apply {
            contact1.setOnClickListener {
                data.putId.value = 0
                openFragment()
            }
            contact2.setOnClickListener {
                data.putId.value = 1
                openFragment()
            }
            contact3.setOnClickListener {
                data.putId.value = 2
                openFragment()
            }
            contact4.setOnClickListener {
                data.putId.value = 3
                openFragment()
            }
            contact5.setOnClickListener {
                data.putId.value = 4
                openFragment()
            }
        }
    }

    private fun openFragment() {
        val screenMode = context?.resources?.configuration?.smallestScreenWidthDp
        if (screenMode!! >= 600) {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container_600dp, ContactFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()
        } else {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, ContactFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    companion object {
        fun newInstance() = ContactsListFragment()
    }
}