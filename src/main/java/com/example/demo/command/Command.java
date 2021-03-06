package com.example.demo.command;

import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public interface Command {
       Router execute(HttpServletRequest request) throws CommandException; // TODO: 13.04.2022 return Router, inside
       default void refresh(){}
}
