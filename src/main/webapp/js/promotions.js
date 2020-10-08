document.addEventListener("DOMContentLoaded", function () {
  console.log("Dom Loaded");
  sendReqToPromotion();
 
});



//  ----- promotions ------

var promotions = "";

function sendReqToPromotion(e) {

  var xmlHttp = new XMLHttpRequest();
  var url = "/reservation/api/promotions";
  xmlHttp.open("GET", url, true);
  xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");	// application/json application/x-www-form-urlencoded

  xmlHttp.send();

  xmlHttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      promotions = JSON.parse(this.response);
      console.log('promotions response: ', promotions);
      displayPromotions(promotions);
    } else {
//    	console.error(xmlHttp.responseText);
    }
  }
};

function displayPromotions(promotions) {
	var length = promotions.list.length;
	let resultHtml ="";
	for (var i=0; i < length; i++) {
		var productImageUrl = promotions.list[i].productImageUrl;
		resultHtml += 
			`<li class="item" style="background-image: url(./${productImageUrl});"> 
	    	  <a href="#"> 
	    	  	<span class="img_btm_border"></span> 
	    	  	<span class="img_right_border"></span> 
	    	  	<span class="img_bg_gra"></span> 
	    	    <div class="event_txt"> 
	    	      <h4 class="event_txt_tit"></h4> 
	    	      <p class="event_txt_adr"></p> 
	    	      <p class="event_txt_dsc"></p> 
	    		</div> 
	    	</a> 
	    	</li>`;
	}
//	console.log(resultHtml);
	document.querySelector(".visual_img").innerHTML = resultHtml;
	
	move(length);
}

function move(length){
	var slider = document.querySelector('.visual_img');
	
    var firstItemClone = slider.firstElementChild.cloneNode(true);
    
    slider.appendChild(firstItemClone);
    
    var curIndex = 0;

    console.log("!!!!", length)
    setInterval(function(){
    	slider.style.transition = '0.3s';
    	slider.style.transform = "translate3d(-"+414*(curIndex+1)+"px, 0px, 0px)";

        curIndex++;
//        console.log(curIndex);
        
        if(curIndex == length){
        	
            setTimeout(function(){
            	slider.style.transition = '0s';
            	slider.style.transform = "translate3d(0px, 0px, 0px)";
            },201)
            curIndex = 0;
        }

    },1000);
}