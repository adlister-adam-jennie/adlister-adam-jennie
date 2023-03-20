"use strict";

const ads = (function () {
	const array = [];
	$(".ad").each(function () {
		array.push({
			id: $(this).attr("data-ad-id"),
			title: $(this).children(".ad-title").first().text(),
			description: $(this).children(".ad-description").first().text()
		});
	})
	return array;
})();

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

function searchAds(input) {
	if (input === "") {
		showAllAdDivs();
		return;
	}
	hideAllAdDivs();
	for (let ad of ads) {
		if (ad.title.toLowerCase().includes(input)) {
			$(`[data-ad-id='${ad.id}']`).first().removeClass("d-none");
		}
		if (ad.description.toLowerCase().includes(input)) {
			$(`[data-ad-id='${ad.id}']`).first().removeClass("d-none");
		}
	}
}

$("#ad-search").on("keyup", function (){
	searchAds($(this).val().toLowerCase().trim());
});