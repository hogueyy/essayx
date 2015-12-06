<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.util.List" %>

<%@ page import="com.googlecode.objectify.*" %>
<%@ page import="java.util.Collections" %> 
<%@ page import="essayX.Extender" %> 
<%@ page import="essayX.Word" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>App Landing Page \ Mist - Responsive Multi-purpose HTML Template</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="HTML5 Template">
        <meta name="description" content="Mist � Multi-Purpose HTML Template">
        <meta name="author" content="zozothemes.com">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.ico">
        <!-- Font -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Arimo:300,400,700,400italic,700italic">
        <link href="http://fonts.googleapis.com/css?family=Oswald:400,300,700" rel="stylesheet" type="text/css">
        <!-- Font Awesome Icons -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/hover-dropdown-menu.css" rel="stylesheet">
        <!-- Icomoon Icons -->
        <link href="css/icons.css" rel="stylesheet">
        <!-- Revolution Slider -->
        <link href="css/revolution-slider.css" rel="stylesheet">
        <link href="rs-plugin/css/settings.css" rel="stylesheet">
        <!-- Animations -->
        <link href="css/animate.min.css" rel="stylesheet">
        <!-- Owl Carousel Slider -->
        <link href="css/owl/owl.carousel.css" rel="stylesheet">
        <link href="css/owl/owl.theme.css" rel="stylesheet">
        <link href="css/owl/owl.transitions.css" rel="stylesheet">
        <!-- PrettyPhoto Popup -->
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <!-- Custom Style -->
        <link href="css/style.css" rel="stylesheet">
       <link href="css/responsive.css" rel="stylesheet" />
        <!-- Color Scheme -->
        <link href="css/color.css" rel="stylesheet">
        <style> 
        	.highlighted { background-color: yellow; }
        	.underlined {text-decoration: underline;}
        	.word {display: inline; cursor: pointer;} 
        	.charCount {display: inline; }
        	.divider {height: 10%; margin-top: 3%;}
       	</style>
    </head>
<body>
<% 
    ObjectifyService.register(Extender.class);
	Key<Extender> key = Key.create(Extender.class, Long.parseLong(request.getParameter("id")));
    Extender ex = ObjectifyService.ofy().load().key(key).now();
    pageContext.setAttribute("old_count", ex.num_original_chars);
    pageContext.setAttribute("new_count", ex.num_extended_chars);
    pageContext.setAttribute("old_essay", ex.originalText.getValue());
%>
<div class="divider"></div>
<div class ="container"><a class="underlined text-left" href="/"><b>Home</b></a></div>
<div class="divider"></div>
<div class = "container">
	<div class= "col-md-6">
		<div class= "text-center"><h2>Original Essay</h2></div>
		<div class= "text-center">Character count: ${fn:escapeXml(old_count)}</div>
		${fn:escapeXml(old_essay)}
    </div>
    
	<div class= "col-md-6">
		<div class= "text-center"><h2>New Essay</h2></div>
		<div class= "text-center">Character count: <div id="count" class="charCount">${fn:escapeXml(new_count)}</div></div>
	    <%
	        int count = 0;
	        for(Word w : ex.taggedWords){
	        	pageContext.setAttribute("word_id", "word" + count);
	        	pageContext.setAttribute("word_orig", w.getOrigValue());
	        	pageContext.setAttribute("word_new", w.getNewValue());
	            if(w.getNewValue() == null ){ %> <div class="word"> ${fn:escapeXml(word_orig)}</div> <% }
	            else{ %> <div id= "${fn:escapeXml(word_id)}" class="highlighted word" onclick="changeToOrig('${fn:escapeXml(word_id)}', '${fn:escapeXml(word_orig)}', '${fn:escapeXml(word_new)}')"> ${fn:escapeXml(word_new)}</div> <%}
	            count += 1;
	        }
	    %>
    </div>
</div>

<script>
    function changeToOrig(id, origWord, newWord){
    	var charCount = Number(document.getElementById("count").innerHTML);
        document.getElementById(id).setAttribute("class", "underlined word");
        document.getElementById(id).setAttribute("onclick", "changeToNew('" + id + "', '" + newWord + "', '" + origWord + "')");
        document.getElementById(id).innerHTML = "";
        document.getElementById(id).innerHTML = origWord;
        document.getElementById("count").innerHTML = charCount - newWord.length + origWord.length;
    }
    
    function changeToNew(id, newWord, origWord){
    	var charCount = Number(document.getElementById("count").innerHTML);
        document.getElementById(id).setAttribute("class", "highlighted word");
        document.getElementById(id).setAttribute("onclick", "changeToOrig('" + id + "', '" + origWord + "', '" + newWord + "')");
        document.getElementById(id).innerHTML = "";
        document.getElementById(id).innerHTML = newWord;
        document.getElementById("count").innerHTML = charCount - origWord.length + newWord.length;
    }
</script>
</body>
</html>