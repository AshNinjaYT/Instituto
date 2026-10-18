document.addEventListener('DOMContentLoaded', () => {
    initTabs();
    initFlashcards();
    initQuiz();
    initCopyToClipboard();
    loadProgress();
});

// 1. Tab Switching System (Theory, Cheat Sheet, Flashcards, Quiz)
function initTabs() {
    const tabs = document.querySelectorAll('.module-tab');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const targetPanelId = tab.getAttribute('data-target');
            const parentContainer = tab.closest('.tabs-container');
            
            // Deactivate all sibling tabs
            parentContainer.querySelectorAll('.module-tab').forEach(t => t.classList.remove('active'));
            // Deactivate all panels
            parentContainer.querySelectorAll('.tab-content-panel').forEach(p => p.classList.remove('active'));
            
            // Activate current tab & panel
            tab.classList.add('active');
            parentContainer.querySelector(`#${targetPanelId}`).classList.add('active');
        });
    });
}

// 2. Flashcard Flitting System
function initFlashcards() {
    const cards = document.querySelectorAll('.flashcard');
    cards.forEach(card => {
        card.addEventListener('click', () => {
            card.classList.toggle('flipped');
        });
    });
}

// 3. Quiz system
function initQuiz() {
    // Single Option Selection
    const optionItems = document.querySelectorAll('.option-item');
    optionItems.forEach(item => {
        item.addEventListener('click', () => {
            const questionBlock = item.closest('.question-block');
            
            // Check if quiz has already been submitted and checked
            if (questionBlock.classList.contains('checked')) return;

            // Deselect other options in same question
            questionBlock.querySelectorAll('.option-item').forEach(opt => {
                opt.classList.remove('selected');
            });

            // Select clicked option
            item.classList.add('selected');
        });
    });

    // Submit Quiz action
    const submitBtn = document.querySelectorAll('.btn-submit-quiz');
    submitBtn.forEach(btn => {
        btn.addEventListener('click', () => {
            const quizContainer = btn.closest('.quiz-container');
            const questions = quizContainer.querySelectorAll('.question-block');
            const moduleId = btn.getAttribute('data-module');
            
            let correctCount = 0;
            let answeredCount = 0;

            questions.forEach(qBlock => {
                const selected = qBlock.querySelector('.option-item.selected');
                const correctAnswer = qBlock.getAttribute('data-correct');
                const feedbackText = qBlock.querySelector('.feedback-text');
                
                if (selected) {
                    answeredCount++;
                    const selectedValue = selected.getAttribute('data-value');
                    qBlock.classList.add('checked'); // Disable changes

                    // Check if answer is correct
                    if (selectedValue === correctAnswer) {
                        selected.classList.add('correct');
                        correctCount++;
                        if (feedbackText) {
                            feedbackText.innerHTML = "✓ ¡Correcto! " + (feedbackText.getAttribute('data-explanation') || '');
                            feedbackText.className = "feedback-text show feedback-correct";
                        }
                    } else {
                        selected.classList.add('incorrect');
                        // Highlight the correct one
                        qBlock.querySelectorAll('.option-item').forEach(opt => {
                            if (opt.getAttribute('data-value') === correctAnswer) {
                                opt.classList.add('correct');
                            }
                        });
                        if (feedbackText) {
                            feedbackText.innerHTML = "✗ Incorrecto. La respuesta correcta es la resaltada. " + (feedbackText.getAttribute('data-explanation') || '');
                            feedbackText.className = "feedback-text show feedback-incorrect";
                        }
                    }
                }
            });

            if (answeredCount < questions.length) {
                alert("Por favor, responde a todas las preguntas antes de enviar.");
                return;
            }

            // Save progress
            const percentage = Math.round((correctCount / questions.length) * 100);
            saveModuleProgress(moduleId, percentage);
        });
    });
}

// 4. Clipboard copier helper
function initCopyToClipboard() {
    const copyButtons = document.querySelectorAll('.btn-copy');
    copyButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const container = btn.closest('.code-container');
            const code = container.querySelector('code').textContent;
            
            navigator.clipboard.writeText(code).then(() => {
                const originalText = btn.innerHTML;
                btn.innerHTML = '<span>✓ Copiado</span>';
                btn.style.color = '#10b981';
                setTimeout(() => {
                    btn.innerHTML = originalText;
                    btn.style.color = '';
                }, 2000);
            });
        });
    });
}

// 5. Progress Tracking using LocalStorage
function saveModuleProgress(moduleId, score) {
    let progress = JSON.parse(localStorage.getItem('acces_dades_progress')) || {};
    // Only save if score is higher than previously saved score
    if (!progress[moduleId] || progress[moduleId] < score) {
        progress[moduleId] = score;
        localStorage.setItem('acces_dades_progress', JSON.stringify(progress));
    }
    loadProgress();
}

function loadProgress() {
    const progress = JSON.parse(localStorage.getItem('acces_dades_progress')) || {};
    
    // Update individual module cards progress fills (if we are on dashboard/index)
    let totalModules = 4;
    let totalPercentageSum = 0;

    for (let i = 1; i <= totalModules; i++) {
        const moduleId = `m${i}`;
        const score = progress[moduleId] || 0;
        totalPercentageSum += score;

        // Update DOM element if exists
        const fillElement = document.getElementById(`progress-fill-m${i}`);
        const textElement = document.getElementById(`progress-text-m${i}`);
        if (fillElement) fillElement.style.width = `${score}%`;
        if (textElement) textElement.textContent = `${score}%`;
    }

    const averageProgress = Math.round(totalPercentageSum / totalModules);
    
    // Update main progress bar
    const mainFill = document.getElementById('main-progress-bar-fill');
    const mainText = document.getElementById('main-progress-text');
    if (mainFill) mainFill.style.width = `${averageProgress}%`;
    if (mainText) mainText.textContent = `${averageProgress}% completado`;
}

// Developer helper to reset progress
function resetAllProgress() {
    localStorage.removeItem('acces_dades_progress');
    loadProgress();
    location.reload();
}
