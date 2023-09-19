package com.project.clone_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine engine;

    String build(String message){
        Context context = new Context();
        context.setVariable("message",message);
        return engine.process("mailTemplate",context);
    }
}
