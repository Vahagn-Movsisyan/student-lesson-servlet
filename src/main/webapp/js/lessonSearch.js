document.addEventListener('DOMContentLoaded', function () {
    const lessonSelect = document.querySelector('select[name="studentId"]');
    const lessonSearchInput = document.getElementById('lessonSearchInput');

    lessonSearchInput.addEventListener('input', function () {
        const searchQuery = lessonSearchInput.value.trim().toLowerCase();

        fetch('/searchLesson?searchQuery=' + searchQuery)
            .then(response => response.json())
            .then(data => {
                Array.from(lessonSelect.options).forEach(option => {
                    const lessonId = option.value;
                    const isMatch = searchQuery === '' || data.some(lesson => lesson.id === parseInt(lessonId));
                    option.style.display = isMatch ? 'block' : 'none';
                });
            })
            .catch(error => console.error('Error:', error));
    });
});
