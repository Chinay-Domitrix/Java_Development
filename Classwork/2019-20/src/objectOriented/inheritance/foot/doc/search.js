/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

const noResult = {l: "No results found"};
const catModules = "Modules";
const catPackages = "Packages";
const catTypes = "Types";
const catMembers = "Members";
const catSearchTags = "SearchTags";
const highlight = "<span class=\"resultHighlight\">$&</span>";
let searchPattern = "";
const RANKING_THRESHOLD = 2;
const NO_MATCH = 0xffff;
const MAX_RESULTS_PER_CATEGORY = 500;

function escapeHtml(str) {
	return str.replace(/</g, "&lt;").replace(/>/g, "&gt;");
}

function getHighlightedText(item, matcher) {
	const escapedItem = escapeHtml(item);
	return escapedItem.replace(matcher, highlight);
}

function getURLPrefix(ui) {
	let urlPrefix = "";
	const slash = "/";
	if (ui.item.category === catModules) {
		return ui.item.l + slash;
	} else if (ui.item.category === catPackages && ui.item.m) {
		return ui.item.m + slash;
	} else if ((ui.item.category === catTypes && ui.item.p) || ui.item.category === catMembers) {
		$.each(packageSearchIndex, function (index, item) {
			if (item.m && ui.item.p == item.l) {
				urlPrefix = item.m + slash;
			}
		});
		return urlPrefix;
	} else {
		return urlPrefix;
	}
	return urlPrefix;
}

