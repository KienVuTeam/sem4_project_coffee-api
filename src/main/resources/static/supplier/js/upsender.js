/**
 * 
 */
async function uploadImage(){
	let urlPath="";
	debugger
	const ref=firebase.storage().ref();
	const file =document.querySelector('#photo').files[0];
	if(file == null){
		//alert("null imqage");
		return null; 
	}
	console.log('flie co gi '+file);
	const metadata ={
		contentType: file.type
	};
	const name =file.name;
	const uploadImage = ref.child(name).put(file, metadata);
	await uploadImage.then(snapshot =>snapshot.ref.getDownloadURL())
	.then(url=> {
		document.getElementById('hdImage').value=url;
		console.log(url);
		urlPath = url;
		//alert('success')
		
	})
	.catch(()=>{
		urlPath ="";
		console.error
	})
	 return urlPath;
	
}


async function uploadImageForEdit(){
	debugger
	let urlPathEdit ="";
	const ref=firebase.storage().ref();
	const file =document.querySelector('#edit_photo').files[0];
	if(file == null){
		//alert("anh null");
		return null; 
	}
	console.log('flie co gi '+file);
	const metadata ={
		contentType: file.type
	};
	const name =file.name;
	const uploadImage = ref.child(name).put(file, metadata);
	await uploadImage.then(snapshot =>snapshot.ref.getDownloadURL())
	.then(url=> {
		document.getElementById('edit_hdImage').value=url;
		console.log(url);
		urlPathEdit = url;
		//alert('success')
		
	})
	.catch(()=>{
		console.log("err for edit image")
		urlPathEdit ="";
		console.error
	})
	return urlPathEdit;
	
}
