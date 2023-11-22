// This file is automatically included by javascript_include_tag :defaults

//Validates the new testimonial form.
//LimitNum is the max number of characters for the testimonial.
//Creator_user_level indicates if the user is an admin, a member or an anonymous user.'
 
function validateNewTestimonialForm(limitNum, creator_user_level, fw_id) {
     // Validate name entered
    if ( creator_user_level == 0 || creator_user_level == 4 || !fw_id || fw_id == 0 ) {
        var name = jQuery('#new_testimonial_name').val();
//        name=name.replace(' ', '') ;
            name= jQuery.trim(name);
        if (name.length == 0) {
            alert("Please, enter your name.");
            return false;
        }
    }

    if (creator_user_level == 0) {
        var mail_field = jQuery('#new_testimonial_mail').val();
        if (!mail_field || mail_field.length == 0) {
            alert("Please, enter your e-mail.");
            return false;
        }

        var validMail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!validMail.test(mail_field)) { //if match failed
            alert("Please, enter a valid e-mail");
            return false;
        }
    }

    var message_field = jQuery('#new_testimonial_message').val();

    if (message_field.length > limitNum) {
        alert("Sorry, your testimonial cannot have more than " + limitNum + " characters.");
        return false;
    }
    if (message_field.length == 0) {
        alert("Please, enter a testimonial.");
        return false;
    }

    return true;
}

function validateEditTestimonialForm(limitNum, creator_user_level, fw_id, id) {

    // Validate name entered

    if ( creator_user_level == 0 || creator_user_level == 4 || !fw_id || fw_id == 0 ) {
        var name = jQuery('#edit_testimonial_name_'+id).val();
//        name=name.replace(' ', '') ;
            name= jQuery.trim(name);
        if (name.length == 0) {
            alert("Please, enter your name.");
            return false;
        }
    }



    if (creator_user_level == 0) {
        var mail_field = jQuery('#edit_testimonial_mail_'+id).val();
        if (!mail_field || mail_field.length == 0) {
            alert("Please, enter your e-mail.");
            return false;
        }

        var validMail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!validMail.test(mail_field)) { //if match failed
            alert("Please, enter a valid e-mail");
            return false;
        }
    }

    var message_field = jQuery('#edit_testimonial_message_'+id).val();

    if (message_field.length > limitNum) {
        alert("Sorry, your testimonial cannot have more than " + limitNum + " characters.");
        return false;
    }
    if (message_field.length == 0) {
        alert("Please, enter a testimonial.");
        return false;
    }
    
    return true;
}

//Limits the text lenght of a textarea field.
function limitText(limitField, limitNum) {
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
    }
    return true;
}

   
jQuery(document).ready(function() {
    
});

