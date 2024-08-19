Validate({

    // get class and id from tag html
    form: '#form-created-acc',
    formOutline: '.form-outline',
    formControl: '.form-control',
    formShow: '.form-show',
    formModel: '.form-model',

    // value form input
    finalID: '#final-id',

    // error say
    formError: '.form-error',
    errorMessage: '.error-message',


    // created properties

    // modal
    buttonModal: '#button-modal',
    modalNoBtn: '#modalNoBtn',


    rulesForm:[
        Validate.isPassword('#password'),
        Validate.isConfirmPassword("#confirm-password"),
        Validate.isDate('#dob-id')
    ],

    onSubmit: function (data) {
        console.log(data)
    }


})

