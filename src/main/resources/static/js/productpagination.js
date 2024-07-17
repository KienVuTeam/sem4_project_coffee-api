/** 
 * @customize Vinh
*/

(function($) {
	$.fn.easyPaginate = function(options) {
		var defaults = {
			paginateElement: 'li',
			hashPage: 'page',
			elementsPerPage: 10,
			effect: 'default',
			slideOffset: 200,
			firstButton: true,
			firstButtonText: '<i class="fa-solid fa-angles-left"></i>',
			lastButton: true,
			lastButtonText: '<i class="fa-solid fa-angles-right"></i>',
			prevButton: true,
			prevButtonText: '<',
			nextButton: true,
			nextButtonText: '>'
		}

		return this.each(function(instance) {

			var plugin = {};
			plugin.el = $(this);
			plugin.el.addClass('easyPaginateList');

			plugin.settings = {
				pages: 0,
				objElements: Object,
				currentPage: 1
			}

			var getNbOfPages = function() {
				return Math.ceil(plugin.settings.objElements.length / plugin.settings.elementsPerPage);
			};

			var displayNav = function() {
				htmlNav = '<ul class="easyPaginateNav">';

				if (plugin.settings.firstButton) {
					htmlNav += '<li><a href="#' + plugin.settings.hashPage + ':1" title="First page" rel="1" class="first">' + plugin.settings.firstButtonText + '</a></li>';
				}

				if (plugin.settings.prevButton) {
					htmlNav += '<li><a href="" title="Previous" rel="" class="prev">' + plugin.settings.prevButtonText + '</a></li>';
				}

				for (i = 1; i <= plugin.settings.pages; i++) {
					htmlNav += '<li><a href="#' + plugin.settings.hashPage + ':' + i + '" title="Page ' + i + '" rel="' + i + '" class="page">' + i + '</a></li>';
				};

				if (plugin.settings.nextButton) {
					htmlNav += '<li><a href="" title="Next" rel="" class="next">' + plugin.settings.nextButtonText + '</a></li>';
				}

				if (plugin.settings.lastButton) {
					htmlNav += '<li><a href="#' + plugin.settings.hashPage + ':' + plugin.settings.pages + '" title="Last page" rel="' + plugin.settings.pages + '" class="last">' + plugin.settings.lastButtonText + '</a></li>';
				}

				htmlNav += '</ul>';
				plugin.nav = $(htmlNav);
				plugin.nav.css({
					'width': plugin.el.width()
				});
				$("#paginationProduct").html(plugin.nav);
				var elSelector = '#' + "paginationProduct";
				$(elSelector + ' .easyPaginateNav li a.page,'
					+ elSelector + ' .easyPaginateNav li a.first,'
					+ elSelector + ' .easyPaginateNav li a.last').on('click', function(e) {
						$('html , body').animate({
							scrollTop: 0
						}, 300);
						e.preventDefault();
						displayPage($(this).attr('rel'));
					});

				$(elSelector + ' .easyPaginateNav li a.prev').on('click', function(e) {
					$('html , body').animate({
						scrollTop: 0
					}, 300);
					e.preventDefault();
					page = plugin.settings.currentPage > 1 ? parseInt(plugin.settings.currentPage) - 1 : 1;
					displayPage(page);
				});

				$(elSelector + ' .easyPaginateNav li a.next').on('click', function(e) {
					$('html , body').animate({
						scrollTop: 0
					}, 300);
					e.preventDefault();
					page = plugin.settings.currentPage < plugin.settings.pages ? parseInt(plugin.settings.currentPage) + 1 : plugin.settings.pages;
					displayPage(page);
				});
			};

			var displayPage = function(page, forceEffect) {
				if (plugin.settings.currentPage != page) {
					plugin.settings.currentPage = parseInt(page);
					offsetStart = (page - 1) * plugin.settings.elementsPerPage;
					offsetEnd = page * plugin.settings.elementsPerPage;
					if (typeof (forceEffect) != 'undefined') {
						eval("transition_" + forceEffect + "(" + offsetStart + ", " + offsetEnd + ")");
					} else {
						eval("transition_" + plugin.settings.effect + "(" + offsetStart + ", " + offsetEnd + ")");
					}

					plugin.nav.find('li a.current').removeClass('current');
					plugin.nav.find('li a.page:eq(' + (page - 1) + ')').addClass('current');

					switch (plugin.settings.currentPage) {
						case 1:
							$('.easyPaginateNav li a', plugin).removeClass('disabled');
							$('.easyPaginateNav li a.first, .easyPaginateNav li a.prev', plugin).addClass('disabled');
							break;
						case plugin.settings.pages:
							$('.easyPaginateNav li a', plugin).removeClass('disabled');
							$('.easyPaginateNav li a.last, .easyPaginateNav li a.next', plugin).addClass('disabled');
							break;
						default:
							$('.easyPaginateNav li a', plugin).removeClass('disabled');
							break;
					}
				}
			};

			var transition_default = function(offsetStart, offsetEnd) {
				plugin.currentElements.hide();
				plugin.currentElements = plugin.settings.objElements.slice(offsetStart, offsetEnd).clone();
				plugin.el.html(plugin.currentElements);
				plugin.currentElements.show();
			};

			var transition_fade = function(offsetStart, offsetEnd) {
				plugin.currentElements.fadeOut();
				plugin.currentElements = plugin.settings.objElements.slice(offsetStart, offsetEnd).clone();
				plugin.el.html(plugin.currentElements);
				plugin.currentElements.fadeIn();
			};

			var transition_slide = function(offsetStart, offsetEnd) {
				plugin.currentElements.animate({
					'margin-left': plugin.settings.slideOffset * -1,
					'opacity': 0
				}, function() {
					$(this).remove();
				});

				plugin.currentElements = plugin.settings.objElements.slice(offsetStart, offsetEnd).clone();
				plugin.currentElements.css({
					'margin-left': plugin.settings.slideOffset,
					'display': 'block',
					'opacity': 0,
					'min-width': plugin.el.width() / 2
				});
				plugin.el.html(plugin.currentElements);
				plugin.currentElements.animate({
					'margin-left': 0,
					'opacity': 1
				});
			};

			var transition_climb = function(offsetStart, offsetEnd) {
				plugin.currentElements.each(function(i) {
					var $objThis = $(this);
					setTimeout(function() {
						$objThis.animate({
							'margin-left': plugin.settings.slideOffset * -1,
							'opacity': 0
						}, function() {
							$(this).remove();
						});
					}, i * 200);
				});

				plugin.currentElements = plugin.settings.objElements.slice(offsetStart, offsetEnd).clone();
				plugin.currentElements.css({
					'margin-left': plugin.settings.slideOffset,
					'display': 'block',
					'opacity': 0,
				});
				plugin.el.html(plugin.currentElements);
				plugin.currentElements.each(function(i) {
					var $objThis = $(this);
					setTimeout(function() {
						$objThis.animate({
							'margin-left': 0,
							'opacity': 1
						});
					}, i * 200);
				});
			};

			plugin.settings = $.extend({}, defaults, options);

			plugin.currentElements = $([]);
			plugin.settings.objElements = plugin.el.find(plugin.settings.paginateElement);
			plugin.settings.pages = getNbOfPages();
			if (plugin.settings.pages > 1) {
				plugin.el.html();

				// Here we go
				displayNav();

				page = 1;
				if (document.location.hash.indexOf('#' + plugin.settings.hashPage + ':') != -1) {
					page = parseInt(document.location.hash.replace('#' + plugin.settings.hashPage + ':', ''));
					if (page.length <= 0 || page < 1 || page > plugin.settings.pages) {
						page = 1;
					}
				}

				displayPage(page, 'default');
			}
		});
	};
})(jQuery);