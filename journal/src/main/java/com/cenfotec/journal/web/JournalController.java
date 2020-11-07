package com.cenfotec.journal.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cenfotec.journal.domain.Journal;
import com.cenfotec.journal.service.JournalService;

@Controller
public class JournalController {
	@Autowired
	JournalService journalService;

	@RequestMapping("/")
	public String index(Model model) throws ParseException {
		model.addAttribute("journal", new Journal());
		model.addAttribute("jrnl", journalService.getAllJournals());
		return "index";
	}

	@PostMapping("/journal")
	public String journalSubmit(@ModelAttribute Journal journal, Model model) {
		Journal newEntry = journal;
		journalService.saveJournal(newEntry);
		return "index";
	}

	@GetMapping("/journal")
	public String getJournal(@RequestParam int id, Model model) {
		for (Journal jrnl : journalService.getAllJournals()) {
			if (jrnl.getId() == id) {
				model.addAttribute("jrnl", jrnl);
			}
		}
		return "journal";
	}

}