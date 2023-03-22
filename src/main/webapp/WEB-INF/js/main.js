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

function getCategories(categories) {
	const array = [];
	categories.each(function () {
		array.push(
			$(this).val().toString()
		)
	})
	return array;
}

function hideAllAdDivs() {
	$(".ad").each(function () {
		$(this).addClass("d-none");
	})
}

function showAdDivs(ads) {
	$(".ad").each(function () {
		for (const ad of ads) {
			if ($(this).attr("data-ad-id") === ad.id) {
				$(this).removeClass("d-none");
			}
		}
	})
}

function searchAds(searchTerm) {
	if (searchTerm === "") {
		return ads;
	}
	const resultsArr = [];
	for (let ad of ads) {
		if (ad.title.toLowerCase().includes(searchTerm)) {
			resultsArr.push(ad);
			continue;
		}
		if (ad.description.toLowerCase().includes(searchTerm)) {
			resultsArr.push(ad);
		}
	}
	return resultsArr;
}

function filterAdsByCategory(ads, categoriesFilter) {
	if (categoriesFilter.includes("0")) {
		return ads;
	}
	const resultsArr = [];
	for (const ad of ads) {
		for (const category of ad.categories) {
			if (categoriesFilter.includes(category)) {
				resultsArr.push(ad);
			}
		}
	}
	return resultsArr;
}

$("#ad-search,#ad-category").each(function () {
	$(this).on("keyup change", function () {
		hideAllAdDivs();
		const searchResult = searchAds($("#ad-search").val().toLowerCase().trim());
		const filterResult = filterAdsByCategory(searchResult, $("#ad-category").val());
		showAdDivs(filterResult);
	})
});