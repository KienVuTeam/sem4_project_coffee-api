
function upload(e) {
	let dropzone = e.parentNode;
	let overlays = dropzone.children[0];
	let imgs = dropzone.children[1];
	let defaultImgs = dropzone.children[2];

	imgs.src = URL.createObjectURL(e.files[0]);
	defaultImgs.style.display = "none";
	overlays.classList.add("active");
	imgs.style.display = "block";
}

function removeImage(e) {
	let dropzone = e.parentNode.parentNode;
	let overlays = dropzone.children[0];
	let imgs = dropzone.children[1];
	let defaultImgs = dropzone.children[2];
	let file = dropzone.children[3];

	imgs.src = "";
	imgs.style.display = "none";
	overlays.classList.remove("active");
	defaultImgs.style.display = "inline-block";
	file.value = "";
}

function removeEditImage(e) {
	let dropzone = e.parentNode.parentNode;
	let overlays = dropzone.children[0];
	let imgs = dropzone.children[1];
	let defaultImgs = dropzone.children[2];
	let file = dropzone.children[3];
	let removeFile = dropzone.children[4];

	if (removeFile === undefined) {
		removeFile = document.createElement('input');
		removeFile.setAttribute("type", "hidden");
		removeFile.setAttribute("name", "removeFile");
		removeFile.value = defaultImgs.src
		dropzone.appendChild(removeFile);
	}
	imgs.src = "";
	imgs.style.display = "none";
	overlays.classList.remove("active");
	defaultImgs.style.display = "inline-block";
	defaultImgs.classList.remove("ob-fit-cover");
	defaultImgs.src = "https://100dayscss.com/codepen/upload.svg"
	file.required = true;
	file.value = "";
}

