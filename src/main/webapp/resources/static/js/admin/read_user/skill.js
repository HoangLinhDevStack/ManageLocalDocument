document.addEventListener('DOMContentLoaded', function() {
    // Handle skills
    document.getElementById('addSkillBtn').addEventListener('click', addSkill);
    document.getElementById('restoreSkillBtn').addEventListener('click', restoreSkills);

    // Add validation to form submission
    document.getElementById('userForm').addEventListener('submit', function(event) {
        const skillInputs = document.querySelectorAll('input[name="skills"]');
        let isValid = true;
        
        skillInputs.forEach(input => {
            if (!input.value.trim()) {
                input.classList.add('is-invalid');
                isValid = false;
            } else {
                input.classList.remove('is-invalid');
            }
        });
        
        if (!isValid) {
            event.preventDefault();
            document.getElementById('submit').disabled = true;
            return false;
        }
        
        document.getElementById('submit').disabled = false;
    });

    document.getElementById('skillsContainer').addEventListener('click', function(event) {
        if (event.target.closest('.remove-skill')) {
            removeSkill(event.target.closest('.remove-skill'));
        }
    });
});

function addSkill() {
    const container = document.getElementById('skillsContainer');
    const newIndex = container.children.length;

    const inputGroup = document.createElement('div');
    inputGroup.className = 'input-group mb-2';

    inputGroup.innerHTML = ` 
        <div class="skill-group">
            <input type="text" class="form-control" name="skills" placeholder="Skill" required>
            <div class="invalid-feedback">Trường này cần giá trị đầu vào</div>
        </div>
        
        <button type="button" class="btn btn-danger remove-skill">
            <i class="bi bi-x"></i>
        </button>
    `;

    container.appendChild(inputGroup);
}

function removeSkill(button) {
    const container = document.getElementById('skillsContainer');
    const inputGroup = button.closest('.input-group');

    // Remove the input group
    inputGroup.remove();

    // No need to reindex with string array approach
}

function restoreSkills() {
    const container = document.getElementById('skillsContainer');
    const fragment = document.createDocumentFragment();

    container.innerHTML = '';  // Clear the current container

    skills.forEach(skill => {
        const inputGroup = document.createElement('div');
        inputGroup.className = 'input-group mb-2';

        const skillGroup = document.createElement('div');
        skillGroup.className = 'skill-group';

        const input = document.createElement('input');
        input.type = 'text';
        input.className = 'form-control';
        input.name = 'skills';
        input.value = skill.descriptions.valueOf();
        input.placeholder = 'Skill';

        const button = document.createElement('button');
        button.type = 'button';
        button.className = 'btn btn-danger remove-skill';
        button.innerHTML = '<i class="bi bi-x"></i>';

        skillGroup.appendChild(input);
        inputGroup.appendChild(skillGroup);
        inputGroup.appendChild(button);
        fragment.appendChild(inputGroup);
    });

    container.appendChild(fragment);
}