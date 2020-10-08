var products;
var categoryId = 0;

function init() {
	//	event box 초기화
	sendReqToProducts(0, 0, function(){
		let availCount = document.querySelector(".pink");
		availCount.firstChild.data = products.count + "개";
	});
}

function ready() {
	// tab 클릭시 event box 초기화 
	let eventBox = document.querySelector(".wrap_event_box").children;
	eventBox[0].innerHTML = "";
	eventBox[1].innerHTML = "";
	
	// tab 클릭시 more button  초기화
	let moreBtn = document.querySelector(".more .btn");
	moreBtn.style.display = "";
}

document.addEventListener("DOMContentLoaded", function() {
	init();
	
	// tab 클릭 이벤트  
	let tabmenu = document.querySelector(".event_tab_lst");
	let clickCount = 1;
	tabmenu.addEventListener("click", function(evt) {
		if (evt.target.nodeName == "SPAN") {
			ready();	 
			clickCount = 1; // 더보기 버튼 동작하도록 초기화 
			
			categoryId = evt.target.parentNode.parentNode.dataset.category;
			
			// send ajax product & change available count
			sendReqToProducts(categoryId, 0, function(){
				let availCount = document.querySelector(".pink");
				availCount.firstChild.data = products.count + "개";
			});
			
			// change tab actvie 
			let tabActive = document.querySelector(".event_tab_lst .active");
			tabActive.className = "anchor";
			let tabActiveChanged = evt.target.parentNode;
			tabActiveChanged.className = "anchor active";
		}
	});

	// more btn
	let moreBtn = document.querySelector(".more .btn");
	
	moreBtn.addEventListener("click", function(evt) {
		if (clickCount < products.pageStartList.length) {
			sendReqToProducts(categoryId, products.pageStartList[clickCount]);
			clickCount += 1;
			if (clickCount == products.pageStartList.length) {
				moreBtn.style.display ="none";
			}
		}
	});
});

function sendReqToProducts(categoryId, start, cb) {
	start = typeof start !== 'undefined' ? start : '0';
	
	let http = new XMLHttpRequest();
	let params = 'categoryId=' + categoryId + '&start=' + start;
	let url = "/reservation/api/products";
	http.open("GET", url+"?"+params, true);
	
	http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");	// "application/json; charset=utf-8" 
	
	http.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			products = JSON.parse(this.response);
			console.log('product response:', categoryId, start, products);
			
			displayProducts(products);
			
			if (cb) cb()
		} else {
//	    	console.error(http.responseText);
	    }
	}
	http.send();
};


function displayProducts(products) {
	let length = products.list.length;
	let resultHtml = "";
	let eventBox = document.querySelector(".wrap_event_box").children;
	
	let ulSwitch = 0;
	for (let i = 0; i < length; i++) {
		let id = products.list[i].displayInfoId;
		let description = products.list[i].productDescription;
		let placeName = products.list[i].placeName;
		let content = products.list[i].productContent;
		let productImageUrl = products.list[i].productImageUrl;
		resultHtml = `<li class="item">
		    <a href="detail.html?id=${id}" class="item_book">
		        <div class="item_preview">
		            <img alt="${description}" class="img_thumb" src="./${productImageUrl}">
		            <span class="img_border"></span>
		        </div>
		        <div class="event_txt">
		            <h4 class="event_txt_tit"> <span>${description}</span> <small class="sm">${placeName}</small> </h4>
		            <p class="event_txt_dsc">${content}</p>
		        </div>
		    </a>
		</li>`
			
		if (ulSwitch == 0) {
			eventBox[0].innerHTML += resultHtml;
			ulSwitch = 1;
		} else if (ulSwitch == 1) {
			eventBox[1].innerHTML += resultHtml;
			ulSwitch = 0;
		}
	}
};

