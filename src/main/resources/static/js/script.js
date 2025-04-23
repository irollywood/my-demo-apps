document.addEventListener("DOMContentLoaded", function () {
    let chatBox = document.getElementById("chatBox");
    let choicesContainer = document.getElementById("choices");

    function loadDialogue(dialogueId) {
        fetch(`/api/dialogues/${dialogueId}`)
            .then(response => response.json())
            .then(dialogue => {
                let newMessage = document.createElement("div");
                newMessage.classList.add("chat-message", "npc", "animate");
                newMessage.innerHTML = `
                    <img src="${dialogue.avatar}" class="avatar">
                    <p>${dialogue.text}</p>
                `;
                chatBox.appendChild(newMessage);

                choicesContainer.innerHTML = '';
                dialogue.choices.forEach(choice => {
                    let button = document.createElement("button");
                    button.textContent = choice.text;
                    button.classList.add("choice-btn", "animate");
                    button.onclick = () => loadDialogue(choice.nextDialogueId);
                    choicesContainer.appendChild(button);
                });

                chatBox.scrollTop = chatBox.scrollHeight;
            })
            .catch(error => console.error("Error loading dialogue:", error));
    }

    loadDialogue(1); // Load initial dialogue
});
