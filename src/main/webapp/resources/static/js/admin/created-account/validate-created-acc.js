function Validate(options) {

    let objectValidate = {
        get getOnKeyUpDown() {
            return this.eventOnKeyUpDown
        },

        get getOnInput() {
            return this.eventOnInput
        },
    }


    let getGroupRules = {}
    let getForm = document.querySelector(options.form)
    // let getFormOutline = document.querySelector(options.formOutline)
    let classInvalid = 'invalid'

    // console.log(getFormOutline)

    function nonWhiteSpaceInput(getInputForm) { // remove whitespace in real-time
        let regexWhiteSpace = /\s+/g
        let getValueInputForm = getInputForm.value
        getInputForm.value = getValueInputForm.replace(regexWhiteSpace, '')
    }

    function removeVietnameseTones(getEvent) {
        const accentsMap = [
            {base: 'a', chars: 'áàảãạâấầẩẫậăắằẳẵặ'},
            {base: 'A', chars: 'ÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶ'},
            {base: 'e', chars: 'éèẻẽẹêếềểễệ'},
            {base: 'E', chars: 'ÉÈẺẼẸÊẾỀỂỄỆ'},
            {base: 'i', chars: 'íìỉĩị'},
            {base: 'I', chars: 'ÍÌỈĨỊ'},
            {base: 'o', chars: 'óòỏõọôốồổỗộơớờởỡợ'},
            {base: 'O', chars: 'ÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢ'},
            {base: 'u', chars: 'úùủũụưứừửữự'},
            {base: 'U', chars: 'ÚÙỦŨỤƯỨỪỬỮỰ'},
            {base: 'y', chars: 'ýỳỷỹỵ'},
            {base: 'Y', chars: 'ÝỲỶỸỴ'},
            {base: 'd', chars: 'đ'},
            {base: 'D', chars: 'Đ'}
        ]

        accentsMap.forEach(element => {
            element.chars.split('').forEach(char => {
                const regex = new RegExp(char, 'g');
                getEvent.target.value = getEvent.target.value.replace(regex, element.base)
            })
        })

    }

    function showPassword(showForm, formElement) { // show and hide password
        let showIcon = showForm.querySelector("i")
        let iconClassShowByTag_i = 'bi-eye'
        let iconClassHideByTag_i = 'bi-eye-slash'
        let containClass = showIcon.classList.contains(iconClassHideByTag_i)
        let typePassword = 'password'
        let typeText = 'text'

        // this code show your code password
        if (containClass && formElement.type === typePassword) {
            showIcon.classList.replace(iconClassHideByTag_i, iconClassShowByTag_i)
            formElement.type = typeText
        } else {
            showIcon.classList.remove(iconClassShowByTag_i)
            showIcon.classList.add(iconClassHideByTag_i)
            formElement.type = typePassword
        }
    }

    function validateFormInput(formElement, rules) { // method to get error when user blur outside input

        let errorElement = formElement.parentElement.querySelector(options.formError)
        let errorMessage
        let getRules = getGroupRules[rules.selector]

        for (let i = 0; i < getRules.length; i++) {
            // processing value form input form
            errorMessage = getRules[i](formElement.value)
            if (errorMessage) break
        }

        if (errorMessage !== undefined) {
            errorElement.classList.add(classInvalid)
            errorElement.querySelector(options.errorMessage).innerText = errorMessage
        } else {
            errorElement.classList.remove(classInvalid)
            errorElement.querySelector(options.errorMessage).innerText = ''
        }

        return Boolean(errorMessage)
    }


    function validateCapsLockOpen(eventOnKey, getParentElement) { // check user open capslock
        let getKeyCapslock = 'CapsLock'
        let warningKeyCapslockIsActive = 'Cảnh báo đang bật Capslock'
        let hasCapslockActive = false
        let getKeyCapslockEvent = eventOnKey.getModifierState(getKeyCapslock)

        if (getKeyCapslockEvent && !hasCapslockActive) {
            getParentElement.insertAdjacentHTML("beforeend",
                `<p class="capslock form-error invalid mt-2">
                        <i class="bi bi-capslock-fill"></i>
                        <span class="error-message">${warningKeyCapslockIsActive}</span> 
                     </p>`);
        } else {
            // this want to remove that tag I insert adjacent in html for event key up and key down
            if (hasCapslockActive === false) {
                let getClassCapslock = getParentElement.querySelectorAll('.capslock')
                if (getClassCapslock) {
                    getClassCapslock.forEach(element => element.remove())
                }
            }
        }

    }

    function removeCapslockNotifyWhileInput(getClassCapslock) {
        for (let i = 0; i < getClassCapslock.length; i++) {
            getClassCapslock[i].remove() // remove element render second
        }
    }

    function removeCapslockDuplicate(getCapslockElementKeyUp) {
        if (getCapslockElementKeyUp.length > 1) {
            for (let i = 1; i <= getCapslockElementKeyUp.length; i++) {
                getCapslockElementKeyUp[i].remove()
                break
            }
        }
    }

    function unleashingNotifyCreatingAccountUser() {

        let getArrayValidateForm = []
        options.rulesForm.forEach((rule, index) => {
            let formElement = document.querySelector(rule.selector)
            getArrayValidateForm.push(validateFormInput(formElement, rule)) // push value true or false check validate is check
        })

        let arrayValidateFormTemp = [] // get always false value in every index in value
        for(let i = 0; i < getArrayValidateForm.length; i++) {
            if(getArrayValidateForm[i] === true) continue
            arrayValidateFormTemp.push(getArrayValidateForm[i])
        }

        if (arrayValidateFormTemp.includes(false) && arrayValidateFormTemp.length === options.rulesForm.length) {
            let getModelYesNo = new bootstrap.Modal(document.getElementById('exampleModal'))
            getModelYesNo.show()
        }

    }

    if (getForm) {
        options.rulesForm.forEach(function (rules) { // get rule need to validate

            if (Array.isArray(getGroupRules)) {
                getGroupRules[rules.selector].push(rules.test)
            } else {
                getGroupRules[rules.selector] = rules.test
            } // convert to array to push key and value each of input id

            // this is to process each input validate
            let formElement = document.querySelector(rules.selector)
            let showForm = formElement.parentElement.querySelector(options.formShow)

            if (formElement) { // check have formElement
                let getParentElement = formElement.parentElement
                let getInputTypeDate = getParentElement.querySelector(`input[type=date]`) // GET input type date

                if (showForm) { // check have .form-show class
                    showForm.onclick = function () { // check event user click
                        showPassword(showForm, formElement)
                    }
                }
                formElement.oninput = (event) => { // event user write value real-time
                    Object.defineProperty(objectValidate, 'eventOnInput', {
                        value: event,
                        writable: true
                    })

                    let getClassCapslock = getParentElement.querySelectorAll('.capslock') // capture class capslock fist

                    if (!getInputTypeDate) {
                        if (objectValidate.getOnKeyUpDown.getModifierState('CapsLock')) {
                            validateCapsLockOpen(objectValidate.getOnKeyUpDown, getParentElement) // render error capslock
                            removeCapslockNotifyWhileInput(getClassCapslock)
                        } else {
                            removeCapslockNotifyWhileInput(getClassCapslock)
                        }
                    }
                    removeVietnameseTones(event)
                    nonWhiteSpaceInput(formElement) // remove space in real-time
                }

                formElement.onblur = () => { // event user click outside input form
                    validateFormInput(formElement, rules)
                }

                formElement.addEventListener('keyup', eventPressKey)
                formElement.addEventListener('keydown', eventPressKey)

                function eventPressKey(event) {
                    Object.defineProperty(objectValidate, 'eventOnKeyUpDown', {
                        value: event,
                        writable: true
                    }) // set event key press
                }

                formElement.onkeyup = (event) => {
                    // not capslock in input type date
                    if (event.key === 'CapsLock' && !getInputTypeDate) { // if key is capslock
                        validateCapsLockOpen(objectValidate.getOnKeyUpDown, getParentElement)
                        let getCapslockElementKeyUp = event.target.parentElement.querySelectorAll('.capslock')
                        removeCapslockDuplicate(getCapslockElementKeyUp)
                    }
                }

            }
        })

        //submit form for database

        let buttonModal = document.querySelector(options.buttonModal)
        buttonModal.onclick = () => {
            unleashingNotifyCreatingAccountUser()
        }

        getForm.onsubmit = (event) => {

        }

        // let getInputIDUserByEventInputTypeDate = document.getElementById('dob-id')
        // let getFormFinalId = getForm.querySelector(options.finalID)
        // getInputIDUserByEventInputTypeDate.oninput = function () {
        //     let getArrDateAccount =  this.value.split('-').reverse()
        //     getArrDateAccount.unshift(34)
        //     getArrDateAccount.push('0001' + 1)
        //     getFormFinalId.value = Number(getArrDateAccount.join(''))
        // }

    }

}




