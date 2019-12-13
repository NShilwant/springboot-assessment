package net.java.springboot.assessment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.java.springboot.assessment.entity.City;
import net.java.springboot.assessment.repository.CityRepository;

@Controller
@RequestMapping("/cities/")
public class CityController {

	private final CityRepository cityRepository;

	@Autowired
	public CityController(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@GetMapping("signup")
	public String showSignUpForm(City city) {
		return "add-city";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("cities", cityRepository.findAll());
		return "index";
	}

	@PostMapping("add")
	public String addStudent(@Valid City city, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-city";
		}

		cityRepository.save(city);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		City city = cityRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid city Id:" + id));
		model.addAttribute("city", city);
		return "update-city";
	}

	@PostMapping("update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid City city, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			city.setId(id);
			return "update-city";
		}

		cityRepository.save(city);
		model.addAttribute("cities", cityRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		City city = cityRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid city Id:" + id));
		cityRepository.delete(city);
		model.addAttribute("cities", cityRepository.findAll());
		return "index";
	}
}
