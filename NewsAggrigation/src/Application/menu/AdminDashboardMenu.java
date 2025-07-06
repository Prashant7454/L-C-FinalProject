package Application.menu;

import Application.auth.login.LoginResponse;
import Application.command.MenuAction;
import Application.command.adminDashboard.*;
import Application.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDashboardMenu implements Menu{
    private final List<MenuAction> actions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final LoginResponse adminSession;

    public AdminDashboardMenu(LoginResponse adminSession) {
        this.adminSession = adminSession;

        actions.add(new ViewServerListAction());
        actions.add(new ViewServerDetailsAction());
        actions.add(new EditServerDetailsAction());
        actions.add(new ViewAllCategoriesAction());
        actions.add(new AddNewsCategoryAction());
        actions.add(new ManageCategoryVisibilityAction());
        actions.add(new ManageReportedNewsAction());
        actions.add(new AssignCategoriesAction());
        actions.add(new ManageNewsHidingAction());
        actions.add(new AdminLogoutAction());
    }

    @Override
    public void showMenu(LoginResponse response) {
        MenuUtil.showMenu(actions,response);
    }
}

