<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/header.jsp">
 <c:param name="title" value="HomePage"></c:param>
</c:import>

<h1>Upload your article</h1>
<div class="hero-unit " id="container">
 <form class="form" action="../UploadArticle" method="post"enctype="multipart/form-data">
 
  <legend>Form For Submitting Article</legend>
   <div>
  <div style="width:200px;display:inline-block"><b>Firstname:</b> </div>
  <div class="input-prepend" style="width:250px;display:inline-block">
  <span class="add-on"><i class="icon-user"></i></span> 
   <input type="text" size="30" name="firstname" value="" placeholder="" />
  </div>
 </div>
    <div>
  <div style="width:200px;display:inline-block"><b>Lastname:</b> </div>
  <div class="input-prepend" style="width:250px;display:inline-block">
  <span class="add-on"><i class="icon-user"></i></span> 
   <input type="text" size="30" name="lastname" value="" placeholder="Lastname" />
  </div>
 </div>
       <div>
  <div style="width:200px;display:inline-block"><b>Email:</b> </div>
  <div class="input-prepend" style="width:250px;display:inline-block">
  <span class="add-on"><i class="icon-envelope"></i></span> 
   <input type="text" size="30" name="email" value="" placeholder="Email" />
  </div>
 </div>
      <div id="author" onclick="addAuthor()"><span id="author"
       class="add-on"><b>Add more author</b><i class="icon-plus"></i></span>
     </div>
    
       <div class="authors" style="position: fixed, top:30px, right:5px">
        <input type="text" id="hiddenc" name="numberOfAuthors"
         style="visibility: hidden" value="0">
       </div>
       
          <div>
  <div style="width:200px;display:inline-block"><b>Title:</b> </div>
  <div class="input-prepend" style="width:250px;display:inline-block">
  <span class="add-on"><i class="icon-pencil"></i></span> 
   <input type="text" size="30" name="title" value="" placeholder="Title" />
  </div>
 </div>
        <div>
  <div style="width:200px;display:inline-block"><b>Abstract:</b> </div>
  <div style="width:250px;display:inline-block"> 
   <textarea rows="5" cols="25" name="firstname"  placeholder="Enter Maximum 10 keywords seperated by comma" ></textarea>
  </div>
 </div>
     
          <div>
  <div style="width:200px;display:inline-block"><b>Keywords:</b> </div>
  <div class="input-prepend" style="width:250px;display:inline-block">
  <span class="add-on"><i class="icon-pencil"></i></span> 
   <input type="text" size="30" name="keywords" value="" placeholder="Enter Maximum 10 keywords seperated by comma" />
  </div>
 </div>
 <div>
 
  <div style="width:200px;display:inline-block"><b>Upload your article:</b> </div>
  <div  style="width:250px;display:inline-block">
   <input size="30" type="file" name="file"/>
  </div>
     <br>
     <br>
    <div><button class="btn btn-primary btn-large" type="submit" >Upload</button></div>
   </div>
 </form>
</div>
<c:import url="/footer.jsp"></c:import>