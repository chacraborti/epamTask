package com.epam.sidarovich.command;

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
        }},
    TOURS {
        {
            this.command = new ObserveToursCommand();
        }

    },
    LANGUAGE {
        {
            this.command = new SelectLanguageCommand();
        }
    },
    SUBMIT_SIGNUP {
        {
            this.command = new SignUpCommand();
        }
    },
    ORDER {
        {
            this.command = new MakeOrderCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    MAKE_REGULAR {
        {
            this.command = new MakeRegularCommand();
        }
    },
    SUBMIT_CREATE_TOUR {
        {
            this.command = new CreateTourCommand();
        }
    },
    DELETE_TOUR {
        {
            this.command = new DeleteTourCommand();
        }
    },
    VIEW_USERS {
        {
            this.command = new ObserveUsersCommand();
        }
    },
    CANCEL_ORDER {
        {
            this.command = new CancelOrderCommand();
        }
    },
    PAY_ORDER {
        {
            this.command = new PayOrderCommand();
        }
    },
    USER_DISCOUNT {
        {
            this.command = new SetUserDiscountCommand();
        }
    },
    VIEW_TOURS {
        {
            this.command = new ObserveToursCommand();
        }
    },
    VIEW_ORDERS {
        {
            this.command = new ObserveOrdersCommand();
        }
    },

    UPDATE_TOUR {
        {
            this.command = new UpdateTourCommand();
        }
    },
    VIEW_PAID_ORDERS {
        {
            this.command = new ObservePaidOrdersCommand();
        }
    },
    TO_GO_TO {
        {
            this.command = new ToGoToCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
