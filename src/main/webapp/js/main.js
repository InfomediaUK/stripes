function initPage() {
	initAutoScalingNav({
		menuId: "nav",
		spacing: 73,
		constant: -70,
		sideClasses: true
	});
}
function initAutoScalingNav(o) {
	if (!o.menuId) o.menuId = "nav";
	if (!o.tag) o.tag = "a";
	if (!o.spacing) o.spacing = 0;
	if (!o.constant) o.constant = 0;
	if (!o.minPaddings) o.minPaddings = 0;
	if (!o.liHovering) o.liHovering = false;
	if (!o.sideClasses) o.sideClasses = false;
	if (!o.equalLinks) o.equalLinks = false;
	if (!o.flexible) o.flexible = false;
	var nav = document.getElementById(o.menuId);
	if(nav) {
		nav.className += " scaling-active";
		var lis = nav.getElementsByTagName("li");
		var asFl = [];
		var lisFl = [];
		var width = 0;
		for (var i=0, j=0; i<lis.length; i++) {
			if(lis[i].parentNode == nav) {
				var t = lis[i].getElementsByTagName(o.tag).item(0);
				asFl.push(t);
				asFl[j++].width = t.offsetWidth;
				lisFl.push(lis[i]);
				if(width < t.offsetWidth) width = t.offsetWidth;
			}
			if(o.liHovering) {
				lis[i].onmouseover = function() {
					this.className += " hover";
				}
				lis[i].onmouseout = function() {
					this.className = this.className.replace("hover", "");
				}
			}
		}
		var menuWidth = nav.clientWidth - asFl.length*o.spacing - o.constant;
		if(o.equalLinks && width * asFl.length < menuWidth) {
			for (var i=0; i<asFl.length; i++) {
				asFl[i].width = width;
			}
		}
		width = getItemsWidth(asFl);
		if(width < menuWidth) {
			var version = navigator.userAgent.toLowerCase();
			for (var i=0; getItemsWidth(asFl) < menuWidth; i++) {
				asFl[i].width++;
				if(!o.flexible) {
					asFl[i].style.width = asFl[i].width + "px";
				}
				if(i >= asFl.length-1) i=-1;
			}
			if(o.flexible) {
				for (var i=0; i<asFl.length; i++) {
					width = (asFl[i].width - o.spacing - o.constant/asFl.length)/menuWidth*100;
					if(i != asFl.length-1) {
						lisFl[i].style.width = width + "%";
					}
					else {
						if(navigator.appName.indexOf("Microsoft Internet Explorer") == -1 || version.indexOf("msie 8") != -1 || version.indexOf("msie 9") != -1)
							lisFl[i].style.width = width + "%";
					}
				}
			}
		}
		else if(o.minPaddings > 0) {
			for (var i=0; i<asFl.length; i++) {
				asFl[i].style.paddingLeft = o.minPaddings + "px";
				asFl[i].style.paddingRight = o.minPaddings + "px";
			}
		}
		if(o.sideClasses) {
			lisFl[0].className += " first-child";
			lisFl[0].getElementsByTagName(o.tag).item(0).className += " first-child-a";
			lisFl[lisFl.length-1].className += " last-child";
			lisFl[lisFl.length-1].getElementsByTagName(o.tag).item(0).className += " last-child-a";
		}
		nav.className += " scaling-ready";
	}
	function getItemsWidth(a) {
		var w = 0;
		for(var q=0; q<a.length; q++) {
			w += a[q].width;
		}
		return w;
	}
}


if (window.addEventListener)
	window.addEventListener("load", initPage, false);
else if (window.attachEvent)
	window.attachEvent("onload", initPage)
	
// custom upload input
function initCustomFile() {
	var inputs = document.getElementsByTagName('input');
	for (var i= 0; i < inputs.length; i++) {
		if(inputs[i].className.indexOf('file-input-area') != -1) {
			new customFileUpload(inputs[i]);
		}
	}
}

// custom file input module
function customFileUpload(obj, opt) {
	if(obj) {
		this.options = {
			jsActiveClass:'file-input-js-active',
			fakeClass:'file-input-value',
			hoverClass:'hover'
		}
		this.fileInput = obj;
		this.fileInput.custClass = this;
		this.init();
	}
}
customFileUpload.prototype = {
	init: function() {
		this.getElements();
		this.setStyles();
		this.addEvents();
	},
	getElements: function() {
		this.fileInputParent = this.fileInput.parentNode;
		this.fileInputParent.className += ' ' + this.options.jsActiveClass;
		var tmpInputs = this.fileInput.parentNode.getElementsByTagName('input');
		for(var i = 0; i < tmpInputs.length; i++) {
			if(tmpInputs[i].className.indexOf(this.options.fakeClass) != -1) {
				this.fakeInput = tmpInputs[i];
				this.fakeInput.readOnly = true;
				break;
			}
		}
	},
	getFileName: function(){
		return this.fileInput.value.replace(/^[\s\S]*(?:\\|\/)([\s\S^\\\/]*)$/g, "$1");
	},
	setStyles: function() {
		// IE styling fix
		if((/(MSIE)/gi).test(navigator.userAgent)) {
			this.tmpNode = document.createElement('span');
			this.fileInputParent.insertBefore(this.tmpNode,this.fileInput);
			this.fileInputParent.insertBefore(this.fileInput,this.tmpNode);
			this.fileInputParent.removeChild(this.tmpNode);
		}
		this.fileInput.style.opacity = 0;
		this.fileInput.style.filter = 'alpha(opacity=0)';
	},
	addEvents: function() {
		this.fileInput.onchange = this.bind(this.updateTitle,this);
		this.fileInput.onmouseover = this.bind(function(){
			this.fileInputParent.className += ' ' + this.options.hoverClass;
		},this);
		this.fileInput.onmouseout = this.bind(function(){
			this.fileInputParent.className = this.fileInputParent.className.replace(' '+this.options.hoverClass,'');
		},this);
	},
	updateTitle: function() {
		if(this.fakeInput) {
			this.fakeInput.value = this.getFileName();
		}
	},
	bind: function(func, scope) {
		return function() {
			return func.apply(scope, arguments);
		}
	}
}

if (window.addEventListener) window.addEventListener("load", initCustomFile, false);
else if (window.attachEvent) window.attachEvent("onload", initCustomFile);


