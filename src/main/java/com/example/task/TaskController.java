package com.example.task;

import com.example.user.User;
import com.example.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {
    @Autowired private TaskService service;

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    @GetMapping("/tasks")
    public String showTasksList(Model model) {
        List<Task> listTasks = service.listAll();
        model.addAttribute("listTasks", listTasks);

        return "tasks";
    }

    @GetMapping("/user/{id}")
    public String showUserTasksList(@PathVariable("id") Integer id, Model model) {
        List<Task> listTasks = service.listUserAll(id);
        model.addAttribute("listTasks", listTasks);

        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String showNewForm(Model model){
        model.addAttribute("task" , new Task());

        return "task_form";
    }

    @PostMapping("/tasks/save")
    public String saveTask(Task task, RedirectAttributes ra, Model model, @RequestParam("files") MultipartFile[] files) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file: files){
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        service.save(task);
        ra.addFlashAttribute("message", "The task has been successfully created");
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Task task = service.get(id);
            model.addAttribute("task", task);
            //model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
            return "task_form";
        } catch (TaskNotFoundException e) {
            ra.addFlashAttribute("message", "The task has been saved successfully");
            return "redirect:/tasks";
        }
    }

    @GetMapping("/tasks/delete/{id}")
    public String showDeleteForm(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "task has been successfully deleted");
        } catch (TaskNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tasks";
    }
}
