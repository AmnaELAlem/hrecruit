// Open the Create Interview Side Panel
function openCreatePanel() {
    const sidePanel = document.getElementById('createInterviewPanel');
    sidePanel.style.width = '300px';
    sidePanel.classList.add('open');
}

// Close the Create Interview Side Panel
function closeCreatePanel() {
    const sidePanel = document.getElementById('createInterviewPanel');
    sidePanel.style.width = '0';
    sidePanel.classList.remove('open');
}

// Open the Score Side Panel
function openScorePanel(title, overallScore, technicalScore, communicationScore) {
    const sidePanel = document.getElementById('sidePanel');
    document.getElementById('panel-title').textContent = title;
    document.getElementById('panel-overall-score').textContent = overallScore || 'N/A';
    document.getElementById('panel-technical-score').textContent = technicalScore || 'N/A';
    document.getElementById('panel-communication-score').textContent = communicationScore || 'N/A';
    sidePanel.style.width = '300px';
    sidePanel.classList.add('open');
}

// Close the Score Side Panel
function closeScorePanel() {
    const sidePanel = document.getElementById('sidePanel');
    sidePanel.style.width = '0';
    sidePanel.classList.remove('open');
}

// Attach event listeners to dynamically created elements for score panel
document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.score-box').forEach((scoreBox) => {
        scoreBox.addEventListener('click', () => {
            const title = scoreBox.getAttribute('data-title');
            const overallScore = scoreBox.getAttribute('data-overall-score');
            const technicalScore = scoreBox.getAttribute('data-technical-score');
            const communicationScore = scoreBox.getAttribute('data-communication-score');

            openScorePanel(title, overallScore, technicalScore, communicationScore);
        });
    });
});
