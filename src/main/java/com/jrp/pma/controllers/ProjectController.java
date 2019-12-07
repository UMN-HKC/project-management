package com.jrp.pma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@RequestMapping("/new")
	public String displayProjectForm() {
		return "new-project";
	}
}
