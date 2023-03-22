"use strict";

const ads = (function () {
	const array = [];
	$(".ad").each(function () {
		array.push({
			id: $(this).attr("data-ad-id"),
			title: $(this).children(".ad-title").first().text(),
			description: $(this).children(".ad-description").first().text(),
			categories: getCategories($(this).find("#ad-categories").children(".ad-category"))
		});
	})
	return array;
})();

function getCategories(categories){
	const array = [];
	categories.each(function (){
		array.push(
			$(this).val()
		)
	})
	return array;
}

function hideAllAdDivs() {
	$(".ad").each(function () {
		$(this).addClass("d-none");
	})
}

function showAllAdDivs() {
	$(".ad").each(function () {
		$(this).removeClass("d-none");
	})
}

function searchAds(searchTerm, catValue) {
	if (searchTerm === "" && catValue[0] === "0") {
		showAllAdDivs();
		return;
	}
	hideAllAdDivs();
	for (let ad of ads) {
		console.log(ad.categories)
		if (ad.title.toLowerCase().includes(searchTerm)) {
			$(`[data-ad-id='${ad.id}']`).first().removeClass("d-none");
		}
		if (ad.description.toLowerCase().includes(searchTerm)) {
			$(`[data-ad-id='${ad.id}']`).first().removeClass("d-none");
		}
		for (let category of ad.categories){
			for (let value of catValue){
				if (value == category){
					$(`[data-ad-id='${ad.id}']`).first().removeClass("d-none");
				}
			}
		}
	}
}

$("#ad-category").on("change",function (){
	searchAds($("#ad-search").val().toLowerCase().trim(), $(this).val());

})


$("#ad-search").on("keyup", function (){
	searchAds($(this).val().toLowerCase().trim(), $(this).val());
});