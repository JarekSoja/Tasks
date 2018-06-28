package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    private final AdminConfig adminConfig;

    private final TemplateEngine templateEngine;

    @Autowired
    public MailCreatorService(AdminConfig adminConfig, @Qualifier("templateEngine") TemplateEngine templateEngine) {
        this.adminConfig = adminConfig;
        this.templateEngine = templateEngine;
    }

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://jareksoja.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("welcome_message", "Hello and welcome!");
        context.setVariable("goodbye_message", "Good bye!");
        context.setVariable("company_name", adminConfig.getCompanyName());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
