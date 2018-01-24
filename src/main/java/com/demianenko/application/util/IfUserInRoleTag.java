package com.demianenko.application.util;

import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IfUserInRoleTag extends TagSupport {

    private String role;

    @Override
    public int doStartTag() throws JspException {

        User user = (User) pageContext.getSession().getAttribute(SessionParameters.USER);
        if("none".equals(role)){
            if(user == null)return EVAL_BODY_INCLUDE;
            else return SKIP_BODY;
        }
        if("user".equals(role)){
            if(user == null)return SKIP_BODY;
            else return EVAL_BODY_INCLUDE;
        }
        if("admin".equals(role)){
            if(user == null || user.getRole().equals(Role.USER))return SKIP_BODY;
            else return EVAL_BODY_INCLUDE;
        }
        return EVAL_BODY_INCLUDE;
    }

    public void setRole(String role) {
        this.role = role;
    }
}