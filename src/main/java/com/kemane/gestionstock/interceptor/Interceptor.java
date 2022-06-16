package com.kemane.gestionstock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        System.out.println("here");
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
            if (sql.contains("where")){
                sql=sql + " and idEntreprise = 1";
            }else{
                sql=sql+" where idEntreprise = 1";
            }
        }
        return super.onPrepareStatement(sql);
    }
}
