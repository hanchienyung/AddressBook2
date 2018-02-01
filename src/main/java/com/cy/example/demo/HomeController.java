package com.cy.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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


    /*@GetMapping("/search")
    public String getSearchInput(Model model){
        return "searchform";
    }*/

    @PostMapping("/search")
    public String showSearchOutput(HttpServletRequest request, Model model){
        String searchString = request.getParameter("lastName");
        System.out.println("searchString is " + searchString);
        model.addAttribute("lastName",searchString);
        model.addAttribute("addressbooks", addressbookRepository.findByLastNameIgnoreCase(searchString));
        List <AddressBook2> l = addressbookRepository.findByLastNameIgnoreCase()  findByLastNameIgnoreCase(searchString);
        System.out.println(l.size());
        for (AddressBook2 a:  l){
            System.out.println(a.getLastName());
        }

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
