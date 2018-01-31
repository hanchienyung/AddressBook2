package com.cy.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AddressBookRepository addressbookRepository;

    @RequestMapping("/")
    public String listAddressBooks(Model model) {
        model.addAttribute("addressbooks", addressbookRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addressbookForm(Model model){
        model.addAttribute("addressbook", new AddressBook2());
        return "addressbookform";

    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("addressbook") AddressBook2 addressbook, BindingResult result)
    {
        if (result.hasErrors()) {
              return "addressbookform";
        }
        addressbookRepository.save(addressbook);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showAddressBook(@PathVariable("id") long id, Model model){
        model.addAttribute("addressbook", addressbookRepository.findOne(id));
        return "show";
    }


    @GetMapping("/search")
    public String getSearchInput(Model model){
        return "searchform";
    }

    @PostMapping("/search")
    public String showSearchOutput(@PathVariable ("lastName") String lastName,
                                   Model model){
        return "list";
    }

    @RequestMapping("/search")
    public String searchAddressBook(Model model){
        return "searchform";
    }

    @RequestMapping("/update/{id}")
    public String updateAddressBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("addressbook", addressbookRepository.findOne(id));
        return "addressbookform";

    }

    @RequestMapping("/delete/{id}")
    public String delAddressBook(@PathVariable("id") long id){
        addressbookRepository.delete(id);
        return "redirect:/";
    }

}
