//==========Create Post FileStack Script ======================
// Set up the picker
const client = filestack.init(FILESTACK_API_KEY);
const options = {
    onUploadDone: updateForm,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false,
};

const picker = client.picker(options);
// Get references to the DOM elements
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');
// Add our event listeners
btn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});

function updateForm (result) {
    const fileData = result.filesUploaded[0];
    fileInput.value = fileData.url; //this is the good part
    console.log(fileData.url);
    console.log(fileInput.value);
}

//==========Update Post FileStack Script ======================
// Set up the picker
const client2 = filestack.init(FILESTACK_API_KEY);
const options2 = {
    onUploadDone: updateForm2,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false,
};

const picker2 = client2.picker(options2);
const fileInput2 = document.getElementById('fileupload2');
const btn2 = document.getElementById('picker2');
const nameBox2 = document.getElementById('nameBox2');
const urlBox2 = document.getElementById('urlBox2');
// Add our event listeners
btn2.addEventListener('click', function (e) {
    e.preventDefault();
    picker2.open();
});

function updateForm2 (result) {
    const fileData2 = result.filesUploaded[0];
    fileInput2.value = fileData2.url; //this is the good part
    console.log(fileData2.url);
    console.log(fileInput2.value);
}