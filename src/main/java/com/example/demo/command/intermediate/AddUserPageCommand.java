package com.example.demo.command.intermediate;

import com.example.demo.command.Command;
import com.example.demo.command.constant.PagePath;
import com.example.demo.command.Router;
import com.example.demo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AddUserPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.ADD_USER);
    }
}
