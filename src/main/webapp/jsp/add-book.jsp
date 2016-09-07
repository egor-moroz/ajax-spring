<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <title>Add book</title>

		<script type="text/javascript">
			$(document).ready(function(){
		var frm = $('#authorForm');
		frm.submit(function (ev) {
			$.ajax({
				type: frm.attr('method'),
				url: frm.attr('action'),
				data: frm.serialize(),
				success: function (data) {
					/*$("#authors").remove();*/
					var options = '';
					for (var i = 0; i < data.length; i++){
						options += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
						drawRow(data[i]);
					}
					$("#authors").html(options);
					/*drawTable(data);*/
				}
			});

			ev.preventDefault();
		});
			});
	</script>
	<script>

		/*	function drawTable(data) {
				for (var i = 0; i < data.length; i++) {
					drawRow(data[i]);
				}
			}*/


		function drawRow(rowData) {
			var row = $("<tr />")
			$("#authorTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
			row.append($("<td>" + rowData.id + "</td>"));
			row.append($("<td>" +"<form action=\"/author/"+rowData.id+"\">"+"<table>"+"<tr>"+"<td>"+"<input type='text' id=\"gallery"+rowData.id+"\" value=\""+rowData.name+"\"/>"+"</td>"+"<td>"+"<button type='submit'>"+"submit"+"</button>"+"</td>"+"</tr>"+"</table>"+"</form>"+ "</td>"));
			row.append($("<td>"+"<a href=# class='btn btn-success custom-width' id='edit'>"+"edit"+"</a>"+"</td>"));
			row.append($("<td>"+"<a href=# class='btn btn-danger custom-width' id='delete'>"+"delete"+"</a>"+"</td>"));
			/*row.append($(""))*/
		}

	</script>
	<script>
	$(document).ready(function(){
		$("#addAuthor").hide();

	});
	</script>
	<script>
		$(document).ready(function(){
			$("#edit").click(function(){
				$("#addAuthor").show();
			});
		});
	</script>
	<script>
		$(document).ready(function(){
			$("#showAuthor").click(function(){
				$("#addAuthor").show();
			});
		});
	</script>
	<script>
		$(document).delegate('option', 'click', function(){
			//etc//event handler...
			var id = $(this).val();
			var text = $(this).text();
			$('#AuthorName:text').val(text);
			/*$('#showAuthor').html(id);*/
			$('#authorForm').get(0).setAttribute('action','${pageContext.request.contextPath}/author/'+id);
		});

	</script>
	<script>

	</script>
	<%--<script>
		$(document).ready(function(){
			$("button").click(function(){
				$.ajax({
					type:'post',
					url: "${pageContext.request.contextPath}/author", success: function(result){
					$("#div1").html(result);
				}});
			});
		});
	</script>--%>
</head>
<body>
<div class="form container">
	<form:form modelAttribute="book" action="${pageContext.request.contextPath}/book/${book.id}"
			   method="post" role="form" data-toggle="validator">

		<h2>Book</h2>

		<div class="row">
			<div class="form-group col-md-12">
				<label for="title" class="control-label col-xs-4">Title</label>
				<form:input path="title" type="text" name="title" id="title" class="form-control"
							value="${book.title}" required="true"/>
				<div class="has-error">
					<form:errors path="title" cssClass="help-inline"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label for="price" class="control-label col-xs-4">Price</label>
				<form:input path="price" type="text" name="price" id="price" class="form-control"
							value="${book.price}" required="true"/>
				<div class="has-error">
					<form:errors path="price" cssClass="help-inline"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label for="nbOfPage" class="control-label col-xs-4">Number Of Page</label>
				<form:input path="nbOfPage" type="text" name="nbOfPage" id="nbOfPage" class="form-control"
							value="${book.nbOfPage}" required="true"/>
				<div class="has-error">
					<form:errors path="nbOfPage" cssClass="help-inline"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label for="description" class="control-label col-xs-4">Description</label>
				<form:input path="description" type="text" name="description" id="description" class="form-control"
							value="${book.description}" required="true"/>
				<div class="has-error">
					<form:errors path="description" cssClass="help-inline"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="authors">Choose authors or add if not preset in list</label>
				<div class="col-md-7">
					<form:select path="authors"  multiple="true"
								  name="authors" id="authors" class="form-control input-sm" >
						<%--<form:option value="" label="--Choose A Author--"></form:option>--%>
						<form:options items="${authors}" itemValue="id" itemLabel="name"></form:options>
					</form:select>
					<div class="has-error">
						<form:errors path="authors" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<button type="submit" class="btn btn-primary btn-md">Accept</button>

	</form:form>




	<button  class="btn btn-primary btn-md" id="showAuthor">Add author</button>


	<div class="form container" id="addAuthor">
		<form:form modelAttribute="author" action="${pageContext.request.contextPath}/author"
			   id="authorForm"	method="post" role="form" data-toggle="validator">
			<div class="row">
				<div class="form-group col-md-12">
					<label for="name" class="control-label col-xs-4">Author name</label>
					<form:input path="name" type="text" name="name" id="AuthorName" class="form-control"
							value=""/>
					<div class="has-error">
						<form:errors path="name" cssClass="help-inline"/>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-md">Accept Author</button>
		</form:form>
	</div>

	<div class="panel-heading"><span class="lead">List of Authors </span></div>
	<table class="table table-hover" id="authorTable">
		<tr>
			<th>Num</th>
			<th>Name</th>
			<th width="100"></th>
			<th width="100"></th>
		</tr>

	</table>
</div>




<div id="div1"><h2>Let jQuery AJAX Change This Text</h2></div>

<button>Get External Content</button>
</body>
</html>
