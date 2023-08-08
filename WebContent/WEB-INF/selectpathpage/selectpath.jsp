<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
</head>
<body>
	<h1>1번 경로</h1>
	<img src="data:image/jpeg;base64,${viewPaths[0].location[0].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[0].location[0].locationImgStr}" alt="Image">
	<p>${viewPaths[0].location[0].locationNo}</p>
	<p>${viewPaths[0].location[0].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[0].location[1].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[0].location[1].locationImgStr}" alt="Image">
	<p>${viewPaths[0].location[1].locationNo}</p>
	<p>${viewPaths[0].location[1].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[0].location[2].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[0].location[2].locationImgStr}" alt="Image">
	<p>${viewPaths[0].location[2].locationNo}</p>
	<p>${viewPaths[0].location[2].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[0].location[3].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[0].location[3].locationImgStr}" alt="Image">
	<p>${viewPaths[0].location[3].locationNo}</p>
	<p>${viewPaths[0].location[3].locationName}</p>
	
	
	<h1>2번 경로</h1>
	<img src="data:image/jpeg;base64,${viewPaths[1].location[0].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[1].location[0].locationImgStr}" alt="Image">
	<p>${viewPaths[1].location[0].locationNo}</p>
	<p>${viewPaths[1].location[0].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[1].location[1].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[1].location[1].locationImgStr}" alt="Image">
	<p>${viewPaths[1].location[1].locationNo}</p>
	<p>${viewPaths[1].location[1].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[1].location[2].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[1].location[2].locationImgStr}" alt="Image">
	<p>${viewPaths[1].location[2].locationNo}</p>
	<p>${viewPaths[1].location[2].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[1].location[3].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[1].location[3].locationImgStr}" alt="Image">
	<p>${viewPaths[1].location[3].locationNo}</p>
	<p>${viewPaths[1].location[3].locationName}</p>

	<h1>3번 경로</h1>
	<img src="data:image/jpeg;base64,${viewPaths[2].location[0].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[2].location[0].locationImgStr}" alt="Image">
	<p>${viewPaths[2].location[0].locationNo}</p>
	<p>${viewPaths[2].location[0].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[2].location[1].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[2].location[1].locationImgStr}" alt="Image">
	<p>${viewPaths[2].location[1].locationNo}</p>
	<p>${viewPaths[2].location[1].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[2].location[2].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[2].location[2].locationImgStr}" alt="Image">
	<p>${viewPaths[2].location[2].locationNo}</p>
	<p>${viewPaths[2].location[2].locationName}</p>
	
	<img src="data:image/jpeg;base64,${viewPaths[2].location[3].posterImgStr}" alt="Image">
	<img src="data:image/jpeg;base64,${viewPaths[2].location[3].locationImgStr}" alt="Image">
	<p>${viewPaths[2].location[3].locationNo}</p>
	<p>${viewPaths[2].location[3].locationName}</p>
	
</body>
</html>
