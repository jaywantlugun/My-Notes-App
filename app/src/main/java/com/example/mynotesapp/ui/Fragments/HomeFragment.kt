package com.example.mynotesapp.ui.Fragments


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.ViewModel.NotesViewModel
import com.example.mynotesapp.databinding.FragmentHomeBinding
import com.example.mynotesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        //for staggered view--
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        //staggeredGridLayoutManager.gapStrategy
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
        //--

        //get all Notes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter=NotesAdapter(requireContext(),notesList)
            binding.rcvAllNotes.adapter = adapter

//            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
//            binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)

        }



        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }


        //filter high
        binding.filterHigh.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter


//                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
//                binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)

            }

        }


        //filter medium
        binding.filterMedium.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter


//                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
//                binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)

            }

        }

        //filter low
        binding.filterLow.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter


//                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
//                binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)

            }

        }

        // filter all
        binding.allNotes.setOnClickListener {

            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter


//                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
//                binding.rcvAllNotes.adapter=NotesAdapter(requireContext(),notesList)

            }
        }


        return binding.root


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0:String?){

        val newFilteredList = arrayListOf<Notes>()

        for(i in oldMyNotes){
            if(i.title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)

    }



}