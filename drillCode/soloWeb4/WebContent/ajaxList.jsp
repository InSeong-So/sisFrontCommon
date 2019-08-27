<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

        <c:forEach items="${boardList }" var="board">
          <tr>
            <td>${board.seq_no }</td>
            <td><a href="count.do?WRITE_NO=${board.write_no}&WRITER=${board.writer}">${board.title }</a></td>
            <td>${board.content }</td>
            <td>${board.writer }</td>
            <td>${board.reg_date }</td>
            <td>${board.view_cnt }</td>
          </tr>
        </c:forEach>