// ==========================================================
// // Customer survey form validation check and sum script
// ==========================================================
function handleClick(input) {
    var chk = document.querySelectorAll(".check:checked");
    var total = 0;

    if (!chk.length) {
        alert("You didn't select an answer for all questions")
    } else {
        chk.forEach(function (el) {
            total += parseInt(el.getAttribute('value'));
        });
        console.log(total);

        var totalRange;

        if (total <= 27) {
            totalRange = 0;
        }
        if (total >= 28 && total <= 40) {
            totalRange = 1;
        }
        if (total >= 41 && total <= 65) {
            totalRange = 2;
        } else if (total >= 70) {
            totalRange = 3;
        }

        // console.log(totalRange);


        var breed = [
            "Bichon Frise, French Bulldog, Havanese, Lhasa Apso , Miniature Poodle, Pug, Scottish Terrier, Shih Taus, Tibetan Terrier, Whippet, Yorkshire Terrier",
            "Basset Hound, Bernese Mountain Dog, Boxer, Collie, Dogue de Bordeaux, English Springer Spaniel, Golden Retriever, Great Dane, Irish Wolfhound, Labrador Retriever, Leonberger, Neopolitan Mastiff, Newfoundland, Poodle, Shetland Sheepdog, Wheaten Terrier",
            "Australian Shepherd, Australian Terrier, Azawakh, Barbet, Basenji, Basset Bleu de Gascogne, Basset Hound, Bavarian Mountain Hound, Beagle, Bearded Collie, Beauceron, Bedlington Terrier",
            "Afghan Hound, Akita, American Pit Bull Terrier, Australian Cattle Dog, Bernese Mountain Dog, Cane Corso, Caucasian Sheepdog (Ovcharka), Chow Chow, Dalmations, Doberman Pinscher, English Bulldog, German Shepherd, Great Pyrenees, Newfoundland, Shar-Pei, Shiba Inu, Siberian Husky, Tibetan Mastiff, Weimaraner"
        ];

        var  info  = [
            "First time dog owners and apartment dwellers should consider the following breeds as they are fun loving and do well in small spaces.",
            "Those looking for a medium to large breed that is easy to train should consider the following breeds.",
            "You'll need to take grooming requirements, activity level, size and common health ailments in mind when choosing one of these breeds for your family.",
            "Experienced owners or active bodies with the patience to train a energetic, enthusiastic, large dog should consider the following breeds."
        ];

        var list = breed[totalRange];
        var dogs = list.split(',');
        console.log(dogs);

        var ul = document.createElement('ul');
        document.getElementById('dogList').appendChild(ul);

        dogs.forEach(function(dog){
            var li = document.createElement('li');
            ul.appendChild(li);
            li.innerHTML += dog;
        });

        // Hidden elements (RESULTS)  made visible after form submit
        document.getElementById("after_submit-0").style.visibility = "visible";
        document.getElementById("after_submit-1").style.visibility = "visible";
        document.getElementById("after_submit-4").style.visibility = "visible";
        document.getElementById("info").innerHTML = info[totalRange];
        document.getElementById("hidden-container").style.visibility = "visible";
        document.getElementById("totalSum").disabled = true;
    }
}

// ==========================================================
// // Refresh page to remove survey results;
// ==========================================================
function resetForm() {
    location.reload();
}

// ==========================================================
// // DOGAPI search
// ==========================================================
// Use JQuery and TheDogAPI to load breed list, and show a breed image and data on selection.
// Setup the Controls
const $breed_select = $('select.breed_select');
$breed_select.change(function() {
    const id = $(this).children(":selected").attr("id");
    getDogByBreed(id)
});
// Load all the Breeds
function getBreeds() {
    ajax_get('https://api.thedogapi.com/v1/breeds', function(data) {
        populateBreedsSelect(data)
    });
}
// Put the breeds in the Select control
function populateBreedsSelect(breeds) {
    $breed_select.empty().append(function() {
        let output = '';
        $.each(breeds, function(key, value) {
            output += '<option id="' + value.id + '">' + value.name + '</option>';
        });
        return output;
    });
}
// triggered when the breed select control changes
function getDogByBreed(breed_id) {
    // search for images that contain the breed (breed_id=) and attach the breed object (include_breed=1)
    ajax_get('https://api.thedogapi.com/v1/images/search?include_breed=1&breed_id=' + breed_id, function(data) {
        if (data.length == 0) {
            // if there are no images returned
            clearBreed();
            $("#breed_data_table").append("<tr><td>Sorry, no Image for that breed yet</td></tr>");
        } else {
            //else display the breed image and data
            displayBreed(data[0])
        }
    });
}
// display the breed image and data
function displayBreed(image) {
    $('#breed_image').attr('src', image.url);
    $("#breed_data_table tr").remove();
    const breed_data = image.breeds[0]
    $.each(breed_data, function(key, value) {
        // as 'weight' and 'height' are objects that contain 'metric' and 'imperial' properties, just use the metric string
        if (key == 'weight' || key == 'height') value = value.metric
        // add a row to the table
        $("#breed_data_table").append("<tr><td>" + key + "</td><td>" + value + "</td></tr>");
    });
}
// make an Ajax request
function ajax_get(url, callback) {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            // console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch (err) {
                // console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
// call the getBreeds function which will load all the Dog breeds into the select control
getBreeds();