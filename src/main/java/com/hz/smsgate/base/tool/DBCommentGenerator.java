package com.hz.smsgate.base.tool;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

public class DBCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn) + "<br>");
        field.addJavaDocLine(" * " + (introspectedColumn.getRemarks() != null ? introspectedColumn.getRemarks() : ""));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn) + "<br>");
        if (introspectedColumn.getRemarks() != null) {
            method.addJavaDocLine(" * 获得 " + introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn) + "<br>");
        if (introspectedColumn.getRemarks() != null) {
            method.addJavaDocLine(" * 设置 " + introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(" */");
    }

    private String getColumnDbTypeInfo(IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJdbcTypeName());
        if (introspectedColumn.getLength() > 0) {
            sb.append("(");
            sb.append(introspectedColumn.getLength());
            if (introspectedColumn.getScale() > 0) {
                sb.append(",").append(introspectedColumn.getScale());
            }
            sb.append(")");
        }
        if (introspectedColumn.getDefaultValue() != null) {
            sb.append(" 默认值[" + introspectedColumn.getDefaultValue() + "]");
        }
        if (!introspectedColumn.isNullable()) {
            sb.append(" 必填");
        }
        return sb.toString();
    }
}

