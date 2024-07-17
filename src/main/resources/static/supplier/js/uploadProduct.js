
var maxSIZE = 5 * 1024 * 1024;

$(document).ready(function() {
	//Handle Image	
	$('.upload-input').change(function() {
		//Variable Common	
		var mimeType = this.files[0].type;
		var fileSize = this.files[0].size;
		var flagCheck = false;
		//	
		if (!mimeType.match(/image.*/)) {
			this.setCustomValidity("Only Upload File As Image");
			this.reportValidity();
		} // After Check Filter Need To Check Size Of Image	
		else if (fileSize > maxSIZE) {
			this.setCustomValidity("Image Upload Must Not Exceed 5 MB");
			this.reportValidity();
		} else {
			this.setCustomValidity("");
			flagCheck = true;
		}
		//Preview Set	
		if (flagCheck == true) {
			var imageSrc = URL.createObjectURL(this.files[0]);
			var currentSelect = $(this).attr('data-select');
			if (currentSelect == 'selMain') {
				document.getElementById("selMainImg").src = imageSrc;
			} else if (currentSelect == 'imageNo1') {
				document.getElementById("imageNo1Img").src = imageSrc;
			} else if (currentSelect == 'imageNo2') {
				document.getElementById("imageNo2Img").src = imageSrc;
			} else if (currentSelect == 'imageNo3') {
				document.getElementById("imageNo3Img").src = imageSrc;
			};
		}
	});


	//Submit Button	
	$('#submitAddProduct').click(function(e) {
		let flagValidate = true;
		let title = document.querySelector("#AddProduct input[name='title']");
		let price = document.querySelector("#AddProduct input[name='price']");
		let description = document.querySelector("#AddProduct textarea[name='description']");
		let files = document.querySelectorAll("#AddProduct input[name='files']");

		if (title.value === "") {
			flagValidate = false;
			title.setCustomValidity("Please fill out this field");
			title.reportValidity()
		} else {
			$.ajax({
				type: "GET",
				contentType: "application/json",
				url: "/Supplier/CheckDuplicateTitle",
				data: {
					title: title.value,
					idSupplier: Number(idSupplier)
				},
				dataType: 'json',
				success: function(data) {
					if (data == false) {
						flagValidate = false;
						title.setCustomValidity("Duplicate Title Product");
						title.reportValidity();
						return false;
					}

					files.forEach(item => {
						if (!item.checkValidity()) {
							flagValidate = false;
							item.reportValidity();
							return false;
						}
					});

					if (!price.checkValidity()) {
						flagValidate = false;
						price.reportValidity();
						return false;
					}

					if (!description.checkValidity()) {
						flagValidate = false;
						description.reportValidity();
						return false;
					}

					if (flagValidate == true) {
						e.preventDefault();
						//Common Variable
						var flagUpload = false;
						var flagSubUpload = true;
						const objectSubUpload = { subImg00: "", subImg01: "", subImg02: "" };
						//
						const mainImg = document.querySelector('#selMain').files[0];
						const subImgNo1 = document.querySelector("#imageNo1").files[0];
						const subImgNo2 = document.querySelector("#imageNo2").files[0];
						const subImgNo3 = document.querySelector("#imageNo3").files[0];
						//Check Other Validate In Here !
						if (mainImg != undefined) {
							//FirstImage	
							if (checkImage(mainImg, "selMain") == true) {
								flagUpload = true;
							}
							//	
							if (flagUpload == true) {
								//NO 1 CHECK	
								if (subImgNo1 != undefined) {
									if (checkImage(subImgNo1, "imageNo1") == true) {
										objectSubUpload.subImg00 = "Upload";
									} else {
										flagSubUpload = false;
									}
								} else {
									objectSubUpload.subImg00 = "Null";
								}
								//NO 2 CHECK	
								if (subImgNo2 != undefined && flagSubUpload == true) {
									if (checkImage(subImgNo2, "imageNo2") == true) {
										objectSubUpload.subImg01 = "Upload";
									} else {
										flagSubUpload = false;
									}
								} else {
									objectSubUpload.subImg01 = "Null";
								}
								//NO 3 CHECK	
								if (subImgNo3 != undefined && flagSubUpload == true) {
									if (checkImage(subImgNo3, "imageNo3") == true) {
										objectSubUpload.subImg02 = "Upload";
									} else {
										flagSubUpload = false;
									}
								} else {
									objectSubUpload.subImg02 = "Null";
								}

								//Submit All IMAGE	
								if (flagSubUpload == true) {
									startUploadFirebase(mainImg, "main");
									//Firebase IMG NO 1	
									if (objectSubUpload.subImg00 == "Upload") {
										startUploadFirebase(subImgNo1, "imageNo1");
									}
									//Firebase IMG NO 2	
									if (objectSubUpload.subImg01 == "Upload") {
										startUploadFirebase(subImgNo2, "imageNo2");
									}
									//Firebase IMG NO 3	
									if (objectSubUpload.subImg02 == "Upload") {
										startUploadFirebase(subImgNo3, "imageNo3");
									}
									//Start Calling Ajax	
									setTimeout(function() {
										$.ajax({
											url: "/Supplier/AddProduct",
											dataType: 'text',
											data: {
												image: document.getElementById("hdMain").value,
												image1: document.getElementById("hdImgNo1").value,
												image2: document.getElementById("hdImgNo2").value,
												image3: document.getElementById("hdImgNo3").value,
												title: document.querySelector("#AddProduct input[name='title']").value,
												idCate: document.querySelector("#AddProduct select[name='idCate']").value,
												price: document.querySelector("#AddProduct input[name='price']").value,
												description: document.querySelector("#AddProduct textarea[name='description']").value,
												idSupplier: Number(idSupplier)
											}
										}).done(function(data) {
											window.location.reload();
										})
									}, 2000);

								}
							}

						} else {
							//Alert ->	
						}
					}
				},
				error: function(e) {
					console.log("ERROR: ", e);
				}
			});
		}
	});

	$('#submitEditProduct').click(function(e) {
		let flagValidate = true;
		let title = document.querySelector("#EditProduct input[name='title']");
		let price = document.querySelector("#EditProduct input[name='price']");
		let description = document.querySelector("#EditProduct textarea[name='description']");
		let files = document.querySelectorAll("#EditProduct input[name='files']");

		if (!title.checkValidity()) {
			flagValidate = false;
			title.reportValidity()
		}
		if (!price.checkValidity()) {
			flagValidate = false;
			price.reportValidity()
		}
		if (!description.checkValidity()) {
			flagValidate = false;
			description.reportValidity()
		}
		files.forEach(item => {
			if (!item.checkValidity()) {
				flagValidate = false;
				item.reportValidity()
			}
		});

		if (flagValidate == true) {
			e.preventDefault();
			//Common Variable
			var flagUpload = false;
			var flagSubUpload = true;
			const objectSubUpload = { subImg00: "", subImg01: "", subImg02: "" };
			//
			const mainImg = document.querySelector('#selMainEdit').files[0];
			const subImgNo1 = document.querySelector("#imageNo1Edit").files[0];
			const subImgNo2 = document.querySelector("#imageNo2Edit").files[0];
			const subImgNo3 = document.querySelector("#imageNo3Edit").files[0];
			//Check Other Validate In Here !
			if (mainImg != undefined) {
				//FirstImage	
				if (checkImage(mainImg, "selMain") == true) {
					flagUpload = true;
				}
				//	
				if (flagUpload == true) {
					//NO 1 CHECK	
					if (subImgNo1 != undefined) {
						if (checkImage(subImgNo1, "imageNo1") == true) {
							objectSubUpload.subImg00 = "Upload";
						} else {
							flagSubUpload = false;
						}
					} else {
						objectSubUpload.subImg00 = "Null";
					}
					//NO 2 CHECK	
					if (subImgNo2 != undefined && flagSubUpload == true) {
						if (checkImage(subImgNo2, "imageNo2") == true) {
							objectSubUpload.subImg01 = "Upload";
						} else {
							flagSubUpload = false;
						}
					} else {
						objectSubUpload.subImg01 = "Null";
					}
					//NO 3 CHECK	
					if (subImgNo3 != undefined && flagSubUpload == true) {
						if (checkImage(subImgNo3, "imageNo3") == true) {
							objectSubUpload.subImg02 = "Upload";
						} else {
							flagSubUpload = false;
						}
					} else {
						objectSubUpload.subImg02 = "Null";
					}

					//Submit All IMAGE	
					if (flagSubUpload == true) {
						startUploadFirebaseEdit(mainImg, "main");
						//Firebase IMG NO 1	
						if (objectSubUpload.subImg00 == "Upload") {
							startUploadFirebaseEdit(subImgNo1, "imageNo1");
						}
						//Firebase IMG NO 2	
						if (objectSubUpload.subImg01 == "Upload") {
							startUploadFirebaseEdit(subImgNo2, "imageNo2");
						}
						//Firebase IMG NO 3	
						if (objectSubUpload.subImg02 == "Upload") {
							startUploadFirebaseEdit(subImgNo3, "imageNo3");
						}
					}
				}
			}

			//Start Calling Ajax	
			setTimeout(function() {
				$.ajax({
					url: "/Supplier/EditProduct",
					dataType: 'text',
					data: {
						image: document.getElementById("hdMainEdit").value,
						image1: document.getElementById("hdImgNo1Edit").value,
						image2: document.getElementById("hdImgNo2Edit").value,
						image3: document.getElementById("hdImgNo3Edit").value,
						idProduct: document.querySelector("#EditProduct input[name='idProduct']").value,
						title: document.querySelector("#EditProduct input[name='title']").value,
						price: document.querySelector("#EditProduct input[name='price']").value,
						description: document.querySelector("#EditProduct textarea[name='description']").value,
						idcate: Number(document.querySelector("#EditProduct select").value),
					}
				}).done(function(data) {
					window.location.reload();
				})
			}, 500);
		}
	});

	//UploadFirebase	
	function startUploadFirebase(fileUpload, positionImg) {
		try {
			const ref = firebase.storage().ref();
			const fileName = Math.floor(Math.random() * 99999999999) + fileUpload.name;
			const metadata = {
				contentType: fileUpload.type
			};
			const firebaseUpload = ref.child(fileName).put(fileUpload, metadata)
				.then(snapshot => snapshot.ref.getDownloadURL())
				.then(url => {
					if (positionImg == "main") {
						document.getElementById("hdMain").value = url;
					} else if (positionImg == "imageNo1") {
						document.getElementById("hdImgNo1").value = url;
					} else if (positionImg == "imageNo2") {
						document.getElementById("hdImgNo2").value = url;
					} else if (positionImg == "imageNo3") {
						document.getElementById("hdImgNo3").value = url;
					}
				})
		} catch (error) {
			console.log(error);
		}
	}
	// Edit UploadFirebase
	function startUploadFirebaseEdit(fileUpload, positionImg) {
		let removeFiles = document.querySelectorAll("#EditProduct input[name='removeFile']");
		try {
			const ref = firebase.storage().ref();
			const fileName = Math.floor(Math.random() * 99999999999) + fileUpload.name;
			const metadata = {
				contentType: fileUpload.type
			};
			const firebaseUpload = ref.child(fileName).put(fileUpload, metadata)
				.then(snapshot => snapshot.ref.getDownloadURL())
				.then(url => {
					if (positionImg == "main") {
						document.getElementById("hdMainEdit").value = url;
					} else if (positionImg == "imageNo1") {
						document.getElementById("hdImgNo1Edit").value = url;
					} else if (positionImg == "imageNo2") {
						document.getElementById("hdImgNo2Edit").value = url;
					} else if (positionImg == "imageNo3") {
						document.getElementById("hdImgNo3Edit").value = url;
					}
				})

			if (removeFiles.length != 0) {
				removeFiles.forEach(function(file) {
					deleteFirebase(file.value);
				});
			}

		} catch (error) {
			console.log(error);
		}
	}

	//FunctionCheck	
	function checkImage(imageCurrent, selPosition) {
		var mimeType = imageCurrent.type;
		var fileSize = imageCurrent.size;
		if (!mimeType.match(/image.*/)) {
			document.getElementById(selPosition).setCustomValidity("Only Upload File As Image");
			document.getElementById(selPosition).reportValidity();
			return false;
		} // After Check Filter Need To Check Size Of Image	
		else if (fileSize > maxSIZE) {
			document.getElementById(selPosition).setCustomValidity("Image Upload Must Not Exceed 5 MB");
			document.getElementById(selPosition).reportValidity();
			return false;
		} else {
			return true;
		}
	}

});

// DeleteFirebase
function deleteFirebase(image) {
	const fileUrl = image;
	// Create a reference to the file to delete
	const fileRef = firebase.storage().refFromURL(fileUrl);

	fileRef.delete().then(function() {
		console.log("File Deleted")
	}).catch(function(error) {
		console.log("Error")
	});
}