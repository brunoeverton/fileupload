package br.com.hotmart.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class IndexController {

	/**
	 * Pagina principal da aplicacao
	 * @return
	 */
    @GetMapping("/")
    public String index() {
        return "fileupload";
    }

}