// ==========================================================
// // Customer survey form validation check and sum script
// ==========================================================
function handleClick(input) {
    let chk = document.querySelectorAll(".check:checked");
    let total = 0;

    if (!chk.length) {
        alert("You didn't select an answer for all questions")
    } else {
        chk.forEach(function (el) {
            total += parseInt(el.getAttribute('value'));
        });
        // console.log(total);

        var totalRange;

        if (total <= 25) {
            totalRange = 0;
        }
        if (total >= 26 && total <= 44) {
            totalRange = 1;
        } else if (total >= 45 && total <= 70) {
            totalRange = 2;
        }

        let breed = [
            "Affenpinscher,Airedale Terrier,Akbash,Akita,Alapaha Blue Blood Buildog,Alaskan husky,Alaskan Klee Kai,American Bulldog,American Bully,American Eskimo Dog,American Foxhound,American Pit Bull Terrier,American Staffordshire Terrier,Anatolian Shepherd Dog ,American Water Spaniel,Australian Cattle Dog,Australian Kelpie,Australian Shepherd,Australian Terrier,Azawakh,Barbet,Basenji,Basset Bleu de Gascogne,Basset Hound,Bavarian Mountain Hound,Beagle,Bearded Collie,Beauceron,Bedlington Terrier,Belgian Malinois ,Belgian Tervuren,Bernese Mountain Dog,Bichon Frisé,Black and Tan Coonhound,Bloodhound,Bluetick Coonhound,Boerboel,Border Collie,Border Terrier,Boston Terrier,Bouvier des Flandres,Boxer,Boykin Spaniel,Bracco Italiano,Briard,Bull Terrier,Bulldog,Bullmastiff,Cairn Terrier,Cane Corso,Cardigan Welsh Corgi,Catahoula Leopard Dog,Catalan Sheepdog,Caucasian Shepherd Dog,Cavalier King Charles Spaniel,Chesapeake Bay Retriever,Chinese Crested Dog,Chinese Shar-Pei,Chow Chow,Clumber Spaniel,Cocker Spaniel,Coton de Tulear,Dalmatian,Doberman Pinscher,Dogo Argentino,Dutch Shepherd,English Setter,English Shepherd,English Springer Spaniel,Enlish Toy Terrier,Eurasier,Field Spaniel,Finnish Lapphund,Finnish Spitz,Flat-coated Retriever,French Bulldog,French Spaniel,Galgo Español,Galician Shepherd Dog,Garafian Shepherd,Gascon Saintongeois,Georgian Shepherd,German Hound,German Longhaired Pointer,German Pinscher,German Roughhaired Pointer,German Shepherd Dog,German Shorthaired Pointer,German Spaniel,German Spitz,German Wirehaired Pointer,Giant Schnauzer,Glen of Imaal Terrier,Golden Retriever,Gończy Polski,Gordon Setter,Gran Mastín de Borínquen,Grand Anglo-Français Blanc et Noir,Grand Anglo-Français Blanc et Orange,Grand Anglo-Français Tricolore,Grand Basset Griffon Vendéen,Grand Bleu de Gascogne,Grand Griffon Vendéen,Great Dane,Great Pyrenees,Greater Swiss Mountain Dog,Greek Harehound,Greek Shepherd,Greenland Dog,Greyhound,Griffon Bleu de Gascogne,Griffon Fauve de Bretagne",
            "Griffon Nivernais,Groenendael,Guatemalan Dogo,Gull Terrier,Hamiltonstövare,Hanover Hound,Harrier,Havanese,Himalayan Sheepdog,Hokkaido,Hortaya borzaya,Hovawart,Huntaway,Hygen Hound,Ibizan Hound,Icelandic Sheepdog,Indian pariah dog,Indian Spitz,Irish Red and White Setter,Irish Setter,Irish Terrier,Irish Water Spaniel,Irish Wolfhound,Istrian Coarse-haired Hound,Istrian Shorthaired Hound,Italian Greyhound,Jack Russell Terrier,Jagdterrier,Jämthund,Japanese Chin,Japanese Spitz,Japanese Terrier,Jindo,Jonangi,Kai Ken,Kangal Shepherd Dog,Kanni,Karakachan dog,Karelian Bear Dog,Karst Shepherd,Keeshond,Kerry Beagle,Kerry Blue Terrier,King Charles Spaniel,King Shepherd,Kintamani,Kishu,Komondor,Kooikerhondje,Koolie,Koyun dog,Kromfohrländer,Kuchi,Kumaon Mastiff,Kunming wolfdog,Kuvasz,Labrador Retriever,Laekenois,Lagotto Romagnolo,Lakeland Terrier,Lancashire Heeler,Landseer,Lapponian Herder,Lapponian Shepherd,Large Münsterländer,Leonberger,Lhasa Apso,Lithuanian Hound,Löwchen,Mackenzie River husky,Magyar agár,Majorca Ratter,Majorca Shepherd Dog,Mahratta Greyhound,Malinois,Maltese,Manchester Terrier,Maremma Sheepdog,McNab dog,Miniature American Shepherd,Miniature Bull Terrier,Miniature Fox Terrier,Miniature Pinscher,Miniature Schnauzer,Miniature Shar Pei,Montenegrin Mountain Hound,Moscow Water Dog,Mountain Cur,Mucuchies,Mudhol Hound,Mudi,Neapolitan Mastiff,Newfoundland,New Zealand Heading Dog,Norfolk Terrier,Norrbottenspets,Northern Inuit Dog,Norwegian Buhund,Norwegian Elkhound,Norwegian Lundehund,Norwich Terrier,Nova Scotia Duck Tolling Retriever,Old Croatian Sighthound,Old Danish Pointer,Old English Sheepdog,Old English Terrier,Olde English Bulldogge,Otterhound,Pachon Navarro,Pandikona,Paisley Terrier,Papillon",
            "Parson Russell Terrier, Patagonian Sheepdog,Patterdale Terrier,Pekingese,Pembroke Welsh Corgi,Perro de Presa Canario,Peruvian Hairless Dog,Petit Basset Griffon Vendéen,Petit Bleu de Gascogne,Phalène,Pharaoh Hound,Phu Quoc Ridgeback,Picardy Spaniel,Plummer Terrier,Plott Hound,Podenco Canario,Pointer,Poitevin,Polish Greyhound,Polish Hound,Polish Lowland Sheepdog,Polish Tatra Sheepdog,Pomeranian,Pont-Audemer Spaniel,Poodle,Porcelaine,Portuguese Podengo,Portuguese Pointer,Portuguese Water Dog,Posavac Hound,Potsdam Greyhound,Pražský Krysařík,Pudelpointer,Pug,Puli,Pumi,Pungsan dog,Pyrenean Mastiff,Pyrenean Shepherd,Rafeiro do Alentejo,Rajapalayam,Rampur Greyhound,Rastreador Brasileiro,Rat Terrier,Ratonero Bodeguero Andaluz,Ratonero Murciano de Huerta,Ratonero Valenciano,Redbone Coonhound,Rhodesian Ridgeback,Romanian Mioritic Shepherd Dog,Romanian Raven Shepherd Dog,Rottweiler,Rough Collie,Russian Spaniel,Russian Toy,Russian Tracker,Russo-European Laika,Russell Terrier,Saarloos wolfdog,Sabueso Español,Saint Bernard,Saint-Usuge Spaniel,Sakhalin Husky,Saluki,Samoyed,Sapsali,Šarplaninac,Schapendoes,Schillerstövare,Schipperke,Schweizer Laufhund,Schweizerischer Niederlaufhund,Scotch Collie,Scottish Deerhound,Scottish Terrier,Sealyham Terrier,Segugio Italiano,Seppala Siberian Sleddog,Serbian Hound,Serbian Tricolour Hound,Serrano Bulldog,Shar Pei,Shetland Sheepdog,Shiba Inu,Shih Tzu,Shikoku,Shiloh Shepherd,Siberian Husky,Sily Terrier,Soft-Coated Wheaten Terrier,Spanish Water Dog,Spinone Italiano,Staffordshire Bull Terrier,Standard Schnauzer,Swedish Vallhund,Thai Ridgeback,Tibetan Mastiff,Tibetan Spaniel,Tibetan Terrier,Toy Fox Terrier,Treeing Walker Coonhound,Vizsla,Weimaraner,Welsh Springer Spaniel,West Highland White Terrier,Whippet,White Shepherd,Wire Fox Terrier,Wirehaired Pointing Griffon,Wirehaired Vizsla,Xoloitzcuintli"
        ];
        document.getElementById("after_submit-0").style.visibility = "visible";
        document.getElementById("after_submit-1").style.visibility = "visible";
        document.getElementById("after_submit-2").style.visibility = "visible";

        document.getElementById("dog-breed").innerHTML = "Dog Breeds" + breed[totalRange];
        document.getElementById("after_submit-3").style.visibility = "visible";


    }
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