package com.trustrace.redditClone_backEnd.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class MainContentBuilder {

    private final TemplateEngine templateEngine;

    String builder(String message){
        Context context = new Context();
        context.setVariable("message",message);
        return templateEngine.process("mailTemplate",context);
    }
}
