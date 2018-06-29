package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    private final AdminConfig adminConfig;

    private final TemplateEngine templateEngine;

    private final DbService dbService;

    @Autowired
    public MailCreatorService(AdminConfig adminConfig, TemplateEngine templateEngine, DbService dbService) {
        this.adminConfig = adminConfig;
        this.templateEngine = templateEngine;
        this.dbService = dbService;
    }


    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("Managing tasks");
        functionality.add("Connection with Trello");
        functionality.add("Sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://jareksoja.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("welcome_message", "Hello and welcome!");
        context.setVariable("goodbye_message", "Good bye!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyTaskCounterEmail(String message) {
        List<Task> currentTasks = dbService.getAllTasks();

        Context context = new Context();
        context.setVariable("admin_config", adminConfig);
        context.setVariable("welcome_message", "Hello and welcome!");
        context.setVariable("message", "There are some tasks waiting for your attention");
        context.setVariable("is_friend", true);
        context.setVariable("tasks_url", "http://jareksoja.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("current_tasks", currentTasks);

        return templateEngine.process("mail/daily-info-mail", context);
    }
}
