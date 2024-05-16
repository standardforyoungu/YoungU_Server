package com.youngustandard.youngu_server.Config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Spring AOP는 Run Time에 Weabing 해줌
@Target(ElementType.METHOD)
public @interface AuthorizeCheck {
}