function makeCamelCaseRegex(term) {
	let pattern = "";
	let isWordToken = false;
	term.replace(/,\s*/g, ", ").trim().split(/\s+/).forEach(function (w, index) {
		if (index > 0) {
			// whitespace between identifiers is significant
			pattern += (isWordToken && /^\w/.test(w)) ? "\\s+" : "\\s*";
		}
		const tokens = w.split(/(?=[A-Z,.()<>[\/])/);
		for (let i = 0; i < tokens.length; i++) {
			const s = tokens[i];
			if (s === "") {
				continue;
			}
			pattern += $.ui.autocomplete.escapeRegex(s);
			isWordToken = /\w$/.test(s);
			if (isWordToken) {
				pattern += "([a-z0-9_$<>\\[\\]]*?)";
			}
		}
	});
	return pattern;
}

function createMatcher(pattern, flags) {
	const isCamelCase = /[A-Z]/.test(pattern);
	return new RegExp(pattern, flags + (isCamelCase ? "" : "i"));
}

const watermark = 'Search';
$(function () {
	$("#search").val('');
	$("#search").prop("disabled", false);
	$("#reset").prop("disabled", false);
	$("#search").val(watermark).addClass('watermark');
	$("#search").blur(function () {
		if ($(this).val().length == 0) {
			$(this).val(watermark).addClass('watermark');
		}
	});
	$("#search").on('click keydown paste', function () {
		if ($(this).val() == watermark) {
			$(this).val('').removeClass('watermark');
		}
	});
	$("#reset").click(function () {
		$("#search").val('');
		$("#search").focus();
	});
	$("#search").focus();
	$("#search")[0].setSelectionRange(0, 0);
});
$.widget("custom.catcomplete", $.ui.autocomplete, {
	_create: function () {
		this._super();
		this.widget().menu("option", "items", "> :not(.ui-autocomplete-category)");
	},
	_renderMenu: function (ul, items) {
		const rMenu = this;
		let currentCategory = "";
		rMenu.menu.bindings = $();
		$.each(items, function (index, item) {
			let li;
			if (item.l !== noResult.l && item.category !== currentCategory) {
				ul.append("<li class=\"ui-autocomplete-category\">" + item.category + "</li>");
				currentCategory = item.category;
			}
			li = rMenu._renderItemData(ul, item);
			if (item.category) {
				li.attr("aria-label", item.category + " : " + item.l);
				li.attr("class", "resultItem");
			} else {
				li.attr("aria-label", item.l);
				li.attr("class", "resultItem");
			}
		});
	},
	_renderItem: function (ul, item) {
		let label = "";
		const matcher = createMatcher(escapeHtml(searchPattern), "g");
		if (item.category === catModules) {
			label = getHighlightedText(item.l, matcher);
		} else if (item.category === catPackages) {
			label = (item.m)
				? getHighlightedText(item.m + "/" + item.l, matcher)
				: getHighlightedText(item.l, matcher);
		} else if (item.category === catTypes) {
			label = (item.p)
				? getHighlightedText(item.p + "." + item.l, matcher)
				: getHighlightedText(item.l, matcher);
		} else if (item.category === catMembers) {
			label = getHighlightedText(item.p + "." + (item.c + "." + item.l), matcher);
		} else if (item.category === catSearchTags) {
			label = getHighlightedText(item.l, matcher);
		} else {
			label = item.l;
		}
		const li = $("<li></li>").appendTo(ul);
		const div = $("<div></div>").appendTo(li);
		if (item.category === catSearchTags) {
			if (item.d) {
				div.html(label + "<span class=\"searchTagHolderResult\"> (" + item.h + ")</span><br><span class=\"searchTagDescResult\">"
					+ item.d + "</span><br>");
			} else {
				div.html(label + "<span class=\"searchTagHolderResult\"> (" + item.h + ")</span>");
			}
		} else {
			div.html(label);
		}
		return li;
	}
});

function rankMatch(match, category) {
	if (!match) {
		return NO_MATCH;
	}
	const index = match.index;
	const input = match.input;
	let leftBoundaryMatch = 2;
	let periferalMatch = 0;
	let delta = 0;
	// make sure match is anchored on a left word boundary
	if (index === 0 || /\W/.test(input[index - 1]) || "_" === input[index - 1] || "_" === input[index]) {
		leftBoundaryMatch = 0;
	} else if (input[index] === input[index].toUpperCase() && !/^[A-Z0-9_$]+$/.test(input)) {
		leftBoundaryMatch = 1;
	}
	const matchEnd = index + match[0].length;
	const leftParen = input.indexOf("(");
	// exclude peripheral matches
	if (category !== catModules && category !== catSearchTags) {
		const endOfName = leftParen > -1 ? leftParen : input.length;
		const delim = category === catPackages ? "/" : ".";
		if (leftParen > -1 && leftParen < index) {
			periferalMatch += 2;
		} else if (input.lastIndexOf(delim, endOfName) >= matchEnd) {
			periferalMatch += 2;
		}
	}
	for (let i = 1; i < match.length; i++) {
		// lower ranking if parts of the name are missing
		if (match[i])
			delta += match[i].length;
	}
	if (category === catTypes) {
		// lower ranking if a type name contains unmatched camel-case parts
		if (/[A-Z]/.test(input.substring(matchEnd)))
			delta += 5;
		if (/[A-Z]/.test(input.substring(0, index)))
			delta += 5;
	}
	return leftBoundaryMatch + periferalMatch + (delta / 200);

}

$(function () {
	$("#search").catcomplete({
		minLength: 1,
		delay: 300,
		source: function (request, response) {
			let result = [];
			const newResults = [];

			searchPattern = makeCamelCaseRegex(request.term);
			if (searchPattern === "") {
				return this.close();
			}
			const camelCaseMatcher = createMatcher(searchPattern, "");
			const boundaryMatcher = createMatcher("\\b" + searchPattern, "");

			function concatResults(a1, a2) {
				a2.sort(function (e1, e2) {
					return e1.ranking - e2.ranking;
				});
				a1 = a1.concat(a2.map(function (e) {
					return e.item;
				}));
				a2.length = 0;
				return a1;
			}

			if (moduleSearchIndex) {
				$.each(moduleSearchIndex, function (index, item) {
					item.category = catModules;
					const ranking = rankMatch(boundaryMatcher.exec(item.l), catModules);
					if (ranking < RANKING_THRESHOLD) {
						newResults.push({ranking: ranking, item: item});
					}
					return newResults.length < MAX_RESULTS_PER_CATEGORY;
				});
				result = concatResults(result, newResults);
			}
			if (packageSearchIndex) {
				$.each(packageSearchIndex, function (index, item) {
					item.category = catPackages;
					const name = (item.m && request.term.indexOf("/") > -1)
						? (item.m + "/" + item.l)
						: item.l;
					const ranking = rankMatch(boundaryMatcher.exec(name), catPackages);
					if (ranking < RANKING_THRESHOLD) {
						newResults.push({ranking: ranking, item: item});
					}
					return newResults.length < MAX_RESULTS_PER_CATEGORY;
				});
				result = concatResults(result, newResults);
			}
			if (typeSearchIndex) {
				$.each(typeSearchIndex, function (index, item) {
					item.category = catTypes;
					const name = request.term.indexOf(".") > -1
						? item.p + "." + item.l
						: item.l;
					const ranking = rankMatch(camelCaseMatcher.exec(name), catTypes);
					if (ranking < RANKING_THRESHOLD) {
						newResults.push({ranking: ranking, item: item});
					}
					return newResults.length < MAX_RESULTS_PER_CATEGORY;
				});
				result = concatResults(result, newResults);
			}
			if (memberSearchIndex) {
				$.each(memberSearchIndex, function (index, item) {
					item.category = catMembers;
					const name = request.term.indexOf(".") > -1
						? item.p + "." + item.c + "." + item.l
						: item.l;
					const ranking = rankMatch(camelCaseMatcher.exec(name), catMembers);
					if (ranking < RANKING_THRESHOLD) {
						newResults.push({ranking: ranking, item: item});
					}
					return newResults.length < MAX_RESULTS_PER_CATEGORY;
				});
				result = concatResults(result, newResults);
			}
			if (tagSearchIndex) {
				$.each(tagSearchIndex, function (index, item) {
					item.category = catSearchTags;
					const ranking = rankMatch(boundaryMatcher.exec(item.l), catSearchTags);
					if (ranking < RANKING_THRESHOLD) {
						newResults.push({ranking: ranking, item: item});
					}
					return newResults.length < MAX_RESULTS_PER_CATEGORY;
				});
				result = concatResults(result, newResults);
			}
			response(result);
		},
		response: function (event, ui) {
			if (!ui.content.length) {
				ui.content.push();
			} else {
				$("#search").empty();
			}
		},
		autoFocus: true,
		position: {
			collision: "flip"
		},
		select: function (event, ui) {
			if (ui.item.l !== noResult.l) {
				let url = getURLPrefix(ui);
				if (ui.item.category === catModules) {
					url += "module-summary.html";
				} else if (ui.item.category === catPackages) {
					if (ui.item.url) {
						url = ui.item.url;
					} else {
						url += ui.item.l.replace(/\./g, '/') + "/package-summary.html";
					}
				} else if (ui.item.category === catTypes) {
					if (ui.item.url) {
						url = ui.item.url;
					} else if (ui.item.p === "<Unnamed>") {
						url += ui.item.l + ".html";
					} else {
						url += ui.item.p.replace(/\./g, '/') + "/" + ui.item.l + ".html";
					}
				} else if (ui.item.category === catMembers) {
					if (ui.item.p === "<Unnamed>") {
						url += ui.item.c + ".html" + "#";
					} else {
						url += ui.item.p.replace(/\./g, '/') + "/" + ui.item.c + ".html" + "#";
					}
					if (ui.item.url) {
						url += ui.item.url;
					} else {
						url += ui.item.l;
					}
				} else if (ui.item.category === catSearchTags) {
					url += ui.item.u;
				}
				if (top !== window) {
					parent.classFrame.location = pathtoroot + url;
				} else {
					window.location.href = pathtoroot + url;
				}
				$("#search").focus();
			}
		}
	});
});