function detailReview(t) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/Supplier/DetailReview",
		data: t.dataset.idproduct,
		dataType: 'json',
		success: function(data) {
			if (data != null) {
				var html = "";
				data.forEach(function(item, index) {
					html += `<tr>
                                        <td>${index + 1}</td>
                                        <td>${item.nameCus}</td>
                                        <td>${item.review}</td>
                                        <td>${item.createDate}</td>
		                            </tr>`;
				})

				$("#ajax-review").empty();
				$("#ajax-review").append(html);
			} else {
				alert("error")
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function editProduct(t) {
	let title = document.querySelector("#EditProduct input[name='title']");
	let id = document.querySelector("#EditProduct input[name='idProduct']");
	let dropzone = document.querySelectorAll("#EditProduct .dropzone");
	let defaultUploads = document.querySelectorAll("#EditProduct .default-upload");
	let price = document.querySelector("#EditProduct input[name='price']");
	let description = document.querySelector("#EditProduct textarea[name='description']");
	let hiddenImages = document.querySelectorAll("#EditProduct input[name='hiddenImage']");

	let selectCate = document.querySelector("#EditProduct select");
	let optionCate = document.querySelectorAll("#EditProduct option");

	// 0: overlay; 1: preview; 2: default; 3: file
	dropzone.forEach(function(item) {
		item.children[0].classList.add("active");
		item.children[2].classList.add("ob-fit-cover");
		item.children[3].required = false;
	})
	defaultUploads[0].src = t.dataset.image;
	defaultUploads[1].src = t.dataset.image1;
	defaultUploads[2].src = t.dataset.image2;
	defaultUploads[3].src = t.dataset.image3;

	hiddenImages[0].value = t.dataset.image;
	hiddenImages[1].value = t.dataset.image1;
	hiddenImages[2].value = t.dataset.image2;
	hiddenImages[3].value = t.dataset.image3;

	id.value = t.dataset.id;
	title.value = t.dataset.title;
	price.value = t.dataset.price;
	description.value = t.dataset.description;

	optionCate.forEach(function(option, index) {
		if (t.dataset.namecate.localeCompare(option.text) == 0) {
			selectCate.children[index].selected = "selected";
		}
	});
}

function filterProduct(value) {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Supplier/FilterProduct",
		data: {
			idSupplier: Number(idSupplier),
			isActive: value
		},
		dataType: 'json',
		success: function(data) {
			if (data != null) {
				var html = "";
				html += `<table class="table table-hover dataTable3">
		                            <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Product Name</th>
                                            <th>In Category</th>
                                            <th>Image</th>
                                            <th>Price</th>
                                            <th>Discount</th>
                                            <th>Show Reviews</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
		                        <tbody>`;
				data.forEach(function(item, index) {
					html += `<tr>
                                            <td>${index + 1}</td> 
                                            <td>${item.title}</td>
                                            <td>${item.nameCate}</td>
                                            <td>
                                                <div class="wrapImg">
                                                	<img src="${item.image}" onerror="this.onerror=null;this.src='/supplier/images/defaultImageProduct.png';" alt="image">
                                                </div>
                                            </td>
                                            <td>${item.price}</td>
                                            <td>`

					switch (item.isStatus) {
						case 0: html += `<div class="text-warning">Upcoming</div>`; break;
						case 1: html += `<div class="text-success">On Sale</div>`; break;
						default: html += `<div class="text-info">No Discount</div>`; break;
					}

					html += `</td>
                                        <td>
                                            <button type="button" class="btn btn-info" data-bs-toggle="modal" data-idProduct="${item.id}"
                                                data-bs-target="#Review" onclick="detailReview(this)" title="Show Reviews">
                                                Reviews
                                            </button>
                                        </td>`

					switch (item.isActive) {
						case 0: html += `
                                    <td>
                                        <div class="text-warning">Pending</div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-info checkIsActive" data-bs-toggle="modal" data-bs-target="#EditProduct" title="Edit Product"
                                            data-title="${item.title}" data-price="${item.price}" data-id="${item.id}" data-nameCate="${item.nameCate}"
                                            data-description="${item.description}" data-image="${item.image}" 
                                            data-image1="${item.image1}" data-image2="${item.image2}" data-image3="${item.image3}" 
                                            onclick="editProduct(this)">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                                        </button>
                                        
                                        <button type="button" data-bs-toggle="modal" data-bs-target="#ConfirmDeleteProduct" data-id="${item.id}" class="btn btn-danger" onclick="deleteProduct(this)" title="Delete Product">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                                        </button>
                                    </td>
                                </tr>`; break;
						case 1: html += `
                                    <td>
                                        <div class="text-success">Active</div>
                                    </td>
                                    <td>

                                    </td>
                                </tr>`; break;
						case 2: html += `
                                    <td>
                                        <div class="text-danger">Deny</div>
                                    </td>
                                    <td>
                                        <button type="button" data-bs-toggle="modal" data-bs-target="#ConfirmDeleteProduct" data-id="${item.id}" class="btn btn-danger" onclick="deleteProduct(this)" title="Delete Product">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                                        </button>
                                    </td>
                                </tr>`; break;
						case 3: html += `
                                    <td>
                                        <div class="text-danger">Deleted</div>
                                    </td>
                                    <td></td>
                                </tr>`; break;
					}
				})
				html += `</tbody></table>`;

				$("#ajax-product").empty();
				$("#ajax-product").append(html);

				checkIsActive();

				table4 = document.querySelector('.dataTable3');
				dataTable3 = new simpleDatatables.DataTable(table4);
			} else {
				alert("error")
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	});
}

$(".AddReset").click(function() {
	let listDropzone = document.querySelectorAll("#AddProduct .dropzone");

	listDropzone.forEach(function(dropzone) {
		// dropzone child:      0: overlay; 1: upload-icon; 2: default; 3: file;
		dropzone.children[0].classList.remove("active");
		dropzone.children[1].src = "";
		dropzone.children[1].removeAttribute("style");
		dropzone.children[2].removeAttribute("style");
		dropzone.children[3].value = "";
	});
	showAlert("success", "Your changes has been successful");
})

function deleteProduct(t) {
	let confirmDelete = document.querySelector("#ConfirmDeleteProduct .confirmDelete");
	let id = t.dataset.id;
	let FilterProduct = document.querySelector("#FilterProduct");
	confirmDelete.addEventListener("click", function() {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/Supplier/DeleteProduct",
			data: {
				id: id,
				idSupplier: Number(idSupplier),
				isActive: FilterProduct.value
			},
			dataType: 'json',
			success: function(data) {
				var html = "";
				html += `<table class="table table-hover dataTable3">
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>Product Name</th>
                                                <th>In Category</th>
                                                <th>Image</th>
                                                <th>Price</th>
                                                <th>Discount</th>
                                                <th>Show Reviews</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                    <tbody>`;
				data.forEach(function(item, index) {
					html += `<tr>
                                                <td>${index + 1}</td> 
                                                <td>${item.title}</td>
                                                <td>${item.nameCate}</td>
                                                <td>
                                                    <div class="wrapImg">
                                                        <img src="${item.image}" onerror="this.onerror=null;this.src='/supplier/images/defaultImageProduct.png';" alt="image">
                                                    </div>
                                                </td>
                                                <td>${item.price}</td>
                                                <td>`

					switch (item.isStatus) {
						case 0: html += `<div class="text-warning">Upcoming</div>`; break;
						case 1: html += `<div class="text-success">On Sale</div>`; break;
						default: html += `<div class="text-info">No Discount</div>`; break;
					}

					html += `</td>
                                            <td>
                                                <button type="button" class="btn btn-info" data-bs-toggle="modal" data-idProduct="${item.id}"
                                                    data-bs-target="#Review" onclick="detailReview(this)" title="Show Reviews">
                                                    Reviews
                                                </button>
                                            </td>`

					switch (item.isActive) {
						case 0: html += `
                                        <td>
                                            <div class="text-warning">Pending</div>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-info checkIsActive" data-bs-toggle="modal" data-bs-target="#EditProduct" title="Edit Product"
                                                data-title="${item.title}" data-price="${item.price}" data-id="${item.id}" data-nameCate="${item.nameCate}"
                                                data-description="${item.description}" data-image="${item.image}" 
                                                data-image1="${item.image1}" data-image2="${item.image2}" data-image3="${item.image3}" 
                                                onclick="editProduct(this)">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                                            </button>
                                            <button type="button" data-bs-toggle="modal" data-bs-target="#ConfirmDeleteProduct" data-id="${item.id}" class="btn btn-danger" onclick="deleteProduct(this)" title="Delete Product">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                                            </button>
                                        </td>
                                    </tr>`; break;
						case 1: html += `
                                        <td>
                                            <div class="text-success">Active</div>
                                        </td>
                                        <td>

                                        </td>
                                    </tr>`; break;
						case 2: html += `
                                        <td>
                                            <div class="text-danger">Deny</div>
                                        </td>
                                        <td>
                                            <button type="button" data-bs-toggle="modal" data-bs-target="#ConfirmDeleteProduct" data-id="${item.id}" class="btn btn-danger" onclick="deleteProduct(this)" title="Delete Product">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                                            </button>
                                        </td>
                                    </tr>`; break;
						case 3: html += `
                                        <td>
                                            <div class="text-danger">Deleted</div>
                                        </td>
                                        <td></td>
                                    </tr>`; break;
					}
				})
				html += `</tbody></table>`;

				$("#ajax-product").empty();
				$("#ajax-product").append(html);

				table4 = document.querySelector('.dataTable3');
				dataTable3 = new simpleDatatables.DataTable(table4);

				checkIsActive();
				showAlert("success", "Your changes has been successful");
			},
			error: function(e) {
				console.log("ERROR: ", e);
				showAlert("danger", "Awww, Somthing went wrong!!!");
			}
		});
	})
}

$(".EditReset").click(function() {
	let name = document.querySelector("#EditProduct input[name='title']");
	let price = document.querySelector("#EditProduct input[name='price']");
	let description = document.querySelector("#EditProduct textarea[name='description']");
	let listDropzone = document.querySelectorAll("#EditProduct .dropzone");

	name.value = "";
	price.value = "";
	description.value = "";

	listDropzone.forEach(function(dropzone) {
		// dropzone child:      0: overlay; 1: upload-icon; 2: default; 3: file;
		dropzone.children[0].classList.remove("active");
		dropzone.children[1].src = "";
		dropzone.children[1].removeAttribute("style");
		dropzone.children[2].src = "https://100dayscss.com/codepen/upload.svg";
		dropzone.children[2].removeAttribute("style");
		dropzone.children[2].classList.remove("ob-fit-cover");
		dropzone.children[3].value = "";
		dropzone.children[3].setAttribute('required', '');
	});
	showAlert("success", "Your changes has been successful");
})