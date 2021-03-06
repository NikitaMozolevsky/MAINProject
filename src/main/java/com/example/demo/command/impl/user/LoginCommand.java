package com.example.demo.command.impl.user;

import com.example.demo.command.Command;
import com.example.demo.command.attribute.PagePath;
import com.example.demo.command.Router;
import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.entity.user.User;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.DaoException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.Optional;

import static com.example.demo.command.attribute.DefaultAttribute.LOGIN_MSG;
import static com.example.demo.command.attribute.OrderAttribute.ORDER_EXIST;
import static com.example.demo.command.attribute.PagePath.INDEX;
import static com.example.demo.command.attribute.UserAttribute.*;

public class LoginCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        Optional<User> optionalUser = Optional.empty();
        String login = request.getParameter(LOGIN); // TODO: 18.04.2022 add to separate class 2 scr.
        String password = request.getParameter(PASSWORD);
        password = PasswordEncryptor.passwordEncryption(password);
        UserService userService = UserServiceImpl.getInstance();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        /*String page;*/
        HttpSession session = request.getSession();
        try {
            optionalUser = userService.authenticate(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String userName = user.getUserName();
                /*String accessLevel = request.getParameter(ACCESS_LEVEL);*/
                String userId = String.valueOf(user.getUserId());
                session.setAttribute(LOGIN, login);
                session.setAttribute(USER_NAME, userName);
                session.setAttribute(USER_ID, userId);
                boolean orderForUserNotExist = orderDao.isOrderForUserIsNotExist(userId);
                session.setAttribute(ORDER_EXIST, orderForUserNotExist);
                /*session.setAttribute(PASSWORD, password);*/
                /*session.setAttribute(ACCESS_LEVEL, accessLevel);*/
                Enumeration<String> s = session.getAttributeNames();//access level access
                router.setPage(PagePath.MAIN_PAGE);
                /*page = PAGES_MAIN_JSP;*/
            } else {
                request.setAttribute(LOGIN_MSG, "incorrect login or pass");
                router.setPage(INDEX);
            }
            /*session.setAttribute(CURRENT_PAGE, page);*/
        } catch (ServiceException e) {
            throw new CommandException("Command exception", e); // TODO: 18.04.2022
        } catch (DaoException e) {
            throw new CommandException();
        }
        return router; // TODO: 01.04.2022 Validation (in UserServiceImpl), implement Router
    }
}