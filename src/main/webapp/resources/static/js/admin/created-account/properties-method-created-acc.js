Validate.isName = function (selector) {
    return {
        selector: selector,
        test: [
            Action.requiredDefault = (value) => {
            console.log(value)
                return value ? undefined: 'Trường này cần chứa giá trị đầu vào'
            }
        ]
    }
}

Validate.isUserName = function (selector) {
    return {
        selector: selector,
        test: [
            Action.requiredDefault = (value) => {
                return value ? undefined: 'Trường này cần chứa giá trị đầu vào'
            }
        ]
    }
}

Validate.isPassword = function (selector) {
    return {
        selector: selector,
        test: [ // get method appropriate for issue value form input
            Action.requiredDefault = (value) => {
                return value ? undefined : 'Trường này cần chứa giá trị đầu vào'
            },
            Action.requiredLessThanDesire = (value) => {
                return value.length > 6 ? undefined : 'Mật khẩu cần ít nhất 6 kí tự'
            },
            Action.requiredRegexSpecialCharacter = (value) => {
                let regexSpecifiedCharacter = /[\-\_\*\/\\\#\&\@\^\~\+\-\*\/\%\=]/g
                return regexSpecifiedCharacter.test(value) ? undefined : 'Mật khẩu cần chứa ít nhất 1 kí tự đặc biệt:  - _ * / \ # & @ ^ ~ +  % = '
            }
        ]
    }
}

Validate.isConfirmPassword = function (selector) {
    return {
        selector: selector,
        test: [
            Action.requiredDefault = (value) => {
                return value ? undefined : 'Trường này cần chứa giá trị đầu vào'
            },
            Action.requiredEqualValuePassword = (value) => {
                return document.querySelector('#password').value === value ? undefined : 'Mật khẩu không trùng khớp'
            }
        ]
    }
}

Validate.isSelectRole = function (selector) {
    return {
        selector: selector,
        test: [
            Action.requiredDefault = (value) => {
                return value.trim() !== '' ? undefined: 'Vai trò ?'
            }
        ]
    }
}
Validate.isSelectGender = function (selector) {
    return {
        selector: selector,
        test: [
            Action.requiredDefault = (value) => {
                return value.trim() !== '' ? undefined: 'Giới tính ?'
            }
        ]
    }
}
