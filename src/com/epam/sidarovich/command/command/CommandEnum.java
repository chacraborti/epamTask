package com.epam.sidarovich.command.command;

/**
 * Created by ilona on 25.03.15.
 */
public enum CommandEnum {
    LOGIN {{

        this.command = new LoginCommand();
    }
         },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        } },
    TOURS {
        {
           this.command = new ObserveToursCommand();
        }

    },
    LANGUAGE{
        {
        this.command = new SelectLanguageCommand();
    }
    },
    SIGNUP{
        {
            this.command = new GoToSignUpPageUpCommand();
        }
    },
    SUBMIT_SIGNUP{
        {
            this.command = new SubmitSignUpCommand();
        }
    },
    ORDER{
        {
            this.command = new MakeOrderCommand();
        }
    },
    DELETE_USER{
        {
            this.command = new DeleteUserCommand();
        }
    },
    MAKE_REGULAR{
        {
            this.command = new MakeRegularCommand();
        }
    },
    CREATE_TOUR{
        {
            this.command = new CreateTourCommand();
        }
    },
    SUBMIT_CREATE_TOUR{
        {
            this.command = new SubmitCreateTourCommand();
        }
    },
    DELETE_TOUR{
        {
            this.command = new DeleteTourCommand();
        }
    }
    ,
    VIEW_USERS{
        {
            this.command = new ObserveUsersCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
